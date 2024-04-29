package com.goboushop.gobousmartshopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.goboushop.gobousmartshopping.beans.VendorType;

@Repository
public interface VendorTypeRepository extends JpaRepository<VendorType, Long> {

}
