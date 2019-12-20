package com.ward.savefood.common.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import org.springframework.stereotype.Component;

@Component
public class AuthKeyUtil {

	public String getAuthKey() throws NoSuchAlgorithmException {
		String authKey = "";
		SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
		
		for(int i=0; i<6; i++) {
			authKey += secureRandom.nextInt(10);
		}
		
		return authKey;
	}
}
