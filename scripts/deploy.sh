#!/bin/bash

set -euo pipefail

APP_HOME="/home/ec2-user/kt_5th_miniproject"
BACKEND_DIR="$APP_HOME/backend"
FRONTEND_DIR="$APP_HOME/frontend"
WEB_ROOT="/var/www/tema9-angular-project"

log() {
  echo "[$(date '+%Y-%m-%d %H:%M:%S')] $*"
}

log "Stopping existing backend if running"
if pgrep -f "library-0.0.1-SNAPSHOT.jar" >/dev/null 2>&1; then
  pkill -15 -f "library-0.0.1-SNAPSHOT.jar" || true
  sleep 5
  pkill -9 -f "library-0.0.1-SNAPSHOT.jar" || true
else
  log "No existing backend process found"
fi

log "Building backend"
cd "$BACKEND_DIR"
chmod +x gradlew
./gradlew build -x test

JAR_PATH=$(ls build/libs/*.jar | grep -v "plain" | head -n 1)
log "Starting backend: $JAR_PATH"
nohup java -jar "$JAR_PATH" > "$BACKEND_DIR/app.log" 2>&1 &

log "Building frontend"
cd "$FRONTEND_DIR"
npm ci
npm run build

log "Deploying frontend build to Nginx root"
mkdir -p "$WEB_ROOT"
rm -rf "$WEB_ROOT"/*
cp -r dist/* "$WEB_ROOT"/

log "Deployment finished"
