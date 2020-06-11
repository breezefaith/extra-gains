package t4;

public class HuaweiPhone implements CameraPhone {
    @Override
    public void takePhoto() {
        System.out.println("take phone by Huawei");
    }

    @Override
    public void call() {
        System.out.println("call by Huawei");
    }

    @Override
    public void receive() {
        System.out.println("receive by Huawei");
    }

    @Override
    public void sendMsg() {
        System.out.println("send message by Huawei");
    }

    @Override
    public void receiveMsg() {
        System.out.println("receive message by Huawei");
    }
}
