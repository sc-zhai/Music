package haut.zsc.music.model;

import haut.zsc.music.utils.MD5Util;
import lombok.Data;

@Data
public class User {
	private int userId;
	private String email;
	private String password;
	private String validateCode;
	private String userName;


	public void setPassword(String password) {
		this.password = MD5Util.string2MD5(password);
	}
	
	

}
