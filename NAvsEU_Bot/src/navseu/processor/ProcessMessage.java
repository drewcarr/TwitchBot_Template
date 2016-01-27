package navseu.processor;

import java.util.ArrayList;

import navseu.server.Client;

public class ProcessMessage {
	public Client client;
	public CharSequence charNA = "na";
	public CharSequence charEU = "eu";
	public CharSequence charKappa = "kappa";
	public CharSequence charPogchamp = "pogchamp";
	public ProcessMessage(Client c)
	{
		client = c;
	}
	public void receiveMessage(String mess)
	{
		ArrayList<Integer> index = new ArrayList<Integer>();
		int i = mess.indexOf(":");
		while(i >= 0)
		{
			index.add(i);
			i = mess.indexOf(":", i+1);
		}
		
		if(mess.startsWith("PING"))
		{
			client.pong();
		}
		
		else
		{	
			String messa = mess.toLowerCase();
			if(messa.contains(charKappa) || messa.contains(charPogchamp) || messa.contains(charNA) || messa.contains(charEU))
			{
				int hashtag = mess.indexOf("#");
				int space = mess.indexOf(" ",hashtag);
				String channel = mess.substring(hashtag+1,space);
				if(index.size() >= 2)
				{
					String Premessage = mess.substring(index.get(1)+1,mess.length());
					String[] message = Premessage.split(" ");
					if(message[0].startsWith("!NAvsEU"))
					{
						if(client.getStorage(channel).getNA() >= client.getStorage(channel).getEU())
						{
							client.sendChat("The NA spam is greater");
						}
						else
						{
							client.sendMessage("The EU spam is greater");
						}
					}
					for(int x = 0; x < message.length; x++)
					{
						message[x] = message[x].toLowerCase();
						switch(message[x])
						{
							case "na": 			client.getStorage(channel).addNA();
												break;
							case "eu": 			client.getStorage(channel).addEU();
												break;
							case "kappa": 		client.getStorage(channel).addKappa();
												break;
							case "pogchamp": 	client.getStorage(channel).addPogchamp();
												break;
												
							
						}
					}
				}
			}
		}
	}
}
