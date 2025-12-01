package org.example.lab5.model;

import org.example.lab5.patterns.SearchStrategy;
import org.example.lab5.patterns.Publisher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CafeModel extends Publisher {
    private Map<String, User> users = new HashMap<>();
    private List<Computer> computers = new ArrayList<>();
    private Map<Computer, Session> activeSessions = new HashMap<>();
    private User currentUser;
    private SearchStrategy searchStrategy;

    public CafeModel() {
        initializeComputers();
    }

    private void initializeComputers() {
        computers.add(new Computer(1, "Gaming PC #1", 8.0, "Игровой"));
        computers.add(new Computer(2, "Gaming PC #2", 8.0, "Игровой"));
        computers.add(new Computer(3, "Standard PC #1", 4.0, "Стандартный"));
        computers.add(new Computer(4, "Standard PC #2", 4.0, "Стандартный"));
        notifyListeners("Инициализированы компьютеры");
    }

    public boolean registerUser(String username, String password, String email) {
        if (users.containsKey(username)) {
            notifyListeners("Попытка регистрации существующего пользователя: " + username);
            return false;
        }
        users.put(username, new User(username, password, email));
        notifyListeners("Зарегистрирован новый пользователь: " + username);
        return true;
    }

    public boolean loginUser(String username, String password) {
        User user = users.get(username);
        if (user == null || !user.getPassword().equals(password)) {
            notifyListeners("Неудачная попытка входа: " + username);
            return false;
        }
        currentUser = user;
        notifyListeners("Пользователь вошел в систему: " + username);
        return true;
    }

    public void logoutUser() {
        if (currentUser != null) {
            notifyListeners("Пользователь вышел из системы: " + currentUser.getUsername());
            currentUser = null;
        }
    }

    public boolean startSession(Computer computer) {
        if (currentUser == null) {
            notifyListeners("Попытка начать сессию без авторизации");
            return false;
        }
        if (!computer.isAvailable()) {
            notifyListeners("Попытка начать сессию на занятом компьютере: " + computer.getName());
            return false;
        }
        if (currentUser.getBalance() < computer.getPricePerHour()) {
            notifyListeners("Недостаточно средств для начала сессии: " + currentUser.getUsername());
            return false;
        }

        computer.setAvailable(false);
        Session session = new Session(currentUser, computer);
        activeSessions.put(computer, session);
        notifyListeners("Начата сессия: " + currentUser.getUsername() + " на " + computer.getName());
        return true;
    }

    public Session endSession(Computer computer) {
        Session session = activeSessions.get(computer);
        if (session == null) {
            notifyListeners("Попытка завершить несуществующую сессию");
            return null;
        }

        session.endSession();
        computer.setAvailable(true);

        double cost = session.calculateCost();
        User user = session.getUser();
        user.setBalance(user.getBalance() - cost);

        activeSessions.remove(computer);
        notifyListeners("Завершена сессия: " + user.getUsername() + " на " + computer.getName() + ", стоимость: $" + cost);

        return session;
    }

    public void addBalanceToCurrentUser(double amount) {
        if (currentUser != null && amount > 0) {
            currentUser.setBalance(currentUser.getBalance() + amount);
            notifyListeners("Пополнен баланс: " + currentUser.getUsername() + " на $" + amount);
        }
    }

    public void setSearchStrategy(SearchStrategy strategy) {
        this.searchStrategy = strategy;
    }

    public List<Computer> searchComputers(String query) {
        if (searchStrategy != null && query != null && !query.trim().isEmpty()) {
            List<Computer> results = searchStrategy.search(computers, query);
            notifyListeners("Выполнен поиск: '" + query + "', найдено: " + results.size() + " компьютеров");
            return results;
        }
        return getAllComputers();
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public List<Computer> getAllComputers() {
        return new ArrayList<>(computers);
    }
}