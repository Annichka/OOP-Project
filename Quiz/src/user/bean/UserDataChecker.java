package user.bean;

import java.util.StringTokenizer;

public class UserDataChecker {
	private User usr;
	
	public UserDataChecker(User usr) {
		this.usr = usr;
	}

	public String Check() {
		boolean u = CheckUser();
		boolean p = CheckPassword();
		boolean m = CheckMail();
		return !u ? "Wrong Name" : !p ? "Wrong Password" : !m ?  "Wrong Mail" : "OK";
	}
	
	private boolean CheckUser() {
		String name = usr.getUserName();
		if(name.length() < 3 || name.length() >= 20)
			return false;
		return true;
	}
	
	private boolean CheckPassword() {
		String pass = usr.getPassword();
		if(pass.length() < 4 || pass.length() > 15)
			return false;
		return true;
	}
	
	private boolean CheckMail() {
		String mail = usr.getEMail();
		StringTokenizer tok = new StringTokenizer(mail, ".@");
		if(tok.countTokens() >= 3)
			if(mail.contains("@") && mail.contains("."))
				return true;
		return false;
	}
}
