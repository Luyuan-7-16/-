package oop_1;
import java.util.*;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;

public class Browser extends User implements Serializable{
	
	String tip_role="�������Ա�˵�";
	String tip_exit="�˳�ϵͳ����лʹ��";
	String menu="************��ӭ����"+tip_role+"*************"
			+"\n \t 1.�����ļ�"+"\n \t 2.�ļ��б�"+"\n \t 3.�޸�����"+"\n \t 4.�˳�"
			+"\n*********************************************";
	String Password = null;
	String filename= null;//------------------------���ص��ļ�������
	String ID=null;//-------------------------------��������ID����
	
	String download="D:\\Build\\JavaTest\\download\\";//------�ļ����ص�ַ�ĸ�Ŀ¼

	Browser(String name,String password,String role){
		super(name,password,role);
	}
	
	public void showMenu() {
		System.out.println(menu);
	}
	
	public void Operate() throws SQLException,IOException{
		int a;
		Scanner scan=new Scanner(System.in);
		for(;;) {
			this.showMenu();
			System.out.print("������ָ�");
			a=scan.nextInt(); 
			scan.nextLine();
			switch(a) {
			case 1: {
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
			case 2:{
				this.showFileList();
				break;
			}
			case 3:{
				System.out.print("�����������룺");
				Password=scan.nextLine();
				if(this.changeSelfInfo(Password)) ;
				else System.out.println("�޸�ʧ��");
				this.xuliehua();
				break;
			}
			case 4:{
				this.exitSystem();
			}					
			default :System.out.println("��������");
			}
		}
	}
	
}

