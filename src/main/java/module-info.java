module testformgenerator.project_group_11 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens testformgenerator.project_group_11 to javafx.fxml;
    exports testformgenerator.project_group_11;
}