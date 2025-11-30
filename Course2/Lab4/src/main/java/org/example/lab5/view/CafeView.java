package org.example.lab5.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import org.example.lab5.model.Computer;
import org.example.lab5.patterns.Observer;

public class CafeView extends BorderPane implements Observer {
    private TableView<Computer> computersTable;
    private TextField searchField;
    private Button searchButton;
    private Button logoutButton;
    private Button startSessionButton;
    private Button endSessionButton;
    private Button addBalanceButton;
    private ComboBox<String> searchTypeComboBox;
    private TextField amountField;
    private Label statusLabel;
    private Label userInfoLabel;

    public CafeView() {
        initializeView();
    }

    private void initializeView() {
        HBox topPanel = new HBox(10);
        topPanel.setPadding(new Insets(10));

        userInfoLabel = new Label();
        logoutButton = new Button("Выйти");
        topPanel.getChildren().addAll(userInfoLabel, logoutButton);

        setTop(topPanel);

        VBox centerPanel = new VBox(10);
        centerPanel.setPadding(new Insets(10));

        Text title = new Text("Компьютеры интернет-кафе");
        title.setFont(Font.font(18));

        HBox searchPanel = new HBox(10);
        searchField = new TextField();
        searchField.setPromptText("Введите название или тип...");
        searchButton = new Button("Поиск");

        searchTypeComboBox = new ComboBox<>();
        searchTypeComboBox.getItems().addAll("По названию", "По типу");
        searchTypeComboBox.setValue("По названию");

        searchPanel.getChildren().addAll(searchField, searchTypeComboBox, searchButton);

        computersTable = new TableView<>();
        initializeTable();

        HBox sessionButtons = new HBox(10);
        startSessionButton = new Button("Начать сессию");
        endSessionButton = new Button("Завершить сессию");
        sessionButtons.getChildren().addAll(startSessionButton, endSessionButton);

        HBox balancePanel = new HBox(10);
        amountField = new TextField();
        amountField.setPromptText("Сумма");
        amountField.setPrefWidth(100);
        addBalanceButton = new Button("Пополнить баланс");
        balancePanel.getChildren().addAll(new Label("Пополнение баланса:"), amountField, addBalanceButton);

        statusLabel = new Label();
        statusLabel.setStyle("-fx-text-fill: green;");

        centerPanel.getChildren().addAll(title, searchPanel, computersTable, sessionButtons, balancePanel, statusLabel);
        setCenter(centerPanel);
    }

    private void initializeTable() {
        TableColumn<Computer, String> nameCol = new TableColumn<>("Название");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Computer, String> typeCol = new TableColumn<>("Тип");
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));

        TableColumn<Computer, Double> priceCol = new TableColumn<>("Цена/час");
        priceCol.setCellValueFactory(new PropertyValueFactory<>("pricePerHour"));

        TableColumn<Computer, String> statusCol = new TableColumn<>("Статус");
        statusCol.setCellValueFactory(cellData -> {
            Computer computer = cellData.getValue();
            String status = computer.isAvailable() ? "Свободен" : "Занят";
            return new javafx.beans.property.SimpleStringProperty(status);
        });

        computersTable.getColumns().addAll(nameCol, typeCol, priceCol, statusCol);
    }

    public void displayComputers(java.util.List<Computer> computers) {
        ObservableList<Computer> observableList = FXCollections.observableArrayList(computers);
        computersTable.setItems(observableList);
    }

    @Override
    public void update(String message) {
        statusLabel.setText(message);
        computersTable.refresh();
    }

    public TableView<Computer> getComputersTable() { return computersTable; }
    public TextField getSearchField() { return searchField; }
    public Button getSearchButton() { return searchButton; }
    public Button getLogoutButton() { return logoutButton; }
    public Button getStartSessionButton() { return startSessionButton; }
    public Button getEndSessionButton() { return endSessionButton; }
    public Button getAddBalanceButton() { return addBalanceButton; }
    public ComboBox<String> getSearchTypeComboBox() { return searchTypeComboBox; }
    public TextField getAmountField() { return amountField; }
    public Label getUserInfoLabel() { return userInfoLabel; }
    public Label getStatusLabel() { return statusLabel; }
}