package org.example.lab5.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class RegisterView extends VBox {
    private TextField usernameField;
    private PasswordField passwordField;
    private TextField emailField;
    private Button registerButton;
    private Button backButton;
    private Label messageLabel;

    public RegisterView() {
        initializeView();
    }

    private void initializeView() {
        setSpacing(15);
        setPadding(new Insets(30));
        setAlignment(Pos.CENTER);

        Label titleLabel = new Label("Регистрация");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        usernameField = new TextField();
        usernameField.setPromptText("Имя пользователя");
        usernameField.setMaxWidth(200);

        passwordField = new PasswordField();
        passwordField.setPromptText("Пароль");
        passwordField.setMaxWidth(200);

        emailField = new TextField();
        emailField.setPromptText("Email");
        emailField.setMaxWidth(200);

        registerButton = new Button("Зарегистрироваться");
        backButton = new Button("Назад");

        HBox buttonBox = new HBox(10, registerButton, backButton);
        buttonBox.setAlignment(Pos.CENTER);

        messageLabel = new Label();

        getChildren().addAll(
                titleLabel, usernameField, passwordField, emailField, buttonBox, messageLabel
        );
    }

    public TextField getUsernameField() { return usernameField; }
    public PasswordField getPasswordField() { return passwordField; }
    public TextField getEmailField() { return emailField; }
    public Button getRegisterButton() { return registerButton; }
    public Button getBackButton() { return backButton; }
    public Label getMessageLabel() { return messageLabel; }
}
