package com.smartdubai.yasir.repository;

import com.smartdubai.yasir.model.PromoCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface PromoCodeRepository extends JpaRepository<PromoCode, String>, JpaSpecificationExecutor {
}
