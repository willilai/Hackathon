import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.*;
public class GUI {
	static JFrame main; static BackGroundPanel bgp = new BackGroundPanel();
	static JPanel mainPanel,listsControl;static JTextField input = new JTextField(30);
	static String statusText,sitesList;
	static ArrayList<String> displaySites;
	static JLabel blank1,blank2,blank4,blank5;
	static JLabel tx = new JLabel("Website"),status;
	static JComboBox<String> favorLists;static JButton saveCurrentList;
	static JList sites;
	static JButton add,save,remove;
	static JCheckBox autoOrSchedule;
	static int copyNumber=1;
	static String copyFileName="List"+copyNumber;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		prepGUI();
		main.setVisible(true);
	}
	public static void prepGUI() {
		listsControl=new JPanel();
		favorLists= new JComboBox<String>();
		favorLists.addItem("Select Your List");
		JScrollPane scrollPane= new JScrollPane();
		remove = new JButton("remove");
		displaySites= new ArrayList<String>();
		saveCurrentList= new JButton("Save this List");
		mainPanel = new JPanel();
		main = new JFrame("Hi~(*･ω-q) ");
		main.setContentPane(bgp);
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
		autoOrSchedule= new JCheckBox("Schedule?");
		
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
		mainPanel.add(tx);
		mainPanel.add(input);
		mainPanel.add(add);mainPanel.add(remove);mainPanel.add(save);mainPanel.add(autoOrSchedule);
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

		
		
		
		remove.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(sites.getSelectedIndex()>=0) {
					for(int i=0;i<displaySites.size();i++) {
						if(displaySites.get(i)==sites.getSelectedValue()) {
							displaySites.remove(i);
						}
					}
					statusText="Successfully removed";

				}else {
					statusText="Nothing to remove";
				}
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
