package mathapp;


public class Main 
{
    public static void main( String[] args )
    {
        try{
            Read.readFile();
            
        } catch(Exception e) {
            System.out.println(e.toString());
            System.out.println("failure");
        }
        

        MathApp.main(args);
    }
}
