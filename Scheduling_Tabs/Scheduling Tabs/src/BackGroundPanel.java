import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FilenameFilter;
import java.util.*;
public class BackGroundPanel extends JPanel{
	ArrayList<Image> backPics;String pics;ArrayList<String> dialogs;
	File resourceDir;
	Image[] backPics2;
	JFrame jf;
	int n;int c;Image random;
	public BackGroundPanel(File resource,JFrame jf) {
		// TODO Auto-generated constructor stub
		this.resourceDir=resource;
		this.jf=jf;
		
		backPics = new ArrayList<Image>();
		
		String[] images = resource.list(new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				// TODO Auto-generated method stub
				if(name.contains(".jpg")||name.contains(".png")||name.contains(".jpeg")||name.contains(".bmp")) {
					return true;
				}else
				return false;
			}
		});
		backPics2=new Image[images.length];
		for(int i=0;i<images.length;i++) {
			//System.out.println(images[i]);
			backPics2[i] = new ImageIcon(resource.getAbsolutePath()+"\\"+images[i]).getImage();
		}
		n=(int)((images.length)*Math.random());
		jf.setContentPane(this);
		random= backPics2[n];
		c=1;
		if(random.getWidth(null)>2000||random.getHeight(null)>1100) {
			c=2;
		}else if(random.getWidth(null)>4000||random.getHeight(null)>2200) {
			c=4;
		}else {
		    c=1;
		}
		this.setSize(random.getWidth(null)/c,random.getHeight(null)/c);
		jf.setSize(random.getWidth(null)/c,random.getHeight(null)/c);
		System.out.println("Resized");
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(random, 0, 0,random.getWidth(null)/c,random.getHeight(null)/c, null);
		
		
	}
	public int getW() {
		return random.getWidth(null)/c;
	}
	public int getH() {
		return random.getHeight(null)/c;
	}

}
