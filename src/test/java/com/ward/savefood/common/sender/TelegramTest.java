package com.ward.savefood.common.sender;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

public class TelegramTest {
	
	@InjectMocks
	private Telegram telegram;
 
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

	@Test
	public void testAuthMember() {
		//assertNotEquals(-1, telegram.authMember("1234"));
	}

	@Test
	public void testSendMessage() {
		assertEquals(true, telegram.sendMessage("605360061", "메시지 테스트"));
	}
}