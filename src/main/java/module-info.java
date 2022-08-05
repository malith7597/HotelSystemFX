module com.example.hotelsystemfx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.hotelsystemfx to javafx.fxml;
    exports com.example.hotelsystemfx;
}