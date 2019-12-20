package com.ward.savefood.common.util;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class PasswordUtil {

	public String hash(String password) {
		return BCrypt.hashpw(password, BCrypt.gensalt());
	}
	
	public boolean checkHash(String password, String hashed) {
		return BCrypt.checkpw(password, hashed);
	}
}
