
/**
 * @author xutao
 * @email xutao199218@163.com
 * @since 2016-01-13
 * @version 1.0
 */

public class Config {

	private byte[] buffer;
	
	public Config(Param param) {
		// TODO Auto-generated constructor stub
		switch(param.getConfType()){
			case 0x0001:{
				configQueryBuffer(param);
				break;
			}
			case 0x0002:{
				configAddBuffer(param);
				break;
			}
			case 0x0003:{
				configResetBuffer(param);
				break;
			}
			case 0x0004:{
				configDeleteBuffer(param);
				break;
			}
			default:
				break;
		}
	}
	
	private void configQueryBuffer(Param param){
		
		//查询只传输消息头
		ConfigHeader header = new ConfigHeader(param.getConfType());
		header.setPktSize(header.getAttributesLength());
		
		this.buffer = header.getBytes();
		System.out.println("ConfigQueryBuffer::bufferLength = " + this.buffer.length);
		
		header = null;
	}
	
	private void configAddBuffer(Param param){
		
		//calculate header length
		ConfigHeader header = new ConfigHeader(param.getConfType());
		int totalLength = 0;
		totalLength += header.getAttributesLength();
		
		byte tag = 0x01;
		char len = (char) (param.getDepartment().getBytes().length);
		String value = param.getDepartment();
		TLVContent tlvDepartment = new TLVContent(tag, len, value);
		totalLength += tlvDepartment.getAttributesLength();
		
		tag = 0x02;
		len = (char) (param.getName().getBytes().length);
		value = param.getName();
		TLVContent tlvName = new TLVContent(tag, len, value);
		totalLength += tlvName.getAttributesLength();
		
		tag = 0x03;
		len = (char) (param.getUkey().getBytes().length);
		value = param.getUkey();
		TLVContent tlvUKey = new TLVContent(tag, len, value);
		totalLength += tlvUKey.getAttributesLength();
		
		System.out.println("ConfigAddBuffer::totalLength = " + totalLength);
		header.setPktSize(totalLength);
		
		//calculate header length done, construct the buffer
		this.buffer = new byte[totalLength];
		int offset = 0;
		byte[] tmp = header.getBytes();
		System.arraycopy(tmp, 0, this.buffer, offset, tmp.length);
		offset += tmp.length;
		
		tmp = tlvDepartment.getBytes();
		System.arraycopy(tmp, 0, this.buffer, offset, tmp.length);
		offset += tmp.length;
		
		tmp = tlvName.getBytes();
		System.arraycopy(tmp, 0, this.buffer, offset, tmp.length);
		offset += tmp.length;
		
		tmp = tlvUKey.getBytes();
		System.arraycopy(tmp, 0, this.buffer, offset, tmp.length);
		offset += tmp.length;
		
		//release the needles
		header = null;
		tlvDepartment = null;
		tlvName = null;
		tlvUKey = null;	
	}
	
	private void configResetBuffer(Param param){
		//calculate header length
		ConfigHeader header = new ConfigHeader(param.getConfType());
		int totalLength = 0;
		totalLength += header.getAttributesLength();
		
		byte tag = 0x01;
		char len = (char) (param.getUkey().getBytes().length);
		String value = param.getUkey();
		TLVContent tlvUKey = new TLVContent(tag, len, value);
		totalLength += tlvUKey.getAttributesLength();
		System.out.println("ConfigResetBuffer::totalLength = " + totalLength);
		header.setPktSize(totalLength);
		
		//calculate header length done, construct the buffer
		this.buffer = new byte[totalLength];
		int offset = 0;
		byte[] tmp = header.getBytes();
		System.arraycopy(tmp, 0, this.buffer, offset, tmp.length);
		offset += tmp.length;
		
		tmp = tlvUKey.getBytes();
		System.arraycopy(tmp, 0, this.buffer, offset, tmp.length);
		offset += tmp.length;
		
		//release the needles
		header = null;
		tlvUKey = null;
	}
	
	private void configDeleteBuffer(Param param){
		//calculate header length
		ConfigHeader header = new ConfigHeader(param.getConfType());
		int totalLength = 0;
		totalLength += header.getAttributesLength();
		
		byte tag = 0x01;
		char len = (char) (param.getUkey().getBytes().length);
		String value = param.getUkey();
		TLVContent tlvUKey = new TLVContent(tag, len, value);
		totalLength += tlvUKey.getAttributesLength();
		System.out.println("ConfigDeleteBuffer::totalLength = " + totalLength);
		header.setPktSize(totalLength);
		
		//calculate header length done, construct the buffer
		this.buffer = new byte[totalLength];
		int offset = 0;
		byte[] tmp = header.getBytes();
		System.arraycopy(tmp, 0, this.buffer, offset, tmp.length);
		offset += tmp.length;
		
		tmp = tlvUKey.getBytes();
		System.arraycopy(tmp, 0, this.buffer, offset, tmp.length);
		offset += tmp.length;
		
		//release the needles
		header = null;
		tlvUKey = null;
	}

	public byte[] getBuffer() {
		return this.buffer;
	}
}
