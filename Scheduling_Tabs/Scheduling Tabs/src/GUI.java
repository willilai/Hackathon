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
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.*;
public class GUI {
	static JFrame main; static BackGroundPanel bgp;
	static JPanel mainPanel,listsControl;static JTextField input = new JTextField(30);
	static String statusText,sitesList;
	static ArrayList<String> displaySites;
	static JLabel blank1,blank2,blank4,blank5;
	static JLabel tx = new JLabel("Website"),status;
	static JComboBox<String> favorLists;static JButton saveCurrentList;
	static JList sites;
	static JButton add,save,remove;
	//static JCheckBox autoOrSchedule;
	//static int copyNumber=1;
	//static String copyFileName="List"+copyNumber;
	static FileWriter trashChannel;static BufferedWriter bw;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		open();
	}
	public static void open() {
		prepGUI();
		main.setVisible(true);
		bgp.repaint();
	}
	public static void prepGUI() {
		try {
			File trash =new File(System.getProperty("user.dir")+"\\Lists\\trash.txt");
			if(trash.exists()==false) {
				trash.createNewFile();
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		listsControl=new JPanel();
		favorLists= new JComboBox<String>();
		favorLists.addItem("Select Your List");
		File listDir=new File(System.getProperty("user.dir")+"\\Lists");
		String[] files = listDir.list();
		for(String str:files) {
			favorLists.addItem(str);
		}
		//favorLists.addItem("Trash Bin");
		JScrollPane scrollPane= new JScrollPane();
		remove = new JButton("remove");
		displaySites= new ArrayList<String>();
		saveCurrentList= new JButton("Save this List");
		mainPanel = new JPanel();
		main = new JFrame("Hi~(*･ω-q) ");
		File dir = new File(System.getProperty("user.dir")+"\\BackPictures");
		bgp=new BackGroundPanel(dir, main);
		main.setContentPane(bgp);
		System.out.println(main.getContentPane());
		Toolkit toolkit=Toolkit.getDefaultToolkit();
		int w1 = toolkit.getScreenSize().width;
		int h1=toolkit.getScreenSize().height;
		int w2= main.getWidth();
		int h2 = main.getHeight();
		int x = (w1-w2)/2;
		int y = (h1-h2)/2;
		blank1=new JLabel();blank2=new JLabel();blank4=new JLabel();blank5=new JLabel();
		main.setLocation(x, y);
		main.setSize(1000, 800);
		//main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main.setLayout(new GridLayout(5, 1));
		sitesList="Woohoo,nothing here";

		statusText="Nothing";
		status = new JLabel(statusText);
		status.setHorizontalAlignment(JLabel.CENTER);;
		Font fa = new Font("Arial", Font.PLAIN, 15);
		//mainPanel.add(test);
		add= new JButton("add");
		save = new JButton("save");
		//autoOrSchedule= new JCheckBox("Schedule?");
		
		mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
		
		sites= new JList<String>();
		sites.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		
		main.add(listsControl);
		listsControl.add(favorLists);
		listsControl.add(saveCurrentList);
		main.add(status);
		main.add(mainPanel);
		status.setFont(fa);
		sites.setFont(fa);
		main.add(scrollPane);
		scrollPane.setViewportView(sites);
		main.add(blank5);
	//	main.setBackground(new Color(0, 0, 0, 0));
		
		status.setForeground(Color.BLUE);
		
		listsControl.setBackground(new Color(10, 10, 10, 40));
		mainPanel.setBackground(new Color(10, 10, 10, 40));
		mainPanel.add(tx);
		mainPanel.add(input);
		mainPanel.add(add);mainPanel.add(remove);mainPanel.add(save);//mainPanel.add(autoOrSchedule);
		tx.setForeground(Color.white);
		tx.setFont(new Font("Arial", Font.BOLD, 18));
		tx.setBackground(Color.GRAY);
		main.setSize(bgp.getW(), bgp.getH());
		/*
		 * Test:
		 * 		System.out.println(System.getProperty("user.dir"));
		File saveCopy = new File(System.getProperty("user.dir")+"\\Lists\\"+copyFileName+".txt"); //when press "save", create a .txt file to record this list of favor.
		try {
			saveCopy.createNewFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		 */

		saveCurrentList.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String selectedItem = favorLists.getSelectedItem().toString();
				System.out.println(selectedItem);
				if(favorLists.getSelectedItem().toString().equals("Select Your List")==false) {
					try {
						BufferedWriter temp = new BufferedWriter(new FileWriter(System.getProperty("user.dir")+"\\Lists\\"+selectedItem));
						for(String str:displaySites) {
							temp.write(str+"\n");
						}
						temp.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			favorLists.setSelectedItem("Select Your List");
			statusText="Successfully updated this list!";
			update();
			}
		});
		favorLists.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				 if(e.getStateChange() == ItemEvent.SELECTED){
				String selectedItem = e.getItem().toString();
			//	System.out.println(selectedItem);
				if(selectedItem.equals("Select Your List")) {
					displaySites=new ArrayList<String>();
					update();
				}else {
					FileReader tempReader;
					try {
						BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir")+"\\Lists\\"+selectedItem));
						displaySites=new ArrayList<String>();
						String str;
						while((str=br.readLine())!=null) {
							System.out.println(str);
							displaySites.add(str);
						}
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				statusText="Now showing "+selectedItem;
				update();
			}}
		});
	
		save.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(favorLists.getSelectedItem()=="Select Your List") {
					String fileName=JOptionPane.showInputDialog(bgp,"Please type in list name","Please type in list name",1);
					File saveCopy = new File(System.getProperty("user.dir")+"\\Lists\\"+fileName+".txt");
					if(saveCopy.exists()) {
						File temp = new File(System.getProperty("user.dir")+"\\Lists\\"+"."+saveCopy.getName());
						if(temp.exists()) {
							JOptionPane.showMessageDialog(null,"This List already exists!","Error ",0);
						}
						saveCopy.renameTo(temp);
						System.out.println("SC:"+saveCopy.toString());
						System.out.println("Temp"+temp.toString());
					}
					try {
						saveCopy.createNewFile();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					try {
						for(String str:displaySites) {
						FileWriter tempWriter = new FileWriter(saveCopy,true);
						bw=new BufferedWriter(tempWriter);
						bw.append(str+"\n");
						bw.close();
						}
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					favorLists.addItem(saveCopy.getName());
					statusText="Saved current list successfully.";
					displaySites=new ArrayList<String>();
					File listDir=new File(System.getProperty("user.dir")+"\\Lists");
					String[] files = listDir.list();
					favorLists.removeAllItems();
					System.out.println("???");
					favorLists.addItem("Select Your List");
					for(String str:files) {
						favorLists.addItem(str);
					}
					update();
				}
			}
		});
		remove.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(sites.getSelectedIndex()>=0) {
					for(int i=0;i<displaySites.size();i++) {
						if(displaySites.get(i)==sites.getSelectedValue()) {
							try {
								trashChannel=new FileWriter(System.getProperty("user.dir")+"\\Lists\\trash.txt",true);
								bw=new BufferedWriter(trashChannel);
								bw.append(displaySites.get(i)+"\n");
								bw.close();
							} catch (IOException e1) {
								e1.printStackTrace();
							}
							displaySites.remove(i);
						}

					}

					statusText="Successfully removed";

				}else {
					statusText="Nothing to remove";
				}
				File listDir=new File(System.getProperty("user.dir")+"\\Lists");

				update();
			}
		});
		add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(input.getText()==null) {
					statusText="Warning:no input";
				}else {
					statusText="input Successfully";
					displaySites.add(input.getText());
				}
				update();
				input.requestFocusInWindow();
			}
		});
	
	input.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(input.getText()==null) {
				statusText="Warning:no input";
			}else {
				statusText="input Successfully";
				displaySites.add(input.getText());
			}
			update();
		}
	});
}
	public static void update() {
		status.setText(statusText);
		String[] listData= (String[])displaySites.toArray(new String[displaySites.size()]);
		sites.setListData(listData);
		input.setText("");

	}
}
