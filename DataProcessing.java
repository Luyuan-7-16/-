package oop_1;

import java.util.Enumeration;
import java.util.Hashtable;
import java.io.File;
import java.io.Serializable;
import java.sql.*;

public  class DataProcessing implements Serializable{

	private static boolean connectToDB=false;
	
	static Hashtable<String, User> users;//----------------------------�û��б�
	static Hashtable<String, Doc> docs;//------------------------------�ļ��б�
	static String upload="D:\\Build\\JavaTest\\upload\\";
	private static String driverName="com.mysql.cj.jdbc.Driver";                                                       // �������ݿ�������
	private static String URL="jdbc:mysql://localhost:3306/document?serverTimezone=UTC&characterEncoding=utf-8";       // �������ݿ��URL
	private static String USER="root";                                                                                 // ���ݿ��û�
	private static String PASSWORD="123456";
	//private static Connection con;      //-----����
	//private static PreparedStatement ps;//-----sqlִ���õĶ���
	//private static ResultSet rs;        //-----�����
	private static String selectUserSql="select * from user_info";
	private static String selectUserbynameSql="select * from user_info where username=?";
	private static String insertUserSql ="insert into user_info values (?,?,?)";
	private static String updateUserSql ="update user_info set role=?,password=? where username=?";
	private static String deleteUserSql ="delete from user_info where username=?";
	private static String selectDocSql="select * from doc_info";
	private static String selectDocbyIDSql="select * from doc_info where ID=?";
	private static String insertDocSql="insert into doc_info values (?,?,?,?,?)";
	//private static String deleteDocSql="delete from doc_info where ID=?";

	static {
		users = new Hashtable<String, User>();
		//users.put("jack", new Operator("jack","123","operator"));
		//users.put("rose", new Browser("rose","123","browser"));
		//users.put("kate", new Administrator("kate","123","administrator"));
		Init();
		
		//Timestamp timestamp = new Timestamp(System.currentTimeMillis()); 
		docs = new Hashtable<String,Doc>();
		//docs.put("0001",new Doc("0001","jack",timestamp,"Doc Source Java","Doc.java"));
				
	}
	
	public static  void Init(){
	}
	
