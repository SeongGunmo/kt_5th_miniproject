import { useState } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import { signup } from '../services/authService';

export default function Signup() {
    const navigate = useNavigate();

    const [formData, setFormData] = useState({
        name: '',
        loginId: '',   // ★ 변경됨 (username → loginId)
        password: '',
        confirmPassword: '',
        email: ''
    });

    const handleChange = (e) => {
        setFormData({
            ...formData,
            [e.target.name]: e.target.value
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        if (formData.password !== formData.confirmPassword) {
            alert('비밀번호가 일치하지 않습니다.');
            return;
        }

        // 백엔드 UserSignupRequest DTO 형식 그대로 보내기
        const dto = {
            name: formData.name,
            loginId: formData.loginId,   // ★ 백엔드 필드명과 동일하게
            password: formData.password,
            email: formData.email
        };

        try {
            const result = await signup(dto);
            console.log("회원가입 성공:", result);

            alert('회원가입이 완료되었습니다!');
            navigate('/login');
        } catch (err) {
            console.error(err);
            alert(err.response?.data?.message || "회원가입 중 오류가 발생했습니다.");
        }
    };

    return (
        <div className="login-page">
            <div className="login-card" style={{ maxWidth: '450px' }}>
                <h1 className="login-title">회원가입</h1>

                <form className="login-form" onSubmit={handleSubmit}>
                    <label className="login-label">
                        닉네임
                        <input
                            type="text"
                            name="name"
                            className="login-input"
                            placeholder="홍길동"
                            value={formData.name}
                            onChange={handleChange}
                            required
                        />
                    </label>

                    <label className="login-label">
                        아이디
                        <input
                            type="text"
                            name="loginId"   // ★ 여기서도 loginId로 변경
                            className="login-input"
                            placeholder="user123"
                            value={formData.loginId}
                            onChange={handleChange}
                            required
                        />
                    </label>

                    <label className="login-label">
                        이메일
                        <input
                            type="email"
                            name="email"
                            className="login-input"
                            placeholder="you@example.com"
                            value={formData.email}
                            onChange={handleChange}
                            required
                        />
                    </label>

                    <label className="login-label">
                        비밀번호
                        <input
                            type="password"
                            name="password"
                            className="login-input"
                            placeholder="••••••••"
                            value={formData.password}
                            onChange={handleChange}
                            required
                        />
                    </label>

                    <label className="login-label">
                        비밀번호 확인
                        <input
                            type="password"
                            name="confirmPassword"
                            className="login-input"
                            placeholder="••••••••"
                            value={formData.confirmPassword}
                            onChange={handleChange}
                            required
                        />
                    </label>

                    <button type="submit" className="primary-btn full">
                        회원가입
                    </button>

                    <p style={{ textAlign: 'center', marginTop: '16px', fontSize: '14px', color: '#666' }}>
                        이미 회원이신가요?{" "}
                        <Link to="/login" style={{ color: '#4285f4', textDecoration: 'none', fontWeight: '500' }}>
                            로그인
                        </Link>
                    </p>
                </form>
            </div>
        </div>
    );
}
