package UI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import java.awt.Color;
import javax.swing.JButton;

public class Info extends JPanel{
	private JTable resource_table;
	
	private String[][] resource_data = {
			{"Brick","0"},{"Ore","0"},{"Wool","0"},{"Grain","0"},{"Lumber","0"}
	};
	
	private String[][] card_data = {{"Soldier","0"},{"BuildRoad","0"},{"GetResource","0"},{"Monopoly","0"}};
	private String[] resource_type = {"type","count"};
	private JTable card_table;
	private JLabel WinPoint;
	private JLabel points;
	private JLabel m_player;
	private JLabel playerNum;
	
	Player currentPlayer;
	
	public Info() {
		setLayout(null);
		JLabel Recources = new JLabel("Recources");
		Recources.setFont(new Font("SimSun", Font.PLAIN, 20));
		Recources.setBounds(10, 78, 180, 27);
		add(Recources);
		
		resource_table = new JTable(resource_data,resource_type);
		resource_table.setFont(new Font("SimSun", Font.PLAIN, 20));
		resource_table.setBounds(10, 115, 180, 150);
		resource_table.setRowHeight(30);
		add(resource_table);
		
		JLabel Cards = new JLabel("Cards");
		Cards.setBounds(10, 291, 180, 27);
		card_table = new JTable(card_data,resource_type);
		card_table.setFont(new Font("SimSun", Font.PLAIN, 14));
		Cards.setFont(new Font("SimSun", Font.PLAIN, 20));
		add(Cards);
		
		card_table.setBounds(10, 328, 180, 120);
		card_table.setRowHeight(30);
		add(card_table);
		
		WinPoint = new JLabel("Winning Points");
		WinPoint.setFont(new Font("SimSun", Font.PLAIN, 20));
		WinPoint.setBounds(10, 498, 180, 27);
		add(WinPoint);
		
		points = new JLabel("0");
		points.setFont(new Font("SimSun", Font.PLAIN, 15));
		points.setForeground(Color.RED);
		points.setBounds(29, 535, 54, 15);
		add(points);
		
		m_player = new JLabel("Player");
		playerNum = new JLabel("1");
		m_player.setFont(new Font("SimSun", Font.PLAIN, 25));
		playerNum.setForeground(Color.red);
		m_player.setForeground(Color.red);
		playerNum.setFont(new Font("SimSun", Font.PLAIN, 25));
		m_player.setBounds(10, 10, 89, 41);
		playerNum.setBounds(109, 10, 54, 41);
		add(m_player);
		add(playerNum);
	}
	
	public void setPlayer(Player player){
		currentPlayer = player;
		playerNum.setText(Integer.toString(player.m_uid+1));
		int[] resources = new int[5];
		for (int i=0;i<5;i++){
			resources[i] = player.getResources(i);
		}
		
		int[] cards = player.getCards();
		switch(player.m_uid){
			case 0:
				playerNum.setForeground(Color.red);
				m_player.setForeground(Color.red);
				break;
			case 1:
				playerNum.setForeground(Color.blue);
				m_player.setForeground(Color.blue);
				break;
			case 2:
				playerNum.setForeground(Color.green);
				m_player.setForeground(Color.green);
				break;
			case 3:
				playerNum.setForeground(Color.orange);
				m_player.setForeground(Color.orange);
				break;
		}
		for(int i=0;i<5;i++){
			resource_table.getModel().setValueAt(Integer.toString(resources[i]), i, 1);
		}
		
		for(int i=0;i<4;i++){
			card_table.getModel().setValueAt(Integer.toString(cards[i]), i, 1);
		}
		
		points.setText(Integer.toString(player.m_point));
		
	}
	
}
