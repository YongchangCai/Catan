package UI;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class Map extends JLayeredPane {
	
	final static int[] NUMBERLIST = {2,3,4,5,6,7,8,9,10,11,12,3,4,5,6,8,9,10,11};
	final static int[] TYPELIST =   {0,1,2,3,4,0,1,2,3 ,4 ,0 ,1,2,3,4,2,3,4};
	/**final static int[][] MAPDICT = {{-4,2},{-4,0},{-4,-2},
	                                {-2,3},{-2,1},{-2,-1},{-2,-3},
	                                {0,4},{0,2},{0,0},{0,-2},{0,-4},
	                                {2,3},{2,1},{2,-1},{2,-3},
	                                {4,2},{4,0},{4,-2}};
*/
	final static int[][] CENTER = {{98,185},{96,305},{99,422},{199,127},{203,246},{202,363},{304,69},{305,186},{305,304},{303,540},{409,126},{407,244},{408,363},{405,483},{506,188},{508,305},{510,425}};
	
	Player[] m_player_list = new Player[4];
	static final int HEIGHT = 65;
	int [][][]actual = null;
	int[][] resourcesNum = {{9},{6,18},{1,17},{3,7},{4,15},{13},{2,16},{0,10},{11,14},{5,8},{12}};
	int[] typeNum = {0,2,2,3,3,3,3,2,1,0,4,2,4,5,0,1,4,1,4};
	Color[] m_color = {Color.RED,Color.blue,Color.green,Color.orange};
	ArrayList<Integer>[] resourcesPlayer = new ArrayList[19];
	
	Road[][] m_road = new Road[19][6];
	Town[][] m_points = new Town[19][6];
	
	public Map(){
		//generateMap();
		
		ImageIcon m_map = new ImageIcon("image/Map.PNG");
		ImageLabel label = new ImageLabel();
		label.setBounds(5, 5, 600, 600);
		label.setIcon(m_map);
		add(label,new Integer(0));
		
		addMouseListener(new MouseAdapter() { 
	          public void mouseClicked(MouseEvent me) {
	        	 System.out.print(",{"+me.getX()+",");
	        	 System.out.print(me.getY()+"}");
	          }
		});
		validate();
	}
	
	/**
	 * List of resources that player could get
	 * @param num
	 * @return increased player resources
	 */
	public int[][] Start(int num){
		int[][] returnValue = new int[4][5];
		int[] selected = resourcesNum[num-2];
		
		return returnValue;
	}
	
	
	public void playerBuild(int user,int type){
		addMouseListener(new MouseAdapter() { 
	          public void mouseClicked(MouseEvent me) {
	        	 int[] location = {me.getX(),me.getY()};
	        	 	build(type, location,user);
	        		removeMouseListener(this);
	        	 }
		});
	}
	
	public void firstPlayerBuild(int user){
		addMouseListener(new MouseAdapter() { 
	          public void mouseClicked(MouseEvent me) {
	        	 int[] location = {me.getX(),me.getY()};
	        	 firstBuild(location,user);
	        	 removeMouseListener(this);
	          }
		});
	}
	
	private void firstBuild(int[] location,int user){
		 ImageLabel label = new ImageLabel();
    	 System.out.println("test");
    	 int[] dir = findClosest(location);
    	 BufferedImage buf_img;
    	 try {
		 buf_img = ImageIO.read(new File("image/town.png")); 
		 label.setIcon((new ImageIcon(dye(buf_img,m_color[user]))));
		 label.revalidate();
		 label.repaint();
		 Town temp =new Town(dir, user, 1);
		 System.out.println(temp.m_location[0]);
			System.out.println(temp.m_location[1]);
		int[][] loc = temp.getAllLocation();
		for(int i=0;i<loc.length;i++){
			System.out.println(loc[i][0]);
			System.out.println(loc[i][1]);
			m_points[loc[i][0]][loc[i][1]] = temp;
		}
    	 label.setBounds(dir[2]-25,dir[3]-25, 50, 50);
    	 add(label,new Integer(1));
    	 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	private boolean checkBuild(int[] location,int user){
		int hex= location[0];
		int point= location[1];
		if(m_points[hex][point]!=null||m_road[hex][point]==null){
			System.out.println(1);
			return false;
		}
		if(m_road[hex][point].owner==user){
			int length = m_points[hex][point].getLeftRoad().next_road.length;
			if (length > 1){
				return true;
			}
		}
		if(m_road[hex][point].owner==user){
			int length = m_points[hex][point].getRightRoad().next_road.length;
			if (length > 1){
				return true;
			}
		}
		if(m_road[hex][point].owner==user){
			int length = m_points[hex][point].getOutRoad().next_road.length;
			if (length > 1){
				return true;
			}
		}
		return false;
	}
	
	private boolean checkRoad(int[] road_location,int user){
		int hex= road_location[0];
		int road= road_location[1];
		if (m_road[hex][road]!=null){
			return false;
		}
		if (m_points[hex][(road-1)].owner==user||m_points[hex][(road+1)].owner==user){
			return true;
		}
		return false;
	}
	
	/**
	 * Get XY location of the mouse click to build the building
	 * @param type
	 * @param location
	 * @param user
	 */
	private void build(int type,int[] location,int user){
		ImageLabel label = new ImageLabel();
		if(type==0){
			int[][] dir = findCloestRoad(location);
			int[] road_location = {dir[0][0],dir[0][1]};
			label = new ImageLabel() {
			    protected void paintComponent(Graphics g) {
			        super.paintComponent(g);
			        g.drawLine(dir[0][2],dir[0][3],dir[1][2],dir[1][3]);
			        g.dispose();
			    };
			};
			add(label,new Integer(1));
		}
			
		else{
			int[] dir = findClosest(location);
			
			
			if (type == 1){if(checkBuild(dir,user)){
				try {
					System.out.println(dir[0]);
					BufferedImage buf_img = ImageIO.read(new File("image/town.png"));
					label.setIcon((new ImageIcon(dye(buf_img,m_color[user]))));
					label.revalidate();
					label.repaint();
					Town temp =new Town(dir, user, 1);
					int[][] loc = temp.getAllLocation();
					for(int i=0;i<loc.length;i++){
						m_points[loc[i][0]][loc[i][1]] = temp;
					}
			    } catch (IOException ex) {
			        Logger.getLogger(Map.class.getName()).log(Level.SEVERE, null, ex);
			    }}
			}
			else{
				try {
					if(checkCity(dir,user)){
					m_points[dir[0]][dir[1]]=new Town(dir, user, 2);
					BufferedImage buf_img = ImageIO.read(new File("image/city.png"));
					label.setIcon((new ImageIcon(dye(buf_img,m_color[user]))));
					label.revalidate();
					label.repaint();
					Town temp =new Town(dir, user, 2);
					int[][] loc = temp.getAllLocation();
					for(int i=0;i<loc.length;i++){
						m_points[loc[i][0]][loc[i][1]] = temp;
					}
					}
			    } catch (IOException ex) {
			        Logger.getLogger(Map.class.getName()).log(Level.SEVERE, null, ex);
			    }
				
			}
			label.setBounds(dir[2]-25,dir[3]-25, 50, 50);
			
			add(label,new Integer(1));
			
		}
	}
	
	private boolean checkCity(int[] dir, int user){
		int hex= dir[0];
		int point= dir[1];
		if(m_points[hex][point]!=null&&m_points[hex][point].owner==user){
			return true;
		}
		return false;
	}
	
	private static BufferedImage dye(BufferedImage image, Color color)
    {
        int w = image.getWidth();
        int h = image.getHeight();
        BufferedImage dyed = new BufferedImage(w,h,BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = dyed.createGraphics();
        g.drawImage(image, 0,0, null);
        g.setComposite(AlphaComposite.SrcAtop);
        g.setColor(color);
        g.fillRect(0,0,w,h);
        g.dispose();
        return dyed;
    }
	
	/**
	 * To generate a Random map of catan
	 */
	private void generateMap(){
		//TODO: in the future to be able have a function generated random maps
	}
	
	private int[][] getHexPoint(int x,int y){
		int[][] value = new int[6][2];
		value[0][0] = x - HEIGHT/2;
		value[0][1] = (int) (y - (HEIGHT/2)*Math.sqrt(3));
		value[1][0] = x + HEIGHT/2;
		value[1][1] = (int) (y - (HEIGHT/2)*Math.sqrt(3));
		value[2][0] = x + HEIGHT;
		value[2][1] = y;
		value[3][0] = x + HEIGHT/2;
		value[3][1] = (int) (y + (HEIGHT/2)*Math.sqrt(3));
		value[4][0] = x - HEIGHT/2;
		value[4][1] = (int) (y + (HEIGHT/2)*Math.sqrt(3));
		value[5][0] = x - HEIGHT;
		value[5][1] = y;
		
		return value;
	}
	
	
	
	/**
	 * 
	 * @param location
	 * @return Array {Center location,index,X,Y}
	 */
	private int[] findClosest(int[] location){
		int difference = Math.abs(CENTER[0][0]-location[0])+Math.abs(CENTER[0][1]-location[1]);
		int loc = 0;
		for(int i=1;i<CENTER.length;i++){
			int temp_diff = Math.abs(CENTER[i][0]-location[0])+Math.abs(CENTER[i][1]-location[1]);
			if (temp_diff<difference){
				loc = i;
				difference = temp_diff;
			}
		}
		int[][] points = getHexPoint(CENTER[loc][0],CENTER[loc][1]);
		
		int distance = Math.abs(points[0][0]-location[0])+Math.abs(points[0][1]-location[1]);
		int index = 0;
		for (int j=1;j<6;j++){
			int temp_dist = Math.abs(points[j][0]-location[0])+Math.abs(points[j][1]-location[1]);
			if (temp_dist<difference){
				index = j;
				distance = temp_dist;
			}
		}
		int[] value = {loc,index,points[index][0],points[index][1]};
		return value;
	}
	
	private int[][] findCloestRoad(int[] location){
		int[][] dir = new int[2][4];
		int[] a_point = findClosest(location);
		int[] temp_point = new int[2];
		temp_point[0] = location[0]-a_point[2]+location[0];
		temp_point[1] = location[1]-a_point[3]+location[1];
		
		int[] b_point = findClosest(temp_point);
		dir[0] = a_point;
		dir[1] = b_point;
		System.out.print(a_point[2]+",");
		System.out.print(a_point[3]+",");
		System.out.print(b_point[2]+",");
		System.out.print(b_point[3]);
		return dir;
	}
	
	public ArrayList<Town> getResouces(int value){
		int[] hex = resourcesNum[value-2];
		ArrayList<Town> towns = new ArrayList<Town>();
		for (int i=0;i<hex.length;i++){
			for(int j=0;j<6;j++){
				if (m_points[hex[i]][j]!=null){
					towns.add(m_points[hex[i]][j]);
				}
			}
		}
		return towns;
	}
}
