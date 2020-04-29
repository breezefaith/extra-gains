import java.awt.*;
import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        AnimationPanel p = new AnimationPanel();
        p.createNewShape(10,20);
        p.setCurrentWidth(30);
        p.setCurrentShapeType(2);
        p.createNewShape(10,20);
        p.setCurrentHeight(10);
        p.createNewShape(20,30);
        p.setCurrentShapeType(0);
        p.createNewShape(30,30);
        System.out.println(p.getSortedInfo());
    }
}
