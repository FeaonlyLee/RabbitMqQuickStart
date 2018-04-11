package com.feaonly.rabbitmq.publishsubscribe;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class EmitLog {
	private static final String EXCHANGE_NAME = "logs";
	
	public static void main(String[] args) throws Exception{
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("192.168.2.59");
		factory.setUsername("admin");
		factory.setPassword("123456");

		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		
		channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);
		
		String message = getMessage(args);
		
		channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes("UTF-8"));
		System.out.println("[x] Sent '" + message + "'");
		
		channel.close();
		connection.close();
	}
	
	private static String getMessage(String[] strings) {
		if (strings.length < 1) {
			return "info: Hello world!";
		}
		return joinStrings(strings, " ");
	}

	private static String joinStrings(String[] strings, String delimiter) {
		int length = strings.length;
		if (length == 0)
			return "";
		StringBuilder words = new StringBuilder(strings[0]);
		for (int i = 1; i < length; i++) {
			words.append(delimiter).append(strings[i]);
		}
		return words.toString();
	}
}