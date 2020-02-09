package mathapp;

import mathapp.enums.ButtonEnum;
import mathapp.enums.SceneEnum;
import mathapp.enums.SubmissionEnum;
import mathapp.scene.QuizScene;

public class MainThread implements Runnable
{
    private static String submittedAnswer;

    public MainThread()
    {
        submittedAnswer = "";
    }

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

            if (!submittedAnswer.equals(""))
            {
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

                submittedAnswer = "";
            }
            
        }
    }

    public static String getSubmittedAnswer()
    {
        return submittedAnswer;
    }

    public static void setSubmittedAnswer(String answerSubmitted)
    {
        submittedAnswer = answerSubmitted;
    }
}