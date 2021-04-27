import java.io.IOException;
import java.util.*;
import java.io.File;
import java.io.FileWriter;
import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*;
import javax.swing.filechooser.*;
import java.io.FileNotFoundException;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import java.awt.Color;

import java.awt.Toolkit;
import java.awt.event.KeyEvent;

public class UKMParking extends JFrame implements ActionListener {

	//tak gune semua frame,buttons, and lable dlm ni. Just saje declare banyak sikit if nanti kalo nk tmbah.
	JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
	JFileChooser k = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
	static JLabel l[]=new JLabel[8];
	JLabel[]lv=new JLabel[7];
	private JFrame[] f=new JFrame[9];
	JTextArea txt = new JTextArea();
	JTextArea []tv=new JTextArea[7];
	JRadioButton rb[]=new JRadioButton[3];
	JRadioButton[]rbv=new JRadioButton[16];
	JButton b[]=new JButton[9];
	JButton save[]=new JButton[3];
	JButton back[]=new JButton[8];
	JButton add[]=new JButton[3];
	JButton remove[]=new JButton[3];
	String []num={"1","2","3","4"};
	JComboBox c=new JComboBox(num);
	JComboBox parklist[]=new JComboBox[4];
	JComboBox nopark=new JComboBox();
	JComboBox delpark=new JComboBox();

	JPanel panel = new JPanel();
	final JScrollPane scroll_1 = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
								JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	
	int n=-1;
	boolean available=true;

	String location="";
	String fileperson="";
	String fileparking="";

	JTextField pname = new JTextField();
	JTextField parea = new JTextField();
	JTextField ptype = new JTextField();
	JTextField psize = new JTextField();
	Object[] message = {
		"Input Parking Name:", pname,
		"Input Parking Area:", parea,
		"Input Parking Type:", ptype,
		"Input Size:", psize,
	};

	JTextField id = new JTextField();
	JTextField job = new JTextField();
	Object[] send = {
		"Enter ID:", id,
		"Enter Job:", job
	};

	JList list=new JList();

	person p[]=new person[100];
	vehicle v[]=new vehicle[100];
	parking park[]=new parking[100];

	staff staf[]=new staff[100];
	students stu[]=new students[100];

	car cr[]=new car[100];
	motorcycle moto[]=new motorcycle[100];
	bus bs[]=new bus[100];
	lorry lor[]=new lorry[100];

	JButton parking[]=new JButton[100];

	DefaultListModel <String> model = new DefaultListModel<>();

	public UKMParking() {
		firstf();
		reset();
	}

	public void reset(){
		for(int dec=0;dec<p.length;dec++){
			p[dec]=new person();
			v[dec]=new vehicle();
			park[dec]=new parking();

			staf[dec]=new staff();
			stu[dec]=new students();

			cr[dec]=new car();
			moto[dec]=new motorcycle();
			bs[dec]=new bus();
			lor[dec]=new lorry();
		}

		for(int i=0;i<parklist.length;i++){
			parklist[i]=new JComboBox();
		}
	}

	public void parkingB(int i){
		parking[i].setVisible(true);
		parking[i].setBackground(Color.GREEN);
		parking[i].setFocusPainted(false);
		parking[i].setPreferredSize(new Dimension(50,50));
		parking[i].addActionListener(this);
	}

	public void extrabutton(JButton extra[], String bname, Color col, int i, int x, int y){
		extra[i] = new JButton(bname);
		extra[i].setVisible(true);
		extra[i].setBackground(col);
		extra[i].setBorderPainted(false);
		extra[i].setFocusPainted(false);
		extra[i].setOpaque(true);
		extra[i].addActionListener(this);
		extra[i].setBounds(x,y,100,50);
	}

	public void makeframe(int fr, String fname){
		f[fr] = new JFrame(fname);
		f[fr].setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f[fr].setVisible(true);
		f[fr].setSize(400,550);
		f[fr].setResizable(false);
		f[fr].setLayout(null);
		f[fr].setLocationRelativeTo(null);
	}

