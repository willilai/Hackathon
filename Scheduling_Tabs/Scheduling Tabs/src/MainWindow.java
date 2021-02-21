import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.channels.SelectableChannel;
import java.util.ArrayList;
import java.util.*;
public class MainWindow {
	static JFrame window,selectList;
	static JComboBox<String> choices;
	static JButton launch;
	static JButton select;
	static JLabel  CurrentChoosingList;
	static String txt="(None)";
	static JPanel status=new JPanel();
	static JButton listSettings=new JButton("List Settings");
	static JButton scheduleSettings=new JButton("Schedule Settings");
	static ArrayList<String> dialogues=new ArrayList<String>();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//GUI.open();
		try {
			BufferedReader dialogueReader = new  BufferedReader(new FileReader(System.getProperty("user.dir")+"\\Sentences.txt"));
			String str;
			while((str=dialogueReader.readLine())!=null) {
				dialogues.add(str);
			}
			System.out.println(dialogues);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		choices= new JComboBox<String>();
		selectList=new JFrame();
		CurrentChoosingList=new JLabel(txt);
		status.setBackground(new Color(10,20,50,60));
		CurrentChoosingList.setForeground(Color.white);
		CurrentChoosingList.setFont(new Font("Arial", Font.BOLD, 16));
		status.add(CurrentChoosingList);
		select=new JButton("List");
		launch=new JButton("Launch!");
		window=new JFrame("Jump Start Scheduling Browser");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(1000, 800);
		window.setLocation(0,0);
		File dir = new File(System.getProperty("user.dir")+"\\BackPictures");
		BackGroundPanel bgp=new BackGroundPanel(dir, window);
		window.setContentPane(bgp);
		window.setLayout(new GridLayout(4,1));
		JPanel blank1=new JPanel();
		blank1.add(listSettings);
		blank1.add(scheduleSettings);
		window.add(blank1);
		JPanel showDia=new JPanel();
		window.add(showDia);
		JPanel control=new JPanel();
		window.add(control);
		JPanel blank4=new JPanel();
		window.add(blank4);
		window.setVisible(true);
		blank4.setBackground(new Color(0,0,0,0));
		blank1.setBackground(new Color(0,0,0,0));
		control.setBackground(new Color(255,255,255,50));
		showDia.setBackground(new Color(0,0,0,70));
		//bgp.repaint();
		Collections.shuffle(dialogues);
		JLabel dialogue = new JLabel(dialogues.get(0));
		showDia.setLayout(new GridLayout(3, 1));
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		showDia.add(p1);showDia.add(p2);showDia.add(p3);
		p2.add(dialogue);
		dialogue.setForeground(Color.WHITE);
		p1.setBackground(new Color(0,0,0,0));
		p2.setBackground(new Color(0,0,0,0));
		p3.setBackground(new Color(0,0,0,0));
		dialogue.setFont(new Font("Comic Sans MS", Font.ITALIC, 38));
		control.add(launch);
		control.add(select);
		control.add(status);
		File listDir=new File(System.getProperty("user.dir")+"\\Lists");
		String[] files = listDir.list();
		for(String str:files) {
			choices.addItem(str);
		}
	//	select.setBackground(new Color(0,0,0,0));
		selectList.add(choices);
		window.requestFocus();
		bgp.repaint();
		listSettings.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				GUI.open();
			}
		});
		scheduleSettings.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				GUI2.prepGUI();
			}
		});
		launch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(CurrentChoosingList.getText().equals("(None)")==false) {
				ArrayList<String> tests = new ArrayList<String>();
				try {
					BufferedReader bf = new BufferedReader(new FileReader(System.getProperty("user.dir")+"\\Lists\\"+txt));
					String str;
					while((str=bf.readLine())!=null) {
						System.out.println(str);
						tests.add(str);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(tests);
				
				try {
					for(String str:tests) {
						java.net.URI uri = java.net.URI.create(str); 
						java.awt.Desktop dp = java.awt.Desktop.getDesktop(); 
					    if (dp.isSupported(java.awt.Desktop.Action.BROWSE)) { 
						      try {
								dp.browse(uri);
							} catch (IOException e) {
								e.printStackTrace();
							}
						    } 
					}
				}catch (Exception e) {
					// TODO: handle exception
				}
				
			}
			}
		});
		choices.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(e.getStateChange() == ItemEvent.SELECTED) {
					txt=e.getItem().toString();
				}
				window.setLocation(window.getX()+1, window.getY()+1);
				
				update();
				bgp.repaint();
			}
		});
		select.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				selectList.setVisible(true);
				selectList.setSize(100, 80);
				selectList.setLocation(1000, 400);
			}
		});
	}
	public static void update() {
		CurrentChoosingList.setText(txt);
	}
}
