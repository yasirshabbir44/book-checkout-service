package com.smartdubai.yasir.repository;

import com.smartdubai.yasir.model.Book;
import com.smartdubai.yasir.model.BookType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface BookTypeRepository extends JpaRepository<BookType, String>, JpaSpecificationExecutor {
}
