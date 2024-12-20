package com.example.demo.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User>{

	UserDetails findByname(String username);

	Optional<User> findOneByLoginId(String username);

}
