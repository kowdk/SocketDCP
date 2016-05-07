/**
 * @author xutao
 * @email xutao199218@163.com
 * @since 2016-01-13
 * @version 1.0
 */

public class ConfigHeader {

	public static final int headerLen = 16;// header length; fixed
	private char version;// 2 bytes
	private char cnfType;// 2 bytes
	private int magic;// 4 bytes
	private int pktSize;// 4 bytes
	private int time;// 4 bytes
	
	private byte[] buffer;
	
	public ConfigHeader() {
		// TODO Auto-generated constructor stub
	}
	
	//only pktSize should be calculated
	public ConfigHeader(char cnfType) {
		this.version = 0x0001;
		this.cnfType = cnfType;
		this.magic = 0xAABBCCDD;
		this.time = Utils.getCurTime();
	}
	
	public byte[] getBytes(){
		int offset = 0;
		buffer = new byte[headerLen];
		
		byte[] tmp;
		try {
			tmp = Utils.charToByteArray(this.version);
			System.out.println(tmp.length);
			System.arraycopy(tmp, 0, this.buffer, offset, tmp.length);
			offset += tmp.length;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {
			tmp = Utils.charToByteArray(this.cnfType);
			System.arraycopy(tmp, 0, this.buffer, offset, tmp.length);
			offset += tmp.length;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		tmp = Utils.tolh(this.magic);
		System.arraycopy(tmp, 0, this.buffer, offset, tmp.length);
		offset += tmp.length;

		tmp = Utils.tolh(this.pktSize);
		System.arraycopy(tmp, 0, this.buffer, offset, tmp.length);
		offset += tmp.length;

		tmp = Utils.tolh(this.time);
		System.arraycopy(tmp, 0, this.buffer, offset, tmp.length);
		offset += tmp.length;
		
		System.out.println("Header::Length = " + offset);
		return buffer;
	}
	
	public int getAttributesLength(){
		return headerLen;
	}
	
	public char getVersion() {
		return version;
	}

	public void setVersion(char version) {
		this.version = version;
	}

	public char getCnfType() {
		return cnfType;
	}

	public void setCnfType(char cnfType) {
		this.cnfType = cnfType;
	}

	public int getMagic() {
		return magic;
	}

	public void setMagic(int magic) {
		this.magic = magic;
	}

	public int getPktSize() {
		return pktSize;
	}

	public void setPktSize(int pktSize) {
		this.pktSize = pktSize;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "ConfigHeader [version=" + version + ", cnfType=" + cnfType
				+ ", magic=" + magic + ", pktSize=" + pktSize + ", time="
				+ time + "]";
	}
	
	
}
