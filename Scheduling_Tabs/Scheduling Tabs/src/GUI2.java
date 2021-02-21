import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.awt.*;
import javax.swing.*;
public class GUI2 {
	static JFrame mainF;//static BackGroundPanel bgp;
	static JPanel jp = new JPanel();
	static JList timers;static JPanel add, listPanel;
	static JComboBox interval,Month,Day,Hour,Minute,List;
	static int time;
	static JButton d =new JButton("Add"),settle;
	static JScrollPane jsc;
	static ArrayList<AlarmTime> at = new ArrayList<AlarmTime>();
	static JMenuBar bar=new JMenuBar();
	static JMenu menu = new JMenu("File");
	static JMenuItem expor=new JMenuItem("Export clock list");
	static JMenuItem impor=new JMenuItem("Import clock list");
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		prepGUI();
	}
	public static void prepGUI() {
		
		menu.add(expor);menu.add(impor);
		mainF=new JFrame("ヾ(=･ω･=)o");
		mainF.setJMenuBar(bar);
		bar.add(menu);
		jsc=new JScrollPane();
		settle=new JButton("Start Alarm!");
		interval=new JComboBox<String>();
		Month=new JComboBox<Integer>();
		Day=new JComboBox<Integer>();
		Minute=new JComboBox<Integer>();
		Hour=new JComboBox<Integer>();
		List=new JComboBox<String>();
		File listDir=new File(System.getProperty("user.dir")+"\\Lists");
		String[] files = listDir.list();
		for(String str:files) {
			List.addItem(str);
		}
		timers=new JList<String>();
		jsc.setViewportView(timers);
		//File dir = new File(System.getProperty("user.dir")+"\\BackPictures");
	//	bgp= new BackGroundPanel(dir, mainF);
		//mainF.setContentPane(jp);
		mainF.setLayout(new GridLayout(5,1));
		add= new JPanel();
		listPanel=new JPanel();
		mainF.setSize(600,350);
		JLabel m = new JLabel("Month");JLabel da = new JLabel("Day");JLabel h = new JLabel("Hour");JLabel mi = new JLabel("Minute");JLabel l = new JLabel("List");
		add.add(m);add.add(Month);add.add(da);add.add(Day);add.add(h);add.add(Hour);add.add(mi);add.add(Minute);add.add(l);add.add(List);
		for(int i=1;i<=12;i++) {
			Month.addItem(i);
		}
		for(int i=1;i<=30;i++) {
			Day.addItem(i);
		}
		for(int i=0;i<=23;i++) {
			Hour.addItem(i);
		}
		for(int i=0;i<=59;i++) {
			Minute.addItem(i);
		}
		add.add(interval);
		interval.addItem("Once");//default
		interval.addItem("Daily");
		interval.addItem("Weekly");
		interval.addItem("Monthly");
		//interval.addItem("Yearly");
		JLabel blank1=new JLabel();
		JLabel blank2=new JLabel();
		JLabel blank3=new JLabel();
		mainF.add(blank1);
		mainF.add(add);
		mainF.add(blank2);
		mainF.add(jsc);
		mainF.add(blank3);
		add.add(d);
		add.add(settle);
		System.out.println("Here?");
		add.setBackground(new Color(0,0,0,0));
		mainF.setVisible(true);
		
		/*
		 * 		for(int i=0;i<at.size();i++) {
			at.get(i).startClock();
			System.out.println("Alarm Start!");
		}
		
		 */

		impor.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFileChooser jfc = new JFileChooser(System.getProperty("user.dir")+"\\ClockList");
				int val=jfc.showOpenDialog(null);
				if(val==jfc.APPROVE_OPTION) {
					File select = jfc.getSelectedFile();					
						at=(ArrayList<AlarmTime>) test.readObjectFromFile(select.getAbsolutePath());
						update();
				}
			}
		});
		
		expor.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(at.size()>0) {
					String fileName=JOptionPane.showInputDialog(mainF,"Please give a clear name, you won't be able to understand the txt file","Please type in list name",1);
					if(fileName!=null) {
					File saveCopy = new File(System.getProperty("user.dir")+"\\ClockList\\"+fileName+".txt");
					if(saveCopy.exists()==false) {
						try {
							saveCopy.createNewFile();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					test.writeObjectToFile(at, saveCopy.getAbsolutePath());
				}
				}
			}
		});
		settle.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				while(true) {
					
					try {
						Thread.sleep(3000);
						mainF.setVisible(false);
					} catch (InterruptedException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				for(int i=0;i<at.size();i++) {
					try {
						at.get(i).startClock();
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					//System.out.println("Alarm Start!");
					
				}
			}}
		});
		
		d.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Integer hr = Integer.valueOf(Hour.getSelectedItem().toString());
				System.out.println(hr);
				AlarmTime temp=new AlarmTime(2021, Integer.valueOf(Month.getSelectedItem().toString()),Integer.valueOf(Day.getSelectedItem().toString()), hr, Integer.valueOf(Minute.getSelectedItem().toString()), List.getSelectedItem().toString(), interval.getSelectedIndex());
				System.out.println(temp.toString());
				at.add(temp);
				update();
			}
		});
	}
	public static void update() {
		ArrayList<String> temp=new  ArrayList<String>();
		for(int i=0;i<at.size();i++) {
			temp.add(at.get(i).toString());
		}
		String[] listData= (String[])temp.toArray(new String[temp.size()]);
		timers.setListData(listData);

	}
}
