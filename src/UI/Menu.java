package UI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Menu extends JFrame{
	/**
	 * Start Application
	 */
	JPanel frame;
	JTextField player1 = new JTextField();
	JTextField player2 = new JTextField();
	JTextField player3 = new JTextField();
	JTextField player4 = new JTextField();
	
	public static void main(String[] args){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Set Menu Frame
	 */
	private Menu(){
		setTitle("The Settlers of Catan");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 430, 600);
		frame = new JPanel();
		frame.setBorder(new EmptyBorder(15, 15, 15, 15));
		setContentPane(frame);
		JPanel userID = new JPanel();
		frame.setLayout(new BoxLayout(frame,BoxLayout.Y_AXIS));
		JLabel label = new JLabel("Welcome to Catan");
		label.setForeground(Color.ORANGE);
		label.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 35));
		frame.add(label);
		JLabel label_1 = new JLabel("Please Enter the Name of player");
		label_1.setFont(new Font("SimSun", Font.PLAIN, 15));
		frame.add(label_1);
		frame.add(new JLabel("Player 1")).setForeground(Color.red);
		player1.setFont(new Font("SimSun", Font.PLAIN, 20));
		
		frame.add(player1);
		frame.add(new JLabel("Player 2")).setForeground(Color.blue);
		player2.setFont(new Font("SimSun", Font.PLAIN, 20));
		
		frame.add(player2);
		frame.add(new JLabel("Player 3")).setForeground(Color.green);
		player3.setFont(new Font("SimSun", Font.PLAIN, 20));
		
		frame.add(player3);
		frame.add(new JLabel("Player 4")).setForeground(Color.orange);
		player4.setFont(new Font("SimSun", Font.PLAIN, 20));
		
		frame.add(player4);
		
		JPanel bottom = new JPanel();
		frame.add(Tutorial());
		frame.add(Start());

	}
	
	/**
	 * Set Tutorial Button method
	 */
	private JButton Tutorial(){
		JButton tutorial = new JButton("Tutorial");
		tutorial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					URI url = new URI("https://www.youtube.com/watch?v=Kw4tIC_cJiE&t=21s");
					play(url);
				} catch (URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		return tutorial;
	}
	
	private static void play(URI uri){
		
		if(Desktop.isDesktopSupported()){
			try{
				Desktop.getDesktop().browse(uri);
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Set Start Button method
	 */
	private JButton Start(){
		JButton Start = new JButton("Start");
		Start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				
				if (Check()){
					dispose();
					Game game_frame = new Game();
					game_frame.setVisible(true);
					game_frame.SetPlayer(0,player1.getText());
					game_frame.SetPlayer(1,player2.getText());
					game_frame.SetPlayer(2,player3.getText());
					if (!player4.getText().isEmpty()){
						game_frame.SetPlayer(3,player4.getText());
					}
				}
				else{
					JOptionPane.showMessageDialog(null,"Not Enough Players. This game need 3-4 players to start");
				}
			
			}
		});
		return Start;
	}
	
	/**
	 * helper method check if the player number is match to 3-4 
	 * @return
	 */
	private boolean Check(){
		if (player1.getText().isEmpty()||player2.getText().isEmpty()||player3.getText().isEmpty()){
			return false;
		}
		return true;
	};
	

}
