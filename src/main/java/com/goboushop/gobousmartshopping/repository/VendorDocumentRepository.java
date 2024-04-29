package com.goboushop.gobousmartshopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.goboushop.gobousmartshopping.beans.VendorDocuments;

@Repository
public interface VendorDocumentRepository extends JpaRepository<VendorDocuments, Long> {

}
