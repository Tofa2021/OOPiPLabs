module org.example.lab5 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;

    opens org.example.lab5 to javafx.fxml;
    exports org.example.lab5;
    exports org.example.lab5.controller;
    exports org.example.lab5.model;
    exports org.example.lab5.view;
    exports org.example.lab5.patterns;
    opens org.example.lab5.controller to javafx.fxml;
}