package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.demo.entity.User;
import com.example.demo.entity.User.Role;
import com.example.demo.form.ChangePasswordForm;
import com.example.demo.form.SignUpForm;
import com.example.demo.model.BalanceAppException;
import com.example.demo.repo.UserRepo;
import com.example.demo.vo.UserVo;

import jakarta.transaction.Transactional;

@Component
@Service
public class UserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Transactional
	public void signUp(SignUpForm form) {
		form.setPassword(passwordEncoder.encode(form.getPassword()));
		userRepo.save(new User(form));
	}

	public UserVo findByLoginId(String username) {

		return userRepo.findOneByLoginId(username).map(UserVo::new).orElseThrow();

	}

	@Transactional
	public void updateContact(String username, String phone, String email) {

		userRepo.findOneByLoginId(username).ifPresent(user -> {
			user.setPhone(phone);
			user.setEmail(email);
		});

	}

	public List<UserVo> search(Boolean status, String name, String phone) {

		Specification<User> spec = (root, query, builder) -> builder.equal(root.get("role"), Role.Member);

		if (null != status) {
			spec = spec.and((root, query, builder) -> builder.equal(root.get("active"), status));
		}
		if (StringUtils.hasLength(name)) {
			spec = spec.and((root, query, builder) -> builder.like(builder.lower(root.get("name")),
					name.toLowerCase().concat("%")));

		}
		if (StringUtils.hasLength(phone)) {
			spec = spec.and((root, query, builder) -> builder.like(root.get("phone"), phone.concat("%")));

		}

		return userRepo.findAll(spec).stream().map(UserVo::new).toList();
	}

	@Transactional
	public void changeStatus(int id, boolean status) {

		userRepo.findById(id).ifPresent(user -> user.setActive(status));

	}

	
	@Transactional
	public void changePassword(ChangePasswordForm form) {

		if(!StringUtils.hasLength(form.getOldPassword())) {
			throw new BalanceAppException("Please enter old password");
		}

		if(!StringUtils.hasLength(form.getNewPassword())) {
			throw new BalanceAppException("Please enter new password");
		}

		if(form.getNewPassword().equals(form.getOldPassword())) {
			throw new BalanceAppException("Please enter different new password");
		}
		
		var user = userRepo.findOneByLoginId(form.getLoginId()).orElseThrow();
		
		if(!passwordEncoder.matches(form.getOldPassword(), user.getPassword())) {
			throw new BalanceAppException("Please check your passwords");
			
		}
		
		user.setPassword(passwordEncoder.encode(form.getNewPassword()));
	}
	

}
