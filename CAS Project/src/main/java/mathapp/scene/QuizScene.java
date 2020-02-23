package mathapp.scene;

import javafx.beans.property.IntegerProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import mathapp.MainThread;
import mathapp.MathApp;
import mathapp.enums.ButtonEnum;
import mathapp.enums.SceneEnum;
import mathapp.enums.SubmissionEnum;

public class QuizScene extends MathScene
{
    private final double MIN_WIDTH = 800;
    private final double MIN_HEIGHT = 800;

    private IntegerProperty fontSize;
    private Font textFont;

    private Text questionText;
    private Text answerText;

    private TextField answerTextField;

    private VBox centreVBox;

    private Button toMainMenuBtn;

    private static String answerString;
    private static boolean answerSubmitted;

    protected void initScene()
    {
        sceneEnum = SceneEnum.SCENE_QUESTION;

        answerString = "";
        answerSubmitted = false;

        textFont = new Font("Arial", 18); 

        questionText = new Text("What is my name?");
        questionText.setTextAlignment(TextAlignment.JUSTIFY);
        questionText.setFont(textFont);

        answerText = new Text("No Answer Submitted");
        answerText.setTextAlignment(TextAlignment.JUSTIFY);
        answerText.setFont(textFont);

        answerTextField = new TextField();
        answerTextField.setMaxSize(MIN_WIDTH / 2, MIN_HEIGHT / 2);

        centreVBox = new VBox(20, questionText, answerTextField, answerText);
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
        answerTextField.setOnAction(evt -> {
            if (!answerSubmitted)
            {
                synchronized(answerString)
                {
                    answerSubmitted = true;
                    answerString = answerTextField.getText();
                }
                answerTextField.setText("");
            }
        });
    }

    public void resolveSubmission(SubmissionEnum submissionToResolve)
    {
        switch (submissionToResolve)
        {
            case ANSWER_CORRECT:
                resolveAnswerCorrect();
                break;
            case ANSWER_INCORRECT:
                resolveAnswerIncorrect();
                break;
            case ANSWER_CLOSE:
                resolveAnswerClose();
                break;
            default:
                break;
        }
    }

    private void resolveAnswerCorrect()
    {
        answerText.setText("Answer Correct");
        answerText.setFill(Color.GREEN);
    }

    private void resolveAnswerIncorrect()
    {
        answerText.setText("Answer Incorrect");
        answerText.setFill(Color.RED);
    }

    private void resolveAnswerClose()
    {
        answerText.setText("Answer Close");
        answerText.setFill(Color.DARKKHAKI);
    }

    public static boolean getAnswerSubmitted()
    {
        boolean prevAnswerSubmitted = answerSubmitted;
        answerSubmitted = false;
        return prevAnswerSubmitted;
    }

    public static String getAnswerString()
    {
        return answerString;
    }
}