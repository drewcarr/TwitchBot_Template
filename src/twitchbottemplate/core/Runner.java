package twitchbottemplate.core;

import java.io.IOException;

import twitchbottemplate.processor.ProcessMessage;
import twitchbottemplate.server.Client;

public class Runner {
	public static Client client;
	public static ProcessMessage pm;
	public static void main(String args[]) throws IOException
	{
		client = new Client(pm);
		pm = new ProcessMessage(client);
		client.updateProcessor(pm);
		
		run();
	}
	public static void run() throws IOException
	{
		while(true)
		{
			if(client.containsMessage() == true)
			{
				client.readMessage();
			}
			
		}
	}
}
