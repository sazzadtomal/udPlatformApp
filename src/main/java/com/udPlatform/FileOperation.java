package com.udPlatform;

import java.io.*;
import java.util.ArrayList;

public class FileOperation {

    public static ListOfParents readFromFile(String path) {
        try {
            File file=new File(path);
            if(file.exists() && file.length()!=0){
                FileInputStream fileInputStream = new FileInputStream(file);
                ObjectInputStream input = new ObjectInputStream(fileInputStream);
                return (ListOfParents) input.readObject();
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return new ListOfParents(new ArrayList<Parent>());

    }

    public static void saveToFile(ListOfParents parentsList,String path){

        try{
            FileOutputStream file=new FileOutputStream(path);
            ObjectOutputStream output=new ObjectOutputStream(file);
            output.writeObject(parentsList);

        }catch (FileNotFoundException e){
            System.out.println("File not found");
        }catch (IOException e){
            System.out.println("Error initializing stream");
        }

    }



}


