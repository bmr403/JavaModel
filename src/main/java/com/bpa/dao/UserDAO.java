package com.bpa.dao;

import com.bpa.model.User;

public class UserDAO extends AbstractDAO{
	
	
	
	public User getLoginVerificationByEmail(User user)
	{
		return (User) super.getLoginVerificationByEmail(user);		
	}
	
}
