package com.Practice3.AuthPractice.service;

import com.Practice3.AuthPractice.payload.BookResponse;
import com.Practice3.AuthPractice.payload.BorrowDTO;
import com.Practice3.AuthPractice.payload.BorrowResponse;

public interface BorrowService {
    BorrowDTO createBorrowRecord(Long userId, Long bookId, BorrowDTO borrowDTO);

    BorrowResponse getBorrowRecordByUser(Long userId, Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

    BorrowDTO updateBorrowRecord(Long userId, Long bookId, BorrowDTO borrowDTO);
}
