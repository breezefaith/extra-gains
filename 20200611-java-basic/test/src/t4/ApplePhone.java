package t4;

public class ApplePhone implements CameraPhone {
    @Override
    public void takePhoto() {
        System.out.println("take phone by Apple");
    }

    @Override
    public void call() {
        System.out.println("call by Apple");
    }

    @Override
    public void receive() {
        System.out.println("receive by Apple");
    }

    @Override
    public void sendMsg() {
        System.out.println("send message by Apple");
    }

    @Override
    public void receiveMsg() {
        System.out.println("receive message by Apple");
    }
}
