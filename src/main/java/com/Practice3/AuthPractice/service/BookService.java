package com.Practice3.AuthPractice.service;

import com.Practice3.AuthPractice.payload.BookDTO;
import com.Practice3.AuthPractice.payload.BookResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface BookService {
    BookDTO addBook(Long categoryId, BookDTO book);

    BookResponse getAllBooks(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

    BookResponse searchByCategory(Long categoryId, Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

    BookResponse searchBookByKeyword(String keyword, Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

    BookDTO removeBook(Long bookId);

    BookDTO updateBook(Long categoryId, BookDTO bookDTO);

    BookDTO updateBookImage(Long bookId, MultipartFile image) throws IOException;
}
