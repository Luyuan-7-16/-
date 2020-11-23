package oop_1;

import java.net.*;
import java.sql.Timestamp;
import java.util.Scanner;

import javax.swing.table.DefaultTableModel;

import java.io.*;

public class Client {
	private Socket client;
	private ObjectOutputStream output; 
	private ObjectInputStream input;
	private String message = ""; // message from server
	private String chatServer; // host server for this application
	
	public Client(String host) {
		chatServer = host;
		runClient();
	}
	
	public void runClient() 
	   {
	      try // connect to server, get streams, process connection
	      {
	         connectToServer(); // create a Socket to make connection
	         getStreams(); // get the input and output streams
	         //processConnection(); // process connection
	      } // end try
	      catch ( EOFException eofException ) 
	      {
	         displayMessage( "\nClient terminated connection" );
	      } // end catch
	      catch ( IOException ioException ) 
	      {
	         ioException.printStackTrace();
	      } // end catch
	      finally 
	      {
	         //closeConnection(); // close connection
	      } // end finally
	   } // end method runClient
	
	
	public void connectToServer() throws UnknownHostException, IOException {
		System.out.println("Attempting connection\n");
		client = new Socket(InetAddress.getByName( chatServer ), 13131);
		System.out.println("Connected to: " + client.getInetAddress().getHostName());
	}
	
	public void getStreams() throws IOException {
		output=new ObjectOutputStream(client.getOutputStream());
		output.flush();
		input=new ObjectInputStream(client.getInputStream());
	}
	
	public String processConnection() throws IOException {//���շ������˷�������Ϣ
		
		try {
			message = ( String ) input.readObject();
			System.out.println(message);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return message;
		
	}
	
	public User receiveUser() throws ClassNotFoundException, IOException {//�����û�ʵ��
		User user=(User)input.readObject();
		return user;
	}
	
	public DefaultTableModel receiveT() throws ClassNotFoundException, IOException {//���ձ��ģ��
		DefaultTableModel t=(DefaultTableModel)input.readObject();
		return t;
	}
	
	public byte[] receiveS() throws ClassNotFoundException, IOException {//�����ļ�
		byte[] file=(byte[])input.readObject();
		return file;
	}
	
	public void sendMessage( String message )//���Ͳ�������
	   {
	      try 
	      {
	         //output.writeObject( "CLIENT>>> " + message );
	    	 output.writeObject(message);//output���ͻ��ˣ�����Ϊ��server+�ı���������ַ����������ٿͻ���չʾ
	         output.flush(); // flush data to output
	         System.out.println( "\nCLIENT>>> " + message );
	      } 
	      catch ( IOException ioException )
	      {
	    	  System.out.println( "\nError writing object" );
	      } 
	   } 
	
	public void sendsingleData(String d) {//ɾ���û���ɾ���ļ��������ļ�
		try {
			output.writeObject(d);
			output.flush();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendUserData(String name,String password) {//��¼����
		String[] buf= {name,password};
		try {
			output.writeObject(buf);
			output.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendUserData(String name,String password,String role) {//����û����޸��û����û��޸�����
		String[] buf= {name,password,role};
		try {
			output.writeObject(buf);
			output.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendByte(byte[] file) {//�����ļ���Ϣ
		try {
			output.writeObject(file);
			output.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendDocData(String ID, String creator, String description, String filename) {//�ϴ��ļ�
		String[] buf= {ID,creator,description,filename};
		try {
			output.writeObject(buf);
			output.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void displayMessage(String message) {
		System.out.println(message);
	}
	
	public void closeConnection()  {
		System.out.println("\nClosing connection" );
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
