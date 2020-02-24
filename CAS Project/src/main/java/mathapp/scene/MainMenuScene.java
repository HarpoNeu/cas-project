package mathapp.scene;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import mathapp.MathApp;
import mathapp.enums.ButtonEnum;
import mathapp.enums.SceneEnum;
import mathapp.enums.SubmissionEnum;

public class MainMenuScene extends MathScene
{
    private Button toQuestionBtn;
    private Button tempBtn;
    private Button quitBtn;
    private HBox btnBox;

    protected void initScene()
    {
        sceneEnum = SceneEnum.SCENE_MAIN_MENU;

        toQuestionBtn = new Button("Go to Question Scene");
        tempBtn = new Button("Temp Button");
        quitBtn = new Button("Quit");

        btnBox = new HBox(25, toQuestionBtn, tempBtn, quitBtn);

        mainGroup.getChildren().add(btnBox);

        toQuestionBtn.setOnAction(evt -> {
            MathApp.setButtonPressedEnum(ButtonEnum.BUTTON_TO_QUESTION);
        });
        tempBtn.setOnAction(evt -> {
            MathApp.setButtonPressedEnum(ButtonEnum.BUTTON_TEMP);
        });
        quitBtn.setOnAction(evt -> {
            MathApp.setButtonPressedEnum(ButtonEnum.BUTTON_QUIT);
        });
    }

    public void resolveSubmission(SubmissionEnum submissionToResolve)
    {
        
    }
}