package com.kt.library.service.impl;

import com.kt.library.domain.Book;
import com.kt.library.domain.Favorite;
import com.kt.library.domain.User;
import com.kt.library.dto.response.BookResponse;
import com.kt.library.repository.BookRepository;
import com.kt.library.repository.FavoriteRepository;
import com.kt.library.repository.UserRepository;
import com.kt.library.service.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FavoriteServiceImpl implements FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    @Override
    public void toggleFavorite(Long userId, Long bookId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        Favorite favorite = favoriteRepository
                .findByUserIdAndBookId(userId, bookId)
                .orElse(null);

        if (favorite != null) {
            favoriteRepository.delete(favorite);
        } else {
            favoriteRepository.save(
                    Favorite.builder()
                            .user(user)
                            .book(book)
                            .build()
            );
        }
    }

    @Override
    public Long getFavoriteCount(Long bookId) {
        return favoriteRepository.countByBookId(bookId);
    }

    @Override
    public List<BookResponse> getMyFavorites(Long userId) {
        List<Favorite> favorites = favoriteRepository.findByUserId(userId);

        return favorites.stream()
                .map(fav -> BookResponse.fromEntity(fav.getBook()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean isFavorited(Long userId, Long bookId) {
        return favoriteRepository.findByUserIdAndBookId(userId, bookId).isPresent();
    }
}
