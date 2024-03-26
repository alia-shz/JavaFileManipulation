//Creating Files

package com.example.helloworld;

import java.io.*;
import java.util.Arrays;


public class HelloWorld {
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        //One Single Input
        System.out.println("Enter Single Input: ");
        String singleInput = bf.readLine();
        System.out.println("Single Input: "+singleInput);

        //Multi Line Input
        //StringBuffer is synchronized, StringBuilder is not.
        String line;
        System.out.println("Enter Text (Press Enter Twice To Finish): \n");
        try{
            StringBuilder inputText = new StringBuilder();
            /* StringBuilder class provides an alternative to String Class, as it creates a mutable sequence of characters.
              The StringBuilder class provides no guarantee of synchronization whereas the StringBuffer class does.
              Instances of StringBuilder are not safe for use by multiple threads.
              If such synchronization is required then it is recommended that StringBuffer be used.
             */
            /*
            StringBuffer is a class in Java that represents a mutable sequence of characters.
            It provides an alternative to the immutable String class,
            allowing you to modify the contents of a string without creating a new object every time.
             */
            while ((line = bf.readLine()) != null && !line.isEmpty()){
                    inputText.append(line).append("\n");
            }

            System.out.println("This is the text: \n"+inputText.toString());

        } catch (IOException e){
            System.out.println(e);
        }
    }
}
