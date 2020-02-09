package mathapp;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Scanner;

// y no rename

public class Read {

    public static void readFile() throws URISyntaxException, IOException {

        URL path = ClassLoader.getSystemResource("Questions.txt");
        File file = new File(path.toURI());
        BufferedReader br = new BufferedReader(new FileReader(file));
        Scanner input = new Scanner(System.in);
        System.out.println("Answer: ");
        String answer = input.nextLine();
        String line; 
        String completeFile = "";
        String difficulty = "2"; // will allow user to input a difficulty between 1-10 to get questions within that difficulty

        Boolean correct = false;

        while ((line = br.readLine()) != null) {
             completeFile = completeFile + "\n" + line;
        } 
        String questions[] = completeFile.split(";");
        String qLines[][] = new String[questions.length][];

        
        for(int a = 0; a < questions.length; a++)
        {
            qLines[a] = questions[a].split("\n");
            if(qLines[a][3] == difficulty)
            {
                //allow these questions to be displayed.
                //put them into an array
            }
            //to add if statement to check whether a button was clicked to check the answer
            if(qLines[a][2].equals(answer)) //checking to see if the answer was correct
            {
                System.out.println("Correct");
                break;
            }
            else{
                System.out.println("Incorrect");
            }
        }
        for(int all = 0; all < questions.length; all++)
        {
            
                
           
        } 
        
        
        br.close();
        input.close();

    }

} 