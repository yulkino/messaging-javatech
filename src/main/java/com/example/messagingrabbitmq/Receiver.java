package com.example.messagingrabbitmq;

import java.util.concurrent.CountDownLatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

	private CountDownLatch latch = new CountDownLatch(1);

	public void receiveMessage(Message message) {
		if (message.From == MessagingRabbitmqApplication.id) {
			return;
		}
		System.out.println("From <" + message.From + ">: \"" + message.Message + "\"");
		latch.countDown();
	}

	public CountDownLatch getLatch() {
		return latch;
	}

}