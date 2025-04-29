package com.Practice3.AuthPractice.impl;

import com.Practice3.AuthPractice.exceptions.ResourceNotFoundException;
import com.Practice3.AuthPractice.model.Book;
import com.Practice3.AuthPractice.model.Category;
import com.Practice3.AuthPractice.payload.BookDTO;
import com.Practice3.AuthPractice.payload.BookResponse;
import com.Practice3.AuthPractice.repo.BookRepository;
import com.Practice3.AuthPractice.repo.CategoryRepository;
import com.Practice3.AuthPractice.service.BookService;
import com.Practice3.AuthPractice.service.FileService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private FileService fileService;

    @Value("${project.image}")
    private String path;

    @Override
    public BookDTO addBook(Long categoryId, BookDTO bookDTO) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("Category", "categoryId", categoryId));
        Book book = modelMapper.map(bookDTO, Book.class);
        book.setImage("default.png");
        book.setCategory(category);
        Book savedBook = bookRepository.save(book);
        return modelMapper.map(savedBook, BookDTO.class);
    }

    @Override
    public BookResponse getAllBooks(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
        Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageDetails = PageRequest.of(pageNumber, pageSize, sortByAndOrder);
        Page<Book> pageBooks = bookRepository.findAll(pageDetails);

        List<Book> books= pageBooks.getContent();
        List<BookDTO> bookDTOS = books.stream()
                .map(book -> modelMapper.map(book, BookDTO.class)).toList();
        BookResponse bookResponse = new BookResponse();
        bookResponse.setContent(bookDTOS);
        bookResponse.setPageNumber(pageBooks.getNumber());
        bookResponse.setPageSize(pageBooks.getSize());
        bookResponse.setTotalElements(pageBooks.getTotalElements());
        bookResponse.setTotalPages(pageBooks.getTotalPages());
        bookResponse.setLastPage(pageBooks.isLast());
        return bookResponse;
    }

    @Override
    public BookResponse searchByCategory(Long categoryId, Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("Category", "categoryId", categoryId));

        Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageDetails = PageRequest.of(pageNumber, pageSize, sortByAndOrder);
        Page<Book> pageBooks = bookRepository.findByCategoryOrderByTitleAsc(category, pageDetails);

        List<Book> books= pageBooks.getContent();

        List<BookDTO> bookDTOS = books.stream()
                .map(book -> modelMapper.map(book, BookDTO.class)).toList();
        BookResponse bookResponse = new BookResponse();
        bookResponse.setContent(bookDTOS);
        bookResponse.setPageNumber(pageBooks.getNumber());
        bookResponse.setPageSize(pageBooks.getSize());
        bookResponse.setTotalElements(pageBooks.getTotalElements());
        bookResponse.setTotalPages(pageBooks.getTotalPages());
        bookResponse.setLastPage(pageBooks.isLast());
        return bookResponse;
    }

    @Override
    public BookResponse searchBookByKeyword(String keyword, Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {

        Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageDetails = PageRequest.of(pageNumber, pageSize, sortByAndOrder);
        Page<Book> pageBooks = bookRepository.findByTitleLikeIgnoreCase("%" + keyword + "%", pageDetails);

        List<Book> books= pageBooks.getContent();

        List<BookDTO> bookDTOS = books.stream()
                .map(book -> modelMapper.map(book, BookDTO.class)).toList();
        BookResponse bookResponse = new BookResponse();
        bookResponse.setContent(bookDTOS);
        bookResponse.setPageNumber(pageBooks.getNumber());
        bookResponse.setPageSize(pageBooks.getSize());
        bookResponse.setTotalElements(pageBooks.getTotalElements());
        bookResponse.setTotalPages(pageBooks.getTotalPages());
        bookResponse.setLastPage(pageBooks.isLast());
        return bookResponse;
    }

    @Override
    public BookDTO updateBook(Long bookId, BookDTO bookDTO) {
        Book bookfromDB = bookRepository.findById(bookId)
                .orElseThrow(()-> new ResourceNotFoundException("Book", "BookId", bookId));
        Book book = modelMapper.map(bookDTO, Book.class);
        bookfromDB.setTitle(book.getTitle());
        bookfromDB.setAuthor(book.getAuthor());
        bookfromDB.setDescription(book.getDescription());
        bookfromDB.setIsbn(book.getIsbn());
        bookfromDB.setTotalCopies(book.getTotalCopies());
        bookfromDB.setAvailableCopies(book.getAvailableCopies());

        Book savedBook = bookRepository.save(bookfromDB);
        return modelMapper.map(savedBook, BookDTO.class);
    }

    @Override
    public BookDTO removeBook(Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(()-> new ResourceNotFoundException("Book", "BookId", bookId));
        bookRepository.delete(book);
        BookDTO bookDTO = modelMapper.map(book,BookDTO.class);
        return bookDTO;
    }

    @Override
    public BookDTO updateBookImage(Long bookId, MultipartFile image) throws IOException {
        Book bookFromDB = bookRepository.findById(bookId)
                .orElseThrow(()-> new ResourceNotFoundException("Book", "BookId", bookId));
        //Get the file name of uploaded image
        String filename = fileService.uploadImage(path, image);

        //Updating the new file name to the book
        bookFromDB.setImage(filename);

        //save updated book
        Book updatedBook  = bookRepository.save(bookFromDB);

        //return DTO after mapping book to DTO
        return modelMapper.map(updatedBook, BookDTO.class);
    }



}
