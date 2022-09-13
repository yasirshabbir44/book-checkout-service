package com.smartdubai.yasir.repository;

import com.smartdubai.yasir.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor {
}
