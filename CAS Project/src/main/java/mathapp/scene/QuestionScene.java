package mathapp.scene;

import javafx.scene.control.*;
import javafx.scene.layout.*;
import mathapp.enums.*;
import mathapp.MathApp;

public class QuestionScene extends MathScene
{
    protected void initScene()
    {
        Button toMainMenuBtn = new Button("Main Menu");
        Button quitBtn = new Button("Quit");

        HBox btnBox = new HBox(25, toMainMenuBtn, quitBtn);

        mainGroup.getChildren().add(btnBox);

        toMainMenuBtn.setOnAction(evt -> {
            MathApp.setButtonPressedEnum(ButtonEnum.BUTTON_SCENE_QUESTION_BTN_MAIN_MENU);
        });
        quitBtn.setOnAction(evt -> {
            MathApp.setButtonPressedEnum(ButtonEnum.BUTTON_SCENE_QUESTION_BTN_QUIT);
        });
    }
}