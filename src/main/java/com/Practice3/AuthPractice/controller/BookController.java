package com.Practice3.AuthPractice.controller;

import com.Practice3.AuthPractice.config.AppConstants;
import com.Practice3.AuthPractice.payload.BookDTO;
import com.Practice3.AuthPractice.payload.BookResponse;
import com.Practice3.AuthPractice.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping("/admin/categories/{categoryId}/book")
    public ResponseEntity<BookDTO> addBook(@RequestBody BookDTO bookDTO,
                                           @PathVariable Long categoryId){
        BookDTO savedBookDTO = bookService.addBook(categoryId, bookDTO);

        return new ResponseEntity<>(bookDTO, HttpStatus.CREATED);
    }

    @GetMapping("/public/books")
    public ResponseEntity<BookResponse> getAllBooks(
            @RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(name = "sortBy", defaultValue = AppConstants.SORT_BOOKS_BY , required = false) String sortBy,
            @RequestParam(name = "sortOrder", defaultValue = AppConstants.SORT_DIR, required = false) String sortOrder
    ){
        BookResponse bookResponse = bookService.getAllBooks(pageNumber, pageSize, sortBy, sortOrder);
        return new ResponseEntity<>(bookResponse, HttpStatus.OK);
    }

    @GetMapping("/public/categories/{categoryId}/books")
    public ResponseEntity<BookResponse> getBooksByCategory(@PathVariable Long categoryId,
                                                           @RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
                                                           @RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
                                                           @RequestParam(name = "sortBy", defaultValue = AppConstants.SORT_BOOKS_BY , required = false) String sortBy,
                                                           @RequestParam(name = "sortOrder", defaultValue = AppConstants.SORT_DIR, required = false) String sortOrder){
        BookResponse bookResponse = bookService.searchByCategory(categoryId, pageNumber, pageSize, sortBy, sortOrder);
        return new ResponseEntity<>(bookResponse, HttpStatus.FOUND);
    }

    @GetMapping("/public/books/keyword/{keyword}")
    public ResponseEntity<BookResponse> getBooksByKeyword(@PathVariable String keyword,
                                                          @RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
                                                          @RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
                                                          @RequestParam(name = "sortBy", defaultValue = AppConstants.SORT_BOOKS_BY , required = false) String sortBy,
                                                          @RequestParam(name = "sortOrder", defaultValue = AppConstants.SORT_DIR, required = false) String sortOrder){
        BookResponse bookResponse = bookService.searchBookByKeyword(keyword, pageNumber, pageSize, sortBy, sortOrder);
        return new ResponseEntity<>(bookResponse, HttpStatus.FOUND);
    }

    @PutMapping("admin/books/{bookId}")
    public ResponseEntity<BookDTO> updateBook(@RequestBody BookDTO bookDTO,
                                           @PathVariable Long bookId){
        BookDTO updatedBookDTO = bookService.updateBook(bookId, bookDTO);

        return new ResponseEntity<>(updatedBookDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/admin/books/{bookId}")
    public ResponseEntity<BookDTO> removeBook(@PathVariable Long bookId){
        BookDTO bookDTO = bookService.removeBook(bookId);
        return new ResponseEntity<>(bookDTO, HttpStatus.OK);
    }

    @PutMapping("/books/{bookId}/image")
    public ResponseEntity<BookDTO> updateBookImage(@PathVariable Long bookId,
                                                   @RequestParam("image")MultipartFile image) throws IOException {
        BookDTO updatedBook =bookService.updateBookImage(bookId, image);
        return new ResponseEntity<>(updatedBook, HttpStatus.OK);
    }
}
