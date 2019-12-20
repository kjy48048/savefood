package com.ward.savefood.common.util;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

public class UtilTest {

	@InjectMocks
	private PasswordUtil passwodUtil;
 
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
	
	@Test
	public void testPasswordUtil() {
		String password = passwodUtil.hash("asdfasdf");
		
		System.out.println(password);
		
		assertEquals(true, passwodUtil.checkHash("asdfasdf", password));
	}
}