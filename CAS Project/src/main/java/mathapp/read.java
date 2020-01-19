package mathapp;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Scanner;

public class Read {

    public static void readFile() throws URISyntaxException, IOException {

        URL path = ClassLoader.getSystemResource("Questions.txt");
        File file = new File(path.toURI());
        BufferedReader br = new BufferedReader(new FileReader(file));
        Scanner input = new Scanner(System.in);
        System.out.print("Answer: ");
        String answer = input.nextLine();
        String line; 
        String completeFile = "";
        Boolean correct = false;

        while ((line = br.readLine()) != null) {
             completeFile = completeFile + "\n" + line;
        } 
        String questions[] = completeFile.split(";");
        String qLines[][] = new String[questions.length][];
        for(int a = 0; a < questions.length; a++)
        {
            qLines[a] = questions[a].split("\n");
            System.out.println(answer);
            if(answer.equals("4"))
            {
                correct = true;
                break;
            }
        }
        if(correct == false)
        {
            System.out.println("Incorrect");
        }
        else{
            System.out.println("Correct");
        }
        


    }

} 