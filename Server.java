package oop_1;

import java.awt.HeadlessException;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
//import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Enumeration;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Server {
	   private ServerSocket server; // server socket
	   private Socket connection; // connection to client            Socket��client sockets
	   private int count=0;	   
	   public Server() throws IOException {
		   runSever();
	   }
	   
	   public void runSever() throws IOException {
		   server = new ServerSocket( 13131, 100 ); // create ServerSocket
		   while ( true ) 
		   {
			   System.out.println("Wating for connection\n");
			   connection = server.accept();
			   count++;
			   new CreateServerThread(connection,"Thread"+count);
		   }
	   }
	public static void main(String[] args) {
		DataProcessing.disconnectFromDB();
		try {
			Server sever=new Server();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

class CreateServerThread extends Thread{
	private String uploadpath="D:\\Build\\JavaTest\\upload\\";
	private Socket client;
	private ObjectInputStream input;
	private ObjectOutputStream output;
	private String Thread;
	public CreateServerThread(Socket c,String s) throws IOException{
	    this.client=c;
	    Thread=s;
	    input=new ObjectInputStream(client.getInputStream());
	    output=new ObjectOutputStream(client.getOutputStream());
	    System.out.println( Thread+":" + Thread + " received from: " + client.getInetAddress().getHostName() );
	    System.out.println( Thread+":" + Thread + "Got I/O streams\n");
	    start();
	}
	
	public void run() {
				String message=null;     
			      do // process messages sent from client ѭ��չʾ�����գ��ͻ��˵���Ϣ��
			      { 
			         try // read message and display it
			         {
			            message = ( String ) input.readObject(); // read new message input��ͻ���socket����
			            System.out.print("\n");
			            displayMessage( message ); // display message
			            
			           if(message.equals("CLIENT_LOGIN")) {//------------------------------------------��¼
			        	   String[] User=(String[])input.readObject();
			        	   if (DataProcessing.hadname(User[0])) {                           //----��֤�û���
			        		   sendMessage("YES");
								if (DataProcessing.searchUser(User[0], User[1])!=null) { 
									User u=DataProcessing.searchUser(User[0], User[1]);
									displayMessage(User[0]);
									sendMessage("SERVER_LOGIN");
									sendUser(u);						
								}
								else {
									sendMessage("LOGIN_FAILURE");
								}
			        	   }
			        	   else sendMessage("NULL");
			        	   
			           }else if(message.equals("CLIENT_USER_ADD")) {//------------------------------����û�
			        	   String[] User=(String[])input.readObject();
			        	   if(DataProcessing.hadname(User[0])) {
			        		   sendMessage("Name had exists");
			        	   }else {
			        		   sendMessage("Allow");
			        		   if(DataProcessing.insertUser(User[0], User[1],User[2])) {	        		   
				        		   sendMessage("SERVER_USER_ADD");
								}
								else sendMessage("ADD_USER_FAILURE");
			        	   }

			           }else if(message.equals("CLIENT_USER_MOD")) {//------------------------------�޸��û�
			        	   String[] User=(String[])input.readObject();
			        	   
			        	   if(DataProcessing.updateUser(User[0], User[1],User[2])) {	        		   
			        		   sendMessage("SERVER_USER_MOD");
							}
							else sendMessage("MOD_USER_FAILURE");
			        	   
			           }else if(message.equals("CLIENT_USER_DEL")){//-------------------------------ɾ���û�
			        	   String name=(String)input.readObject();
			        	   
			        	   if(DataProcessing.deleteUser(name)) {	        		   
			        		   sendMessage("SERVER_USER_DEL");
							}
							else sendMessage("DEL_USER_FAILURE");
			        	   
			           }else if(message.equals("CLIENT_DOC_UP")) {//--------------------------------�ϴ��ļ�
			        	   String[] fileData=(String[])input.readObject();//2.�����ļ���Ϣ
			        	   
			        	   if(DataProcessing.searchDoc(fileData[0]).getID()!=null) {
			        		   sendMessage("ID had exists");			        		   
			        	   }else {
			        		   sendMessage("Allow");
			        		   User u=DataProcessing.searchUser(fileData[1]);
				        	   u.changname(fileData[3]);//�ļ����޸�
				        	   
				        	   File f=new File(u.upload+u.Final);
				        	   BufferedOutputStream bos=new BufferedOutputStream(new FileOutputStream(f));
				        	   byte[] file=(byte[])input.readObject();//3.�����ļ�����
				        	   bos.write(file);//�ļ�����д��f��·����
				        	   bos.close();
				        	   
				        	   Timestamp timestamp= new Timestamp(System.currentTimeMillis());	        	  
				        	   if(DataProcessing.insertDoc(fileData[0],fileData[1],timestamp,fileData[2],u.Final)) {//4.�������ݿ⣬������Ϣ���ͻ���
				        		   sendMessage("SERVER_DOC_UP");	
				        		   sendFN(u.Final);//�����޸ĺ���ļ������ͻ���
				        	   }else sendMessage("SERVER_DOC_UP_FAILURE");
			        	   }
			        	   
			        	  
			        	   
			           }else if(message.equals("CLIENT_DOC_DOWN")) {//------------------------------�����ļ�
			        	    String ID=(String) input.readObject();
			        	    
			        	    if(DataProcessing.searchDoc(ID)!=null) {
			        	    	
			        	    	sendMessage("SERVER_DOC_DOWM");
			        	    	
			        	    	Doc dgz=DataProcessing.searchDoc(ID);
								String filename=dgz.getFilename();
								File f1=new File(uploadpath+filename);
								BufferedInputStream bis=new BufferedInputStream(new FileInputStream(f1));
								int count=bis.available();
								byte[] file=new byte[count];
								bis.read(file);
								sendS(file);
								bis.close();
			        	    }else sendMessage("SERVER_DOC_DOWN_FAILURE");
			        	    
							
			           }else if(message.equals("CLIENT_SELF_MOD")) {//------------------------------�޸ı�������
			        	   
			        	   String[] User=(String[])input.readObject();
			        	   System.out.println(User[0]+User[1]+User[2]);
			        	   if(DataProcessing.updateUser(User[0],User[1],User[2])) {
			        		   sendMessage("SERVER_SELF_MOD");	        		   
			        	   }
			        	   else {
			        		   sendMessage("SELF_MOD_FAILURE");
			        	   }
			           }else if(message.equals("open_userframe")) {//----------------------------�û����	        	   
			        	   String[] columnNames = {"�û���","����","��ɫ"};   //����
			       		   String[][] Value= {};
			       		   DefaultTableModel T1=new DefaultTableModel(Value,columnNames);
			        	   for(Enumeration<User> e  = DataProcessing.getAllUser();e.hasMoreElements();) {//-------------��table�����û���Ϣ
			       			User dgz=e.nextElement();
			       			String s1=dgz.getName();
			       			String s2=dgz.getPassword();
			       			String s3=dgz.getRole();
			       			String[] ss= {s1,s2,s3};	       	
			       			T1.insertRow(0, ss);
			                }
			        	   sendTM(T1);        	   
			           }else if(message.equals("open_docframe")) {//-----------------------------�ļ����
			        	   String[] columnNames = {"������","������","ʱ��","�ļ���","����"};   //����
			        		String[][] Value= {};
			        		DefaultTableModel T1=new DefaultTableModel(Value,columnNames);
			        		for(Enumeration<Doc> e  = DataProcessing.getAllDocs();e.hasMoreElements();) {//-------------��table�����û���Ϣ
			        			Doc dgz=e.nextElement();
			        			String s1=dgz.getID();
			        			String s2=dgz.getCreator();
			        			String s3=dgz.getTimestamp().toString();
			        			String s4=dgz.getFilename();
			        			String s5=dgz.getDescription();
			        			String[] ss= {s1,s2,s3,s4,s5};
			        			T1.insertRow(0, ss);
			        		}
			        		sendTM(T1);
			           }
			         } // end try
			         catch ( ClassNotFoundException | IOException | HeadlessException | SQLException classNotFoundException ) 
			         {
			            displayMessage( "\nUnknown object type received" );
			         } // end catch

			      } while ( !message.equals( "TERMINATE" ) );
			      closeConnection();//�ر�����socket
	}
	private void sendUser(User u) {//�����û�ʵ��
		try {
			output.writeObject(u);
			output.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void sendS(byte[] file) {//�����ļ�����
		try {
			output.writeObject(file);
			output.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void sendTM(DefaultTableModel t) {//���ͱ��ģ��
		try {
			output.writeObject(t);
			output.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void sendFN(String FN) {
		try {
			output.writeObject(FN);
			output.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void sendMessage(String message) {
		try // send object to client
	      {
			output.writeObject( message );//output���ͻ��ˣ�����Ϊ��server+�ı���������ַ����������ٿͻ���չʾ
	         output.flush(); // flush output to client ȷ�����ݲ���ʧ��ȫд��output����
	         displayMessage( "SERVER>>> " + message );//Ȼ����server�ͻ�������չʾһ��
	      } // end try
	      catch ( IOException ioException ) 
	      {
	         System.out.println( "\nError writing object" );
	      } // end catch
	}

	private void displayMessage(String message) {
		System.out.print(Thread+":  ");
		System.out.println(message);
	}

	private void closeConnection() {
		displayMessage( "\nTerminating connection\n" );
		try 
	      {
	         output.close(); 
	         input.close(); 
	         client.close(); 
	      } 
	      catch ( IOException ioException ) 
	      {
	         ioException.printStackTrace();
	      } 
	}
}