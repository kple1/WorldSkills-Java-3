package Form;

import java.util.regex.Pattern;

public class TestZone {

	public static void main(String[] args) { //(?!.*(.)\\1\\1\\1)([a-zA-Z0-9!@#$%]){9,24}
		if (Pattern.matches(".*([a-zA-Z0-9])\\1{2,}.*", "123")) {
			System.out.println("X");
		} else {
			System.out.println("로그인 성공");
		}
	}
}
