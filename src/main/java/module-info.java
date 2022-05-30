module com.example.cryptochart {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;


    opens com.example.cryptochart to javafx.fxml;
    exports com.example.cryptochart;
}