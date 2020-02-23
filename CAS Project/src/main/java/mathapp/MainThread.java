package mathapp;

import mathapp.enums.ButtonEnum;
import mathapp.enums.SceneEnum;
import mathapp.enums.SubmissionEnum;
import mathapp.scene.QuizScene;

public class MainThread implements Runnable
{
    // private static String submittedAnswer;

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
                    MathApp.setButtonPressedEnum(ButtonEnum.BUTTON_NOT_PRESSED);
                    break;
                case BUTTON_TO_MAIN_MENU:
                    MathApp.setCurSceneEnum(SceneEnum.SCENE_MAIN_MENU);
                    MathApp.setButtonPressedEnum(ButtonEnum.BUTTON_NOT_PRESSED);
                    break;
                case BUTTON_QUIT:
                    MathApp.setRunning(false);
                    break;
            }

            switch (MathApp.getCurSceneEnum())
            {
                case SCENE_QUESTION:
                    checkAnswer();
                    break;
                default:
                    break;
            }
        }
    }

    private void checkAnswer()
    {
        try
        {
            synchronized(QuizScene.getAnswerString())
            {
                if (QuizScene.getAnswerSubmitted())
                {
                    String submittedAnswer = QuizScene.getAnswerString();

                    if (submittedAnswer.equals("yes"))
                    {
                        MathApp.setCurrentSubmissionEnum(SubmissionEnum.ANSWER_CORRECT);
                    }
                    else if (submittedAnswer.equals("ye"))
                    {
                        MathApp.setCurrentSubmissionEnum(SubmissionEnum.ANSWER_CLOSE);
                    }
                    else
                    {
                        MathApp.setCurrentSubmissionEnum(SubmissionEnum.ANSWER_INCORRECT);
                    }
                }
            }
        } catch (NullPointerException e)
        { }
    }
}