package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Game extends JFrame{
	JPanel frame;
	ArrayList<Player> playerList = new ArrayList<Player>();
	int currentPlayer = 0;
	JPanel bottom = new JPanel();
	Map map = new Map();
	Info resource = new Info();
	boolean rollflage;
	int firstflage;
	boolean townflage;
	int[] typeNum = {0,2,2,3,3,3,3,2,1,0,4,2,4,5,0,1,4,1,4};

	
	public Game() {
		setTitle("The Settlers of Catan");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 950, 800);
		frame = new JPanel();
		frame.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setLayout(new BorderLayout(5,5));
		setContentPane(frame);
		bottom();
		frame.add(map,BorderLayout.CENTER);
		map.setPreferredSize(new Dimension(600, 600));
		frame.add(resource,BorderLayout.EAST);
		resource.setPreferredSize(new Dimension(200, 450));
		frame.add(bottom,BorderLayout.SOUTH);
		bottom.setPreferredSize(new Dimension(600, 150));
		JOptionPane.showMessageDialog(null,"Game Start! Please place inital towns for each player");
		firstflage = 0;
		
	}
	
	
	public void SetPlayer(int uid,String player){
		playerList.add(new Player(uid,player));
	}
	
	private void NextPlayer(){
		rollflage = true;
		townflage = false;
		if(currentPlayer <playerList.size()-1){
			currentPlayer++;
		    resource.setPlayer(playerList.get(currentPlayer));
		}
		else{
			firstflage++;
			currentPlayer = 0;
			resource.setPlayer(playerList.get(currentPlayer));
		}
	}
	
	private void bottom(){
		bottom.setLayout(null);
		
		JButton m_next = new JButton("Next Player");
		m_next.setFont(new Font("SimSun", Font.PLAIN, 15));
		m_next.setBounds(40, 50, 140, 62);
		m_next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NextPlayer();
				JOptionPane.showMessageDialog(null,"Player "+(currentPlayer+1) +": "+playerList.get(currentPlayer).m_name+ " Please make your choice");
			}
		});
		bottom.add(m_next);
		JButton m_build = new JButton("Build");
		m_build.setFont(new Font("SimSun", Font.PLAIN, 15));
		m_build.setBounds(277, 50, 99, 62);
		bottom.add(m_build);
		
		JPopupMenu popupMenu = new JPopupMenu();
		popupMenu.setFont(new Font("Segoe UI", Font.BOLD, 20));
		popupMenu.setPopupSize(new Dimension(100, 150));
		JMenuItem build_road = new JMenuItem("Road");
		build_road.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {if(checkFlag()){
				try {
					if(checkResource(0)){
						map.playerBuild(currentPlayer, 0);
						playerList.get(currentPlayer).build(0);
						resource.setPlayer(playerList.get(currentPlayer));
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				};
			}}
		});
		popupMenu.add(build_road);
		JMenuItem build_town = new JMenuItem("Town");
		build_town.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(firstflage<2){if(townflage){
					JOptionPane.showMessageDialog(null, "You already placed your Town, press Next Player");
				}else{
					townflage=true;
					map.firstPlayerBuild(currentPlayer);
					try {
						playerList.get(currentPlayer).build(1);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					resource.setPlayer(playerList.get(currentPlayer));
				}
				} else
					try {
						if(checkResource(1)){
								map.playerBuild(currentPlayer, 1);
								playerList.get(currentPlayer).build(1);
								resource.setPlayer(playerList.get(currentPlayer));
							}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		});
		popupMenu.add(build_town);
		JMenuItem build_city = new JMenuItem("City");
		build_city.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {if(checkFlag()){
				try {
					if(checkResource(2)){
						map.playerBuild(currentPlayer, 2);
						playerList.get(currentPlayer).build(2);
						resource.setPlayer(playerList.get(currentPlayer));
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				};}}
		});
		popupMenu.add(build_city);
		JMenuItem build_dvp = new JMenuItem("Card");
		build_dvp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {if(checkFlag()){
				try {
					if(checkResource(3)){
						playerList.get(currentPlayer).addCards(getCard());
						playerList.get(currentPlayer).build(3);
						resource.setPlayer(playerList.get(currentPlayer));
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				};
			}
			}
		});
		popupMenu.add(build_dvp);
		addPopup(m_build, popupMenu);
		m_build.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				popupMenu.show(bottom,m_build.getX(),m_build.getY());
			}
		});
		JButton m_trade = new JButton("Trade");
		m_trade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkFlag()){
				try {
					Trade trade = new Trade(playerList,currentPlayer);
					trade.setVisible(true);
					playerList = trade.updatePlayerList();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				};
				}
			}
		});
		m_trade.setFont(new Font("SimSun", Font.PLAIN, 15));
		m_trade.setBounds(437, 50, 99, 62);
		bottom.add(m_trade);
		
		JButton m_roll = new JButton("Roll");
		JLabel label = new JLabel("0");
		label.setBackground(Color.WHITE);
		JLabel label_1 = new JLabel("0");
		label_1.setBackground(Color.WHITE);
		m_roll.setBounds(635, 75, 93, 37);
		m_roll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rollflage){
				int[] num = roll();
				label.setText(Integer.toString(num[0]));
				label_1.setText(Integer.toString(num[1]));
				getResource(num[0]+num[1]);
				resource.setPlayer(playerList.get(currentPlayer));
				rollflage=false;
				}
			}
		});
		bottom.add(m_roll);
		
		
		label.setFont(new Font("SimSun", Font.PLAIN, 23));
		label.setBounds(697, 36, 31, 37);
		bottom.add(label);
		
		
		label_1.setFont(new Font("SimSun", Font.PLAIN, 23));
		label_1.setBounds(635, 36, 33, 37);
		bottom.add(label_1);
	}
	
	private int[] roll(){
		Random rand = new Random();
		int[] dice = {rand.nextInt(6)+1,rand.nextInt(6)+1};
		return dice;		
	}
	
	private void getResource(int value){
		ArrayList<Town> towns =  map.getResouces(value);
		for(Town town:towns){
			System.out.println("added");
			playerList.get(town.owner).addResources(typeNum[value], town.m_type);
		}
	}
	
	private void playerBuild(){
		//TODO:
	}
	
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
	
	private boolean checkResource(int resource_type) throws Exception{
		return playerList.get(currentPlayer).checkResources(resource_type);
	}
	
	private int getCard(){
		Random rand = new Random();
		return rand.nextInt(4);
	}
	
	private boolean checkFlag(){
		if (firstflage<2){
			JOptionPane.showMessageDialog(null,"For first 2 round, please build one town for each player");
			return false;
		}
		return true;
	}
}
