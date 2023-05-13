package com.example.ToDoApp.login;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

	public boolean authentication(String username, String password)
	{
          boolean isValidUserName = username.equalsIgnoreCase("in28Minutes");
          boolean isValidPassword = password.equalsIgnoreCase("dummy");
          return  isValidUserName && isValidPassword;
	}
}
