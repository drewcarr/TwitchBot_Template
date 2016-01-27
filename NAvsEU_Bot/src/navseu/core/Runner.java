package navseu.core;

import java.io.IOException;

import navseu.display.DisplayFrame;
import navseu.processor.ProcessMessage;
import navseu.processor.Storage;
import navseu.server.Client;

public class Runner {
	public static Client client;
	public static ProcessMessage pm;
	public static DisplayFrame frame;
	public static void main(String args[]) throws IOException
	{
		client = null;
		client = new Client(pm);
		pm = new ProcessMessage(client);
		client.updateProcessor(pm);
		
		client.joinChannel("netherrealm");
		client.joinChannel("scglive");
		client.joinChannel("gcdtv");
		frame = new DisplayFrame(client);
		run();
	}
	public static void run() throws IOException
	{
		while(true)
		{
			if(client.containsMessage() == true)
			{
				client.readMessage();
				frame.updateFrame(client);
			}
			
		}
	}
}
