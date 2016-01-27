package navseu.display;

import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import navseu.processor.Storage;
import navseu.server.Client;

public class DisplayFrame extends JFrame{
	public StatPanel channelPanel;
	public Storage storage;
	public ArrayList<StatPanel> channelPanels = new ArrayList<StatPanel>();
	public DisplayFrame(Client client)
	{
		for(int x = 0; x < client.getChannels().size(); x++)
		{
			channelPanel = new StatPanel(client,client.getChannel(x));
			channelPanels.add(channelPanel);
		}
		for(int x = 0; x < channelPanels.size(); x++)
		{
			
			this.add(channelPanels.get(x));
		}
		
		this.setVisible(true);
		this.setTitle("NA vs EU");
		this.setSize(2000, 2000);
		this.setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public void updateFrame(Client client)
	{
		System.out.println("Test: " + client.getChannels());
		if(channelPanels.size() > client.getChannels().size())
		{
			for(int x = 0; x < channelPanels.size(); x++)
			{
				if(!client.getChannels().contains(channelPanels.get(x).getChannel()))
				{
					this.remove(channelPanels.get(x));
					channelPanels.remove(x);
				}
			}
		}
		for(int x = 0; x < channelPanels.size(); x++)
		{
			( channelPanels.get(x)).updatePanel();
		}
		this.revalidate();
		this.repaint();
	}
}
