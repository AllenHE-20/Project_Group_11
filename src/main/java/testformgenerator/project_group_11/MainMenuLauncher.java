package testformgenerator.project_group_11;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuLauncher extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(createQuestionDemo.class.getResource("mainMenu.fxml"));
        DBMgrHolder dbSetup = DBMgrHolder.getInstance();
        DBMgr data = new DBMgr();
        QuestionBank qb = new QuestionBank("Bank 1");
        Question q = new Question("What is today's date?", "July 24th, 2022", "August 16th, 2022", "January 24th, 2022", "April 21st, 2022");
        Question q1 = new Question("What is today's date?", "July 24th, 2022", "August 16th, 2022", "January 24th, 2022", "April 21st, 2022");
        qb.addNewQuestion(q);
        qb.addNewQuestion(q1);
        data.addBank(qb);
        dbSetup.setDatabase(data);

        Scene scene = new Scene(fxmlLoader.load(), 590, 400);
        stage.setTitle("Test Creator");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