	public void makebutton(int bu, String bname,int x,int y,int z){
		b[bu]=new JButton(bname);
		b[bu].setSize(x,50);
		b[bu].setLocation(y,z);
		b[bu].setVisible(true);
		b[bu].setOpaque(true);
		b[bu].addActionListener(this);
		b[bu].setBackground(Color.YELLOW);
		b[bu].setBorderPainted(false);
		b[bu].setFocusPainted(false);
		b[bu].setForeground(Color.RED);
	}

	//ni buat kotak nk isi ayat
	public void maketext(int x,int y){
		txt.setSize(100,20);
		txt.setLocation(x,y);
		txt.setEditable(true);
	}

	//bende ni buat title or name
	public void makelabel(String lname, int t, int x,int y){
		l[t]= new JLabel(lname);
		l[t].setLocation(x,y);
		l[t].setSize(200,50);
		l[t].setVisible(true);
	}

	public void selectbuttton(){
		rb[0]=new JRadioButton("Student");
		rb[0].setBounds(40,70,70,30);
		rb[0].addActionListener(this);

		rb[1]=new JRadioButton("Staff");
		rb[1].setBounds(130,70,70,30);
		rb[1].addActionListener(this);

		rb[2]=new JRadioButton("Management");
		rb[2].setBounds(200,70,100,30);
		rb[2].addActionListener(this);
	}

	public void vehicleinfo(int nv,int nl){
		f[3].repaint();
		for(int i=nl+1;i<nv;i++){
			lv[i]=new JLabel("Vehicle "+(i+1)+": ");
			lv[i].setLocation(50,135+i*60);
			lv[i].setSize(200,50);
			lv[i].setVisible(true);
			f[3].add(lv[i]);

			parklist[i].setLocation(250,150+i*60);
			parklist[i].setSize(70,20);
			parklist[i].setVisible(true);
			parklist[i].addActionListener(this);
			f[3].add(parklist[i]);
			
			tv[i]=new JTextArea();
			tv[i].setSize(100,20);
			tv[i].setLocation(120,150+i*60);
			tv[i].setEditable(true);
			f[3].add(tv[i]);

			//ni formula i+(i*3)
			rbv[4*i]=new JRadioButton("CAR");
			rbv[4*i].setBounds(20,180+i*60,70,15);
			rbv[4*i].addActionListener(this);
			f[3].add(rbv[4*i]);

			//i+1+(i+3)
			rbv[4*i+1]=new JRadioButton("BIKE");
			rbv[4*i+1].setBounds(90,180+i*60,70,15);
			rbv[4*i+1].addActionListener(this);
			f[3].add(rbv[4*i+1]);

			rbv[4*i+2]=new JRadioButton("BUS");
			rbv[4*i+2].setBounds(160,180+i*60,70,15);
			rbv[4*i+2].addActionListener(this);
			f[3].add(rbv[4*i+2]);

			rbv[4*i+3]=new JRadioButton("LORRY");
			rbv[4*i+3].setBounds(250,180+i*60,70,15);
			rbv[4*i+3].addActionListener(this);
			f[3].add(rbv[4*i+3]);
		}
	}

	public void secondf(String setname){
		makeframe(1,setname);
		f[1].setSize(400,400);

		makebutton(2,"Parking Lots",110,60,70);
		f[1].add(b[2]);
		
		makebutton(3,"Create New",100,200,70);
		f[1].add(b[3]);
		
		makebutton(4,"Change Job",105,60,150);
		f[1].add(b[4]);
		
		makebutton(5,"Change Vehicle",125,190,150);
		f[1].add(b[5]);
		
		extrabutton(back,"Back",Color.PINK,0,130,250);
		f[1].add(back[0]);
	}

	public void thirdf(String setname){
		makeframe(3,setname);

		makelabel("ID :",1,80,30);
		f[3].add(l[1]);
		maketext(150,45);
		f[3].add(txt);
		selectbuttton();
		f[3].add(rb[0]);
		f[3].add(rb[1]);
		f[3].add(rb[2]);

		makelabel("No. of Vehicle :",2,60,100);
		f[3].add(l[2]);

		c.setLocation(150,115);
		c.setSize(40,20);
		c.setVisible(true);
		c.addActionListener(this);
		f[3].add(c);

		extrabutton(back,"Back",Color.PINK,1,80,400);
		f[3].add(back[1]);

		extrabutton(save,"Save",Color.GREEN,0,190,400);
		f[3].add(save[0]);
	}

