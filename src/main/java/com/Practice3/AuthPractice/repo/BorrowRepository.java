package com.Practice3.AuthPractice.repo;

import com.Practice3.AuthPractice.model.BorrowRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowRepository extends JpaRepository<BorrowRecord, Long> {

    Page<BorrowRecord> findByUser_userId(Long userId, Pageable pageable);

    BorrowRecord findByUser_UserIdAndBook_BookId(Long userId, Long bookId);
}
