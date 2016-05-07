import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;

/**
 * @author xutao
 * @email xutao199218@163.com
 * @since 2016-01-13
 * @version 1.0
 */

public class Control {

	private final String ip = "172.16.18.190";
	private final int port = 10502;
	
	private final char configQuery = 0x0001;
	private final char configAdd = 0x0002;
	private final char configReset = 0x0003;
	private final char configDelete = 0x0004;

	/**
	 * 查询UKey，下发header， 返回header + 4字节status + 16字节Ukey
	 */
	public void configQuery(){
		Param param = new Param(this.configQuery);
		
		//construct the config query buffer
		byte[] buffer = new Config(param).getBuffer();
		
		Utils.printByteHex(buffer);
		//send the config query buffer
		int length = ConfigHeader.headerLen + 4 + 16;
		byte[] receive = sendConfig(buffer, length);
		String ukey = "";
		if(receive == null){
			System.out.println("socket receive failed");
		}
		//Utils.printByteHex(receive);
		byte status = receive[ConfigHeader.headerLen + 4 - 1];
		if(status == 0x00){
			System.out.println("query success");
			byte[] tmp = Arrays.copyOfRange(receive, ConfigHeader.headerLen + 4, ConfigHeader.headerLen + 20);
			ukey = new String(tmp);
			System.out.println("status = [" + status + "], ukey = [" + ukey + "]");
		}else{
			System.out.println("query failed, status = " + status);
		}
	}
	
	/**
	 * 注册UKey 下发header + 3个TLV， 返回header + 4字节status + 16字节Ukey
	 */
	public void configAdd(){
		//acquire the param to construct the buffer
		String ukey = "1E725D350003000A";
		String name = "xutao";
		String department = "工程三部";
		Param param = new Param(this.configAdd, ukey, name, department);
		
		//construct the config query buffer
		byte[] buffer = new Config(param).getBuffer();
		
		Utils.printByteHex(buffer);
		//send the config query buffer
		int length = ConfigHeader.headerLen + 4 + 16;
		
		byte[] receive = sendConfig(buffer, length);
		if(receive == null || receive.length < ConfigHeader.headerLen + 4){
			System.out.println("socket receive failed");
		}
		byte status = receive[ConfigHeader.headerLen + 4 - 1];
		System.out.println("status == " + status);
		if(status == 0x00){
			System.out.println("add success");
		}else{
			System.out.println("add failed, status = " + status);
		}
	}
	
	/**
	 * 重置UKey 下发header + 16字节Key值， 返回header + 4字节status + 16字节Ukey
	 */
	public void configReset(){
		
		//acquire the param to construct the buffer
		String ukey = "1E725D358003000B";
		Param param = new Param(this.configReset, ukey);
		
		//construct the config query buffer
		byte[] buffer = new Config(param).getBuffer();
		Utils.printByteHex(buffer);
		
		//send the config query buffer
		int length = ConfigHeader.headerLen + 4 + 16;
		byte[] receive = sendConfig(buffer, length);
		if(receive == null || receive.length < ConfigHeader.headerLen + 4){
			System.out.println("socket receive failed");
		}
		byte status = receive[ConfigHeader.headerLen + 4 - 1];
		if(status == 0x00){
			System.out.println("reset success");
		}else{
			System.out.println("add failed, status = " + status);
		}
	}
	
	/**
	 * 删除UKey，下发header + 16字节Key值， 返回header + 4字节status + 16字节Ukey 
	 */
	public void configDelete(){
		
		//acquire the param to construct the buffer
		String ukey = "1E725D358003000B";
		Param param = new Param(this.configDelete, ukey);
		
		//construct the config query buffer
		byte[] buffer = new Config(param).getBuffer();
		//Utils.printByteHex(buffer);
		
		//send the config query buffer
		int length = ConfigHeader.headerLen + 4 + 16;
		byte[] receive = sendConfig(buffer, length);
		if(receive == null || receive.length < ConfigHeader.headerLen + 4){
			System.out.println("socket receive failed");
		}
		byte status = receive[ConfigHeader.headerLen + 4 - 1];
		if(status == 0x00){
			System.out.println("delete success");
		}else{
			System.out.println("delete failed, status = " + status);
		}
	}
	
	public Control(){
		
	}

	/**
	 * 发送buffer，接收消息并返回一个receive数组
	 * param1 buffer: 要下发的config
	 * param2 receiveLength: 接收的byte数组的长度
	 */
	public byte[] sendConfig(byte[] buffer, int receiveLength) {
		if(buffer == null || buffer.length == 0){
			System.out.println("Buffer is null");
			return null;
		}
		byte[] receive = new byte[receiveLength];
		Socket sock = null;
		try {
			sock = new Socket(ip, port);
			// write to socket
			sock.setSoTimeout(60 * 1000);
			sock.getOutputStream().write(buffer);
			//Thread.sleep(1000);
			
			// read from socket
			DataInputStream input = new DataInputStream(sock.getInputStream());
			input.readFully(receive);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				sock.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return (receive==null) ? null : receive;
	}

	public static void main(String[] args) {
		Control con = new Control();
		//con.configQuery();
		con.configAdd();
		//con.configReset();
		//con.configDelete();
		
	}

}
