

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author xutao
 * @email xutao199218@163.com
 * @since 2016-01-13
 * @version 1.0
 */

public class Utils {
	
	/**
	 * 打印缓冲区内容，16字节形式
	 * */
	public static void printByteHex(byte[] arr){
		StringBuilder hexString = new StringBuilder();
		for(int i = 0; i < arr.length; i++){
			String temp = Integer.toHexString(arr[i] & 0xFF);
			if(temp.length() == 1){
				temp = "0" + temp;
			}
			hexString.append(" " + temp);
		}
		char[] buf = hexString.toString().toCharArray();
		for(int i=0; i<hexString.length(); i++){
			System.out.print(buf[i]);
			if(i % (3*8) == 0){
				System.out.print(" ");
			}
			if(i % (3*16) == 0){
				System.out.println();
			}
		}
		System.out.println();
	}
	
	/**
	 * 将int转换为byte数组
	 * */
	public static byte[] intToByte(int i) {  
		byte[] result = new byte[4];   
		result[0] = (byte)((i >> 24) & 0xFF);
		result[1] = (byte)((i >> 16) & 0xFF);
		result[2] = (byte)((i >> 8) & 0xFF); 
		result[3] = (byte)(i & 0xFF);
		return result;
	}
	
	/**
	 * 将char转化为Byte数组
	 * */
	public static byte[] charToByte(char c) {
        byte[] b = new byte[2];
        b[0] = (byte) ((c & 0xFF00) >> 8);
        b[1] = (byte) (c & 0xFF);
        return b;
    }
	
	/**
	 * 将char截断为byte
	 * */
	public static byte charToByte1(char c) {
        byte[] b = new byte[2];
        b[0] = (byte) ((c & 0xFF00) >> 8);
        b[1] = (byte) (c & 0xFF);
        return b[1];
    }
	
	/**
	 * 将主机序转换为网络序，int
	 */
	public static byte[] tolh(int i){
		byte[] result = new byte[4];   
		result[0] = (byte)((i >> 24) & 0xFF);
		result[1] = (byte)((i >> 16) & 0xFF);
		result[2] = (byte)((i >> 8) & 0xFF); 
		result[3] = (byte)(i & 0xFF);
		return result;
	}
	
	/**
	 * 将主机序转换为网络序，short
	 * */
	public static byte[] toLH(short n) {  
		  byte[] b = new byte[2];  
		  b[0] = (byte) (n & 0xff);  
		  b[1] = (byte) (n >> 8 & 0xff);  
		  return b;  
	}
	
	/**
	 * 将主机序转换为网络序，char
	 * */
	public static byte[] tolc(char c){
		byte[] result = new byte[2];
		result[0] = (byte)((c >> 8) & 0xFF); 
		result[1] = (byte)(c & 0xFF);
		return result;
	}
	
	/**
	 * 将主机序转换为网络序，char
	 * */
	public static byte[] charToByteArray(char c) throws Exception {
		ByteArrayOutputStream buf = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(buf);
		out.writeChar(c);
		byte[] b = buf.toByteArray();
		out.close();
		buf.close();
		return b;
	}
	
	/**
	 * 将时间字符串转换为int
	 * */
	public static int getCurTime(String time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		int stamp = 0;
		try {
			Date date = sdf.parse(time);
			stamp = (int) date.getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stamp;
	}
	
	/**
	 * 获取当前时间
	 * */
	public static int getCurTime() {
		Date date = new Date();
		int stamp = (int) date.getTime();
		return stamp;
	}
}
