package calculator;
import java.util.Scanner;

public class Main {
	private static Calculator calculator;
	private static Scanner scanner;
	
	public static void main(String[] args) {
		try {
			scanner=new Scanner(System.in);
			//scanner=new Scanner("测试文件的路径");
			calculator = new Calculator();
			calculator.setPrintWriter("D:\\result.txt");
			while (scanner.hasNext()) {
				calculator.input(scanner.nextLine());
				calculator.cal();
			}
			calculator.closePrintWriter();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
