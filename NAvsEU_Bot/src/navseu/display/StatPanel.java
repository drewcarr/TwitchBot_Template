package navseu.display;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import navseu.processor.Storage;
import navseu.server.Client;

public class StatPanel extends JPanel{
	public JLabel na;
	public JLabel eu;
	public JLabel kappa;
	public JLabel pogchamp;
	public JLabel title;
	public JLabel space = new JLabel(" ");
	public ImageIcon naIcon,euIcon,kappaIcon,pogchampIcon;
	public Client client;
	public String channel;
	public JButton exit;
	public StatPanel(Client c, String cha)
	{
		channel = cha;
		client = c;
		this.setSize(500,500);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		Dimension d = new Dimension(200,200);
		Font font = new Font("Monotype Corsivia",1,40);
		loadIcons();
		
		title = new JLabel("Channel: " + channel);
		title.setFont(font);
		this.add(title);
		this.add(space);
		exit = new JButton("EXIT");
		exit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				client.leaveChannel(channel);
			}
		
		});
		
		na = new JLabel();
		na.setPreferredSize(d);
		na.setFont(font);
		eu = new JLabel();
		eu.setPreferredSize(d);
		eu.setFont(font);
		kappa = new JLabel();
		kappa.setPreferredSize(d);
		kappa.setFont(font);
		kappa.setIcon(kappaIcon);
		pogchamp = new JLabel();
		pogchamp.setPreferredSize(d);
		pogchamp.setFont(font);
		pogchamp.setIcon(pogchampIcon);
		
		updatePanel();
		
		this.add(na);
		this.add(eu);
		this.add(kappa);
		this.add(pogchamp);
		this.add(exit);
	}
	public void updatePanel()
	{
		int totalNA = 0,totalEU = 0,totalKappa = 0,totalPogchamp = 0;
		
		totalNA += (client.getStorage(channel)).getNA();
		totalEU += (client.getStorage(channel)).getEU();
		totalKappa += (client.getStorage(channel)).getKappa();
		totalPogchamp += (client.getStorage(channel)).getPogchamp();
	
		na.setText("Total Na's : " + totalNA);
		eu.setText("Total EU's : " + totalEU);
		kappa.setText("Total Kappa's: " + totalKappa);
		pogchamp.setText("Total PogChamp's: " + totalPogchamp);
		this.revalidate();
		this.repaint();
	}
	public void loadIcons()
	{
		BufferedImage img = null;
		try 
		{
		    img = ImageIO.read(new File("C:/Users/DNAKMEMES/git/NAvsEU_Bot/kappa.png"));
		    kappaIcon = new ImageIcon(img);
		    img = ImageIO.read(new File("C:/Users/DNAKMEMES/git/NAvsEU_Bot/pogchamp.jpg"));
		    pogchampIcon = new ImageIcon(img);
		} 
		catch (IOException e)
		{
			System.out.println("Couldn't load image");
		}
		
	}
	public String getChannel()
	{
		return channel;
	}
	
}
