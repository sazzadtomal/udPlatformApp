package com.udPlatform;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        String[] listOfOperations={"Create","Update","Delete","Terminate","PrintAll"};
        String[] listOfEntity={"Parent","Child"};
        String[] listOfUpdatableParents={"FirstName","LastName","Address"};
        String[] listOfUpdatableChildren={"FirstName","LastName"};
        ListOfParents parentsList=new ListOfParents(new ArrayList<Parent>());
        Scanner sc=new Scanner(System.in);
        Utility.promptStartUp();


        try{
            FileInputStream file=new FileInputStream("database.txt");
            ObjectInputStream input=new ObjectInputStream(file);
            parentsList=(ListOfParents) input.readObject();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }





        while(true) {
            Utility.listOfSelection("Select a CRUD operation to Perform!", listOfOperations);
            String selectedOperation = listOfOperations[Utility.getSelection(sc, listOfOperations.length)];

            if (selectedOperation.equals("Terminate")){
                try{
                    FileOutputStream file=new FileOutputStream("database.txt");
                    ObjectOutputStream output=new ObjectOutputStream(file);
                    output.writeObject(parentsList);

                }catch (FileNotFoundException e){
                    System.out.println("File not found");
                }catch (IOException e){
                    System.out.println("Error initializing stream");
                }finally {
                    sc.close();
                    return;
                }

            }

            if(selectedOperation.equals("PrintAll")){
                System.out.println(parentsList.toString());
                continue;
            }

            Utility.listOfSelection("What kind of entity you want to work with?", listOfEntity);
            String selectedEntity = listOfEntity[Utility.getSelection(sc, listOfEntity.length)];

            //Create and Parent
            if (selectedOperation.equals("Create")) {

                if (selectedEntity.equals("Parent")) {
                    Parent.createParent(parentsList, sc);
                }

                //Create and Children
                if (selectedEntity.equals("Child")) {
                    Parent.createChild(parentsList, sc);
                }
            }

            if (selectedOperation.equals("Update")) {
                if (selectedEntity.equals("Parent")) {
                    Parent status = Parent.searchParent(parentsList, sc);
                    if (status == null) {
                        System.out.println("Cannot find parent to update");
                        continue;
                    }else {
                        Parent.updateParent(status,listOfUpdatableParents,sc);
                        Utility.success();
                    }
                }

                if (selectedEntity.equals("Child")) {
                    System.out.println("Search Parent to update the parent");
                    Parent status = Parent.searchParent(parentsList, sc);
                    if (status == null) {
                        System.out.println("Cannot find parents to update");
                        continue;
                    }

                    String[] name=Parent.getChildInfo(sc);
                    String childrenFName = name[0];
                    String childrenLName = name[1];

                    Entity childExist = Parent.childExist(childrenFName, childrenLName, status.getChildrensList());
                    if (childExist == null) {
                        System.out.println("Cannot find Children to update");
                        continue;
                    }else{
                        Parent.updateChild(childExist,listOfUpdatableChildren,sc);
                        Utility.success();
                    }
                }
            }

            if (selectedOperation.equals("Delete")){
                Parent status = Parent.searchParent(parentsList, sc);

                if (selectedEntity.equals("Parent")) {
                    if (status == null) {
                        System.out.println("Cannot find parent to Delete");
                        continue;
                    }else {
                          parentsList.getList().remove(status);
                          Utility.success();
                    }
                }

                if (selectedEntity.equals("Child")){
                    String[] name=Parent.getChildInfo(sc);
                    String childrenFName = name[0];
                    String childrenLName = name[1];

                    Entity childExist = Parent.childExist(childrenFName, childrenLName, status.getChildrensList());
                    if (childExist == null) {
                        System.out.println("Cannot find Children to Delete");
                        continue;
                    }else{
                        status.getChildrensList().remove(childExist);
                        Utility.success();
                    }


                }

            }

            System.out.println(parentsList.toString());
        }

    }
}