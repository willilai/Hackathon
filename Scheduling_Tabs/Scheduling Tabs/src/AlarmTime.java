import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class AlarmTime implements java.io.Serializable{
	Integer Year,Month,Day,Hour,Minute;
	int type;
	String file;
	boolean onOrOff;
	public AlarmTime(Integer Year,Integer Month,Integer Day,Integer Hour,Integer Minute,String file,int type) {//type 0=once,type 1=daily,type 2=weekly,type 3=monthly
		this.Month=Month;
		this.Day=Day;
		this.Hour=Hour;
		this.Minute=Minute;
		this.type=type;
		this.file=file;
		this.Year=Year;
		onOrOff=true;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LocalDate ld = LocalDate.now();
		System.out.println("\ntoday is "+ld);
		LocalTime lt = LocalTime.now();
		System.out.println("now is "+lt);
		LocalDateTime ldt = LocalDateTime.now();
		System.out.println("now is "+ldt);
		System.out.println(ld.toString().substring(5,7));
		System.out.println(Integer.valueOf(ld.toString().substring(5,7)));
		System.out.println(ld.toString().substring(8,10));
		System.out.println(lt.toString().substring(0,2));
		System.out.println(lt.toString().substring(3,5));
		  Date date=new Date();
		   SimpleDateFormat dateFm = new SimpleDateFormat("EEEE");
		   dateFm.format(date);
		   System.out.println(date);
		   System.out.println(ld.toString().substring(0,4));
		   try {
			System.out.println(toDate(21,2,2021));
			System.out.println(getDay()+"/"+getMon()+"/"+getYear());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean isOnOrOff() {
		return onOrOff;
	}
	public void setOnOrOff(boolean onOrOff) {
		this.onOrOff = onOrOff;
	}
	public void startClock() throws InterruptedException {
		///while(true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(type==0) {
				if(getYear()==Year&&getMon()==Month&&getDay()==Day&&getHour()==Hour&&getMinute()==Minute) {
					launch();
					Thread.sleep(60000);
				}
			}else if(type==1) {
				if(getHour()==Hour&&getMinute()==Minute) {
					launch();
					Thread.sleep(60000);
			}
		}else if(type==2) {
			try {
				String dateAssigned=toDate(Day, Month, Year);
				String dateCurrent=toDate(getDay(),getMon(),getYear());
				if(dateAssigned.equals(dateCurrent)&&getHour()==Hour&&getMinute()==Minute) {
					launch();
					Thread.sleep(60000);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}else if(type==3) {
		if(getDay()==Day&&getHour()==Hour&&getMinute()==Minute) {
			launch();
			Thread.sleep(60000);
	}
}
		}
		
	//}
	public static String getDate() {
		  Date date=new Date();
		   SimpleDateFormat dateFm = new SimpleDateFormat("EEEE");
		   dateFm.format(date);
		   return date.toString().substring(0,3);
	}
	public static int getYear() {
		LocalDate ld = LocalDate.now();
		return Integer.valueOf(ld.toString().substring(0,4));
	}
	public static int getMon() {
		LocalDate ld = LocalDate.now();
		return Integer.valueOf(ld.toString().substring(5,7));
	}
	public static int getDay() {
		LocalDate ld = LocalDate.now();
		return Integer.valueOf(Integer.valueOf(ld.toString().substring(8,10)));
	}
	public static int getHour() {
		LocalTime lt = LocalTime.now();
		return Integer.valueOf(Integer.valueOf(lt.toString().substring(0,2)));
	}
	public static int getMinute() {
		LocalTime lt = LocalTime.now();
		return Integer.valueOf(Integer.valueOf(lt.toString().substring(3,5)));
	}
	public void launch() {
		ArrayList<String> tests = new ArrayList<String>();
		try {
			BufferedReader bf = new BufferedReader(new FileReader(System.getProperty("user.dir")+"\\Lists\\"+file));
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
	public static String toDate(Integer d,Integer m,Integer y) throws Exception {
		String dd = ""+d;String mm=""+m;String yy=""+y;
		if(d<10) {
			dd=0+""+dd;
		}
		if(m<10) {
			mm=0+""+mm;
		}
		  String input_date=dd+"/"+mm+"/"+yy;
		  SimpleDateFormat format1=new SimpleDateFormat("dd/MM/yyyy");
		  Date dt1=format1.parse(input_date);
		  DateFormat format2=new SimpleDateFormat("EEEE"); 
		  String finalDay=format2.format(dt1);
		  return finalDay;
	}
	public String toString() {
		if(type==0) {
			return Day+"/"+Month+"/"+Year+"  "+Hour+":"+Minute+"  Once"+"  List:"+file;
		}else if(type==1) {
			return Hour+":"+Minute+"  Daily"+"  List:"+file;
		}else if(type==2) {
			try {
				return toDate(Day,Month,Year)+"  Weekly"+"  List:"+file;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(type==3) {
			return Day+"  "+Hour+":"+Minute+"  Monthly "+"  List:"+file;
		}
			return "ERROR";
		
	}

	
}
