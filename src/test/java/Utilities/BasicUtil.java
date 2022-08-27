package Utilities;

import org.apache.commons.lang3.RandomStringUtils;

public class BasicUtil {

	public static String randomString() {
		String generatedString =RandomStringUtils.randomAlphabetic(10);
		return generatedString;
	}
	
	public static String randomNumber(int len) {
		String generatedString =RandomStringUtils.randomNumeric(len) ;
		return generatedString;
	}
	
//	public static void main(String[] args) {
//		BasicUtil bas = new BasicUtil();
//		System.out.println(bas.randomNumber());
//	}
	
}
