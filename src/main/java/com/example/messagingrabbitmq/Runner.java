package com.example.messagingrabbitmq;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {

	private final RabbitTemplate rabbitTemplate;
	private final Receiver receiver;

	public Runner(Receiver receiver, RabbitTemplate rabbitTemplate) {
		this.receiver = receiver;
		this.rabbitTemplate = rabbitTemplate;
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Write \"/q\" for stop");
		sendingMessage();
	}

	private void sendingMessage() throws InterruptedException {
		while (true){
			Scanner s = new Scanner(System.in);
			String mess = s.nextLine();
			Message message = new Message(MessagingRabbitmqApplication.id, mess);
			if(mess.equals("/q"))
				break;
			rabbitTemplate.convertAndSend(MessagingRabbitmqApplication.fanoutExchangeName, MessagingRabbitmqApplication.fanoutExchangeName, message);
			receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
		}
	}
}
