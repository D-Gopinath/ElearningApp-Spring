package com.elearningapp.validation;

import com.elearningapp.exception.ValidationException;
import com.elearningapp.model.User;

public class UserValidation {

	public static void validateRegistrationData(User user) throws ValidationException {

		if (user.getName().isBlank() || user.getName().isEmpty()) {
			throw new ValidationException("Name cannot be Empty");
		}

		if (user.getPhone().length() != 10) {
			throw new ValidationException("Invalid phone Number");
		}
		for (int i = 0; i < user.getPhone().length(); i++) {
			char ch = user.getPhone().charAt(i);
			if (!Character.isDigit(ch)) {
				throw new ValidationException("Invalid number!!! Only numbers are allowed");
			}
		}

		if ((!user.getEmail().contains("@")) || (!user.getEmail().endsWith(".com"))) {
			throw new ValidationException("Invalid Email");
		}

		if (user.getPassword().length() < 8 || user.getPassword().length() > 16) {
			throw new ValidationException("Invalid Password");
		}

	}
	
	public static void validateLoginData(String email,String password)throws ValidationException {
		if ((!email.contains("@")) || (!email.endsWith(".com"))) {
			throw new ValidationException("Invalid Email");
		}

		if (password.length() < 8 || password.length() > 16) {
			throw new ValidationException("Invalid Password");
		}
	}
}
