package simulator;

import simulator.entity.Manager;
import simulator.view.InitFrame;

/**
 * 启动程序
 */
public class Main {
    public static void main(String[] args) {
        Manager manager = new Manager();

        InitFrame initFrame = new InitFrame();
        initFrame.setManager(manager);
        initFrame.setVisible(true);
        initFrame.load();
    }
}
