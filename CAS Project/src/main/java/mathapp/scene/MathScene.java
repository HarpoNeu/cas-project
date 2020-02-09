package mathapp.scene;

import javafx.scene.Group;
import javafx.scene.Scene;
import mathapp.enums.SceneEnum;
import mathapp.enums.SubmissionEnum;

public abstract class MathScene
{   
    protected Scene scene;
    protected Group mainGroup;

    protected SceneEnum sceneEnum;

    public MathScene()
    {
        mainGroup = new Group();
        scene = new Scene(mainGroup, 800, 600);

        initScene();
    }

    public Scene getScene()
    {
        return scene;
    }

    public SceneEnum getSceneEnum()
    {
        return sceneEnum;
    }

    protected abstract void initScene();

    public abstract void resolveSubmission(SubmissionEnum submissionToResolve);
}