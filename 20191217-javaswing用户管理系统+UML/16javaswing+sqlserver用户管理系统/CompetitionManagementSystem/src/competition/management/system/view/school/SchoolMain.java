package competition.management.system.view.school;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;

public class SchoolMain {
    public static void main(String[] args) throws InvocationTargetException, InterruptedException {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                LoginFrame loginFrame = new LoginFrame();
                loginFrame.setVisible(true);
            }
        });
    }
}
