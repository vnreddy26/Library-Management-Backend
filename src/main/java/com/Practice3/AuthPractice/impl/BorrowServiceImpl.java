package com.Practice3.AuthPractice.impl;

import com.Practice3.AuthPractice.exceptions.ResourceNotFoundException;
import com.Practice3.AuthPractice.model.Book;
import com.Practice3.AuthPractice.model.BorrowRecord;
import com.Practice3.AuthPractice.model.Category;
import com.Practice3.AuthPractice.model.User;
import com.Practice3.AuthPractice.payload.BorrowDTO;
import com.Practice3.AuthPractice.payload.BorrowResponse;
import com.Practice3.AuthPractice.repo.BookRepository;
import com.Practice3.AuthPractice.repo.BorrowRepository;
import com.Practice3.AuthPractice.repo.UserRepository;
import com.Practice3.AuthPractice.service.BorrowService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BorrowServiceImpl implements BorrowService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BorrowRepository borrowRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public BorrowDTO createBorrowRecord(Long userId, Long bookId, BorrowDTO borrowDTO) {

        User user = userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User", "userId", userId));

        Book book = bookRepository.findById(bookId)
                .orElseThrow(()-> new ResourceNotFoundException("Book", "bookID", bookId));

        BorrowRecord borrowRecord = modelMapper.map(borrowDTO, BorrowRecord.class);
        borrowRecord.setUser(user);
        borrowRecord.setBook(book);
        BorrowRecord savedBorrowRecord = borrowRepository.save(borrowRecord);
        return modelMapper.map(savedBorrowRecord, BorrowDTO.class);
    }

    @Override
    public BorrowResponse getBorrowRecordByUser(Long userId, Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
        // Check if user exists
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));

        // Create Pageable object with sort parameters
        Pageable pageable = PageRequest.of(pageNumber, pageSize,
                sortOrder.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending());

        // Fetch paginated borrow records for the user
        Page<BorrowRecord> borrowRecordPage = borrowRepository.findByUser_userId(userId, pageable);

        // Convert the BorrowRecord entities to DTOs
        List<BorrowDTO> borrowDTOS = borrowRecordPage.getContent().stream()
                .map(borrowRecord -> modelMapper.map(borrowRecord, BorrowDTO.class))
                .collect(Collectors.toList());

        // Prepare and return the response with paginated results
        BorrowResponse borrowResponse = new BorrowResponse();
        borrowResponse.setContent(borrowDTOS);
        borrowResponse.setTotalPages(borrowRecordPage.getTotalPages());
        borrowResponse.setTotalElements(borrowRecordPage.getTotalElements());
        borrowResponse.setPageNumber(borrowRecordPage.getNumber());
        borrowResponse.setPageSize(borrowRecordPage.getSize());
        borrowResponse.setLastPage(borrowRecordPage.isLast());
        return borrowResponse;
    }

    @Override
    public BorrowDTO updateBorrowRecord(Long userId, Long bookId, BorrowDTO borrowDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User", "userId", userId));

        Book book = bookRepository.findById(bookId)
                .orElseThrow(()-> new ResourceNotFoundException("Book", "bookID", bookId));

                BorrowRecord borrowRecord = modelMapper.map(borrowDTO, BorrowRecord.class);

        BorrowRecord retrivedBorrowRecord = borrowRepository.findByUser_UserIdAndBook_BookId(userId,bookId);
        if (retrivedBorrowRecord == null) {
            throw new ResourceNotFoundException("BorrowRecord", "userId and bookId", userId + " & " + bookId);
        }
        retrivedBorrowRecord.setUser(user);
        retrivedBorrowRecord.setBook(book);
        retrivedBorrowRecord.setFineAmount(borrowRecord.getFineAmount());
        retrivedBorrowRecord.setReturnDate(borrowRecord.getReturnDate());


        BorrowRecord savedBorrowRecord = borrowRepository.save(retrivedBorrowRecord);
        return modelMapper.map(savedBorrowRecord, BorrowDTO.class);
    }


}
