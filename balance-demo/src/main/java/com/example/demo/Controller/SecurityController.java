package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.  GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.User.Role;
import com.example.demo.form.ChangePasswordForm;
import com.example.demo.form.SignUpForm;
import com.example.demo.service.UserService;

import jakarta.validation.Valid;

@Controller
public class SecurityController {
	
	@Autowired
	private UserService userService;
	

	@GetMapping("/")
	public String index() {
		
		var auth= SecurityContextHolder.getContext().getAuthentication();
		
		if(null != auth && 
				auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals(Role.Admin.name()) 
						|| a.getAuthority().equals(Role.Member.name()))) {
			
			return "redirect:/user/home";
			
		}
		return"signin";
	}
	
	@GetMapping("signup")
	public void LoadSignUp() {
		
	}
	
	@PostMapping("signup")
	public String signUp(@ModelAttribute(name = "form")@Valid SignUpForm form, BindingResult result) {
		
		if(result.hasErrors()) {
			System.out.println(result.getAllErrors());
			return "signup";
		}
		
		userService.signUp(form);
		
		return"redirect:/";
	}   
	
	
	@PostMapping("user/changepass")
	public String changePass(@ModelAttribute ChangePasswordForm form, RedirectAttributes redirect) {
		userService.changePassword(form);
		redirect.addFlashAttribute("message", "Your password has been changed successfully");
 		
		return "redirect:/";
	}
	
	@ModelAttribute(name = "form")
	SignUpForm form() {
		return new SignUpForm();
	}

}
