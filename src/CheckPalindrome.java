/**
 * Recursive solution to check for Palindrome
 */
public class CheckPalindrome {
	
	public static void main(String[] args) {

		CheckPalindrome driver = new CheckPalindrome();
		System.out.println(driver.checkPalindrome("abecba"));
		System.out.println(driver.checkPalindrome("eabba"));
		System.out.println(driver.checkPalindrome(""));
	}
	
	boolean checkPalindrome(String s) {
		
		if (s.length() == 1 || s.length() == 0) return true;
		
		if (s.charAt(0) != s.charAt(s.length()-1)) return false;
		
		return checkPalindrome(s.substring(1, s.length()-1));
	}
}
