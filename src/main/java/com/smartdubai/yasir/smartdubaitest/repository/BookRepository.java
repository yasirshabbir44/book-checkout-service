package com.smartdubai.yasir.smartdubaitest.repository;

import com.smartdubai.yasir.smartdubaitest.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor {
}
