package haksik;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class haksik {
	public static void main(String[] args) throws IOException {
		String[] each = {"조식(한식)\r\n","조식(양식)\r\n","중식(한식)\r\n","중식(양식)\r\n", "중식(특식)\r\n","석식(한식)\r\n","석식(양식)\r\n"};
		Document doc = Jsoup.connect("http://dormitory.cau.ac.kr/garden/weekly_menu.php").get();
		Element e = doc.select("table").get(0);
		String[] sikdan = new String[50]; 
		Calendar cal = Calendar.getInstance();
		File file = new File("D:\\Javascript_Projects\\haksik\\information.txt");
		File bfile = new File("D:\\Javascript_Projects\\haksik\\binformation.txt");
		File lfile = new File("D:\\Javascript_Projects\\haksik\\linformation.txt");
		File dfile = new File("D:\\Javascript_Projects\\haksik\\dinformation.txt");
		
		BufferedWriter bfw = new BufferedWriter(new FileWriter(file));
		BufferedWriter bbfw = new BufferedWriter(new FileWriter(bfile));
		BufferedWriter lbfw = new BufferedWriter(new FileWriter(lfile));
		BufferedWriter dbfw = new BufferedWriter(new FileWriter(dfile));
		
		//System.out.println(e);
		int j=0;
		Elements e2 = e.select(".f11");
		//18부터, 27부터,(16.. 25..) 
		int k=0;
		for(Element ee :e2)
		{
			String temp = ee.text();//.replaceAll(" ", "\n");
			if(temp.equals("")) temp = "학식이없어요(훌쩍)";
			if(temp.substring(0, 2).equals("한식") || temp.substring(0,2).equals("평일")|| temp.substring(0,2).equals("양식"))
			{
				
			}
			else {
				sikdan[j++] = each[k/7] + temp;
				k++;
			}
		}
		int line=0;
		System.out.println("************************************************************************************");
		System.out.println(" *********************************  오 늘 의  기 식 ************************************");
		System.out.println("************************************************************************************");
		System.out.println("-----------------------------------------------------------------------------------");
		int count=0;
		int dayofweek;
		switch(cal.get(Calendar.DAY_OF_WEEK)) {
		case 1:
			dayofweek = 6;
			break;
		default:
			dayofweek = cal.get(Calendar.DAY_OF_WEEK)-2;
		}
		for(int i=0;i<j;i++) {
			if(i%7==dayofweek) {
				System.out.println(sikdan[i]);
				bfw.write(sikdan[i].replaceAll(" ", "\r\n"));
				bfw.newLine();
				if(count<2) { 
					bbfw.write(sikdan[i].replaceAll(" ", "\r\n"));
					bbfw.newLine();
					bbfw.write("----------------------------\r\n");
					count++;
				}
				else if(count<5) {
					lbfw.write(sikdan[i].replaceAll(" ", "\r\n"));
					lbfw.newLine();
					count++;
					lbfw.write("----------------------------\r\n");
				}
				else
				{
					dbfw.write(sikdan[i].replaceAll(" ", "\r\n"));
					dbfw.newLine();
					count++;
					dbfw.write("----------------------------\r\n");
				}
				System.out.println();
				bfw.write("----------------------------\r\n");
				line++;
				if(line==2 || line==5) System.out.println("-----------------------------------------------------------------------------------");
			}
		}
		bfw.close();
		bbfw.close();
		lbfw.close();
		dbfw.close();
		System.out.println("-----------------------------------------------------------------------------------");
	}
}
