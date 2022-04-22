package testformgenerator.project_group_11;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class createTestDemo extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(createQuestionDemo.class.getResource("createTestUI.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 590, 400);
        stage.setTitle("Create Test");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}