package Test;

public class Dois_em_Dois {
	static int n = 324;
	static String s = String.valueOf(n);

	public static void main(String[] args) {
		//s = s.substring(0, 1) + " " + s.substring(1, s.length());
		s = "+ " + s.substring(0, s.length());
		System.out.println(s);
	}

}
