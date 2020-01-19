package mathapp;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;

public class Read {

    public static void readFile() throws URISyntaxException, IOException {

        URL path = ClassLoader.getSystemResource("Questions.txt");
        File file = new File(path.toURI());
        BufferedReader br = new BufferedReader(new FileReader(file));
        
        String line; 
        String completeFile = "";

        while ((line = br.readLine()) != null) {
             completeFile = completeFile + "\n" + line;
        } 
        String questions[] = completeFile.split(";");
        String qLines[][] = new String[questions.length][];

        for(int a = 0; a < questions.length; a ++)
        {
            qLines[a] = questions[a].split("\n");
        }
        System.out.println(qLines[1][2]);


    }

} 