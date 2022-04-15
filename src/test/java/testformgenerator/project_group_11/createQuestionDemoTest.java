package testformgenerator.project_group_11;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.loadui.testfx.GuiTest;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils; //delete


import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class createQuestionDemoTest extends ApplicationTest {
    @Override
    public void start (Stage stage) throws Exception {
        Parent mainNode = FXMLLoader.load(Objects.requireNonNull(createQuestionDemo.class.getResource("srx/resources/testformgenerator.project_group_11/createQuestion.fxml")));
        stage.setScene(new Scene(mainNode));
        stage.show();
        stage.toFront();
    }

    @Before
    public void setUp () throws Exception {
    }

    @After
    public void tearDown () throws Exception {
        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }


    @Test //All inputs valid
    public void test_one(){
        //Label targetLabel = (Label) GuiTest.find("#invalidQuestionLabel");
        clickOn("submitButton");
        //WaitForAsyncUtils.waitForFxEvents();
        //assertEquals(targetLabel.getText(), "Invalid Question Input");
    }



    /**
     * Use glass robot.
     * Note: this must happen once before the class is loaded. Otherwise,
     * the very first test run uses the awt robot
     */
    /*
    @BeforeClass
    public static void config() throws Exception {
        System.getProperties().put("testfx.robot", "glass");
    }
     */
}