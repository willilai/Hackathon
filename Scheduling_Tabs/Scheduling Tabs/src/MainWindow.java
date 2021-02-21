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
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//GUI.open();
		choices= new JComboBox<String>();
		selectList=new JFrame();
		CurrentChoosingList=new JLabel(txt);
		select=new JButton("List");
		launch=new JButton("Launch!");
		window=new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(1000, 800);
		window.setLocation(1000,400);
		File dir = new File(System.getProperty("user.dir")+"\\BackPictures");
		BackGroundPanel bgp=new BackGroundPanel(dir, window);
		window.setContentPane(bgp);
		
		window.setLayout(new GridLayout(4,1));
		JPanel blank1=new JPanel();
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
		JLabel dialogue = new JLabel("To be or not to be, that's a question");
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
		dialogue.setFont(new Font("Comic Sans MS", Font.ITALIC, 18));
		control.add(launch);
		control.add(select);
		control.add(CurrentChoosingList);
		File listDir=new File(System.getProperty("user.dir")+"\\Lists");
		String[] files = listDir.list();
		for(String str:files) {
			choices.addItem(str);
		}
	//	select.setBackground(new Color(0,0,0,0));
		selectList.add(choices);
		
		
		launch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
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
		});
		choices.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(e.getStateChange() == ItemEvent.SELECTED) {
					txt=e.getItem().toString();
				}
				update();
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
