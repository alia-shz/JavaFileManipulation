package com.example.FileManipulation;

import org.apache.commons.lang3.StringUtils;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;


public class ReadFiles {

    static void ReadFile1() throws IOException{
        /*This method reads text from a character-input stream.
        It does buffer for efficient reading of characters, arrays, and lines.
        The buffer size may be specified, or the default size may be used.
        Reads everything in the file line by line
        */
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the file to be read:");
        try{
            String fileName = bf.readLine();
            File file1= new File(fileName + ".txt");
            BufferedReader br = new BufferedReader(new FileReader(file1));
            while(true) {
                try {
                    String st;
                    System.out.println("Data found in file: "+fileName);
                    while ((st = br.readLine()) != null){
                        System.out.println(st);
                    }
                    break;
                }
                catch (FileNotFoundException e){
                    System.out.println("File Not Found :"+e);
                }
            }
        }catch (IOException e){
            System.out.println("Error:"+e);
        }
    }


    static void ReadFile2() throws IOException{
        /*After reading the file the output is gotten in the form of integers which are converted to characters.
        Therefore, output is character by character in nature.
        Easier to modify
        */
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the file to be read:");
        try {
            String fileName = bf.readLine();
            try( FileReader file1 = new FileReader(fileName + ".txt")){
                int i;
                while ((i = file1.read()) != -1) {
                    System.out.println((char) i);
                }
            }catch (FileNotFoundException e){
            System.out.println("FileNotFound:"+e);
                }
        }catch (IOException e){
            System.out.println("Error:"+e);
        }
    }

    static void ReadFile3() throws IOException{
        /*Read Files Line by Line
        File is tokenized
        Can be differentiated using Delimiters
        */
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Select Method for Reading a File using Scanner: ");
        System.out.println("1. With using loops \n2. Without using loops (Using Delimiters)\n");
        String choice = bf.readLine();
        int day;
        if (StringUtils.isNumeric(choice)) {
            day = Integer.parseInt(choice);
        } else {
            day = 7;
        }

        System.out.println("Enter the file to be read:");


        switch (day) {
            case 1:
            {
                try{
                    String fileName = bf.readLine();
                    File file1 = new File(fileName + ".txt");
                    Scanner sc = new Scanner(file1);
                    while (sc.hasNextLine()) {
                        System.out.println(sc.nextLine());
                    }
                }catch (FileNotFoundException e){
                    System.out.println("FileNotFound:"+e);
                }catch (IOException e){
                    System.out.println("IOException:"+e);
                }
            }
                break;
            case 2:
            {
                try{
                    String fileName = bf.readLine();
                    File file1 = new File(fileName + ".txt");
                    Scanner sc = new Scanner(file1);
                    sc.useDelimiter("\\Z");
                    System.out.println(sc.next());
                }catch (FileNotFoundException e){
                    System.out.println("FileNotFound:"+e);
                }catch (IOException e){
                    System.out.println("IOException:"+e);
                }
            }
                break;
            default:
                System.out.println("Wrong choice");
                break;
        }

    }
    public static void ReadFile4() {
        //Read all lines from a file.
        //All lines given in a list element. Every line in file separated by '\n' is an element in list
        // This method ensures that the file is closed when all bytes have been read

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the file to be read:");
        List<String> lines = null;
        try {
            String fileName = bf.readLine();
            lines = Collections.emptyList();
            try {
                lines = Files.readAllLines(Paths.get(fileName+".txt"), StandardCharsets.UTF_8);
            } catch (IOException e) {
                System.out.println("IOException:" + e);
            }
            bf.close();
//            fr.close();
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFound:" + e);
        } catch (IOException e) {
            System.out.println("IOException:" + e);
        }
        System.out.println(lines);
    }


    public static void ReadFile5() {
        //Read anf output entire data as string in paragraph form
        //

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the file to be read:");
        try {
            String fileName = bf.readLine();
            String data = new String(
                    Files.readAllBytes(Paths.get(fileName+".txt")));
            System.out.println(data);
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFound:" + e);
        } catch (IOException e) {
            System.out.println("IOException:" + e);
        }

    }


    public static void main(String[] args) {

        System.out.println("Select Method for Reading a File: ");
        System.out.println("1. BufferedReader \n2. FileReader\n3. Scanner\n4. java.nio List\n5. java.nio String");
        System.out.println("------------------------------------");
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            String choice = bf.readLine();
            int day;
            if (StringUtils.isNumeric(choice)) {
                day = Integer.parseInt(choice);
            } else {
                day = 7;
            }
            switch (day) {
                case 1:
                    ReadFile1();
                    break;
                case 2:
                    ReadFile2();
                    break;
                case 3:
                    ReadFile3();
                    break;
                case 4:
                    ReadFile4();
                    break;
                case 5:
                    ReadFile5();
                    break;
                default:
                    System.out.println("Wrong choice");
                    break;
            }
        } catch (IOException e) {
            System.out.println("Error:" + e);
        }


    }
}
