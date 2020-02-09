package mathapp;

import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;

public class MathApp extends Application
{
    public void start(Stage primaryStage)
    {
        Scene scene = new Scene(new Group());

        primaryStage.setScene(scene);
        primaryStage.setTitle("Math App");
        primaryStage.show();
    }
    public static void main(String args[])
    {
        launch(args);
    }

}