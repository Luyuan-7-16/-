package oop_1;

import java.util.Enumeration;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.swing.JFileChooser;

//import com.sun.media.sound.Toolkit;

import java.awt.Dimension;
import java.io.*;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

public class text {
	static String Final=null;
	static int j;
	public static void main(String args[]) throws IOException{
		/*File f=new File("D:\\Build\\JavaTest");
		System.out.println("f name   "+f.getName());
		System.out.println("is Exists   "+f.exists());	
		System.out.println("Creat PFile   "+f.mkdir());
		System.out.println("Creat File   "+f.createNewFile());
		System.out.println("is file   "+f.isFile());
		System.out.println("is D   "+f.isDirectory());
		System.out.println("f Lenght   "+f.length());
		System.out.println("f AP   "+f.getAbsolutePath());
		System.out.println("f P   "+f.getPath());
		System.out.println("f lasrChange   "+f.lastModified());*/
		
		
//	    1574662198695
		/*System.out.println();
		System.out.println();
		File f2=new File("D:\\Build\\JavaTest.txt");
		System.out.println(f2.getName());
		System.out.println(f2.exists());
		System.out.println(f2.mkdir());
		System.out.println(f2.createNewFile());*/
		
		//--------------------------------��upload�ļ��������ļ�---------------------------------------------
		/*byte buffer[] = new byte[1024];
		String upload="D:\\Build\\JavaTest\\upload\\";
		String download="D:\\Build\\JavaTest\\download\\";
		String filename=null;
		Scanner cin=new Scanner(System.in);
		System.out.print("�������ļ���:");
		filename=cin.nextLine();
		File f1=new File(upload+filename);//---------------------------------------f1���ļ�������ļ�
		File f2=new File(download+filename);//-------------------------------------f2�����ص������ļ�
		if(!f2.exists()) {
			f2.createNewFile();//--------------------------------------���f2�����������أ�����һ���ļ�������д�����ݣ�
		}
		BufferedInputStream bis=new BufferedInputStream(new FileInputStream(f1));
		BufferedOutputStream bos=new BufferedOutputStream(new FileOutputStream(f2));
		int c=0;
		while((c=bis.read(buffer))!=-1) {
			for(int i=0;i<c;i++) {
				bos.write(buffer[i]);
			}
		}
		bis.close();
		bos.close();*/
		//File f1=new File("D:\\Build\\JavaTest\\123.txt");
		//File f2=new File("D:\\Build\\JavaTest\\upload\\1223.txt");
		//File f2=new File("D:\\Build\\JavaTest\\"+f1.getName()+".txt");
        //System.out.println(f1.getName());
	    //f2.createNewFile();
        
	    
	    
	    //StringTokenizer fn=new StringTokenizer("Doc.java","(");
	    //int n=fn.countTokens();//--���Ե���next Token�Ĵ����������3���ָ�������4��
		//for(int i=0;fn.hasMoreTokens();i++) if(i==0) head=fn.nextToken("(");else fn.nextToken();
		
		
		//System.out.println(Final);
		
		//String[] ss= {"123.mp4","123(1).mp4","123(4).mp4"};
		/*for(int i=0;i<ss.length;i++) {
			if(Final.equals(ss[i])) {
				j++;
				Final=head+"("+j+")"+"."+tail;
			}
		}*/
		//updataname(head,tail,Final,j);
        
        /*String origion="Doc.java";
        String head=null,tail=null;
		//String Final=null;
		
		Doc dgz;
		for(Enumeration<Doc> e=DataProcessing.docs.elements();e.hasMoreElements();) {
			dgz=e.nextElement();
			if(origion.equals(dgz.getFilename())) {
				StringTokenizer fn2=new StringTokenizer(origion,"(.");
				int m=fn2.countTokens();//--���Ե���next Token�Ĵ����������3���ָ�������4��
				for(int i=0;fn2.hasMoreTokens();i++) if(i==0) head=fn2.nextToken("(.");else if(i==m-1) tail=fn2.nextToken(".");else fn2.nextToken(".");				
				Final=head+"."+tail;				
			    updataname(head,tail);
			}
			if(j==0) Final=origion;
		}
		System.out.println(Final+j);
	}
	public static void updataname(String head,String tail) {
		Doc dgz;
		for(Enumeration<Doc> e=DataProcessing.docs.elements();e.hasMoreElements();) {
			dgz=e.nextElement();
			if(Final.equals(dgz.getFilename())) {
				j++;
				Final=head+"("+j+")"+"."+tail;
				updataname(head,tail);
			}	
		}*/	
		//Toolkit kit = Toolkit.getDefaultToolkit(); // ���幤�߰�
		//Dimension screenSize = kit.getScreenSize(); // ��ȡ��Ļ�ĳߴ�
		//int screenWidth = screenSize.width/2; // ��ȡ��Ļ�Ŀ�
		//int screenHeight = screenSize.height/2; // ��ȡ��Ļ�ĸ�
		//Client client=new Client("127.0.0.1");
		//ObjectOutputStream o=new ObjectOutputStream(new OutputStream());
		//String[][] s= {{"1","3"},{"2","4","6"}};
		//String[] s0= {"1","2"};
		//String[] s1= {"3","4"};
		//String[][] q= {};
		//q[0]=s0;
		//q[1]=s1;
		//for(int i=0;i<q.length*q[0].length;i++) {
		//	System.out.println(q[i]);
		//}
		/*System.out.println(s.length);
		for(int i=0;i<s0.length;i++) {
		   System.out.println(s0[i]);
		}
		for(int i=0;i<s1.length;i++) {
			System.out.println(s1[i]);
		}*/
		//File f1=new File("D:\\Build\\test.txt");
		/*File f2=new File("D:\\Build\\test.txt");
		//BufferedInputStream bis=new BufferedInputStream(new FileInputStream(f1));
		if(!f2.exists()) {
			f2.createNewFile();
		}
		BufferedOutputStream bos=new BufferedOutputStream(new FileOutputStream(f2));
		//ObjectInputStream o=new ObjectInputStream(bis);
		ObjectOutputStream p=new ObjectOutputStream(bos);
		p.writeObject(bos);
*/

		/*JFileChooser j=new JFileChooser();
		j.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int i=j.showOpenDialog(null);
		if(i==JFileChooser.APPROVE_OPTION) {
			System.out.println(j.getSelectedFile().getPath());
			File f=new File(j.getSelectedFile().getPath());
			System.out.println(f.getName());
		}else if(i==JFileChooser.CANCEL_OPTION){
			
		}*/
		/*System.out.println(InetAddress.getLocalHost());*/
		String driverName="com.mysql.cj.jdbc.Driver";                                                       // �������ݿ�������
		String URL="jdbc:mysql://localhost:3306/document?serverTimezone=UTC&characterEncoding=utf-8";       // �������ݿ��URL
		String USER="root";                                                                                 // ���ݿ��û�
		String PASSWORD="123456";
	    String selectDocbyIDSql="select * from doc_info where ID=?";
	    String tempID=null;
		String tempc=null;
		Timestamp tempt=null;
		String tempd=null;
		String tempf=null;
		String ID="7";

		try {
			Connection con;      //-----����
			PreparedStatement ps;//-----sqlִ���õĶ���
			ResultSet rs;        //-----�����
		    Class.forName(driverName);		// �������ݿ�������
		    con=DriverManager.getConnection(URL,USER,PASSWORD);   // �������ݿ�����
			ps=con.prepareStatement(selectDocbyIDSql);
			ps.setString(1, ID);
		    rs=ps.executeQuery();
		    System.out.println("1"+tempID+":"+tempc+":"+tempt+":"+tempd+":"+tempf);
			while (rs.next()){
				tempID=rs.getString(1);
				tempc=rs.getString(2);
				tempt=rs.getTimestamp(3);
				tempd=rs.getString(4);
				tempf=rs.getString(5);
				System.out.println("1"+tempID+":"+tempc+":"+tempt+":"+tempd+":"+tempf);
			}			
			rs.close();
			ps.close();
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		Doc d=new Doc(tempID,tempc,tempt,tempd,tempf);
		if(d.getID()==null) {
			System.out.println("1");
		}
		

	}
}




