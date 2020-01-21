package mathapp.scene;

import javafx.beans.property.IntegerProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import mathapp.MathApp;
import mathapp.enums.ButtonEnum;
import mathapp.enums.SceneEnum;

public class QuestionScene extends MathScene
{
    private final double MIN_WIDTH = 800;
    private final double MIN_HEIGHT = 800;

    private IntegerProperty fontSize;
    private Font textFont;

    private Text questionText;

    private TextField answerTextField;

    private VBox centreVBox;

    private Button toMainMenuBtn;

    protected void initScene()
    {
        sceneEnum = SceneEnum.SCENE_QUESTION;

        textFont = new Font("Arial", 18); 

        questionText = new Text("What is my name?");
        questionText.setTextAlignment(TextAlignment.JUSTIFY);

        answerTextField = new TextField();
        answerTextField.setMaxSize(MIN_WIDTH / 2, MIN_HEIGHT / 2);

        centreVBox = new VBox(20, questionText, answerTextField);
        centreVBox.maxWidthProperty().bind(scene.widthProperty().subtract(centreVBox.getLayoutX()));
        centreVBox.maxHeightProperty().bind(scene.heightProperty().subtract(centreVBox.getLayoutY()));

        centreVBox.setAlignment(Pos.CENTER);
        VBox.setVgrow(questionText, Priority.ALWAYS);

        toMainMenuBtn = new Button("x");
        toMainMenuBtn.setMinWidth   (10);
        toMainMenuBtn.setPrefWidth  (25);
        toMainMenuBtn.setMinHeight  (10);
        toMainMenuBtn.setPrefHeight (25);

        AnchorPane mainPane = new AnchorPane(centreVBox, toMainMenuBtn);
        mainPane.setMaxSize(2000, 2000);
        mainPane.setMinSize(800, 600);

        AnchorPane.setLeftAnchor   (toMainMenuBtn, 10.0);
        AnchorPane.setTopAnchor    (toMainMenuBtn, 10.0);
        AnchorPane.setLeftAnchor   (centreVBox, 20.0);
        AnchorPane.setTopAnchor    (centreVBox, 20.0);
        AnchorPane.setRightAnchor  (centreVBox, 10.0);
        AnchorPane.setBottomAnchor (centreVBox, 10.0);

        scene = new Scene(mainPane);

        toMainMenuBtn.setOnAction(evt -> {
            MathApp.setButtonPressedEnum(ButtonEnum.BUTTON_TO_MAIN_MENU);
        });
    }
}