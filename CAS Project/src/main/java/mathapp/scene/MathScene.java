package mathapp.scene;

import javafx.scene.*;

public abstract class MathScene
{
    protected Scene scene;
    protected Group mainGroup;

    public MathScene()
    {
        mainGroup = new Group();
        scene = new Scene(mainGroup);

        initScene();
    }

    public Scene getScene()
    {
        return scene;
    }

    protected abstract void initScene();
}