package oop_1;
import java.sql.SQLException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.*;

public class Administrator extends User implements Serializable{
	
	String tip_role="�����߲˵�";
	String tip_exit="�˳�ϵͳ����лʹ��";
	String menu="************��ӭ����"+tip_role+"*************"
			+"\n \t 1.�޸��û�"+"\n \t 2.ɾ���û�"+"\n \t 3.�����û�"+"\n \t 4.�г��û�"
			+"\n \t 5.�����ļ�"+"\n \t 6.�ļ��б�"+"\n \t 7.�޸�(����)����"+"\n \t 8.�˳�"
			+"\n**********************************************";
	
	//String upload="D:\\Build\\JavaTest\\upload\\";
	String download="D:\\Build\\JavaTest\\download\\";
	
	String Name = null, Password = null, Role = null;
	String filename= null;//------------------------���ص��ļ�������
	String ID=null;//-------------------------------��������ID����
	int a;
	
	Administrator(String name,String password,String role){
		super(name,password,role);
	}
	
	public void showMenu() {
		System.out.println(menu);
	}
	
	public void Operate() throws SQLException,IOException, ClassNotFoundException {
		Scanner scan=new Scanner(System.in);
		for(;;) {
			this.showMenu();
			System.out.println("������ָ��");
			a=scan.nextInt();
			scan.nextLine();
			switch(a) {
			case 1:{
				System.out.print("����Ҫ�޸ĵ��û���:");
				Name=scan.nextLine();
				//User dgz=null;
				try {
				User dgz=DataProcessing.searchUser(Name);
				dgz.ForException(); //��Ӧ�Ƿ��д��û�
				System.out.println(" 1--���� \t 2--��ɫ");
				System.out.print("�޸���ʲô:");					
				a=scan.nextInt();
				scan.nextLine();
				
				switch(a) {
				case 1:{
					System.out.print("����������:");
					Password=scan.nextLine();
					dgz.setPassword(Password);
					System.out.print("55");
					if(DataProcessing.updateUser(dgz.getName(), dgz.getPassword(), dgz.getRole())) System.out.println("�޸ĳɹ�");
					else System.out.println("�޸�ʧ��");
					break;
				}
				case 2:{
					System.out.print("����ı��Ľ�ɫ:");
					Role=scan.nextLine();
					dgz.setRole(Role);
					if(DataProcessing.updateUser(dgz.getName(), dgz.getPassword(), dgz.getRole())) System.out.println("�޸ĳɹ�");
					else System.out.println("�޸�ʧ��");
					break;
				}
				default: {
					System.out.println("�������"); 
					break;
				}
				}
				this.xuliehua();
				//ObjectInputStream ois=new ObjectInputStream(new FileInputStream("D:\\Build\\JavaUsersSave.ser"));
				//ObjectInputStream ois2=new ObjectInputStream(new FileInputStream("D:\\Build\\JavaDocsSave.ser"));
				//DataProcessing.users=(Hashtable<String, User>)ois.readObject();
				//DataProcessing.docs=(Hashtable<String, Doc>)ois2.readObject();
				//ois.close();
				//throw new NullPointerException();
			}catch (NullPointerException e) {
			    System.out.println("�û��б��в����ڴ��û��� Admini ");
			}
			    break;
			}
			case 2:{
				System.out.print("����Ҫɾ�����û�������:");
				Name=scan.nextLine();
				if(DataProcessing.deleteUser(Name)) System.out.println("ɾ���ɹ�");
				else System.out.println("ɾ��ʧ��");
				this.xuliehua();
				break;
			}
			case 3:{
				System.out.print("�������û�������:");
				Name=scan.nextLine();
				System.out.print("�������û�������:");
				Password=scan.nextLine();
				System.out.print("�������û��Ľ�ɫ:");
				Role=scan.nextLine();
									
				if(DataProcessing.insertUser( Name, Password, Role)) System.out.println("��ӳɹ�");
				else System.out.println("���ʧ��");	
				this.xuliehua();
				//ObjectInputStream ois=new ObjectInputStream(new FileInputStream("D:\\Build\\JavaUsersSave.ser"));
				//ObjectInputStream ois2=new ObjectInputStream(new FileInputStream("D:\\Build\\JavaDocsSave.ser"));
				//DataProcessing.users=(Hashtable<String, User>)ois.readObject();
				//DataProcessing.docs=(Hashtable<String, Doc>)ois2.readObject();
				//ois.close();
				break;
			}
			case 4:{
				User dgz = null;
				for(Enumeration<User> e  = DataProcessing.users.elements();e.hasMoreElements();) {
				dgz=e.nextElement();
				System.out.println(dgz.getName()+"\t"+dgz.getPassword()+"\t"+dgz.getRole());
                }
				break;
			}
			case 5:{
				System.out.print("�����뵵���ţ�");
				ID=scan.nextLine();
				filename=DataProcessing.searchDoc(ID).getFilename();
				try{
					if(this.downloadFile(filename)) System.out.println("���سɹ�");
				}catch(IOException e) {
					System.out.println("Op downloadFile");
				}
				break;
			}
			case 6:{
				this.showFileList();
				break;
			}
			case 7:{
				System.out.print("�����������룺");
				Password=scan.nextLine();
				if(this.changeSelfInfo(Password)) ;
				else System.out.println("�޸�ʧ��");
				this.xuliehua();
				//ObjectInputStream ois=new ObjectInputStream(new FileInputStream("D:\\Build\\JavaUsersSave.ser"));
				//ObjectInputStream ois2=new ObjectInputStream(new FileInputStream("D:\\Build\\JavaDocsSave.ser"));
				//DataProcessing.users=(Hashtable<String, User>)ois.readObject();
				//DataProcessing.docs=(Hashtable<String, Doc>)ois2.readObject();
				//ois.close();
				break;
			}
			case 8:this.exitSystem();
			default :System.out.println("��������");
		    }
	    }
    }
}	


