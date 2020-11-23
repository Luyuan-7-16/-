package GUIFrame;

import oop_1.*;
//import static oop_1.DataProcessing.users;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.JPasswordField;

public class loginFrame extends JFrame{

	public static JFrame frame;
	private JTextField textField;
	private String username;
	private String password;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DataProcessing.disconnectFromDB();
					loginFrame window = new loginFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 * @throws SQLException 
	 * @throws HeadlessException 
	 */
	public loginFrame() throws HeadlessException, SQLException {
		initialize();
		//System.out.print("login");
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException 
	 * @throws HeadlessException 
	 */
	private void initialize() throws HeadlessException, SQLException {
		//System.out.print("login");
		frame = new JFrame();
		frame.setTitle("\u7CFB\u7EDF\u767B\u5F55");
		frame.setBounds(532, 275, 466, 286);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				MainGUI.client.sendMessage("TERMINATE");
			    System.exit(0);
			}
			}); 
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u7528\u6237\u540D");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("������", Font.PLAIN, 20));
		label.setBounds(115, 64, 73, 28);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("\u5BC6  \u7801");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("������", Font.PLAIN, 20));
		label_1.setBounds(115, 102, 73, 29);
		frame.getContentPane().add(label_1);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				username=textField.getText()+e.getKeyChar();//ÿ��һ��ִ��һ�Σ���һ�ΰ���ʱ��.getTextΪ�գ�+getchar�ŵõ�������
				//textField_1.setText(username);
			}
		});
		
		textField.setFont(new Font("������", Font.PLAIN, 20));
		textField.setBounds(212, 63, 127, 30);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton button = new JButton("\u786E\u5B9A");
		
		button.setFont(new Font("������", Font.PLAIN, 20));
		button.setBounds(134, 153, 92, 30);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("\u53D6\u6D88");
		button_1.setFont(new Font("������", Font.PLAIN, 20));
		button_1.setBounds(236, 153, 92, 30);
		frame.getContentPane().add(button_1);
		
		passwordField = new JPasswordField();
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {//---------------------------------------------------�س���¼
				if(e.getKeyChar()!='\n') {
					password=new String(new String(passwordField.getPassword())+e.getKeyChar());
				}
				else {
					if(username==null||password==null) {
						JOptionPane.showMessageDialog(null, "����д������Ϣ");
					}else {
						try {
							MainGUI.client.sendMessage("CLIENT_LOGIN");
							MainGUI.client.sendUserData(username, password);
							if(MainGUI.client.processConnection().equals("YES")) {//�����Ƿ��������
								if(MainGUI.client.processConnection().equals("SERVER_LOGIN")) {//�����Ƿ��¼��							
									User user=MainGUI.client.receiveUser();//--------------------------���������ݿ�
									if (user.getRole().equalsIgnoreCase("administrator")) {
										frame.dispose();
										mainFrame mf=new mainFrame(user);
										mf.frame.setTitle("ϵͳ����Ա����");
										mf.frame.setVisible(true);
										/*JFrame mf=new mainFrame();//���������ӷ������������frame����
										mf.frame.setVisible(true);*/
									}
										
									else if (user.getRole().equalsIgnoreCase("operator")) {
										frame.dispose();
										mainFrame mf=new mainFrame(user);
										mf.frame.setTitle("����¼��Ա����");
										mf.frame.setVisible(true);
									}
										
									else {
										frame.dispose();
										mainFrame mf=new mainFrame(user);
										mf.frame.setTitle("�������Ա����");
										mf.frame.setVisible(true);
									}
								}else {
									passwordField.setText("");
									JOptionPane.showMessageDialog(null, "�������");
								}
							}else {
								textField.setText("");
								passwordField.setText("");
								JOptionPane.showMessageDialog(null, "�û���������");
							}
							
						}catch(HeadlessException c) {
							c.printStackTrace();
						} catch (IOException e1) {
							e1.printStackTrace();
						}catch(SQLException e1) {
							e1.printStackTrace();
						} catch (ClassNotFoundException e1) {
							e1.printStackTrace();
						}//--end try
					}//--end else
				}
				//textField_1.setText(password);
			}
		});
		passwordField.setBounds(212, 104, 127, 30);
		frame.getContentPane().add(passwordField);
		
		button_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MainGUI.client.sendMessage("TERMINATE");
			    System.exit(0);
			}
			
		});
		
		button.addActionListener(new ActionListener() {//-----------------------�����¼
			public void actionPerformed(ActionEvent e) {
				if(username==null||password==null) {
					JOptionPane.showMessageDialog(null, "����д������Ϣ");
				}else {
					try {
						MainGUI.client.sendMessage("CLIENT_LOGIN");
						MainGUI.client.sendUserData(username, password);
						if(MainGUI.client.processConnection().equals("YES")) {//�����Ƿ��������
							if(MainGUI.client.processConnection().equals("SERVER_LOGIN")) {//�����Ƿ��¼��							
								User user=MainGUI.client.receiveUser();//--------------------------���������ݿ�
								if (user.getRole().equalsIgnoreCase("administrator")) {
									frame.dispose();
									mainFrame mf=new mainFrame(user);
									mf.frame.setTitle("ϵͳ����Ա����");
									mf.frame.setVisible(true);
									/*JFrame mf=new mainFrame();//���������ӷ������������frame����
									mf.frame.setVisible(true);*/
								}
									
								else if (user.getRole().equalsIgnoreCase("operator")) {
									frame.dispose();
									mainFrame mf=new mainFrame(user);
									mf.frame.setTitle("����¼��Ա����");
									mf.frame.setVisible(true);
								}
									
								else {
									frame.dispose();
									mainFrame mf=new mainFrame(user);
									mf.frame.setTitle("�������Ա����");
									mf.frame.setVisible(true);
								}
							}else {
								passwordField.setText("");
								JOptionPane.showMessageDialog(null, "�������");
							}
						}else {
							textField.setText("");
							passwordField.setText("");
							JOptionPane.showMessageDialog(null, "�û���������");
						}
						
					}catch(HeadlessException c) {
						c.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}catch(SQLException e1) {
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					}//--end try
				}//--end else
			}//emd event
		});
		//User w=DataProcessing.searchUser(username);
		/*if (DataProcessing.searchUser(username)!=null) {
			if (DataProcessing.searchUser(username, password)!=null) {
				
			}
			else {
				JOptionPane.showMessageDialog(null, "�������");
			}
				
		}
		else {
			JOptionPane.showMessageDialog(null, "�û���������");
		}*/
		
	}
}
