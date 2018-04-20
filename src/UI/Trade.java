package UI;

import java.util.ArrayList;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Trade extends JDialog{
	
	ArrayList<Player> m_player = new ArrayList<Player>();
	int m_current;
	private JTextField pay_value;
	private JTextField get_value;
	String[] m_resources = {"Brick","Ore","Wool","Grain","Lumber"};
	JComboBox name;
	JComboBox pay_resources = new JComboBox(m_resources);
	JComboBox get_resources = new JComboBox(m_resources);
	
	
	public Trade(ArrayList<Player> players,int current){
		setTitle("Trading");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(700, 700, 450, 250);
		setResizable(false);
		m_current = current;
		String[] name_list=new String[players.size()-1];
		int i =0;
		for (Player index:players){
			if(index.m_uid !=current){
				name_list[i] = index.m_name;
				i++;
			}
		}
		name = new JComboBox(name_list);
		JPanel resource_panel = new JPanel();		
		setContentPane(resource_panel);
		resource_panel.setLayout(null);
		
		
		pay_resources.setBounds(26, 109, 96, 33);
		resource_panel.add(pay_resources);
		
		get_resources.setBounds(261, 109, 96, 33);
		resource_panel.add(get_resources);
		
		
		name.setBounds(309, 10, 124, 33);
		resource_panel.add(name);
		
		JButton request = new JButton("Request");
		request.setFont(new Font("SimSun", Font.PLAIN, 15));
		request.setBounds(170, 168, 93, 43);
		request.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(name.getSelectedItem() != null){
						if(pay_resources.getSelectedItem() != null && get_resources.getSelectedItem() !=null)
						{
							try{
								Integer.parseInt(pay_value.getText());
								Integer.parseInt(get_value.getText());
								  // is an integer!
								if (checkTrade(name.getSelectedIndex(),pay_resources.getSelectedIndex(),Integer.parseInt(pay_value.getText()),get_resources.getSelectedIndex(),Integer.parseInt(get_value.getText()))){
									JOptionPane.showMessageDialog(null, "Trade Success");
									dispose();
								}
								else{
									JOptionPane.showMessageDialog(null, "Invaild Values");
								}
							} catch (NumberFormatException e1) {
								JOptionPane.showMessageDialog(null, "Please input the value");
									// not an integer!
							}
						}
						else{
							JOptionPane.showMessageDialog(null, "Please Select The Resources");
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Please Select The Player");
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				};
			}
		});
		resource_panel.add(request);
		
		pay_value = new JTextField();
		pay_value.setFont(new Font("SimSun", Font.PLAIN, 15));
		pay_value.setBounds(132, 109, 66, 33);
		resource_panel.add(pay_value);
		pay_value.setColumns(10);
		
		get_value = new JTextField();
		get_value.setFont(new Font("SimSun", Font.PLAIN, 15));
		get_value.setColumns(10);
		get_value.setBounds(367, 109, 66, 33);
		resource_panel.add(get_value);
		
		
		
		JLabel lblNewLabel = new JLabel("Please select the user to trade with");
		lblNewLabel.setFont(new Font("SimSun", Font.PLAIN, 15));
		lblNewLabel.setBounds(10, 10, 408, 33);
		resource_panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("You Pay");
		lblNewLabel_1.setFont(new Font("SimSun", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(26, 53, 66, 21);
		resource_panel.add(lblNewLabel_1);
		
		JLabel lblGet = new JLabel("Get");
		lblGet.setFont(new Font("SimSun", Font.PLAIN, 15));
		lblGet.setBounds(261, 53, 66, 21);
		resource_panel.add(lblGet);
		
		JLabel lblType = new JLabel("Type");
		lblType.setBounds(26, 84, 54, 15);
		resource_panel.add(lblType);
		
		JLabel label = new JLabel("Type");
		label.setBounds(261, 78, 54, 15);
		resource_panel.add(label);
		
		JLabel lblAmount = new JLabel("Amount");
		lblAmount.setBounds(132, 84, 54, 15);
		resource_panel.add(lblAmount);
		
		JLabel label_1 = new JLabel("Amount");
		label_1.setBounds(367, 78, 54, 15);
		resource_panel.add(label_1);
		m_player = players;
		
	}
	
	/**
	 * 
	 * @param TradePlayer
	 * @param PayResources
	 * @param PayValue
	 * @param GetResources
	 * @param GetValue
	 * @return
	 */
	public boolean checkTrade(int tp,int pr,int pv,int gr, int gv){
		if(m_player.get(m_current).getResources(pr)<pv || m_player.get(tp).getResources(gr)<gv){
			return false;
		}
		else{
			int response = JOptionPane.showConfirmDialog(null, "Please let "+m_player.get(tp).m_name+" control and Press YES");
			if (response == JOptionPane.YES_OPTION){
				String text = m_player.get(m_current).m_name + " want to Trade " + pv + m_resources[pr] + " for " + gv + m_resources[gr] + " Please type in CONFIRM to confirm the trade";
				if (JOptionPane.showInputDialog(text).equals("CONFIRM")){
					m_player.get(m_current).takeResources(pr, pv);
					m_player.get(tp).takeResources(gr, gv);
					m_player.get(m_current).addResources(gr, gv);
					m_player.get(tp).addResources(pr, pv);
					return true;
				}
				else{
					return false;
				}
			}
		}
		return false;
	}
	
	public ArrayList<Player> updatePlayerList(){
		return m_player;
	}
}
