package com.goboushop.gobousmartshopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.goboushop.gobousmartshopping.beans.FinancialYear;

@Repository
public interface FinancialYearRepository extends JpaRepository<FinancialYear, Integer> {
	 @Query(value = "SELECT * FROM financial_year ORDER BY id DESC LIMIT 1", nativeQuery = true)
	 FinancialYear findLastRecord();
}
