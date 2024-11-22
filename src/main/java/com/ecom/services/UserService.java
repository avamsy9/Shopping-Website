package com.ecom.services;

import java.util.List;

import com.ecom.entities.User;

public interface UserService {

    public User saveUser(User user);

    public User getUserByEmail(String email);

    public List<User> getUsers(String role);

    public Boolean updateAccountStatus(Integer id, Boolean status);

    public void increaseFailedAttempt(User user);

	public void userAccountLock(User user);

	public boolean unlockAccountTimeExpired(User user);

	public void resetAttempt(int userId);

    public void updateUserResetToken(String email, String resetToken);
	
	public User getUserByToken(String token);
	
	public User updateUser(User user);

}
