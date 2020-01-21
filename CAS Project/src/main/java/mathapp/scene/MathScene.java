package mathapp.scene;

import javafx.scene.Group;
import javafx.scene.Scene;
import mathapp.enums.SceneEnum;

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
}