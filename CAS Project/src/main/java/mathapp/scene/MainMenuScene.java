package mathapp.scene;

import javafx.scene.control.*;
import mathapp.MathApp;

public class MainMenuScene extends MathScene
{
    protected void initScene()
    {
        Button toQuestionSceneBtn = new Button("Go to Question Scene");
        mainGroup.getChildren().add(toQuestionSceneBtn);

        toQuestionSceneBtn.setOnAction(evt -> {
            MathApp.setButtonPressedEnum(mathapp.enums.ButtonEnum.BUTTON_SCENE_MAIN_MENU_BTN_QUESTION);
        });
    }
}