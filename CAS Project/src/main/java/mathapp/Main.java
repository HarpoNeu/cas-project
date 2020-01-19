package mathapp;


public class Main 
{
    public static void main( String[] args )
    {
        try{
            read.readFile();
        } catch(Exception e) {
            System.out.println("failure");
        }
        

        MathApp.main(args);
    }
}