	public static Doc searchDoc(String ID) throws SQLException { //-----------------------�����ݿ����水ID���ļ�
		String tempID=null;
		String tempc=null;
		Timestamp tempt=null;
		String tempd=null;
		String tempf=null;
		Doc temp=null;
		if(connectToDB) {
			try {
				Connection con;      //-----����
				PreparedStatement ps;//-----sqlִ���õĶ���
				ResultSet rs;        //-----�����
			    Class.forName(driverName);		// �������ݿ�������
			    con=DriverManager.getConnection(URL,USER,PASSWORD);   // �������ݿ�����
				ps=con.prepareStatement(selectDocbyIDSql);
				ps.setString(1, ID);
			    rs=ps.executeQuery();
				while (rs.next()){
					tempID=rs.getString(1);
					tempc=rs.getString(2);
					tempt=rs.getTimestamp(3);
					tempd=rs.getString(4);
					tempf=rs.getString(5);
				}			
				rs.close();
				ps.close();
				con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
			temp=new Doc(tempID,tempc,tempt,tempd,tempf);
			return temp;
		}
		else return null;
	}
	
	public static Enumeration<Doc> getAllDocs() throws SQLException{//-----------------------
		docs.clear();
		String tempID=null;
		String tempc=null;
		Timestamp tempt=null;
		String tempd=null;
		String tempf=null;
		if(connectToDB) {
			try {
				Connection con;
				PreparedStatement ps;
				ResultSet rs;
				Class.forName(driverName);
				con=DriverManager.getConnection(URL, USER, PASSWORD);
				ps=con.prepareStatement(selectDocSql);
				rs=ps.executeQuery();
				while(rs.next()) {
					tempID=rs.getString(1);
					tempc=rs.getString(2);
					tempt=rs.getTimestamp(3);
					tempd=rs.getString(4);
					tempf=rs.getString(5);
					docs.put(tempID, new Doc(tempID,tempc,tempt,tempd,tempf));
				}
			}catch(Exception e) {
				e.printStackTrace();
			}			
		}
		Enumeration<Doc> e  = docs.elements();
		return e;
	} 
	
	public static boolean insertDoc(String ID, String creator, Timestamp timestamp, String description, String filename) throws SQLException{ //�ڹ�ϣ����������ļ�
		if(connectToDB) {
			try {
				Connection con;
				PreparedStatement ps;
				Class.forName(driverName);
				con=DriverManager.getConnection(URL,USER,PASSWORD);
				ps=con.prepareStatement(insertDocSql);
				ps.setString(1, ID);
				ps.setString(2, creator);
				ps.setTimestamp(3, timestamp);
				ps.setString(4, description);
				ps.setString(5, filename);
				ps.executeUpdate();
				ps.close();
				con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}			
			return true;
		}else {
			return false;
		}
	} 
	
	public static boolean hadname(String name) {
		String tempn=null;
		if(connectToDB) {
			try {
				Connection con;      //-----����
				PreparedStatement ps;//-----sqlִ���õĶ���
				ResultSet rs;        //-----�����
			    Class.forName(driverName);		// �������ݿ�������
			    con=DriverManager.getConnection(URL,USER,PASSWORD);   // �������ݿ�����
				ps=con.prepareStatement(selectUserbynameSql);
				ps.setString(1, name);
			    rs=ps.executeQuery();
				while (rs.next()){
					tempn=rs.getString(1);//������ݿ�����������ˣ�tempn��Ϊnull
				}			
				rs.close();
				ps.close();
				con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
			if(tempn!=null) return true;
			else return false;
		}else return false;		
	}
	
	public static User searchUser(String name) throws SQLException{  //--------------------------------------�����������û�		
		String tempn=null;
		String tempp=null;
		String tempr=null;
		User temp=null;
		if(connectToDB) {
			try {
				Connection con;      //-----����
				PreparedStatement ps;//-----sqlִ���õĶ���
				ResultSet rs;        //-----�����
			    Class.forName(driverName);		// �������ݿ�������
			    con=DriverManager.getConnection(URL,USER,PASSWORD);   // �������ݿ�����
				ps=con.prepareStatement(selectUserbynameSql);
				ps.setString(1, name);
			    rs=ps.executeQuery();
				while (rs.next()){
					tempn=rs.getString(1);
					tempp=rs.getString(2);
					tempr=rs.getString(3);
				}			
				rs.close();
				ps.close();
				con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
			if(tempn==null) return null;
			if(tempr.equalsIgnoreCase("administrator"))
				temp=new Administrator(tempn,tempp,tempr);
			else if(tempr.equalsIgnoreCase("operator"))
				temp=new Operator(tempn,tempp,tempr);
			else if(tempr.equalsIgnoreCase("browser"))
				temp=new Browser(tempn,tempp,tempr);
			return temp;
		}
		else return null;
	}
	
	public static User searchUser(String name, String password) throws SQLException {  //--------------------------��֤�û���������
		String tempn=null;
		String tempp=null;
		String tempr=null;
		User temp=null;
		if(connectToDB) {
			try {
				Connection con;      //-----����
				PreparedStatement ps;//-----sqlִ���õĶ���
				ResultSet rs;        //-----�����
			    Class.forName(driverName);		// �������ݿ�������
			    con=DriverManager.getConnection(URL,USER,PASSWORD);   // �������ݿ�����
				ps=con.prepareStatement(selectUserbynameSql);
				ps.setString(1, name);
			    rs=ps.executeQuery();
				while (rs.next()){
					tempn=rs.getString(1);
					tempp=rs.getString(2);
					tempr=rs.getString(3);
				}			
				rs.close();
				ps.close();
				con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}			
		}
		if(tempn==null) return null;
		if(password.equals(tempp)) {
			if(tempr.equalsIgnoreCase("administrator"))
				temp=new Administrator(tempn,tempp,tempr);
			else if(tempr.equalsIgnoreCase("operator"))
				temp=new Operator(tempn,tempp,tempr);
			else if(tempr.equalsIgnoreCase("browser"))
				temp=new Browser(tempn,tempp,tempr);
			return temp;
		}else return null;
	}
	
	public static Enumeration<User> getAllUser() throws SQLException{         //-------------------------------�г��û��б�
		users.clear();
		String tempn=null;
		String tempp=null;
		String tempr=null;
		if(connectToDB) {
			try {
				Connection con;
				PreparedStatement ps;
				ResultSet rs;
				Class.forName(driverName);
				con=DriverManager.getConnection(URL, USER, PASSWORD);
				ps=con.prepareStatement(selectUserSql);
				rs=ps.executeQuery();
				while(rs.next()) {
					tempn=rs.getString(1);
					tempp=rs.getString(2);
					tempr=rs.getString(3);
					//System.out.println(rs.getString(1)+":"+rs.getString(2)+":"+rs.getString(3));
					if(tempr.equalsIgnoreCase("administrator"))
						users.put(tempn, new Administrator(tempn,tempp,tempr));
					else if(tempr.equalsIgnoreCase("operator"))
						users.put(tempn, new Operator(tempn,tempp,tempr));
					else if(tempr.equalsIgnoreCase("browser"))
						users.put(tempn, new Browser(tempn,tempp,tempr));
				}
			}catch(Exception e) {
				e.printStackTrace();
			}			
		}
		Enumeration<User> e  = users.elements();//����һ��Enumeration���͵Ķ�������ָ��users�ĵ�һ��Ԫ�أ��������ṩ�ķ������Ա���users�����ϣ��
		return e;
	}
	
	
	
	public static boolean updateUser(String name, String password, String role) throws SQLException{//-----------------�����û���Ϣ(��ɫ������)
		if(connectToDB) {
			try {
				Connection con;
				PreparedStatement ps;
				Class.forName(driverName);
				con=DriverManager.getConnection(URL,USER,PASSWORD);
				ps=con.prepareStatement(updateUserSql);
				ps.setString(1, role);
				ps.setString(2, password);
				ps.setString(3, name);
				ps.executeUpdate();
				ps.close();
				con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}			
			return true;
		}else {
			return false;
		}
	}
	
	public static boolean insertUser(String name, String password, String role) throws SQLException{//----------�����û�
		if(connectToDB) {
			try {
				Connection con;
				PreparedStatement ps;
				Class.forName(driverName);
				con=DriverManager.getConnection(URL,USER,PASSWORD);
				ps=con.prepareStatement(insertUserSql);
				ps.setString(1, name);
				ps.setString(2, password);
				ps.setString(3, role);
				ps.executeUpdate();
				ps.close();
				con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}			
			return true;
		}else {
			return false;
		}
	}
	
	public static boolean deleteUser(String name) throws SQLException{//---------------ɾ���û�
		if(connectToDB) {
			try {
				Connection con;
				PreparedStatement ps;
				Class.forName(driverName);
				con=DriverManager.getConnection(URL,USER,PASSWORD);
				ps=con.prepareStatement(deleteUserSql);
				ps.setString(1, name);
				ps.executeUpdate();
				ps.close();
				con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}			
			return true;
		}else {
			return false;
		}
	}	
	public static boolean deleteDoc(String ID) throws SQLException{//---------------ɾ���ļ�	
		if (docs.containsKey(ID)){
			Doc dgz=DataProcessing.searchDoc(ID);
			File f=new File(upload+dgz.getFilename());
			f.delete();
			docs.remove(ID);
			return true;
		}else
			return false;
		
	}
            
	public static void disconnectFromDB() {//----------------------------------------�Ƿ����ӵ����ݿ�
		try {
			Connection con;                                        //-----����
			PreparedStatement ps;                                  //-----sqlִ���õĶ���
			ResultSet rs;                                          //-----�����
			Class.forName("com.mysql.cj.jdbc.Driver");		       // �������ݿ�������
			con=DriverManager.getConnection(URL,USER,PASSWORD);    // �������ݿ�����
			con.close();
			connectToDB=true;
		}catch(ClassNotFoundException e ){
	    	System.out.println("������������");
	    	e.printStackTrace();
	    }catch(SQLException e){
	    	System.out.println("���ݿ����");
	    	e.printStackTrace();
	    }
   }           

	
	/*public static void main(String[] args) throws SQLException {		
		DataProcessing.disconnectFromDB();
		User dgz;
		for(Enumeration<User> e  = DataProcessing.getAllUser();e.hasMoreElements();) {
			dgz=e.nextElement();
			System.out.println(dgz.getName()+"\t"+dgz.getPassword()+"\t"+dgz.getRole());
		    }
		System.out.println("5");
		Doc DGZ;
		for(Enumeration<Doc> e  = DataProcessing.getAllDocs();e.hasMoreElements();) {
			DGZ=e.nextElement();
			System.out.println(DGZ.getID()+"\t"+DGZ.getCreator()+"\t"+DGZ.getTimestamp().toString());
		    }
	}*/
	
}
