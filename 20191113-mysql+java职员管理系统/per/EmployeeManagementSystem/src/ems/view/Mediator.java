package ems.view;

public class Mediator {
    private AddProfileController detailController;
    private MainController mainController;

    public Mediator(AddProfileController detailController, MainController mainController) {
        this.detailController = detailController;
        this.mainController = mainController;
    }


}
