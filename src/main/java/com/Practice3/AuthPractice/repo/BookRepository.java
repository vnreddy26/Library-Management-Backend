package com.Practice3.AuthPractice.repo;

import com.Practice3.AuthPractice.model.Book;
import com.Practice3.AuthPractice.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Page<Book> findByCategoryOrderByTitleAsc(Category category, Pageable pageDetails);

    Page<Book> findByTitleLikeIgnoreCase(String keyword, Pageable pageDetails);
}
