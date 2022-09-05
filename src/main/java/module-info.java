module com.grupp7.spaceorbit {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.grupp7.spaceorbit to javafx.fxml;
    exports com.grupp7.spaceorbit;
}