package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.demo.entity.UserAccessLog;

public interface UserAccessLogRepo extends JpaRepository<UserAccessLog, Integer>, JpaSpecificationExecutor<UserAccessLog>{

}
