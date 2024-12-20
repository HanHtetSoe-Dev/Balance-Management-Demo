package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Balance;

@Repository
public interface BalanceRepo extends JpaRepository<Balance, Integer>, JpaSpecificationExecutor<Balance>{

}
