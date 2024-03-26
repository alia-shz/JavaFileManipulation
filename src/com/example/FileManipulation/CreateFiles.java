package com.example.FileManipulation;

import org.apache.commons.lang3.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class CreateFiles {


    static File appendFiles(StringBuilder fileName) {
        String fileNumber;
        if (fileName.charAt(fileName.length()-1) == ')'){
            fileNumber = fileName.substring(fileName.length() - 2, fileName.length() - 1);
        }
        else{
            fileNumber = "NA";
        }
        if (StringUtils.isNumeric(fileNumber)) {
            int fileNo = Integer.parseInt(fileNumber) + 1;
            String appellation = "(" + fileNo + ")";
            fileName.replace(fileName.length() - 3, fileName.length(), appellation);

        } else {
            fileName.append("(1)");
        }

        return new File(fileName + ".txt");
    }
    static void CreateFile1() throws IOException{
        /*
        * java.io.File class can be used to create a new File in Java.
        * When we initialize File object, we provide the file name and, then we can call createNewFile() method to create new file in Java.
        * File createNewFile() method returns true if new file is created and false if file already exists.
        * This method also throws java.io.IOException when it’s not able to create the file. The files*/
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the file name:");
        try{
            StringBuilder fileName = new StringBuilder(bf.readLine());
            File file1= new File(fileName+ ".txt");
            while(true) {
                if(file1.createNewFile()){
                    System.out.println("File created: " + file1.getName());
                    break;
                }
                else{
                    try {
                    file1 = appendFiles(fileName);

                    }catch(NumberFormatException e) {
                        System.out.println("Error:" + e);
                    }
                }
            }
        }catch (IOException e){
            System.out.println("Error:"+e);
       }
    }


    static void CreateFile2()throws IOException{
        //If you want to create a new file and at the same time write some data into it, you can use FileOutputStream write method.
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the file name:");
        try{
            StringBuilder fileName = new StringBuilder(bf.readLine());
            File file1 = new File(fileName+".txt");
            while(true){
                if(file1.exists()){
                    file1 = appendFiles(fileName);
                }
                else{
                    try (FileOutputStream fos = new FileOutputStream(fileName + ".txt"))
                    {
                        System.out.println("Data to be Input in File: ");
                        String fileData = bf.readLine();
                        fos.write(fileData.getBytes());
                        fos.flush();
                    } catch (FileNotFoundException e) {
                        System.out.println("File not found: " + e);
                    } catch (SecurityException ex) {
                        System.out.println("Security Exception occurred: " + ex);
                    }
                    break;
                }
            }
        }catch (IOException e){
            System.out.println("Error:"+e);
        }
    }


    static void CreateFile3()throws IOException{
        //We can use Java NIO Files class to create a new file and write some data into it.
        // This is a good option because we don’t have to worry about closing IO resources.
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the file name:");
        try{
            StringBuilder fileName = new StringBuilder(bf.readLine());
            File file1 = new File(fileName+".txt");
            while(true){
                if(file1.exists()){
                    file1 = appendFiles(fileName);
                    //java.nio.file.FileAlreadyExistsException:
                }
                else{
                    try
                    {
                        Path filePath = Paths.get(fileName+".txt");
                        System.out.println(Files.createFile(filePath));
                    } catch (FileNotFoundException e) {
                        System.out.println("File not found: " + e);
                    } catch (SecurityException ex) {
                        System.out.println("Security Exception occurred: " + ex);
                    }
                    break;
                }
            }
        }catch (IOException e){
            System.out.println("Error:"+e);
        }
    }

    public static void main(String[] args) {

        System.out.println("Select Method for Creating File: ");
        System.out.println("1. File.createNewFile()\n2. FileOutputStream.write(byte[] b)\n3. Java NIO Files");
        System.out.println("------------------------------------");
        try{
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            String choice = bf.readLine();
            int day;
            if(StringUtils.isNumeric(choice)){
                day = Integer.parseInt(choice);
            }
            else{
                day = 7;
            }
            switch (day) {
                case 1:
                    CreateFile1();
                    break;
                case 2:
                    CreateFile2();
                    break;
                case 3:
                    CreateFile3();
                    break;
                default:
                    System.out.println("Sunday");
                    break;
            }
        }catch (IOException e){
            System.out.println("Error:"+e);
        }


    }
}
