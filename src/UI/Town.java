package UI;

public class Town extends Road{
	
	int m_type;
	public Town(int[] location,int user,int type){
		super(location,user);
		type = m_type;
	}
	
	Road m_left;
	Road m_right;
	Road m_out;
	
	/**
	 * return all location for the points;
	 */
	public int[][] getAllLocation(){
		int[][] all_location = new int[3][2];
		all_location[0] = m_location;
		if(m_location[1] ==0){
			int match_a[] = new int[2];
			match_a[0] = super.getTop(m_location[0]);
			match_a[1] = 4;
			all_location[1] = match_a;
			int match_b[] = new int[2];
			match_b[0] = super.getTopLeft(m_location[0]);
			match_b[1] = 2;
			all_location[2] = match_b;
			return all_location;
		}
		else if(m_location[1] ==1){
			int match_a[] = new int[2];
			match_a[0] = super.getTop(m_location[0]);
			match_a[1] = 3;
			all_location[1] = match_a;
			int match_b[] = new int[2];
			match_b[0] = super.getTopRight(m_location[0]);
			match_a[1] = 5;
			all_location[2] = match_b;
			return all_location;
		}
		else if(m_location[1] ==2){
			int match_a[] = new int[2];
			match_a[0] = super.getBotRight(m_location[0]);
			match_a[1] = 0;
			all_location[1] = match_a;
			int match_b[] = new int[2];
			match_b[0] = super.getTopRight(m_location[0]);
			match_a[1] = 4;
			all_location[2] = match_b;
			return all_location;
		}
		else if(m_location[1] ==3){
			int match_a[] = new int[2];
			match_a[0] = super.getBot(m_location[0]);
			match_a[1] = 1;
			all_location[1] = match_a;
			int match_b[] = new int[2];
			match_b[0] = super.getBotRight(m_location[0]);
			match_a[1] = 5;
			all_location[2] = match_b;
			return all_location;
		}
		else if(m_location[1] ==4){
			int match_a[] = new int[2];
			match_a[0] = super.getBot(m_location[0]);
			match_a[1] = 0;
			all_location[1] = match_a;
			int match_b[] = new int[2];
			match_b[0] = super.getBotLeft(m_location[0]);
			match_a[1] = 2;
			all_location[2] = match_b;
			return all_location;
		}
		else{
			int match_a[] = new int[2];
			match_a[0] = super.getBotLeft(m_location[0]);
			match_a[1] = 1;
			all_location[1] = match_a;
			int match_b[] = new int[2];
			match_b[0] = super.getTopLeft(m_location[0]);
			match_a[1] = 3;
			all_location[2] = match_b;
			return all_location;
		}
	}
	
	/**
	 * get the road that smaller than road number;
	 * @param left
	 */
	public void setLeftRoad(Road left){
		m_left = left;
	}
	
	/**
	 * get the road that smaller than road number;
	 * @param left
	 */
	public void setRightRoad(Road right){
		m_right = right;
	}
	
	public void setOutRoad(Road out){
		m_out = out;
	}
	
	public Road getLeftRoad(){
		return m_left;
	}
	
	public Road getRightRoad(){
		return m_right;
	}
	
	public Road getOutRoad(){
		return m_out;
	}
}