	public void forthf(String setname){
		makeframe(4,setname);
		f[4].setLayout(new BorderLayout());
		JPanel bot=new JPanel();

		f[4].add(scroll_1,BorderLayout.CENTER);
		f[4].add(bot,BorderLayout.SOUTH);
		bot.setBackground(Color.white);
		panel.setLayout(new GridLayout(50,2,20,10));
		for(int h=0;h<parking.length;h++){
			if(park[h].getname()!=null){
				parking[h]=new JButton(park[h].getname());
				parkingB(h);
				panel.add(parking[h]);
			}
		}
		
		extrabutton(back,"Back",Color.PINK,2,20,440);
		extrabutton(add,"Add",Color.GREEN,0,260,440);
		extrabutton(remove,"Remove",Color.RED,0,140,440);
		back[2].setPreferredSize(new Dimension(100,50));
		add[0].setPreferredSize(new Dimension(100,50));
		remove[0].setPreferredSize(new Dimension(100,50));
		bot.add(back[2]);
		bot.add(add[0]);
		bot.add(remove[0]);
	}

	public void fifthf(String setname){
		makeframe(5,setname);
		extrabutton(back,"Back",Color.PINK,3,20,440);
		extrabutton(add,"Add",Color.GREEN,1,260,440);
		extrabutton(remove,"Remove",Color.RED,1,140,440);
		makelabel("Select from the list first, then click remove to remove",5,40,400);
		l[5].setSize(300,50);
		f[5].add(add[1]);
		f[5].add(remove[1]);
		f[5].add(back[3]);
		f[5].add(l[5]);
	}

	public void sixth(String setname){
		makeframe(6,setname);
		model.removeAllElements();
		for(int q=0;q<staf.length;q++){
			if(staf[q].getid()!=null){
				model.addElement(staf[q].data());
			}
		}
		list=new JList(model);
		list.setBounds(90,50,200,330);
		f[6].add(list);

		makebutton(6,"Change",105,200,440);
		b[6].setForeground(Color.BLACK);
		f[6].add(b[6]);
		
		extrabutton(back,"Back",Color.PINK,4,80,440);
		f[6].add(back[4]);

		makelabel("Select from the list first, then click CHANGE to change",6,40,400);
		l[6].setSize(320,50);
		f[6].add(l[6]);
	}

	public void seventh(String setname){
		makeframe(7,setname);
		model.removeAllElements();

		for(int q=0;q<p.length;q++){
			if(p[q].getid()!=null){
				model.addElement(p[q].data()+" "+v[q].data()+" "+p[q].parked());
			}
		}
		list=new JList(model);
		list.setBounds(60,50,250,330);
		f[7].add(list);
		
		makebutton(7,"Change",105,200,440);
		b[7].setForeground(Color.BLACK);
		f[7].add(b[7]);

		extrabutton(back,"Back",Color.PINK,5,80,440);
		f[7].add(back[5]);

		makelabel("Select from the list first, then click CHANGE to change",7,40,400);
		l[7].setSize(320,50);
		f[7].add(l[7]);
	}

	public void firstf() {
		makeframe(0,"Start");
		f[0].setSize(400,400);
		f[3] = new JFrame("Add Vehicle and Person");

		makebutton(0,"Select From File",100,70,90);
		makebutton(1,"Start New",100,200,90);

		l[0] = new JLabel("no file selected");
		l[0].setLocation(150,150);
		l[0].setSize(200,50);
		l[0].setVisible(true);

		f[0].add(b[0]);
		f[0].add(b[1]);
		f[0].add(l[0]);

		makelabel("When opening this program, your capslock is set to ON",3,30,200);
		l[3].setSize(320,50);
		f[0].add(l[3]);

		makelabel("If you want, you can turn it off i guess",4,70,230);
		l[4].setSize(210,50);
		f[0].add(l[4]);
	}

