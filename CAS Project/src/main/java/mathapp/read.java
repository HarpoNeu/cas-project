package mathapp;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;

public class read {

    public static void readFile() throws URISyntaxException, IOException {

        URL path = ClassLoader.getSystemResource("Questions.txt");
        File file = new File(path.toURI());
        BufferedReader questions = new BufferedReader(new FileReader(file));
        
        String st; 
        while ((st = questions.readLine()) != null) {
          System.out.println(st); 
        } 

    }

} 