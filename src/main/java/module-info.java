module hellofx {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.ClassicGames to javafx.fxml;
    exports org.ClassicGames;
}
