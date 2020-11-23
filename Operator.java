package oop_1;
import java.util.*;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;

public class Operator extends User implements Serializable{
	
	String tip_role="����Ա�˵�";
	String tip_exit="�˳�ϵͳ����лʹ��";
	String menu="************��ӭ����"+tip_role+"*************"
			+"\n \t 1.�ϴ��ļ�"+"\n \t 2.�����ļ�"+"\n \t 3.�ļ��б�"+"\n \t 4.�޸�����"+"\n \t 5.�˳�"
			+"\n*********************************************";
	
	String filename= null;
	String description=null;
	String ID=null;		
	String Password = null;
	
	String upload="D:\\Build\\JavaTest\\upload\\";
	String download="D:\\Build\\JavaTest\\download\\";
	
	int a;

	Operator(String name,String password,String role){
		super(name,password,role);
	}
	
	public void showMenu() {
		System.out.println(menu);
	}
	
	public void Operate() throws SQLException,IOException{
		Scanner scan=new Scanner(System.in);
		for(;;) {
			this.showMenu();
			System.out.print("������ָ�");
			a=scan.nextInt(); 
			scan.nextLine();
			switch(a) {
			case 1: {
				System.out.println("�������ϴ����ļ�����");
				filename=scan.nextLine();
				System.out.println("�����뵵���ţ�");
				ID=scan.nextLine();
				System.out.println("������������");
				description=scan.nextLine();
				if(this.upload(filename,ID,description)) System.out.println("�ϴ��ɹ�");
				else System.out.println("�ϴ�ʧ��");
				this.xuliehua();
				break;
			}
			case 2: {//------------------------------------------------�����ļ�
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
			case 3:{//-------------------------------�ļ��б�
				try{
					this.showFileList();
				}catch(SQLException e) {
					System.out.println("Op showFile");
				}
				break;
			}
			case 4:{
				System.out.print("�����������룺");
				Password=scan.nextLine();
				if(this.changeSelfInfo(Password)) ;
				else System.out.println("�޸�ʧ��");
				this.xuliehua();
				break;
			}
			case 5:{
				this.exitSystem();
			}
			case 6:{
				System.out.print("���ز˵�,ɾ���ļ������뵵����:");
				ID=scan.nextLine();
				if(DataProcessing.deleteDoc(ID)) System.out.println("ɾ���ļ��ɹ�");				
				else System.out.println("ɾ���ļ�ʧ��");
				this.xuliehua();
				break;
			}
			case 7:{
				User dgz = null;
				for(Enumeration<User> e  = DataProcessing.users.elements();e.hasMoreElements();) {
				dgz=e.nextElement();
				System.out.println(dgz.getName()+"\t"+dgz.getPassword()+"\t"+dgz.getRole());
                }
				break;
			}
			default :System.out.println("��������");
			}
		}
	}
	
}
