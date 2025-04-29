package com.Practice3.AuthPractice.controller;

import com.Practice3.AuthPractice.config.AppConstants;
import com.Practice3.AuthPractice.payload.BookDTO;
import com.Practice3.AuthPractice.payload.BookResponse;
import com.Practice3.AuthPractice.payload.BorrowDTO;
import com.Practice3.AuthPractice.payload.BorrowResponse;
import com.Practice3.AuthPractice.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BorrowController {

    @Autowired
    BorrowService borrowService;

    @PostMapping("/admin/user/{userId}/{bookId}/borrowrecord")
    public ResponseEntity<BorrowDTO> createBorrowRecord(@RequestBody BorrowDTO borrowDTO,
                                           @PathVariable Long bookId,
                                           @PathVariable Long userId){
        BorrowDTO savedborrowDTO = borrowService.createBorrowRecord(userId, bookId, borrowDTO);

        return new ResponseEntity<>(savedborrowDTO, HttpStatus.CREATED);
    }

    @GetMapping("/public/user/{userId}/borrowrecord")
    public ResponseEntity<BorrowResponse> getBorrowRecordByUser(@PathVariable Long userId,
                                                                         @RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
                                                                         @RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
                                                                         @RequestParam(name = "sortBy", defaultValue = AppConstants.SORT_BORROW_RECORD_BY , required = false) String sortBy,
                                                                         @RequestParam(name = "sortOrder", defaultValue = AppConstants.SORT_DIR, required = false) String sortOrder){
        BorrowResponse borrowResponse = borrowService.getBorrowRecordByUser(userId, pageNumber, pageSize, sortBy, sortOrder);
        return new ResponseEntity<>(borrowResponse, HttpStatus.OK);
    }

    @PutMapping("/admin/user/{userId}/{bookId}/borrowrecordupdate")
    public ResponseEntity<BorrowDTO> updateBorrowRecord(@RequestBody BorrowDTO borrowDTO,
                                                        @PathVariable Long bookId,
                                                        @PathVariable Long userId){
        BorrowDTO savedborrowDTO = borrowService.updateBorrowRecord(userId, bookId, borrowDTO);

        return new ResponseEntity<>(savedborrowDTO, HttpStatus.CREATED);
    }
}
