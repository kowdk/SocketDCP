/**
 * @author xutao
 * @email xutao199218@163.com
 * @since 2016-01-13
 * @version 1.0
 */
public class TLVContent {
	
	private byte tag;
	private char len;
	private String value;
	private byte[] buffer;
	
	public TLVContent() {
		// TODO Auto-generated constructor stub
	}
	
	public TLVContent(byte tag, char len, String value) {
		super();
		this.tag = tag;
		this.len = len;
		this.value = value;
	}
	
	public byte[] getBytes(){
		int offset = 0;
		int bufLen = getAttributesLength();
		buffer = new byte[bufLen];
		
		buffer[offset++] = this.getTag();
		
		byte[] tmp;
		try {
			tmp = Utils.charToByteArray(this.getLen());
			System.arraycopy(tmp, 0, this.buffer, offset, tmp.length);
			offset += tmp.length;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		tmp = this.getValue().getBytes();
		System.arraycopy(tmp, 0, this.buffer, offset, tmp.length);
		offset += tmp.length;
		
		System.out.println("TLV::Length = " + offset + ", TLV::value = " + value);
		return buffer;
	}
	
	public int getAttributesLength(){
		int totalLength = 0;
		if(null == value || value.getBytes().length == 0){
			return 0;
		}
		totalLength += 1;
		totalLength += 2;
		totalLength += value.getBytes().length;
		return totalLength;
	}

	public byte getTag() {
		return tag;
	}

	public void setTag(byte tag) {
		this.tag = tag;
	}

	public char getLen() {
		return len;
	}

	public void setLen(char len) {
		this.len = len;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "TLVContent [tag=" + tag + ", len=" + len + ", value=" + value
				+ "]";
	}
}
