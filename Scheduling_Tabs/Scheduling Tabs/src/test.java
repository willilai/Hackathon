import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.io.*;
public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//AlarmTime testO= new AlarmTime(2021, 2, 21, 12, 0,"trash.txt" , 1);
		//writeObjectToFile(testO);
		//AlarmTime out = (AlarmTime) readObjectFromFile();
		//System.out.println(out.toString());
		//ArrayList<AlarmTime> j = new ArrayList<AlarmTime>();
		//j.add(new AlarmTime(2021, 2, 21, 12, 0,"trash.txt" , 1));
		//j.add(new AlarmTime(2020, 12, 30, 11, 0,"trash.txt" , 1));
		//j.add(new AlarmTime(2000, 5, 2, 1, 40,"trash.txt" , 1));
		//System.out.println(j);
		File f = new File("C:\\Users\\HP\\Downloads\\obj.txt");
		
		//writeObjectToFile(j,f.getAbsolutePath() );
		System.out.println(readObjectFromFile(f.getAbsolutePath()));
	}

public static void writeObjectToFile(Object obj,String dest)
    {
        File file =new File(dest);
        FileOutputStream out;
        try {
            out = new FileOutputStream(file);
            ObjectOutputStream objOut=new ObjectOutputStream(out);
            objOut.writeObject(obj);
            objOut.flush();
            objOut.close();
            System.out.println("write object success!");
        } catch (IOException e) {
            System.out.println("write object failed");
            e.printStackTrace();
        }
    }

public static Object readObjectFromFile(String fileName)
    {
        Object temp=null;
        File file =new File(fileName);
        FileInputStream in;
        try {
            in = new FileInputStream(file);
            ObjectInputStream objIn=new ObjectInputStream(in);
            temp=objIn.readObject();
            objIn.close();
            System.out.println("read object success!");
        } catch (IOException e) {
            System.out.println("read object failed");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return temp;
    }
}
