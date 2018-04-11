package com.feaonly.rabbitmq.helloworld;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Send {
	private final static String QUEUE_NAME = "hello";

	public static void main(String[] args) throws Exception{
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("192.168.2.59");
		factory.setUsername("admin");
		factory.setPassword("123456");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		for(int i = 0;i<100;i++){
		String message = "Hello world!" + i;
		channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
		System.out.println("[x] Sent '" + message + "'");
		}
		channel.close();
		connection.close();
	}

}
