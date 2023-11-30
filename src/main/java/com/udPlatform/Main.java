package com.udPlatform;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc=new Scanner(System.in);
        String[] listOfOperations={"Create","Update","Delete","Terminate","PrintAll"};
        String[] listOfEntity={"Parent","Child"};
        String[] listOfUpdatableParents={"FirstName","LastName","Address"};
        String[] listOfUpdatableChildren={"FirstName","LastName"};
        ListOfParents parentsList;

        System.out.println("Welcome! Let's get started!\n");
        parentsList=FileOperation.readFromFile();





        while(true) {
            Utility.listOfSelection("Select a CRUD operation to Perform!", listOfOperations);
            String selectedOperation = listOfOperations[Utility.getSelection(sc, listOfOperations.length)];

            switch (selectedOperation){
                case "Terminate": {
                    FileOperation.saveToFile(parentsList);
                    sc.close();
                    return;
                }
                case "PrintAll":{
                    System.out.println(parentsList);
                    continue;
                }
            }


            Utility.listOfSelection("What kind of entity you want to work with?", listOfEntity);
            String selectedEntity = listOfEntity[Utility.getSelection(sc, listOfEntity.length)];




            String[] parentName=Parent.getParentInfo(sc);
            Parent status = Parent.searchParent(parentName,parentsList);

            switch (selectedOperation){
                case "Create":{
                    switch (selectedEntity){
                        case "Parent":{
                            Address address=Parent.getAddress(sc);
                            Parent.addParent(parentsList,parentName,status,address);
                            break;
                        }
                        case "Child":{
                            if(status==null){
                                System.out.println("Parent not found");
                                continue;
                            }
                            String[] childName=Parent.getChildInfo(sc);
                            Parent.createChild(status,childName);
                        }
                    }
                    break;
                }


                case "Update":{
                    if(status==null){
                        System.out.println("Cannot Find Parent to Update");
                        continue;
                    }


                    switch (selectedEntity){
                        case "Parent":{
                            Utility.listOfSelection("What kind of update you want to do?", listOfUpdatableParents);
                            String selectedUpdate = listOfUpdatableParents[Utility.getSelection(sc, listOfUpdatableParents.length)];

                            if(selectedUpdate.equals("Address")){
                                status.setAddress(Parent.getAddress(sc));
                            }

                            String update = Utility.inputString("Updated Value", sc);
                            boolean check=Parent.updateParentName(update,selectedUpdate,status,parentsList);

                            if(check){
                                System.out.println("Success");
                            }else{
                                System.out.println("Already has a Parent with Same First and Last name");
                            }
                            break;
                        }


                        case "Child":{
                            String[] childName=Parent.getChildInfo(sc);
                            Entity childExist = Parent.searchChild(childName,status.getChildrenList());
                            if (childExist == null) {
                                System.out.println("Cannot find Children to update");
                                continue;
                            }else{
                                Utility.listOfSelection("What kind of update you want to do?", listOfUpdatableChildren);
                                String updateType = listOfUpdatableChildren[Utility.getSelection(sc, listOfUpdatableChildren.length)];
                                String updateValue = Utility.inputString("Updated Value", sc);
                                Boolean updated=Parent.updateChild(updateType,updateValue,childExist,status);
                                if(updated){
                                    System.out.println("Success");
                                }else {
                                    System.out.println("Updated child already Exists");
                                }
                            }
                        }
                    }
                    break;
                }
                case "Delete":{
                    switch (selectedEntity){
                        case "Parent":{
                            Parent.deleteParent(status,parentsList);
                            System.out.println("Success");
                            break;
                            }

                        case "Child":{
                            String[] childName=Parent.getChildInfo(sc);
                            Entity childExist = Parent.searchChild(childName,status.getChildrenList());
                            if (childExist == null) {
                                System.out.println("Cannot find Children to Delete");
                            }else{
                                Parent.deleteChild(status,childExist);
                                System.out.println("Success");
                            }
                        }
                    }
                }
            }

            System.out.println(parentsList);



        }

    }
}


//            if (selectedOperation.equals("Terminate")){
//                FileOperation.saveToFile(parentsList);
//                sc.close();
//                return;
//            }
//
//            if(selectedOperation.equals("PrintAll")){
//                System.out.println(parentsList.toString());
//                continue;
//            }



//            if (selectedOperation.equals("Create")) {
//                if (selectedEntity.equals("Parent")) {
//                    Address address=Parent.getAddress(sc);
//                    Parent.addParent(parentsList,parentName,address);
//                }
//
//                if (selectedEntity.equals("Child")) {
//                    Parent.createChild(parentsList,sc,parentName);
//                }
//            }
//
//            if (selectedOperation.equals("Update")) {
//                Parent status = Parent.searchParent(parentName,parentsList);
//                if(status==null){
//                    System.out.println("Cannot Find Parent to Update");
//                    continue;
//                }
//
//                if (selectedEntity.equals("Parent")) {
//                    Boolean check=Parent.updateParent(status,listOfUpdatableParents,parentsList,sc);
//                        if(check){
//                            System.out.println("Success");
//                        }else{
//                            System.out.println("Already has a Parent with Same First and Last name");
//                        }
//                }
//
//                if (selectedEntity.equals("Child")) {
//                    String[] childName=Parent.getChildInfo(sc);
//                    Entity childExist = Parent.childExist(childName,status.getChildrenList());
//                    if (childExist == null) {
//                        System.out.println("Cannot find Children to update");
//                        continue;
//                    }else{
//                        Boolean updated=Parent.updateChild(childExist,listOfUpdatableChildren,sc,status);
//                        if(updated){
//                            System.out.println("Success");
//                        }else {
//                            System.out.println("Updated child already Exists");
//                        }
//                    }
//                }
//
//                if (selectedEntity.equals("Address")) {
//                    status.setAddress(Parent.getAddress(sc));
//                }
//
//
//            }
//
//            if (selectedOperation.equals("Delete")){
//                Parent status = Parent.searchParent(parentName,parentsList);
//
//                if (selectedEntity.equals("Parent")) {
//                    if (status == null) {
//                        System.out.println("Cannot find parent to Delete");
//                        continue;
//                    }else {
//                          parentsList.getList().remove(status);
//                        System.out.println("Success");
//                    }
//                }
//
//                if (selectedEntity.equals("Child")){
//                    String[] childName=Parent.getChildInfo(sc);
//                    Entity childExist = Parent.childExist(childName,status.getChildrenList());
//                    if (childExist == null) {
//                        System.out.println("Cannot find Children to Delete");
//                    }else{
//                        status.getChildrenList().remove(childExist);
//                        System.out.println("Success");
//                    }
//
//
//                }
//
//            }


