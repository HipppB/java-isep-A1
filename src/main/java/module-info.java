module ISEP.A1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;

    requires lombok;
    requires java.desktop;
    requires annotations;

    opens com.hippp.harrypotter to javafx.fxml;
    exports com.hippp.harrypotter;

    opens com.hippp.harrypotter.view to javafx.fxml;
    exports com.hippp.harrypotter.view;
    exports com.hippp.harrypotter.game;
    opens com.hippp.harrypotter.game to javafx.fxml;

}