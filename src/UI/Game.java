package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Game extends JFrame{
	JPanel frame;
	ArrayList<String> playerList = new ArrayList();
	
	
	public Game() {
		setTitle("The Settlers of Catan");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		frame = new JPanel();
		frame.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setLayout(new BorderLayout(5,5));
		setContentPane(frame);
		JPanel map = new JPanel();
		JPanel resource = new JPanel();
		JPanel bottom = new JPanel();
		
		frame.add(map,BorderLayout.CENTER);
		frame.add(resource,BorderLayout.EAST);
		frame.add(bottom,BorderLayout.SOUTH);
	}
	
	public void SetPlayer(String player){
		playerList.add(player);
	}
}
