module org.example.game2d_v1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.game2d_v1 to javafx.fxml;
    exports org.example.game2d_v1;
}