package dsw.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtil {

	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static String getCurrentTime() {
		
		return sdf.format(new Date());
	}
	
	public static String getCurrentTime(long num){
		return sdf.format(new Date(num));
	}

	public static String converTime(String str) {
		if (null != str) {
			str = str.replaceAll("[A-Z]", " ");
		}
		return str;
	}
	
	/**
	 * 简单的ID生成器
	 * @return
	 */
	public static long getId(){
		return (new Date()).getTime();
	}
	
	public static void main(String[] args){
		long i=1545030886204L;
		Date date=new Date(i);
		System.out.println(date);
	}

}
