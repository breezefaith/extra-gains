package des;

import java.util.Scanner;

public class CharacterExercise1 {

	public boolean judgeUpperOrLower(char ch) {
		return ch>='A'&&ch<='Z';
	}
	public static void main(String[] args) {
		CharacterExercise1 exercise1=new CharacterExercise1();
		
		Scanner scanner=new Scanner(System.in);
		char ch=scanner.next().charAt(0);
		boolean result=exercise1.judgeUpperOrLower(ch);
		if(result==true) {
			System.out.println(result);
			System.out.println(ch);
		}else {
			System.out.println(result);
			System.out.println((char)(ch-32));
		}
	}

}