	public void sortpark(int i,String a, String b){
		if(b==null){
			for(int pr=0;pr<park.length;pr++){
				if(park[pr].getname()!=null&&park[pr].gettype().contains(a)){
					parklist[i].addItem(park[pr].getname());
				}
			}
		}
		if(b!=null){
			for(int pr=0;pr<park.length;pr++){
				if(park[pr].getname()!=null){
					if(park[pr].gettype().contains(a)||park[pr].gettype().contains(b)){
						parklist[i].addItem(park[pr].getname());
					}
				}
			}
		}
	}

	public void addpark(String parkjob){
		nopark.removeAllItems();
		for(int u=0;u<p.length;u++){
			if(p[u].getid()!=null&&p[u].job().contains(parkjob)){
				nopark.addItem(p[u].getid()+" "+v[u].getplat());
			}
		}
		for(int t=0;t<nopark.getItemCount();t++){
			for(int z=0;z<nopark.getItemCount();z++){
				if(t!=z&&nopark.getItemAt(z).equals(nopark.getItemAt(t))){
					nopark.removeItemAt(z);
				}
			}
		}
	}

 	public void actionPerformed (ActionEvent e) throws NullPointerException{

		if (e.getSource()==b[0]){
			j.setDialogTitle("Open Person File");
			k.setDialogTitle("Open Parking File");

			int r = j.showOpenDialog(null);
			int q = k.showOpenDialog(null);
			if (r == JFileChooser.APPROVE_OPTION && q == JFileChooser.APPROVE_OPTION) {
				fileperson=j.getSelectedFile().getAbsolutePath();
				readDataperson(j.getSelectedFile().getAbsolutePath());

				fileparking=k.getSelectedFile().getAbsolutePath();
				readDataparking(k.getSelectedFile().getAbsolutePath());

				secondf(b[0].getText());
			}
			else{
				l[0].setText("This Operation Has Been Cancelled"); 
			}
		}
		else if (e.getSource()==b[1]){
			reset();

			j.setDialogTitle("Create Person File");
			k.setDialogTitle("Create Parking File");
			int r = j.showSaveDialog(null);
			int q = k.showSaveDialog(null);
			if (r == JFileChooser.APPROVE_OPTION && q == JFileChooser.APPROVE_OPTION) {
				fileperson=j.getSelectedFile().getAbsolutePath();
				fileparking=k.getSelectedFile().getAbsolutePath();
				secondf(b[1].getText());
			}
			else
				l[0].setText("This Operation Has Been Cancelled"); 
		}

		//ni semua nak bukak frame2
		if(e.getSource()==b[2]){
			forthf(b[2].getText());
		}
		else if(e.getSource()==b[3]){
			f[3].getContentPane().removeAll();
			n=-1;
			thirdf(b[3].getText());
		}
		else if(e.getSource()==b[4]){
			sixth(b[4].getText());
		}
		else if(e.getSource()==b[5]){
			seventh(b[5].getText());
		}

		//ni back button
		for(int i=0;i<back.length;i++){
			if(e.getSource()==back[i]){
				JFrame frame=(JFrame) SwingUtilities.getRoot(back[i]);
				frame.getContentPane().removeAll();
				frame.dispose();
			}
		}

		if(e.getSource()==back[2]){
			panel.removeAll();
		}

		//ni untuk bagi satu je radio button selected
		for(int i=0;i<3;i++){
			if(e.getSource()==rb[i]){
				for(int a=0;a<3;a++){
					if(a!=i){
						rb[a].setSelected(false);
					}
				}
			}
		}
		for(int i=0;i<rbv.length;i++){
			if(e.getSource()==rbv[i]){
				for(int a=i/4*4;a<i/4*4+4;a++){
					if(i!=a){
						rbv[a].setSelected(false);
					}
				}
			}
		}

		//ni buat list vehicle yg bole isi dari user selection
		Integer vn = c.getSelectedIndex();
		if(vn!=n){
			if(vn<n){
				for(int i=vn+1;i<=n;i++){
					f[3].remove(lv[i]);
					f[3].remove(tv[i]);
					f[3].remove(rbv[4*i]);
					f[3].remove(rbv[4*i+1]);
					f[3].remove(rbv[4*i+2]);
					f[3].remove(rbv[4*i+3]);
					f[3].remove(parklist[i]);
				}
			}
			vehicleinfo(vn+1,n);
			n=vn;
		}

		//ni untuk kluakan parking untuk pilih
		for(int a=0;a<4;a++){
			if(e.getSource()==rbv[a*4]){
				parklist[a].removeAllItems();
				if(rb[0].isSelected()){
					sortpark(a,"C",null);
				}
				else if(rb[1].isSelected()){
					sortpark(a,"C","STAFF");
				}
				else if(rb[2].isSelected()){
					sortpark(a,"C","MANAGEMENT");
				}
			}
			else if(e.getSource()==rbv[a*4+1]){
				parklist[a].removeAllItems();
				if(rb[0].isSelected()){
					sortpark(a,"B",null);
				}
				else if(rb[1].isSelected()){
					sortpark(a,"B","STAFF");
				}
				else if(rb[2].isSelected()){
					sortpark(a,"B","MANAGEMENT");
				}
			}
			else if(e.getSource()==rbv[a*4+2]||e.getSource()==rbv[a*4+3]){
				parklist[a].removeAllItems();
				parklist[a].addItem("N/A");
			}
		}

		//ni untuk save button
		if(e.getSource()==save[0]){
			String job="", vehicle="", noplat="", sps="";
			Integer loop = c.getSelectedIndex();

			if(rb[0].isSelected()){
				job="STUDENT";
			}
			else if(rb[1].isSelected()){
				job="O_STAFF";
			}
			else if(rb[2].isSelected()){
				job="M_STAFF";
			}

			for(int j=0;j<loop+1;j++){
				for(int i=j;i<4;i++){
					if(rbv[j*4+i].isSelected()){
						vehicle=rbv[j*4+i].getText();
					}
				}

				noplat=tv[j].getText().toUpperCase();
				sps=parklist[j].getSelectedItem().toString();
				for(int i=0;i<p.length;i++){
					if(p[i].getid()==null){
						p[i].person(txt.getText().toUpperCase(),job,sps);
						v[i].vehicle(vehicle,noplat);
						break;
					}
				}
				for(int i=0;i<staf.length;i++){
					if(staf[i].getid()==null){
						staf[i].person(txt.getText(),job,sps);
						break;
					}
				}
			}
			f[3].getContentPane().removeAll();
			f[3].dispose();
			writeperson();
		}

		//button parking2
		for(int i=0;i<parking.length;i++){
			if(e.getSource()==parking[i]){
				location=park[i].getname();
				model.removeAllElements();
				fifthf(location);
				for(int q=0;q<p.length;q++){
					if(p[q]!=null&&park[i].getname().equals(p[q].parked())){
						model.addElement(p[q].getid()+" "+p[q].job()+" "+v[q].getvehicle()+" "+v[q].getplat());
					}
				}

				list=new JList(model);
				list.setBounds(90,50,200,330);
				f[5].add(list);
				
				available=park[i].parkable(list.getModel().getSize());

				if(park[i].gettype().equals("STAFF")){
					addpark("O");
				}
				else if(park[i].gettype().equals("MANAGEMENT")){
					addpark("M");
				}
				else{
					addpark("S");
				}
			}
		}

		//nak add parking space baru
		if(e.getSource()==add[0]){
			int y=JOptionPane.showConfirmDialog(null, message, "New Parking", JOptionPane.OK_CANCEL_OPTION);

			if(y==0){
				for(int g=0;g<park.length;g++){
					if(park[g].getname()==null){
						park[g].parking(pname.getText().toUpperCase(),parea.getText().toUpperCase(),ptype.getText().toUpperCase(),Integer.parseInt(psize.getText()));
						parking[g]=new JButton(pname.getText().toUpperCase());
						parkingB(g);
						panel.add(parking[g]);
						panel.doLayout();
						break;
					}
				}
				writeparking();
				writeperson();
			}
		}

		//nak add org dalam parking space yg dipilih
		if(e.getSource()==add[1]){
			if(available){
				String plus=f[5].getTitle();
				int g= JOptionPane.showOptionDialog(null, nopark, "Choose From Existing ID",
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null,null);
				if(g==0){
					for(int i=0;i<p.length;i++){
						String take[]=nopark.getSelectedItem().toString().trim().split("\\s");
						if(p[i].getid()!=null&&take[0].equals(p[i].getid())&&take[1].equals(v[i].getplat())){
							for(int w=0;w<p.length;w++){
								if(p[w].getid()==null){
									p[w].person(p[i].getid(),p[i].job(),plus);
									v[w].vehicle(v[i].getvehicle(),v[i].getplat());
									break;
								}
							}
							if(p[i].job().contains("STAFF")){
								for(int u=0;u<staf.length;u++){
									if(staf[u].getid()==null){
										staf[u].person(p[i].getid(),p[i].job(),plus);
										break;
									}
								}
							}
							break;
						}
					}
					writeperson();
					f[5].getContentPane().removeAll();
					f[5].dispose();
					for(int i=0;i<parking.length;i++){
						if(parking[i]!=null&&plus.equals(parking[i].getText())){
							parking[i].doClick();
						}
					}
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "This Parking Is Full!");
			}
		}
		
		//nk buang parking
		if(e.getSource()==remove[0]){
			delpark.removeAllItems();
			for(int i=0;i<park.length;i++){
				if(park[i].getname()!=null){
					delpark.addItem(park[i].getname());
				}
			}
			int g= JOptionPane.showOptionDialog(null, delpark, "Enter Parking name",
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null,null);
			if(g==0){
				for(int i=0;i<park.length;i++){
					if(park[i].getname()!=null&&delpark.getSelectedItem().equals(park[i].getname())){
						panel.remove(parking[i]);
						panel.doLayout();
						park[i]=new parking();
						f[4].repaint();
						break;
					}
				}
				for(int i=0;i<p.length;i++){
					if(p[i].parked()!=null&&delpark.getSelectedItem().equals(p[i].parked())){
						p[i].setparked("N/A");
					}	
				}
				writeparking();
				writeperson();
			}
		}

		//buton remove org dari parking
		if(list.getSelectedIndex()!=-1){
			if(e.getSource()==remove[1]){
				String update="";
				String data=(String) list.getModel().getElementAt(list.getSelectedIndex());
				String[] split = data.trim().split("\\s");
				for(int i=0;i<p.length;i++){
					if(split[3].equals(v[i].getplat())&&p[i].parked().equals(f[5].getTitle())){
						update=p[i].parked();
						//p[i].setparked("N/A");
						p[i]=new person();
						f[5].getContentPane().removeAll();
						f[5].dispose();
						break;
					}
				}

				for(int i=0;i<staf.length;i++){
					if(staf[i]!=null&&update.equals(staf[i].parked())){
						staf[i].setparked("N/A");
					}
				}
				writeperson();
				for(int i=0;i<parking.length;i++){
					if(parking[i]!=null&&update.equals(parking[i].getText())){
						parking[i].doClick();
					}
				}
			}
		}
		
		//button nk tukar vehicle owner
		if(list.getSelectedIndex()!=-1){
			if(e.getSource()==b[7]){
				String place="";
				String change=(String) list.getModel().getElementAt(list.getSelectedIndex());
				String[] cut=change.trim().split("\\s");
				int input = JOptionPane.showConfirmDialog(null, send, 
					"Change Vehicle Owner", JOptionPane.OK_CANCEL_OPTION);
				if(input==0){
					for(int i=0;i<v.length;i++){
						if(v[i].getplat()!=null&&cut[3].equals(v[i].getplat())){
							p[i].person(id.getText().toUpperCase(),job.getText().toUpperCase());
							place=p[i].parked();
						}
					}
					if(job.getText().contains("STAFF")){
						for(int i =0;i<staf.length;i++){
							if(staf[i]!=null&&cut[0].equals(staf[i].getid())){
								staf[i].setparked("N/A");
							}
						}
						for(int i =0;i<staf.length;i++){
							if(staf[i].getid()==null){
								staf[i].person(id.getText(),job.getText(),place);
								break;
							}
						}
					}
					writeperson();
					f[7].getContentPane().removeAll();
					f[7].dispose();
					b[5].doClick();
				}
			}
		}

		//button change status staff
		if(list.getSelectedIndex()!=-1){
			if(e.getSource()==b[6]){
				String change=(String) list.getModel().getElementAt(list.getSelectedIndex());
				String[] cut=change.trim().split("\\s");
				String say1="",say2="",assign="";
				if(cut[1].contains("O")){
					say1="Ordinary Staff";
					say2="Management Staff";
					assign="M_STAFF";
				}
				else if(cut[1].contains("M")){
					say1="Management Staff";
					say2="Ordinary Staff";
					assign="O_STAFF";
				}
				int input = JOptionPane.showConfirmDialog(null, "Are you sure you want to change "+say1+" to "+say2,
							"Change Staff",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

				if(input==0){
					for(int i=0;i<p.length;i++){
						if(p[i].getid()!=null&&cut[0].equals(p[i].getid())){
							p[i].setjob(assign);
						}
					}
					for(int i=0;i<staf.length;i++){
						if(staf[i].getid()!=null&&cut[0].equals(staf[i].getid())){
							if(staf[i].job().contains("O")){
								staf[i].management();
							}
							else{
								staf[i].ordinary();
							}
						}
					}
					writeperson();
					f[6].getContentPane().removeAll();
					f[6].dispose();
					b[4].doClick();
				}
			}
		}
	}

	public void sortdata(){
		for(int i=0;i<p.length;i++){
			for(int y=0;y<p.length;y++){
				if(i!=y&&p[y].getid()!=null&&p[y].data().equals(p[i].data())&&p[y].parked().equals(p[i].parked())&&v[y].data().equals(v[i].data())){
					p[y]=new person();
					v[y]=new vehicle();
				}
			}
		}
	}

	public void writeperson(){
		try {
			FileWriter pWriter = new FileWriter(fileperson,false);
			sortdata();
			for(int s=0;s<p.length;s++){
				if(p[s].getid()!=null){
					pWriter.write(p[s].data()+" "+v[s].data()+" "+p[s].parked()+"\n");
				}
			}
			pWriter.close();
			System.out.println("Successfully wrote to the file.");
		}
		catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	public void writeparking(){
		try {
			FileWriter parkWriter = new FileWriter(fileparking,false);
			for(int d=0;d<park.length;d++){
				if(park[d].getname()!=null){
					parkWriter.write(park[d].data()+"\n");
				}
			}
			parkWriter.close();
			System.out.println("Successfully wrote to the file.");
		}
		catch (IOException e){
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	public void readDataperson(String str) {
		String id,vehicle,job,parked,platno;
		int ip=0;

		try {
			Scanner fileIn = new Scanner(new File(str));
			if (fileIn != null) {
				while (fileIn.hasNext()) {
					id=fileIn.next();
					job=fileIn.next();
					vehicle=fileIn.next();
					platno=fileIn.next();
					if(vehicle.equals("CAR")){
						cr[ip].vehicle(vehicle,platno);
					}
					else if(vehicle.equals("BIKE")){
						moto[ip].vehicle(vehicle,platno);
					}
					else if(vehicle.equals("LORRY")){
						lor[ip].vehicle(vehicle,platno);
					}
					else if(vehicle.equals("BUS")){
						bs[ip].vehicle(vehicle,platno);
					}
					parked=fileIn.next();
					p[ip].person(id,job,parked);

					if(job.equals("STUDENT")){
						stu[ip].person(id,job,parked);
					}
					if(job.contains("STAFF")){
						staf[ip].person(id,job,parked);
					}
					v[ip].vehicle(vehicle,platno);
					ip++;		
				}
				fileIn.close();
			}

		}catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	public void readDataparking(String str) {
		String name;
		String area;
		String type;
		String s;
		int space;
		int i=0;

		try {
			Scanner myReader = new Scanner(new File(str));
			if(myReader!=null){
				while (myReader.hasNext()){
					name = myReader.next();
					area = myReader.next();
					type= myReader.next();
					s=myReader.next();
					space=Integer.parseInt(s);
					
					park[i].parking(name,area,type,space);
					i++;
				}
				myReader.close();
			}
		}catch (FileNotFoundException e){
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	public static void main (String[] args) {
		new UKMParking();
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		toolkit.setLockingKeyState(KeyEvent.VK_CAPS_LOCK, Boolean.TRUE);
		//System.out.println(toolkit.getScreenResolution());
	}
}

