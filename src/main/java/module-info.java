module com.grupp7.spaceorbit {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.json;
    requires javafx.graphics;


    opens com.grupp7.spaceorbit to javafx.fxml;
    exports com.grupp7.spaceorbit;
    exports com.grupp7.spaceorbit.controllers;
    opens com.grupp7.spaceorbit.controllers to javafx.fxml;
    exports model.modelObjects;
    opens model.modelObjects to javafx.fxml;
    exports com.grupp7.spaceorbit.views;
    opens com.grupp7.spaceorbit.views to javafx.fxml;
    exports utilitys;
    opens utilitys to javafx.fxml;



}