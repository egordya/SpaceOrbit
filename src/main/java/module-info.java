module com.grupp7.spaceorbit {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.json;


    opens com.grupp7.spaceorbit to javafx.fxml;
    exports com.grupp7.spaceorbit;

}