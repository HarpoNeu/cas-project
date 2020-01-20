package mathapp.scene.text;

import javafx.scene.text.*;

public class QuestionText extends Text
{
    private static volatile String qText = "";

    public static String getQuestion()
    {
        return qText;
    }

    public static void setQuestion(String newQ)
    {
        qText = newQ;
    }

    public QuestionText()
    {
        super("");
    }
}