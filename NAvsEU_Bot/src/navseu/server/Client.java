package navseu.server;


import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import navseu.processor.ProcessMessage;
import navseu.processor.Storage;
public class Client {
	public String servername = "irc.twitch.tv";
	public int port = 6667;
	public Socket socket;
	public String username = "navseubot";
	public String password = "oauth:68phvkvj3jiuxucmxgnblrkqvdgc4a";
	public PrintWriter pw;
	public BufferedReader br;
	public BufferedReader fr;
	public InputStreamReader isr;
	public String message = "potato";
	public ArrayList<String> connectedChannels = new ArrayList<String>();
	public HashMap<String,Storage> masterStorage = new HashMap<String,Storage>();
	public ProcessMessage pm;
	public Client(ProcessMessage processmessage)
	{
		pm = processmessage;
		try
		{
			
			fr = new BufferedReader(new FileReader("C:/Users/DNAKMEMES/Documents/Twitch_Oauth.txt"));
			password = fr.readLine();
			System.out.println(password);
			fr.close();
			socket = new Socket(servername,port);
			System.out.println(socket.isConnected());
			pw = new PrintWriter(socket.getOutputStream());
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			
			login();
			System.out.println("Logged in");
			
		}
		catch(IOException ioException)
		{
			System.out.println("Error");
		}
		
	}
	public void sendMessage(String mess)
	{
		mess = mess + "\n";
		pw.print(mess);
		pw.flush();
		System.out.println("Sent message: " + mess);
		
	}
	public void processMessage(String message)
	{
		pm.receiveMessage(message);
	}
	public void login() throws IOException
	{
		sendMessage("PASS " + password);
		sendMessage("NICK " + username);
		message = br.readLine();
		while(br.ready())
		{
			System.out.println(message);
			message = br.readLine();
		}
	}
	public void joinChannel(String channel)
	{
		sendMessage("JOIN #" + channel);
		try
		{
			message = br.readLine();
			while(br.ready())
			{
				System.out.println(message);
				message = br.readLine();
			}
		}
		catch(IOException ioException)
		{
			System.out.println("Error: " + ioException);
		}
		connectedChannels.add(channel);
		Storage storage = new Storage();
		masterStorage.put(channel,storage);
	}
	public void leaveChannel(String channel)
	{
		sendMessage("PART #" + channel);
		try
		{
			message = br.readLine();
			while(br.ready())
			{
				System.out.println(message);
				message = br.readLine();
			}
		}
		catch(IOException ioException)
		{
			System.out.println("Error: " + ioException);
		}
		connectedChannels.remove(channel);
		masterStorage.remove(channel);
	}
	public void sendChat(String channel, String message)
	{
		String prefix = "PRIVMSG #";
		if(connectedChannels.contains(channel))
		{
			prefix += channel;
			prefix += " :";
			String finalMessage = prefix + message;
			sendMessage(finalMessage);
		}
		else
		{
			System.out.println("You are not connected to this chat");
		}
	}
	public void sendChat(String message)
	{
		//sends message to all connected channels
		for(int x = 0; x < connectedChannels.size(); x++)
		{
			String prefix = "PRIVMSG #";
			prefix += connectedChannels.get(x);
			prefix += " :";
			String finalMessage = prefix + message;
			sendMessage(finalMessage);
		}
	}
	public void reveiveMessages() throws IOException
	{
		while(br.ready())
		{
			message = br.readLine();
			processMessage(message);
			System.out.println(message);
			
		}
	}
	public void closeConnections()
	{
		try
		{
			br.close();
			pw.close();
			socket.close();
		}
		catch(IOException ioException)
		{
			ioException.printStackTrace();
		}
	}
	public boolean containsMessage() throws IOException
	{
		return br.ready();
	}
	public void readMessage() throws IOException
	{
		message = br.readLine();
		processMessage(message);
		System.out.println(message);
	}
	public void pong()
	{
		sendMessage("PONG tmi.twitch.tv");
	}
	public void updateProcessor(ProcessMessage p)
	{
		pm = p;
	}
	public HashMap getMasterStorage()
	{
		return masterStorage;
	}
	public Storage getStorage(String key)
	{
		if(masterStorage.containsKey(key) == true)
		{
			return masterStorage.get(key);
		}
		else
		{
			System.out.println("Couldn't find key: " + key +"end");
			return null;
		}
	}
	public ArrayList getChannels()
	{
		return connectedChannels;
	}
	public String getChannel(int x)
	{
		return connectedChannels.get(x);
	}
}
