package UI;

import java.util.HashSet;
import java.util.List;

public class Road{
	int[] m_location;
	int[] next_road;
	int owner;
	public Road(int[] location,int user){
		m_location = location;
		owner = user;
	}
	
	public int[] getLocation(){
		if(m_location[1] ==0){
			int match[] = new int[2];
			match[0] = getTop(m_location[0]);
			match[1] = 3;
			return match;
		}
		else if(m_location[1] ==1){
			int match[] = new int[2];
			match[0] = getTopRight(m_location[0]);
			match[1] = 4;
			return match;
		}
		else if(m_location[1] ==2){
			int match[] = new int[2];
			match[0] = getBotRight(m_location[0]);
			match[1] = 5;
			return match;
		}
		else if(m_location[1] ==3){
			int match[] = new int[2];
			match[0] = getBot(m_location[0]);
			match[1] = 0;
			return match;
		}
		else if(m_location[1] ==4){
			int match[] = new int[2];
			match[0] = getBotLeft(m_location[0]);
			match[1] = 1;
			return match;
		}
		else{
			int match[] = new int[2];
			match[0] = getTopLeft(m_location[0]);
			match[1] = 2;
			return match;
		}
	}
	
	/**
	 * helper function to get specific neighbor block
	 * @param n
	 * @return
	 */
	protected int getTop(int n){
		HashSet<Integer> topest = new HashSet<Integer>();
		topest.add(0);
		topest.add(3);
		topest.add(7);
		topest.add(12);
		topest.add(16);
		if (topest.contains(n)){
			return -1;
		}
		else{
			return n-1;
		}
	}
	
	protected int getTopRight(int n){
		HashSet<Integer> topest = new HashSet<Integer>();
		topest.add(18);
		topest.add(17);
		topest.add(7);
		topest.add(12);
		topest.add(16);
		if (topest.contains(n)){
			return -1;
		}
		else if(n<3||n>12){
			return n+3;
		}
		else {
			return n+4;
		}
	}
	
	protected int getTopLeft(int n){
		HashSet<Integer> topest = new HashSet<Integer>();
		topest.add(0);
		topest.add(3);
		topest.add(7);
		topest.add(1);
		topest.add(2);
		if (topest.contains(n)){
			return -1;
		}
		else if(n<7||n>=16){
			return n-4;
		}
		else {
			return n-5;
		}
	}
	
	protected int getBot(int n){
		HashSet<Integer> botest = new HashSet<Integer>();
		botest.add(2);
		botest.add(6);
		botest.add(11);
		botest.add(15);
		botest.add(18);
		if (botest.contains(n)){
			return -1;
		}
		else{
			return n+1;
		}
	}
	
	protected int getBotRight(int n){
		HashSet<Integer> botest = new HashSet<Integer>();
		botest.add(18);
		botest.add(17);
		botest.add(11);
		botest.add(15);
		botest.add(16);
		if (botest.contains(n)){
			return -1;
		}
		else if(n<3||n>12){
			return n+4;
		}
		else {
			return n+5;
		}
	}
	
	protected int getBotLeft(int n){
		HashSet<Integer> botest = new HashSet<Integer>();
		botest.add(0);
		botest.add(6);
		botest.add(11);
		botest.add(1);
		botest.add(2);
		if (botest.contains(n)){
			return -1;
		}
		else if(n<7||n>=16){
			return n-3;
		}
		else {
			return n-4;
		}
	}
	
	public void connectRoad(int[] location){
		next_road = location;
	}
	
}
