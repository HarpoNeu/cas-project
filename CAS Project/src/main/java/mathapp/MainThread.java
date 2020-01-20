package mathapp;

import mathapp.enums.*;

public class MainThread implements Runnable
{
    @Override
    public void run()
    {
        while (MathApp.isRunning())
        {
            switch (MathApp.getButtonPressedEnum())
            {
                case BUTTON_NOT_PRESSED:
                    break;
                case BUTTON_SCENE_MAIN_MENU_BTN_QUESTION:
                    MathApp.setCurSceneEnum(SceneEnum.QUESTION_SCENE);
                    break;
                case BUTTON_SCENE_QUESTION_BTN_MAIN_MENU:
                    MathApp.setCurSceneEnum(SceneEnum.MAIN_MENU_SCENE);
                    break;
                case BUTTON_SCENE_QUESTION_BTN_QUIT:
                    MathApp.setRunning(false);
                    break;
            }

            MathApp.setButtonPressedEnum(ButtonEnum.BUTTON_NOT_PRESSED);
        }
    }
}