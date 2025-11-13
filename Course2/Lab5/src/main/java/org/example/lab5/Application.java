package org.example.lab5;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.example.lab5.controller.AuthController;
import org.example.lab5.controller.CafeController;
import org.example.lab5.model.CafeModel;
import org.example.lab5.view.CafeView;
import org.example.lab5.view.LoginView;
import org.example.lab5.view.RegisterView;

public class Application extends javafx.application.Application {
    private CafeModel model;
    private LoginView loginView;
    private RegisterView registerView;
    private CafeView cafeView;
    private AuthController authController;
    private CafeController cafeController;
    private Stage primaryStage;
    private Scene mainScene;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

        model = new CafeModel();
        loginView = new LoginView();
        registerView = new RegisterView();
        cafeView = new CafeView();

        mainScene = new Scene(new StackPane(), 800, 600);
        primaryStage.setScene(mainScene);

        authController = new AuthController(model, loginView, registerView);
        cafeController = new CafeController(model, cafeView, loginView);

        authController.setOnLoginSuccess(this::showCafeView);
        authController.setShowRegisterView(this::showRegisterView);
        authController.setShowLoginView(this::showLoginView);
        cafeController.setShowLoginView(this::showLoginView);

        showLoginView();

        primaryStage.setTitle("Интернет-кафе");
        primaryStage.show();
    }

    private void showLoginView() {
        mainScene.setRoot(loginView);
        primaryStage.setWidth(400);
        primaryStage.setHeight(300);
    }

    private void showRegisterView() {
        mainScene.setRoot(registerView);
        primaryStage.setWidth(400);
        primaryStage.setHeight(350);
    }

    private void showCafeView() {
        cafeController.refreshComputers();
        mainScene.setRoot(cafeView);
        primaryStage.setWidth(800);
        primaryStage.setHeight(600);
    }

    public static void main(String[] args) {
        launch(args);
    }
}