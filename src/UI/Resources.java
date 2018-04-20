package UI;

import java.util.*;
import java.lang.*;

import javax.swing.JPanel;

import jdk.internal.util.xml.impl.Pair;

public class Resources{
	int m_type;
	int m_value;
	int[][] m_corner = new int[6][2];
	int[] m_side= new int[6];
	
	public Resources(int type,int value){
		m_type = type;
		m_value = value;
		Arrays.fill(m_corner,null);
		Arrays.fill(m_side,0);
	}
	
	/**
	 * to set building to corners
	 * @param num
	 * @param type: town = 1; city = 2;
	 * @param user
	 */
	public void setCorner(int num,int type,int user){
		m_corner[num][0] = user;
		m_corner[num][1] = type;
	}
	
	public void setSide(int num,int user){
		m_side[num] = user;
	}
	
	public int[] getResource(){
		int[] value = {0,0,0,0};
		for (int i=0;i<6;i++){
			if(m_corner[i]!=null){
				value[m_corner[i][0]]+= m_corner[i][1];
			}
		}
		return value;
		
	}
}
