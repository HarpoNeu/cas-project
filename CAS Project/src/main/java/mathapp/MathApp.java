package mathapp;

import javafx.animation.*;
import javafx.application.*;
import javafx.stage.*;
import mathapp.enums.*;
import mathapp.scene.*;


public class MathApp extends Application
{
    private static boolean running;

    private static SceneEnum curSceneEnum;
    private static volatile ButtonEnum buttonPressedEnum;

    private static boolean sceneEnumChanged;

    private Stage stage;

    private AnimationTimer frameTimer;

    public void start(Stage primaryStage)
    {
        running = true;

        curSceneEnum = SceneEnum.QUESTION_SCENE;
        buttonPressedEnum = ButtonEnum.BUTTON_NOT_PRESSED;

        sceneEnumChanged = true;

        initFrameTimer();

        stage = primaryStage;
        stage.setTitle("Math App");
        stage.centerOnScreen();
        stage.show();

        Thread mainThread = new Thread(new MainThread());
        mainThread.start();
    }

    private void initFrameTimer()
    {
        frameTimer = new AnimationTimer()
        {
            @Override
            public void handle(long now) 
            {
                if (sceneEnumChanged)
                {
                    sceneEnumChanged = false;

                    switch (curSceneEnum)
                    {
                        case MAIN_MENU_SCENE:
                            stage.setScene(new MainMenuScene().getScene());
                            break;
                        case QUESTION_SCENE:
                            stage.setScene(new QuestionScene().getScene());
                            break;
                    }
                }

                if (!running)
                {
                    Platform.exit();
                }
            }
        };

        frameTimer.start();
    }

    public static boolean isRunning()
    {
        return running;
    }

    public static void setRunning(boolean nowRunning)
    {
        running = nowRunning;
    }

    public static SceneEnum getCurSceneEnum()
    {
        return curSceneEnum;
    }

    public static void setCurSceneEnum(SceneEnum newSceneEnum)
    {
        curSceneEnum = newSceneEnum;
        sceneEnumChanged = true;
    }

    public static ButtonEnum getButtonPressedEnum()
    {
        return buttonPressedEnum;
    }

    public static void setButtonPressedEnum(ButtonEnum buttonPressed)
    {
        buttonPressedEnum = buttonPressed;
    }
    public static void main(String args[])
    {
        launch(args);
    }

}