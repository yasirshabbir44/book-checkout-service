package com.smartdubai.yasir.repository;

import com.smartdubai.yasir.model.BookClassification;
import com.smartdubai.yasir.model.BookType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface BookClassificationRepository extends JpaRepository<BookClassification, Long>, JpaSpecificationExecutor {
}
