package mathapp;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import mathapp.enums.ButtonEnum;
import mathapp.enums.SceneEnum;
import mathapp.enums.SubmissionEnum;
import mathapp.scene.MainMenuScene;
import mathapp.scene.MathScene;
import mathapp.scene.QuizScene;


public class MathApp extends Application
{
    private static boolean running;

    private static volatile SceneEnum currentSceneEnum;
    private static volatile ButtonEnum buttonPressedEnum;
    private static volatile SubmissionEnum currentSubmissionEnum;

    private static MathScene currentScene;

    private Stage stage;

    private AnimationTimer frameTimer;

    public void start(Stage primaryStage)
    {
        running = true;
        stage = primaryStage;

        currentSceneEnum = SceneEnum.SCENE_MAIN_MENU;
        buttonPressedEnum = ButtonEnum.BUTTON_NOT_PRESSED;
        currentSubmissionEnum = SubmissionEnum.NO_SUBMISSION;

        setScene(currentSceneEnum);

        initFrameTimer();

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
                if (currentScene.getSceneEnum() != currentSceneEnum)
                {
                    setScene(currentSceneEnum);
                }

                if (currentSubmissionEnum != SubmissionEnum.NO_SUBMISSION)
                {
                    currentScene.resolveSubmission(currentSubmissionEnum);
                    currentSubmissionEnum = SubmissionEnum.NO_SUBMISSION;
                }

                if (!running)
                {
                    Platform.exit();
                }
            }
        };

        frameTimer.start();
    }

    public void setScene(SceneEnum newSceneEnum)
    {
        switch (newSceneEnum)
        {
            case SCENE_MAIN_MENU:
                currentScene = new MainMenuScene();
                break;
            case SCENE_QUESTION:
                currentScene = new QuizScene();
                break;
        }

        stage.setScene(currentScene.getScene());
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
        return currentSceneEnum;
    }

    public static void setCurSceneEnum(SceneEnum newSceneEnum)
    {
        currentSceneEnum = newSceneEnum;
    }

    public static ButtonEnum getButtonPressedEnum()
    {
        return buttonPressedEnum;
    }

    public static void setButtonPressedEnum(ButtonEnum buttonPressed)
    {
        buttonPressedEnum = buttonPressed;
    }

    public static SubmissionEnum getCurrentSubmissionEnum()
    {
        return currentSubmissionEnum;
    }

    public static void setCurrentSubmissionEnum(SubmissionEnum newSubmissionEnum)
    {
        currentSubmissionEnum = newSubmissionEnum;
    }
    
    public static void main(String args[])
    {
        launch(args);
    }

}