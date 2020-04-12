package mathapp.scene;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
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

    private final int BEG_HOR_SPACE = 100;
    private final int BEG_VER_SPACE = 100;

    private final int MIN_HOR_SPACE = 40;
    private final int MIN_VER_SPACE = 40;

    private final int MID_HOR_SPACE = 200;
    private final int MID_VER_SPACE = 200;

    private final int MAX_HOR_SPACE = 400;
    private final int MAX_VER_SPACE = 400;

    private DoubleProperty centreX;
    private DoubleProperty centreY;
    private IntegerProperty centreXOffset;
    private IntegerProperty centreYOffset;

    private DoubleProperty horSpace;
    private DoubleProperty verSpace;

    private double mouseXInit;
    private double mouseYInit;
    private double centreXInit;
    private double centreYInit;

    private double horZero;
    private double verZero;

    private Text numberHor[];
    private Text numberVer[];

    private Pane mainPane;

    protected void initScene() {
        sceneEnum = SceneEnum.SCENE_GRAPH;

        mouseXInit = 0;
        mouseYInit = 0;

        horSpace = new SimpleDoubleProperty(BEG_HOR_SPACE);
        verSpace = new SimpleDoubleProperty(BEG_VER_SPACE);

        horZero = 0;
        verZero = 0;

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

        centreX = new SimpleDoubleProperty((double) BEG_WIDTH / 2);
        centreY = new SimpleDoubleProperty((double) BEG_HEIGHT / 2);
        centreXInit = centreX.get();
        centreYInit = centreY.get();

        centreXOffset = new SimpleIntegerProperty(5);
        centreXOffset.bind(centreX.subtract(mainPane.widthProperty()).divide(horSpace));

        centreYOffset = new SimpleIntegerProperty(5);
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

        createText();

        Text zeroText = new Text("0");

        zeroText.xProperty().bind(centreX.add(5));
        zeroText.yProperty().bind(centreY.subtract(5));

        mainPane.getChildren().add(zeroText);

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

            createText();

            leftWidthBind.setValue(Math.max(Math.min(centreX.get(), mainPane.widthProperty().get()), 0));
            rightWidthBind.setValue(Math.max(Math.min(mainPane.widthProperty().get() - centreX.get(), mainPane.widthProperty().get()), 0));
            upHeightBind.setValue(Math.max(Math.min(centreY.get(), mainPane.heightProperty().get()), 0));
            downHeightBind.setValue(Math.max(Math.min(mainPane.heightProperty().get() - centreY.get(), mainPane.heightProperty().get()), 0));
        });

        mainPane.setOnScroll(evt -> {
            horSpace.setValue(Math.max(1, horSpace.get() + evt.getDeltaY()));
            verSpace.setValue(Math.max(1, verSpace.get() + evt.getDeltaY()));

            if (horSpace.get() < MIN_HOR_SPACE)
            {
                horSpace.setValue(MAX_HOR_SPACE);
                horZero++;
            }
            else if (horSpace.get() > MAX_HOR_SPACE)
            {
                horSpace.setValue(MIN_HOR_SPACE);
                horZero--;
            }
            if (verSpace.get() < MIN_VER_SPACE)
            {
                verSpace.setValue(MAX_VER_SPACE);
                verZero++;
            }
            else if (verSpace.get() > MAX_VER_SPACE)
            {
                verSpace.setValue(MIN_VER_SPACE);
                verZero--;
            }

            createText();
        });
        
        scene = new Scene(mainPane);
    }

    public void resolveSubmission(SubmissionEnum submissionToResolve)
    {

    }

    private void createText()
    {
        try
        {
            mainPane.getChildren().removeAll(numberHor);
            mainPane.getChildren().removeAll(numberVer);
        } catch (NullPointerException e)
        {

        }

        int horCount = mainPane.widthProperty().divide(horSpace).intValue() + 1;
        numberHor = new Text[horCount];

        for (int i = 0; i < numberHor.length; i++)
        {
            numberHor[i] = new Text();

            NumberBinding textXBindSpec = Bindings.subtract(centreX, 
                Bindings.multiply(horSpace, centreXOffset.add(i)));
            
            numberHor[i].xProperty().bind(textXBindSpec);
            numberHor[i].yProperty().bind(centreY.subtract(5));

            int number = centreXOffset.add(i).multiply(-1).get();
            String text = "";
            if (number != 0)
            {
                text = formatNum(number, horZero);
            }
            numberHor[i].setText(text);
        }

        int verCount = mainPane.heightProperty().divide(verSpace).intValue() + 1;
        numberVer = new Text[verCount];

        for (int i = 0; i < numberVer.length; i++)
        {
            numberVer[i] = new Text();

            NumberBinding textYBindSpec = Bindings.subtract(centreY, 
                Bindings.multiply(verSpace, centreYOffset.add(i)));
            
            numberVer[i].xProperty().bind(centreX.add(5));
            numberVer[i].yProperty().bind(textYBindSpec);

            int number = centreYOffset.add(i).get();
            String text = "";
            if (number != 0)
            {
                text = formatNum(number, horZero);
            }
            numberVer[i].setText(text);
        }

        mainPane.getChildren().addAll(numberHor);
        mainPane.getChildren().addAll(numberVer);
    }

    private String formatNum(int num, double mul)
    {
        String str = "";
        boolean neg = num < 0 ? true : false;

        num = Math.abs(num);

        if (mul > 3 || mul < -3)
        {
            str = String.format("%dE%d", num, (int) mul);
        }
        else if (mul >= 0)
        {
            str = Integer.toString(num);
            for (int i = 0; i < mul; i++)
            {   
                str += "0";
            }
        }
        else if (mul < 0)
        {
            str = Integer.toString(num);
            for (int i = -1; i > mul; i--)
            {
                str = "0" + str;
            }
            str = "0." + str;
        }

        if (neg)
        {
            str = "-" + str;
        }

        return str;
    }
}