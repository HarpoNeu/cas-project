package mathapp.scene;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;
import mathapp.enums.SceneEnum;
import mathapp.enums.SubmissionEnum;
import mathapp.scene.MathScene;

public class GraphScene extends MathScene {

    private final int BEG_WIDTH = 800;
    private final int BEG_HEIGHT = 800;

    private int horSpace;
    private int verSpace;

    private double mouseXInit;
    private double mouseYInit;
    private double centreXInit;
    private double centreYInit;

    private Pane mainPane;

    protected void initScene() {
        sceneEnum = SceneEnum.SCENE_GRAPH;

        mouseXInit = 0;
        mouseYInit = 0;

        horSpace = 375 / 5;
        verSpace = 375 / 5;

        mainPane = new Pane();
        mainPane.setPrefWidth(BEG_WIDTH);
        mainPane.setPrefHeight(BEG_HEIGHT);

        Rectangle upLeft = new Rectangle(0, 0, Color.TRANSPARENT);
        Rectangle upRight = new Rectangle(0, 0, Color.TRANSPARENT);
        Rectangle downLeft = new Rectangle(0, 0, Color.TRANSPARENT);
        Rectangle downRight = new Rectangle(0, 0, Color.TRANSPARENT);

        upLeft.setStrokeType(StrokeType.INSIDE);
        upLeft.setStroke(Color.BLACK);
        upRight.setStrokeType(StrokeType.INSIDE);
        upRight.setStroke(Color.BLACK);
        downLeft.setStrokeType(StrokeType.INSIDE);
        downLeft.setStroke(Color.BLACK);
        downRight.setStrokeType(StrokeType.INSIDE);
        downRight.setStroke(Color.BLACK);

        DoubleProperty centreX = new SimpleDoubleProperty((double) BEG_WIDTH / 2);
        DoubleProperty centreY = new SimpleDoubleProperty((double) BEG_HEIGHT / 2);
        centreXInit = centreX.get();
        centreYInit = centreY.get();

        IntegerProperty centreXOffset = new SimpleIntegerProperty(5);
        centreXOffset.bind(centreX.subtract(mainPane.widthProperty()).divide(horSpace));

        IntegerProperty centreYOffset = new SimpleIntegerProperty(5);
        centreYOffset.bind(centreY.subtract(mainPane.heightProperty()).divide(verSpace));

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

        Text numberHor[] = new Text[11];

        for (int i = 0; i < numberHor.length; i++)
        {
            numberHor[i] = new Text(Integer.toString(5 - i));

            NumberBinding textXBindSpec = Bindings.subtract(centreX, 
                Bindings.multiply(new SimpleDoubleProperty(horSpace), centreXOffset.add(i)));
            
            numberHor[i].xProperty().bind(textXBindSpec);
            numberHor[i].yProperty().bind(centreY.subtract(5));

            StringProperty textProperty = new SimpleStringProperty();
            textProperty.bind(centreXOffset.add(i).multiply(-1).asString());

            numberHor[i].textProperty().bind(textProperty);
        }

        mainPane.getChildren().addAll(numberHor);

        Text numberVer[] = new Text[11];

        for (int i = 0; i < numberVer.length; i++)
        {
            numberVer[i] = new Text(Integer.toString(5 - i));

            NumberBinding textYBindSpec = Bindings.subtract(centreY, 
                Bindings.multiply(new SimpleDoubleProperty(verSpace), centreYOffset.add(i)));
            
            numberVer[i].xProperty().bind(centreX.add(5));
            numberVer[i].yProperty().bind(textYBindSpec);

            StringProperty textProperty = new SimpleStringProperty();
            textProperty.bind(centreYOffset.add(i).asString());

            numberVer[i].textProperty().bind(textProperty);
        }

        mainPane.getChildren().addAll(numberVer);

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