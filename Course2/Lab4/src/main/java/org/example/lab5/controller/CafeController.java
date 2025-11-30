package org.example.lab5.controller;

import org.example.lab5.model.CafeModel;
import org.example.lab5.model.Computer;
import org.example.lab5.model.Session;
import org.example.lab5.model.User;
import org.example.lab5.patterns.*;
import org.example.lab5.view.CafeView;
import org.example.lab5.view.LoginView;

public class CafeController implements Observer {
    private final CafeModel model;
    private final CafeView cafeView;
    private Runnable showLoginView;

    public CafeController(CafeModel model, CafeView cafeView, LoginView loginView) {
        this.model = model;
        this.cafeView = cafeView;

        model.addObserver(cafeView);
        setupEventHandlers();
    }

    private void setupEventHandlers() {
        cafeView.getLogoutButton().setOnAction(e -> handleLogout());
        cafeView.getSearchButton().setOnAction(e -> handleSearch());
        cafeView.getStartSessionButton().setOnAction(e -> handleStartSession());
        cafeView.getEndSessionButton().setOnAction(e -> handleEndSession());
        cafeView.getAddBalanceButton().setOnAction(e -> handleAddBalance());
    }

    private void handleLogout() {
        model.logoutUser();
        cafeView.getUserInfoLabel().setText("");
        showLoginView.run();
    }

    private void handleSearch() {
        String query = cafeView.getSearchField().getText();
        String searchType = cafeView.getSearchTypeComboBox().getValue();

        SearchStrategy strategy;
        if ("По типу".equals(searchType)) {
            strategy = new ComputerTypeSearchStrategy();
        } else {
            strategy = new NameSearchStrategy();
        }

        model.setSearchStrategy(strategy);
        cafeView.displayComputers(model.searchComputers(query));
    }

    private void handleStartSession() {
        Computer selectedComputer = cafeView.getComputersTable().getSelectionModel().getSelectedItem();
        if (selectedComputer != null) {
            if (model.startSession(selectedComputer)) {
                cafeView.getStatusLabel().setText("Сессия начата на компьютере: " + selectedComputer.getName());
                refreshComputers();
            } else {
                cafeView.getStatusLabel().setText("Не удалось начать сессию. Проверьте баланс.");
            }
        }
    }

    private void handleEndSession() {
        Computer selectedComputer = cafeView.getComputersTable().getSelectionModel().getSelectedItem();
        if (selectedComputer != null) {
            Session endedSession = model.endSession(selectedComputer);
            if (endedSession != null) {
                double cost = endedSession.calculateCost();
                cafeView.getStatusLabel().setText(
                        String.format("Сессия завершена. Стоимость: %.2f", cost)
                );
                refreshComputers();
                updateUserInfo();
            }
        }
    }

    private void handleAddBalance() {
        try {
            double amount = Double.parseDouble(cafeView.getAmountField().getText());
            if (amount > 0) {
                model.addBalanceToCurrentUser(amount);
                cafeView.getAmountField().clear();
                cafeView.getStatusLabel().setText("Баланс пополнен на: " + amount);
                updateUserInfo();
            }
        } catch (NumberFormatException e) {
            cafeView.getStatusLabel().setText("Введите корректную сумму");
        }
    }

    public void refreshComputers() {
        cafeView.displayComputers(model.getAllComputers());
        updateUserInfo();
    }

    private void updateUserInfo() {
        User currentUser = model.getCurrentUser();
        if (currentUser != null) {
            cafeView.getUserInfoLabel().setText(
                    String.format("Пользователь: %s | Баланс: %.2f",
                            currentUser.getUsername(),
                            currentUser.getBalance())
            );
        }
    }

    @Override
    public void update(String message) {
        cafeView.getStatusLabel().setText(message);
        System.out.println("Cafe System: " + message);
    }

    public void setShowLoginView(Runnable showLoginView) {
        this.showLoginView = showLoginView;
    }
}