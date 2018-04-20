package UI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Player {
	String m_name;
	int m_uid;
	int[] m_resources = new int[5];
	int[] m_cards = new int[4];
	int m_point;
	int m_armynum;
	int m_roadnum;
	ArrayList<Road> m_road;
	ArrayList<Town> m_town;
	
	public Player(int id,String name){
		m_name = name;
		m_uid = id;
		m_point = 0;
		m_armynum = 0;
		m_roadnum = 0;
		Arrays.fill(m_resources,10);
		Arrays.fill(m_cards, 0);
	}
	
	
	public void addResources(int type, int num){
		System.out.println(num);
		m_resources[type] += num;
	}
	
	public void takeResources(int type,int num){
		m_resources[type] -= num;
	}
	
	public void addCards(int type){
		m_cards[type]++;
	}
	
	/**
	 * cost resources from users: 
	 * road = 1brick,1lumber; town = 1brick,1lumber,1wool,1grain
	 * city = 3ore, 2grain; dvp = 1ore,1wool,1gramin
	 * @throws Exception 
	 */
	public void build(int type) throws Exception{
		if(checkResources(type)){
		switch(type){
		case 0:
			m_resources[0]-=1;
			m_resources[4]-=1;
			break;
		case 1:
			m_resources[0]-=1;
			m_resources[2]-=1;
			m_resources[3]-=1;
			m_resources[4]-=1;
			m_point++;
			break;
		case 2:
			m_resources[1]-=3;
			m_resources[3]-=2;
			m_point++;
			break;
		case 3:
			m_resources[1]-=1;
			m_resources[2]-=1;
			m_resources[3]-=1;
			break;
		default:
			throw new Exception("invalid type");
		}
		}
	}
	
	public int getResources(int num){
		return m_resources[num];
	}
	
	
	public int[] getCards(){
		return m_cards;
	}
	
	public void addPoint(int num){
		m_point+=num;
	}
	public boolean checkPoint(){
		if (m_point >= 10){
			return true;
		}
		return false;
	}
	
	public boolean checkResources(int type) throws Exception{
		switch(type){
		case 0:
			return m_resources[0]>=1&&m_resources[4]>=1;
		case 1:
			return m_resources[0]>=1&&m_resources[2]>=1&&m_resources[3]>=1&&m_resources[4]>=1;
		case 2:
			return m_resources[1]>=3&&m_resources[3]>=2;
		case 3:
			return m_resources[1]>=1&&m_resources[2]>=1&&m_resources[3]>=1;
		default:
			throw new Exception("invalid type");
		}
	}

}
