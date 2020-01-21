package mathapp.scene;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import mathapp.MathApp;
import mathapp.enums.ButtonEnum;
import mathapp.enums.SceneEnum;

public class MainMenuScene extends MathScene
{
    private Button toQuestionBtn;
    private Button quitBtn;
    private HBox btnBox;

    protected void initScene()
    {
        sceneEnum = SceneEnum.SCENE_MAIN_MENU;

        toQuestionBtn = new Button("Go to Question Scene");
        quitBtn = new Button("Quit");

        btnBox = new HBox(25, toQuestionBtn, quitBtn);

        mainGroup.getChildren().add(btnBox);

        toQuestionBtn.setOnAction(evt -> {
            MathApp.setButtonPressedEnum(ButtonEnum.BUTTON_TO_QUESTION);
        });
        quitBtn.setOnAction(evt -> {
            MathApp.setButtonPressedEnum(ButtonEnum.BUTTON_QUIT);
        });
    }
}