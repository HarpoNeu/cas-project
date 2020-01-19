package mathapp;

import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;

public class MathApp extends Application
{
    public void start(Stage primaryStage)
    {
        Group grp = new Group();

        Scene scene = new Scene(grp, 800, 600);

        Button btn = new Button("Test Button");
        grp.getChildren().add(btn);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Math App");
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    public static void main(String args[])
    {
        launch(args);
    }
}