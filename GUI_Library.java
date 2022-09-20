package GUI;
import java.awt.Color;
import java.awt.Font;
import java.awt.MenuBar;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import Library_Tools.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.Date;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
class Admin implements ActionListener
{
	JFrame jf;
	JButton jb1,jb2,jb3,jb4;
	JMenuBar mb;
	JMenu m1;
	JMenuItem m11,m12,m21;
	JLabel jl1,jl2;
	JTextField jtf1,jtf2,jtf3,jtf4,jtf5;
	JPanel jp;
	int width=Toolkit.getDefaultToolkit().getScreenSize().width;
	int height=Toolkit.getDefaultToolkit().getScreenSize().height;
	Admin()
	{
		jf=new JFrame();
		jf.setSize(width,height);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setLayout(null);
		jb1=new JButton();
		jb2=new JButton();
		jb3=new JButton();
		jb4=new JButton();
		jl1=new JLabel();
		jl1.setIcon(new ImageIcon("D:\\OOP_Project\\Admin.jpg"));
		jl1.setBounds(0,40,width,height-39);
		mb=new JMenuBar();
		mb.setBackground(Color.BLACK);
		m1=new JMenu();
		m1.setForeground(Color.WHITE);
		m11=new JMenuItem();
		m11.setForeground(Color.BLACK);
		m12=new JMenuItem();
		m12.setForeground(Color.BLACK);
		mb.setBounds(0,0,width,40);
		m1.setText("Profile");
		m11.setText(Login_GUI.userid);
		m12.setText("Logout");
		m12.addActionListener(this);
		m12.setFont(new Font("Calibri",Font.PLAIN,17));
		m1.setFont(new Font("Calibri",Font.BOLD,20));
		m1.add(m11);
		m1.add(m12);
		mb.add(m1);
		jb1.setIcon(new ImageIcon("D:\\OOP_Project\\AdminButton1.jpg"));
		jb2.setIcon(new ImageIcon("D:\\OOP_Project\\AdminButton2.jpg"));
		jb3.setIcon(new ImageIcon("D:\\OOP_Project\\AdminButton3.jpg"));
		jb4.setIcon(new ImageIcon("D:\\OOP_Project\\AdminButton4.jpg"));
		jb1.setBounds(282,180,800,62);
		jb2.setBounds(282,282,800,62);
		jb3.setBounds(282,382,800,62);
		jb4.setBounds(282,482,800,62);
		jb1.addActionListener(this);
		jb2.addActionListener(this);
		jb3.addActionListener(this);
		jb4.addActionListener(this);
		jf.getContentPane().add(jb4);
		jf.getContentPane().add(jb3);
		jf.getContentPane().add(jb2);
		jf.getContentPane().add(jb1);
		jf.getContentPane().add(jl1);
		jf.getContentPane().add(mb);
		jf.setVisible(true);
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==jb1)
		{
			String sdt[]=new String[5];
			String st[]=new String[5];
			st[0]="Enter Book Name: ";
			st[1]="Enter Book Id: ";
			st[2]="Enter Book Author: ";
			st[3]="Enter Book Stream: ";
			st[4]="Enter Status: ";
			sdt[0]=JOptionPane.showInputDialog(jf,st[0]);
			try
			{
			if(!sdt[0].trim().equals(""))
			{
				sdt[1]=JOptionPane.showInputDialog(jf,st[1]);
				if(!sdt[1].trim().equals(""))
				{
					sdt[2]=JOptionPane.showInputDialog(jf,st[2]);
					if(!sdt[2].trim().equals(""))
					{
						sdt[3]=JOptionPane.showInputDialog(jf,st[3]);
						if(!sdt[3].trim().equals(""))
						{
							sdt[4]=JOptionPane.showInputDialog(jf,st[4]).toUpperCase();
							if(!sdt[4].trim().equals(""))
							{
								int t=JOptionPane.showConfirmDialog(jf,"Do You Want To Add Book?");
								if(t==JOptionPane.YES_OPTION)
								{
									
									//System.out.println(sdt[0]+"\n"+sdt[1]+"\n"+sdt[2]+"\n"+sdt[3]+"\n"+sdt[4]);
									if(LibraryMethods.addBook(sdt[0],sdt[1],sdt[2],sdt[3],sdt[4]))
										JOptionPane.showMessageDialog(jf,"LIST UPDATED !");
									else
										JOptionPane.showMessageDialog(jf,"Sorry! Can't able to update List");
								}
							}
						}
					}
				}
			}
			}
			catch(NullPointerException ee) {};
		
		}
		else if(e.getSource()==jb2)
		{
			String bookname=JOptionPane.showInputDialog(jf,"Enter Book Name: ");
			if(JOptionPane.showConfirmDialog(jf,"Do You Want to Delete "+bookname+" ?")==JOptionPane.YES_OPTION)
			{
				if(LibraryMethods.removeBook(bookname))
					JOptionPane.showMessageDialog(jf,bookname+" Removed !");
				else
					JOptionPane.showMessageDialog(jf,"Book Not Found !");
			}
		}
		else if(e.getSource()==jb3)
		{
			String username=JOptionPane.showInputDialog(jf,"Enter User name: ");
			if(LibraryMethods.userIdValidater(username))
			{
				String pass=JOptionPane.showInputDialog(jf,"Enter Password: ");
				if(LibraryMethods.passValidater(pass))
				{
					if(LibraryMethods.addUser(username,pass))
						JOptionPane.showMessageDialog(jf,"User Registered !");
					else
						JOptionPane.showMessageDialog(jf,"Unable to register !");
				}
				else
					JOptionPane.showMessageDialog(jf,"Write Password in Proper Format !");
			}
			else
			{
				JOptionPane.showMessageDialog(jf,"Enter Proper Username");
			}
		}
		else if(e.getSource()==jb4)
		{
			jf.dispose();
			new AdminBookList();
		}
		else if(e.getSource()==m12)
		{
			jf.dispose();
			new Login_GUI();
		}
	}
}
class CurrentBook implements ActionListener
{
	JFrame jf;
	JTable jt;
	JLabel jl1,jl2;
	JButton b1,b2;
	int width=Toolkit.getDefaultToolkit().getScreenSize().width;
	int height= Toolkit.getDefaultToolkit().getScreenSize().height;
	CurrentBook()
	{
		try
		{
		jl1=new JLabel();
		jl1.setIcon(new ImageIcon("D:\\OOP_Project\\currentpage.jpeg"));
		jl1.setBounds(0,0,width-1,height-1);
		b2=new JButton();
		b2.setText("Book Id                             Book Name                         Day                         Month                         Date                             Due(Days)");
		b2.setForeground(Color.BLACK);
		b2.setBounds(128,90,width-280,22);
		b2.setFont(new Font("Calibri",Font.BOLD,18));
		jf=new JFrame();
		jf.setSize(width,height);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setLayout(null);
		String col[]= {"Book Id","Book Name","Day","Month","Year","Due(days)"};
		jt =new JTable(LibraryMethods.getUserBookDetail(Login_GUI.userid),col)
		{
			public boolean isCellEditable(int x ,int y)
			{
				return false;
			}
		};
		b1=new JButton("BACK");
		b1.setBounds(580,40,100,40);
		b1.addActionListener(this);
		jt.setBounds(128,120,width-280,height-280);
		jf.getContentPane().add(jt);  
		jf.getContentPane().add(b1);
		jf.getContentPane().add(jl1);
		jf.getContentPane().add(b2);
		jf.setVisible(true);
		}
		catch(IOException ee)
		{
			JOptionPane.showMessageDialog(jf,"No Pending Books");
			new UserHomePage();
		}
	}
	public void actionPerformed(ActionEvent e)
	{
		jf.dispose();
		new UserHomePage();
	}
}
class AdminBookList implements ActionListener
{
	JFrame jf;
	JTable jt;
	JTextField jtf1,jtf2;
	JLabel jl1,jl2;
	JButton b,b1;
	String addDate[]=new String[3];
	boolean avail =false;
	int row,col;
	int day;
	AdminBookList()
	{
		jf=new JFrame();
		int width= Toolkit.getDefaultToolkit().getScreenSize().width;
		int height=Toolkit.getDefaultToolkit().getScreenSize().height;
		jf.setSize(width,height);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setLayout(null);
		String cols[]= {"Book Id","Book Name","Author","Stream","Availabe"};
		jt=new JTable(LibraryMethods.getBookList(),cols) 
		{
			public boolean isCellEditable(int x,int y)
			{
				return false;
			}
		};
		jl2=new JLabel();
		jl2.setIcon(new ImageIcon("D:\\OOP_Project\\BookLib.jpeg"));
		jl2.setBounds(0,0,width,height);
	    b1=new JButton("BACK");
	    b1.setBounds(880,64,100,40);
	    b1.addActionListener(this);
		jt.setBounds(128,120,width-280,height-280);
		jf.getContentPane().add(jt);
		jf.getContentPane().add(b1);
		jf.getContentPane().add(jl2);
		jf.setVisible(true);
	}
	public void actionPerformed(ActionEvent e1)
	{
		if(e1.getSource()==b1)
		{
			jf.dispose();
			new Admin();
		}
		
	}
}
class BookList implements ListSelectionListener,ActionListener
{
	JFrame jf;
	JTable jt;
	JTextField jtf1,jtf2;
	JLabel jl1,jl2;
	JButton b,b1;
	String addDate[]=new String[3];
	boolean avail =false;
	int row,col;
	int day;
	BookList()
	{
		jf=new JFrame();
		int width= Toolkit.getDefaultToolkit().getScreenSize().width;
		int height=Toolkit.getDefaultToolkit().getScreenSize().height;
		jf.setSize(width,height);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setLayout(null);
		String cols[]= {"Book Id","Book Name","Author","Stream","Availabe"};
		jt=new JTable(LibraryMethods.getBookList(),cols) 
		{
			public boolean isCellEditable(int x,int y)
			{
				return false;
			}
		};
		ListSelectionModel lsm= jt.getSelectionModel();
		jl2=new JLabel();
		jl2.setIcon(new ImageIcon("D:\\OOP_Project\\BookLib.jpeg"));
		jl2.setBounds(0,0,width,height);
	    lsm.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    b=new JButton("ADD");
	    b.addActionListener(this);
	    b1=new JButton("BACK");
	    b1.setBounds(880,64,100,40);
	    b1.addActionListener(this);
	    lsm.addListSelectionListener(this);
	    jtf1=new JTextField();
	    jl1=new JLabel("Borrow for Number of Days:");
	    jl1.setFont(new Font("Calibri",Font.BOLD,18));
	    jl1.setForeground(Color.WHITE);
	    jl1.setBounds(20,64,228,40);
	    jtf1.setBounds(250,64,210,40);
	    b.setBounds(1000,64,100,40);
		jt.setBounds(128,120,width-280,height-280);
		jf.getContentPane().add(jt);
		jf.getContentPane().add(jl1);
		jf.getContentPane().add(jtf1);
		jf.getContentPane().add(b);
		jf.getContentPane().add(b1);
		jf.getContentPane().add(jl2);
		jf.setVisible(true);
	}
	public void valueChanged(ListSelectionEvent e)
	{
		row = jt.getSelectedRow();
		col =jt.getSelectedColumn();
		if(String.valueOf(jt.getValueAt(row,4)).equals("AVAILABLE"))
			avail=true;
		else
			avail=false;
		String c = String.valueOf(jt.getValueAt(row,col));
		System.out.println(c);
	}
	public void actionPerformed(ActionEvent e1)
	{
		if(e1.getSource()==b)
		{
			if(avail)
			{
				try
				{
					day=Integer.parseInt(jtf1.getText());
					if(day==0)
						throw new NumberFormatException();
					else if(day>10)
					{
						JOptionPane.showMessageDialog(jf,"MAXIMUM DAY LIMIT IS 10 !");
					}
					else
					{
						Date date =new Date();
						String garbarr[]=String.valueOf(date).split(" ");
						addDate[0]=garbarr[0];
						addDate[1]=garbarr[1];
						addDate[2]=garbarr[2];
						System.out.println(String.valueOf(date));
						try
						{
							writeData();
						}
						catch(Exception e11)
						{
							System.out.println("Problem");
							e11.printStackTrace();
						}
					}
				}
				catch(NumberFormatException ee)
				{
					JOptionPane.showMessageDialog(jf,"Invalid Entry");
				}
			}
			else
				JOptionPane.showMessageDialog(jf,"Soory! It is not Availabe");
		}
		else if(e1.getSource()==b1)
		{
			jf.dispose();
			new UserHomePage();
		}
		
	}
	public void writeData() throws Exception
	{
		String garbage;
		String filename[]=Login_GUI.userid.split("@");
		System.out.println(filename[0]);
		ArrayList<String> userbooklst = new ArrayList<String>();
		try
		{
		File fr =new File("D:\\OOP_Project\\"+filename[0]+".txt");
		Scanner sc=new Scanner(fr);
		while(sc.hasNextLine())
		{
			userbooklst.add(sc.nextLine());
			for(int i=0;i<=4;i++)
				garbage=sc.nextLine();
		}
		sc.close();
		if(userbooklst.contains(jt.getValueAt(row,0)))
			JOptionPane.showMessageDialog(jf,"You have this book Already in your List!");
		else
		{
			FileWriter fw =new FileWriter("D:\\OOP_Project\\"+filename[0]+".txt",true);
			for(int i=0;i<=4;i++)
			{
				if(i<2)
					fw.append(jt.getValueAt(row,i)+"\n");
				else
					fw.append(addDate[i-2]+"\n");
			}
			fw.append(String.valueOf(day)+"\n");
			JOptionPane.showMessageDialog(jf,"Added in Your List !");
			fw.close();
		}
		}
		catch(IOException ee)
		{
			File fc =new File("D:\\OOP_Project\\"+filename[0]+".txt");
			fc.createNewFile();
			FileWriter fw =new FileWriter("D:\\OOP_Project\\"+filename[0]+".txt",true);
			for(int i=0;i<=5;i++)
			{
				if(i<2)
					fw.append(jt.getValueAt(row,i)+"\n");
				else if(i!=5)
					fw.append(addDate[i-2]+"\n");
				else
					fw.append(String.valueOf(day)+"\n");
			}
			fw.close();
			JOptionPane.showMessageDialog(jf,"Book Added in your List !");
		}
	}
}
class UserHomePage implements ActionListener
{
	JFrame jf;
	JButton b1,b2,b3;
	JMenuBar mb;
	JMenu m1,m2;
	JMenuItem m11,m12,m13,m21;
	JLabel jl;
	JTable jt;
    UserHomePage()
	{
		jf=new JFrame();
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		jf.setSize(width,height);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setLayout(null);
		jl=new JLabel();
		jl.setBounds(0,0,width,height);
		jl.setIcon(new ImageIcon("D:\\OOP_Project\\HomePage.jpeg"));
		b1=new JButton();
		b1.setIcon(new ImageIcon("D:\\OOP_Project\\BookList.png"));
		b1.setBounds(282,120,800,100);
		b1.addActionListener(this);
		b2=new JButton();
		b2.setBounds(282,272,800,100);
		b2.setIcon(new ImageIcon("D:\\OOP_Project\\DigiBook.png"));
		b2.addActionListener(this);
		b3=new JButton();
		b3.setIcon(new ImageIcon("D:\\OOP_Project\\PendingBook.png"));
		b3.setBounds(282,422,800,100);
		b3.addActionListener(this);
		mb=new JMenuBar();
		mb.setBounds(0,0,width,28);
		mb.setBackground(Color.YELLOW);
		m1=new JMenu("Profile");
		m2=new JMenu("Setting");
		m11=new JMenuItem(Login_GUI.userid);
		m13=new JMenuItem("Logout");
		m21=new JMenuItem("Change Password ");
		m21.addActionListener(this);
		m13.addActionListener(this);
		m2.add(m21);
		m1.add(m11);
		m1.add(m13);
		mb.add(m1);
		mb.add(m2);
		jf.getContentPane().add(mb);
		jf.getContentPane().add(jl);
		jf.getContentPane().add(b1);
		jf.getContentPane().add(b2);
		jf.getContentPane().add(b3);
		jf.setVisible(true);
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==b1)
		{
			jf.dispose();
			new BookList();
		}
		else if(e.getSource()==m13)
		{
			jf.dispose();
			new Login_GUI();
		}
		else if(e.getSource()==m21)
		{
			try
			{
				int op = JOptionPane.showConfirmDialog(jf,"Do you Want?");
				if(op==JOptionPane.YES_OPTION)
				{
					String passchanged=JOptionPane.showInputDialog(jf,"Enter New Password");
					if(LibraryMethods.passValidater(passchanged))
					{
					op=JOptionPane.showConfirmDialog(jf,"Press Yes to Update Password");
					if(op==JOptionPane.YES_OPTION)
					{
						LibraryMethods.setPass(Login_GUI.userid,passchanged);
					}
					}
					else
					{
						JOptionPane.showMessageDialog(jf,"Password Should contain atleast one \nsmall case,upper case,digit,special character \nlength >=8");
					}
				}
			}
			catch(Exception ee12)
			{
				ee12.printStackTrace();
			}
		}
		else if(e.getSource()==b3)
		{
			jf.dispose();
			new CurrentBook();
		}
		else if(e.getSource()==b2)
		{
			int ix=0;
			String bookname=JOptionPane.showInputDialog(jf,"Enter Book Name: ");
			String lst[][]=LibraryMethods.getBookList();
			try
			{
				int ck=0;
				while(lst[ix][1]!=null)
				{
					if(lst[ix][1].toUpperCase().equals(bookname.toUpperCase()))
					{
						if(lst[ix][4].toUpperCase().equals("AVAILABLE"))
							{	ck=1;
								JOptionPane.showMessageDialog(jf,"BOOK AVAILABE !");
							}
						else
							{	ck=1;
								JOptionPane.showMessageDialog(jf,"Sorry, Currently Book Unavailable");
							}
						break;
					}
					ix++;
				}
				if(ck==0)
					JOptionPane.showMessageDialog(jf,"Book Unavailable");
			}
			catch(Exception ee)
			{
				JOptionPane.showMessageDialog(jf,"Sorry, Currently Book Unavailable");
			}
		}
	}
}
class Login_GUI implements ActionListener
{
	JFrame jf;
	JButton b1,b2;
	JLabel jl1,jl2,jl3;
	JTextField jt1,jt2,jt3;
	JPasswordField jpw;
	int w=Toolkit.getDefaultToolkit().getScreenSize().width;
	int h=Toolkit.getDefaultToolkit().getScreenSize().height;
	static String userid=null;
	static int userindex;
    Login_GUI()
	{
		jf=new JFrame();
		//Dimension maxscr  = Toolkit.getDefaultToolkit().getScreenSize();
		jf.setSize(w,h);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setLayout(null);
	//	JPanel jp  =new JPanel();
		jt1=new JTextField();
		jt2=new JTextField();
		jt3=new JTextField();
		jpw=new JPasswordField();
		jl1 =new JLabel();
		jl2 =new JLabel();
		b1=new JButton();
		jl1.setIcon(new ImageIcon("D:\\OOP_Project\\home01.jpg"));
		jl1.setBounds(0,0,w,h);
		jt1.setFont(new Font("Calibri",Font.BOLD,28));
		jt1.setText("Username");
		jt1.setBackground(Color.BLACK);
		jt1.setForeground(Color.WHITE);
		jt1.setEditable(false);
		jt1.setBounds(410,204,158,48);
		jt2.setBounds(571,204,380,48);
		jt2.setFont(new Font("Calibri",Font.PLAIN,22));
		jt3.setText("Password");
		jt3.setFont(new Font("Calibri",Font.BOLD,28));
		jt3.setBackground(Color.BLACK);
		jt3.setForeground(Color.WHITE);
		jt3.setBounds(410,311,158,48);
		jpw.setBounds(571,311,380,48);
		jpw.setFont(new Font("Calibri",Font.PLAIN,22));
		b1.setBounds(620,408,100,42);
		b1.setIcon(new ImageIcon("D:\\OOP_Project\\login.png"));
		b1.addActionListener(this);
		jf.getContentPane().add(jl1);
		jf.getContentPane().add(jt1);
		jf.getContentPane().add(jt2);
		jf.getContentPane().add(jt3);
		jf.getContentPane().add(jpw);
		jf.getContentPane().add(b1);
		//jf.getContentPane().add();
		jf.setVisible(true);
	}
	public void actionPerformed(ActionEvent e)
	{
		String uid= jt2.getText();
		String upass= new String(jpw.getPassword());
		if(!LibraryMethods.userIdValidater(uid))
			JOptionPane.showMessageDialog(jf,"Invalid Entry !");
		else if(!LibraryMethods.passValidater(upass))
			JOptionPane.showMessageDialog(jf,"Invalid Entry !"+new String(jpw.getPassword()));
		else
			try
			{
				if(LibraryMethods.adminCheck(uid,upass))
				{
					userid=uid;
					jf.dispose();
					new Admin();
				}
				else if(LibraryMethods.userCheck(uid,upass))
					{
						userid=uid;
						jf.dispose();
						new UserHomePage();
					}
				else
					JOptionPane.showMessageDialog(jf,"Invalid Details !");
			}
		catch(Exception e1) 
		{
			e1.printStackTrace();
		}
	}
}
public class GUI_Library 
{
	public static void main(String[] args) 
	{
		new Login_GUI();
	//	new Admin();
	}
}
