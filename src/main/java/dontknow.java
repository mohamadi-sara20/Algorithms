import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Stack {

    public static void main(String[] args){
        ArrayList<String> a = new ArrayList<>();
        try {


        FileReader inputFile = new FileReader("src/main/java/test");

        //Instantiate the BufferedReader Class
        BufferedReader bufferReader = new BufferedReader(inputFile);

        //Variable to hold the one line data
        String line;

        // Read file line by line and print on the console
        while ((line = bufferReader.readLine()) != null)   {
            if(!a.contains(line))
            System.out.println(line);
        }
        //Close the buffer reader
        bufferReader.close();
    }


    catch(Exception e){
        System.out.println("Error while reading file line by line:" + e.getMessage());





    }}
}
