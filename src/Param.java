
/**
 * @author xutao
 * @email xutao199218@163.com
 * @since 2016-01-13
 * @version 1.0
 */

public class Param {

	private char confType;// 配置类型，1查询 2增加 3重置 4删除
	private String ukey;// 16字节ukey
	private String name;// 用户姓名
	private String department;// 部门

	public Param(char confType) {
		// TODO Auto-generated constructor stub
		this.confType = confType;
	}
	
	public Param(char confType, String ukey) {
		// TODO Auto-generated constructor stub
		this.confType = confType;
		this.ukey = ukey;
	}

	public Param(char confType, String ukey, String name, String department) {
		this.confType = confType;
		this.ukey = ukey;
		this.name = name;
		this.department = department;
	}

	public char getConfType() {
		return confType;
	}

	public void setConfType(char confType) {
		this.confType = confType;
	}

	public String getUkey() {
		return ukey;
	}

	public void setUkey(String ukey) {
		this.ukey = ukey;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "Param Object : [confType=" + confType + ", ukey=" + ukey
				+ ", name=" + name + ", department=" + department + "]";
	}

}
