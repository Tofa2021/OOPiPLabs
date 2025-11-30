package org.example.lab5.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class LoginView extends VBox {
    private TextField usernameField;
    private PasswordField passwordField;
    private Button loginButton;
    private Button registerButton;
    private Label messageLabel;

    public LoginView() {
        initializeView();
    }

    private void initializeView() {
        setSpacing(15);
        setPadding(new Insets(30));
        setAlignment(Pos.CENTER);

        Label titleLabel = new Label("Интернет-кафе");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        usernameField = new TextField();
        usernameField.setPromptText("Имя пользователя");
        usernameField.setMaxWidth(200);

        passwordField = new PasswordField();
        passwordField.setPromptText("Пароль");
        passwordField.setMaxWidth(200);

        loginButton = new Button("Войти");
        registerButton = new Button("Регистрация");

        HBox buttonBox = new HBox(10, loginButton, registerButton);
        buttonBox.setAlignment(Pos.CENTER);

        messageLabel = new Label();

        getChildren().addAll(
                titleLabel, usernameField, passwordField, buttonBox, messageLabel
        );
    }

    public TextField getUsernameField() { return usernameField; }
    public PasswordField getPasswordField() { return passwordField; }
    public Button getLoginButton() { return loginButton; }
    public Button getRegisterButton() { return registerButton; }
    public Label getMessageLabel() { return messageLabel; }
}