package twitchbottemplate.processor;

import twitchbottemplate.server.Client;

public class ProcessMessage {
	public Client client;
	public ProcessMessage(Client c)
	{
		client = c;
	}
	public void receiveMessage(String mess)
	{
		if(mess.startsWith("PING"))
		{
			client.pong();
		}
		//Put code to deal with incoming messages
	}
	
}
