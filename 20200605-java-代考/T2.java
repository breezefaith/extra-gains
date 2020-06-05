import java.awt.*;
import java.applet.*;

class Complex {
	private int realPart;
	private int imaginPart;
	
	public Complex(){
		realPart = 0;
		imaginPart = 0;
	}
	
	public Complex(int r, int i){
		realPart = r;
		imaginPart = i;
	}
	
	public Complex complexAdd(Complex a){
		return new Complex(realPart+a.realPart, imaginPart+a.imaginPart);
	}
	
	public String toString(){
		return realPart+"+"+imaginPart+"i";
	}
}
public class T2 extends Applet {
	public void paint(Graphics g){
		Complex a = new Complex(1,2);
		Complex b= new Complex(3,4);
		g.drawString(a.complexAdd(b).toString(), 50, 25);
	}
	
	public static void main(String [] args){
		Complex a = new Complex(1,2);
		Complex b= new Complex(3,4);
		
		System.out.println(a.complexAdd(b));
	}
}
