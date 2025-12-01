package org.example.lab5.controller;

import org.example.lab5.model.CafeModel;
import org.example.lab5.patterns.Listener;
import org.example.lab5.view.LoginView;
import org.example.lab5.view.RegisterView;

public class AuthController implements Listener {
    private final CafeModel model;
    private final LoginView loginView;
    private final RegisterView registerView;
    private Runnable onLoginSuccess;
    private Runnable showRegisterView;
    private Runnable showLoginView;

    public AuthController(CafeModel model, LoginView loginView, RegisterView registerView) {
        this.model = model;
        this.loginView = loginView;
        this.registerView = registerView;

        model.addListener(this);
        setupEventHandlers();
    }

    private void setupEventHandlers() {
        loginView.getLoginButton().setOnAction(e -> handleLogin());
        loginView.getRegisterButton().setOnAction(e -> showRegisterView.run());

        registerView.getRegisterButton().setOnAction(e -> handleRegister());
        registerView.getBackButton().setOnAction(e -> showLoginView.run());
    }

    private void handleLogin() {
        String username = loginView.getUsernameField().getText();
        String password = loginView.getPasswordField().getText();

        if (username.isEmpty() || password.isEmpty()) {
            loginView.getMessageLabel().setText("Заполните все поля");
            return;
        }

        if (model.loginUser(username, password)) {
            loginView.getUsernameField().clear();
            loginView.getPasswordField().clear();
            loginView.getMessageLabel().setText("");
            onLoginSuccess.run();
        } else {
            loginView.getMessageLabel().setText("Неверное имя пользователя или пароль");
        }
    }

    private void handleRegister() {
        String username = registerView.getUsernameField().getText();
        String password = registerView.getPasswordField().getText();
        String email = registerView.getEmailField().getText();

        if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
            registerView.getMessageLabel().setText("Заполните все поля");
            return;
        }

        if (model.registerUser(username, password, email)) {
            registerView.getMessageLabel().setText("Регистрация успешна! Теперь войдите в систему.");
            registerView.getUsernameField().clear();
            registerView.getPasswordField().clear();
            registerView.getEmailField().clear();
            showLoginView.run();
        } else {
            registerView.getMessageLabel().setText("Пользователь с таким именем уже существует");
        }
    }

    @Override
    public void update(String message) {
        System.out.println("Auth System: " + message);
    }

    public void setOnLoginSuccess(Runnable onLoginSuccess) {
        this.onLoginSuccess = onLoginSuccess;
    }

    public void setShowRegisterView(Runnable showRegisterView) {
        this.showRegisterView = showRegisterView;
    }

    public void setShowLoginView(Runnable showLoginView) {
        this.showLoginView = showLoginView;
    }
}