package mathapp.scene;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import mathapp.enums.SceneEnum;
import mathapp.enums.SubmissionEnum;
import mathapp.scene.MathScene;

public class GraphScene extends MathScene {

    private final int BEG_WIDTH = 800;
    private final int BEG_HEIGHT = 800;

    private double mouseXInit;
    private double mouseYInit;
    private double centreXInit;
    private double centreYInit;

    private Pane mainPane;

    protected void initScene() {
        sceneEnum = SceneEnum.SCENE_GRAPH;

        mouseXInit = 0;
        mouseYInit = 0;

        Rectangle upLeft = new Rectangle(0, 0, Color.RED);
        Rectangle upRight = new Rectangle(0, 0, Color.BLUE);
        Rectangle downLeft = new Rectangle(0, 0, Color.GREEN);
        Rectangle downRight = new Rectangle(0, 0, Color.YELLOW);

        mainPane = new Pane();
        mainPane.setPrefWidth(BEG_WIDTH);
        mainPane.setPrefHeight(BEG_HEIGHT);

        DoubleProperty centreX = new SimpleDoubleProperty((double) BEG_WIDTH / 2);
        DoubleProperty centreY = new SimpleDoubleProperty((double) BEG_HEIGHT / 2);
        centreXInit = centreX.get();
        centreYInit = centreY.get();

        NumberBinding leftXBind = Bindings.min(centreX, new SimpleDoubleProperty(0));
        NumberBinding rightXBind = Bindings.max(centreX, new SimpleDoubleProperty(0));
        NumberBinding upYBind = Bindings.min(centreY, new SimpleDoubleProperty(0));
        NumberBinding downYBind = Bindings.max(centreY, new SimpleDoubleProperty(0));

        DoubleProperty leftWidthBind = new SimpleDoubleProperty((double) BEG_WIDTH / 2);
        DoubleProperty rightWidthBind = new SimpleDoubleProperty((double) BEG_WIDTH / 2);
        DoubleProperty upHeightBind = new SimpleDoubleProperty((double) BEG_HEIGHT / 2);
        DoubleProperty downHeightBind = new SimpleDoubleProperty((double) BEG_HEIGHT / 2);

        upLeft.xProperty().bind(leftXBind);
        upLeft.yProperty().bind(upYBind);
        upLeft.widthProperty().bind(leftWidthBind);
        upLeft.heightProperty().bind(upHeightBind);

        upRight.xProperty().bind(rightXBind);
        upRight.yProperty().bind(upYBind);
        upRight.widthProperty().bind(rightWidthBind);
        upRight.heightProperty().bind(upHeightBind);

        downLeft.xProperty().bind(leftXBind);
        downLeft.yProperty().bind(downYBind);
        downLeft.widthProperty().bind(leftWidthBind);
        downLeft.heightProperty().bind(downHeightBind);

        downRight.xProperty().bind(rightXBind);
        downRight.yProperty().bind(downYBind);
        downRight.widthProperty().bind(rightWidthBind);
        downRight.heightProperty().bind(downHeightBind);

        mainPane.getChildren().addAll(upLeft, upRight, downLeft, downRight);

        mainPane.widthProperty().addListener(lst -> {
            leftWidthBind.setValue(Math.max(Math.min(centreX.get(), mainPane.widthProperty().get()), 0));
            rightWidthBind.setValue(Math.max(Math.min(mainPane.widthProperty().get() - centreX.get(), mainPane.widthProperty().get()), 0));
        });

        mainPane.heightProperty().addListener(lst -> {
            upHeightBind.setValue(Math.max(Math.min(centreY.get(), mainPane.heightProperty().get()), 0));
            downHeightBind.setValue(Math.max(Math.min(mainPane.heightProperty().get() - centreY.get(), mainPane.heightProperty().get()), 0));
        });

        mainPane.setOnMousePressed(evt -> {
            mouseXInit = evt.getX();
            mouseYInit = evt.getY();

            centreXInit = centreX.get();
            centreYInit = centreY.get();
        });

        mainPane.setOnMouseDragged(evt -> {
            double mouseXChange = evt.getX() - mouseXInit;
            double mouseYChange = evt.getY() - mouseYInit;

            centreX.setValue(centreXInit + mouseXChange);
            centreY.setValue(centreYInit + mouseYChange);

            leftWidthBind.setValue(Math.max(Math.min(centreX.get(), mainPane.widthProperty().get()), 0));
            rightWidthBind.setValue(Math.max(Math.min(mainPane.widthProperty().get() - centreX.get(), mainPane.widthProperty().get()), 0));
            upHeightBind.setValue(Math.max(Math.min(centreY.get(), mainPane.heightProperty().get()), 0));
            downHeightBind.setValue(Math.max(Math.min(mainPane.heightProperty().get() - centreY.get(), mainPane.heightProperty().get()), 0));
        });
        
        scene = new Scene(mainPane);
    }

    public void resolveSubmission(SubmissionEnum submissionToResolve)
    {

    }
}