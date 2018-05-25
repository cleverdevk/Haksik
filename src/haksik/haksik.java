package haksik;

import java.io.IOException;
import java.util.Calendar;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class haksik {
	public static void main(String[] args) throws IOException {
		String[] each = {"조식(한식) : ","조식(양식) : ","중식(한식) : ","중식(양식) : ", "중식(특식) : ","석식(한식) : ","석식(양식) : "};
		Document doc = Jsoup.connect("http://dormitory.cau.ac.kr/garden/weekly_menu.php").get();
		Element e = doc.select("table").get(0);
		String[] sikdan = new String[50]; 
		Calendar cal = Calendar.getInstance();
		//System.out.println(e);
		int j=0;
		Elements e2 = e.select(".f11");
		//18부터, 27부터,(16.. 25..) 
		int k=0;
		for(Element ee :e2)
		{
			String temp = ee.text();//.replaceAll(" ", "\n");
			if(temp.equals("")) temp = "학식 없음";
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
		for(int i=0;i<j;i++) {
			if(i%7==cal.get(Calendar.DAY_OF_WEEK)-1) {
				System.out.println(sikdan[i]);
				System.out.println();
				line++;
				if(line==2 || line==5) System.out.println("-----------------------------------------------------------------------------------");
			}
		}
		System.out.println("-----------------------------------------------------------------------------------");
	}
}
