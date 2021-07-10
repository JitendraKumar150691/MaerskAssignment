
public class ValidatePassword {

	public static void main(String[] args) {
		
		System.out.print(validatePassword("Jitendra"));
	}
	
	public static boolean validatePassword(String password) {
		
		if (password == null)
			return false;
		
		int length =0;
		boolean haveUpperCase =false;
		boolean haveLowerCase =false;
		boolean haveDigit =false;
		
		for (int i = 0; i < password.length(); i++) {
			length+=1;
			
			if (!haveUpperCase && Character.isUpperCase(password.charAt(i))) {
				haveUpperCase = true;
			} else if (!haveLowerCase && Character.isLowerCase(password.charAt(i))) {
				haveLowerCase = true;
			} else if(!haveDigit && Character.isDigit(password.charAt(i))) {
				haveDigit =true;
			}
		}
		
		if (length > 8 &&  haveLowerCase)
			return true;
		
		if (haveLowerCase && haveUpperCase)
			return true;
		
		if (haveLowerCase && haveDigit)
			return true;
		
		return false;
	}
}
