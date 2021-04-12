module ClassicGames {
    requires javafx.controls;
    requires javafx.fxml;
    requires nitrite;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.databind;

    opens org.ClassicGames to javafx.fxml;
    opens org.ClassicGames.model to com.fasterxml.jackson.databind;
    exports org.ClassicGames;
}
