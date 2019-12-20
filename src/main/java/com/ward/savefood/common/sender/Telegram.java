package com.ward.savefood.common.sender;

import java.util.List;

import org.springframework.stereotype.Component;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.GetUpdatesResponse;
import com.pengrad.telegrambot.response.SendResponse;

@Component
public class Telegram {
	private TelegramBot bot = new TelegramBot("959534924:AAEpJrEMxNmYrVYgLJMkSzwq8Ah9lksX7IQ");
	private GetUpdates getUpdates = new GetUpdates().limit(100).offset(0).timeout(0);
	
	public long authMember(String authKey) {
		GetUpdatesResponse updatesResponse = bot.execute(getUpdates);
		List<Update> updates = updatesResponse.updates();
		
		if(!updatesResponse.isOk()) {
			return -2;
		}
		
		for(int i=updates.size()-1; i>=0; i--) {
			Message message = updates.get(i).message();
			
			if(message.text() != null && message.text().equals(authKey)) {
				return message.chat().id();
			}
		}
		return -1;
	}
	
	public boolean sendError(String text) {
		return sendMessage("-327590611", text);
	}
	
	public boolean sendMessage(String chatId, String text) {
		SendMessage request = new SendMessage(chatId, text).disableNotification(false);
		
		SendResponse sendResponse = bot.execute(request);
		boolean ok = sendResponse.isOk();
		
		return ok;
	}
}
