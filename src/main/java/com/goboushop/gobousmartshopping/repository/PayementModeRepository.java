package com.goboushop.gobousmartshopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.goboushop.gobousmartshopping.beans.PayementMode;

@Repository
public interface PayementModeRepository extends JpaRepository<PayementMode, Long> {

}
