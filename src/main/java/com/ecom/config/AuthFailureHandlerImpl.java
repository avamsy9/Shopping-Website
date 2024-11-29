package com.ecom.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.ecom.constant.AppConstant;
import com.ecom.entities.User;
import com.ecom.repository.UserRepo;
import com.ecom.services.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthFailureHandlerImpl extends SimpleUrlAuthenticationFailureHandler {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private UserService userService;

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {

		String email = request.getParameter("username");

		User user = userRepo.findByEmail(email);

		if (user != null) {
			if (user.getIsEnable()) {
				if (user.getAccountNonLocked()) {
					if (user.getFailedAttempt() < AppConstant.ATTEMPT_TIME) {
						userService.increaseFailedAttempt(user);
					} else {
						userService.userAccountLock(user);
						exception = new LockedException("Your account is locked !! failed attempt 3");
					}
				} else {
					if (userService.unlockAccountTimeExpired(user)) {
						exception = new LockedException("Your account is unlocked !! Please try to login");
					} else {
						exception = new LockedException("your account is Locked !! Please try after sometimes");
					}
				}
			} else {
				exception = new LockedException("your account is inactive");
			}
		} else {
			exception = new LockedException("Email & password invalid");
		}

		super.setDefaultFailureUrl("/signin?error");
		super.onAuthenticationFailure(request, response, exception);
	}
}