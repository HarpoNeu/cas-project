package mathapp;

import mathapp.enums.ButtonEnum;
import mathapp.enums.SceneEnum;

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
                case BUTTON_TO_QUESTION:
                    MathApp.setCurSceneEnum(SceneEnum.SCENE_QUESTION);
                    break;
                case BUTTON_TO_MAIN_MENU:
                    MathApp.setCurSceneEnum(SceneEnum.SCENE_MAIN_MENU);
                    break;
                case BUTTON_QUIT:
                    MathApp.setRunning(false);
                    break;
            }

            MathApp.setButtonPressedEnum(ButtonEnum.BUTTON_NOT_PRESSED);
        }
    }
}