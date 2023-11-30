package com.udPlatform;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Parent extends Entity implements Serializable {
    private Address address;
    private ArrayList<Child> listOfChildren;

    private Parent(String fName, String lName, Address address) {
        super(fName,lName);
        this.address=address;
    }





    //Parents Operation
    public static Parent searchParent(String[] parentName, ListOfParents parentsList){
        return parentsList.getList().stream().filter(fName -> fName.getFName().equalsIgnoreCase(parentName[0])).filter(lName-> lName.getLName().equalsIgnoreCase(parentName[1])).findAny().orElse(null);
    }


    public static void addParent(ListOfParents parentsList,String [] parentName,Parent status,Address address){
        if(status==null){
            parentsList.getList().add(new Parent(parentName[0],parentName[1],address));
        }else{
            System.out.println("Entered Parent already exists");
        }
    }


    public static boolean updateParentName(String updateValue, String updateType, Parent parent, ListOfParents parentsList){
        if (updateType.equals("FirstName")) {
            return updateFirstNameParent(updateValue,parent,parentsList);
        }
        return updateLastNameParent(updateValue,parent,parentsList);
    }


    public static boolean updateFirstNameParent(String firstName, Entity entity, ListOfParents parentsList){
        String [] parentName={firstName,entity.getLName()};
        Parent check=Parent.searchParent(parentName,parentsList);
        if(check==null) {
            entity.setFName(firstName);
            return true;
        }
        return false;
    }

    public static boolean updateLastNameParent(String lastName, Entity parent, ListOfParents parentsList){
        String [] parentName={parent.getFName(),lastName};
        Parent check=Parent.searchParent(parentName,parentsList);
        if(check==null) {
            parent.setLName(lastName);
            return true;
        }
        return false;
    }

    public static void deleteParent(Parent parent,ListOfParents parentsList){
        parentsList.getList().remove(parent);
    }






    //Children Operations

    public static Entity searchChild(String[] childName, ArrayList<Child> listOfChildren){
        if(listOfChildren==null) return null;
        return listOfChildren.stream().filter(fName -> fName.getFName().equalsIgnoreCase(childName[0])).filter(lName-> lName.getLName().equalsIgnoreCase(childName[1])).findAny().orElse(null);
    }


    public static void createChild( Parent searchedParent,String[] childName){
            if(searchedParent==null) return;

            System.out.println("Success");
            Entity status=Parent.searchChild(childName,searchedParent.getChildrenList());
            if(status==null){
                searchedParent.addChild(childName);
                System.out.println("Operation Successful");
            }else{
                System.out.println("Entered Child already exists");
            }
    }

    public void addChild(String[] childName){
        if(listOfChildren==null){
            listOfChildren = new ArrayList<>();

        }
        listOfChildren.add(new Child(childName[0],childName[1]));
    }


    public static boolean updateChild(String updateType,String updateValue,Entity childExist,Parent parent){
        if (updateType.equals("FirstName")) {
            String[] checkChild={updateValue,childExist.getLName()};
            Entity check=Parent.searchChild(checkChild,parent.getChildrenList());
            if(check==null){
                childExist.setFName(updateValue);
                return true;
            }

        }
        else {
            String [] checkName={childExist.getFName(),updateValue};
            Entity check=Parent.searchChild(checkName,parent.getChildrenList());
            if(check==null){
                childExist.setLName(updateValue);
                return true;
            }
        }
        parent.getChildrenList().remove(childExist);
        return false;

    }


    public static void deleteChild(Parent parent,Entity child){
        parent.getChildrenList().remove(child);
    }





    //Getting data from the User
    public static String[] getParentInfo(Scanner sc){
        String[] name=new String[2];
        name[0]= Utility.inputString("First Name of Parent", sc);
        name[1] = Utility.inputString("Last Name of Parent", sc);
        return name;
    }



    public static Address getAddress(Scanner sc){
        String street = Utility.inputString("Street", sc);
        String city = Utility.inputString("City", sc);
        String state = Utility.inputString("State", sc);
        int zipCode = Utility.inputInt("Zip", sc);

        return new Address(street, city, state, zipCode);
    }


    public static String[] getChildInfo(Scanner sc){
        String[] name=new String[2];
        name[0]= Utility.inputString("First Name of Children", sc);
        name[1] = Utility.inputString("Last Name of Children", sc);
        return name;
    }



    //Getters and Setters
    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }
    public ArrayList<Child> getChildrenList() {
        return listOfChildren;
    }




















//    public static boolean updateChild(Entity childExist, String[] listOfUpdatableChildren, Scanner sc, Parent parent){
//
//        Utility.listOfSelection("What kind of update you want to do?", listOfUpdatableChildren);
//        String update = listOfUpdatableChildren[Utility.getSelection(sc, listOfUpdatableChildren.length)];
//
//        if (update.equals("FirstName")) {
//            String newFName = Utility.inputString("Updated First Name", sc);
//            String[] checkChild={newFName,childExist.getLName()};
//            Entity check=Parent.childExist(checkChild,parent.getChildrenList());
//            if(check==null){
//                childExist.setFName(newFName);
//                return true;
//            }
//
//        } else {
//            String newLName = Utility.inputString("Updated Last Name", sc);
//            String [] checkName={childExist.getFName(),newLName};
//            Entity check=Parent.childExist(checkName,parent.getChildrenList());
//            if(check==null){
//                childExist.setLName(newLName);
//                return true;
//            }
//        }
//
//        parent.getChildrenList().remove(childExist);
//        return false;
//
//    }
















//    public static boolean updateParent(Parent parent,String[] listOfUpdatableParents,ListOfParents parentsList,Scanner sc){
//        Utility.listOfSelection("What kind of update you want to do?", listOfUpdatableParents);
//        String selectedUpdate = listOfUpdatableParents[Utility.getSelection(sc, listOfUpdatableParents.length)];
//
//        if (selectedUpdate.equals("FirstName")) {
//            String newFName = Utility.inputString("Updated First Name", sc);
//            return updateFirstName(newFName,parent,parentsList);
//        }
//
//        if (selectedUpdate.equals("LastName")) {
//            String newLName = Utility.inputString("Updated Last Name", sc);
//            return updateLastName(newLName,parent,parentsList);
//        }
//
//        return false;
//
//    }









//    public static boolean updateParent(Parent parent,String[] listOfUpdatableParents,ListOfParents parentsList,Scanner sc){
//        Utility.listOfSelection("What kind of update you want to do?", listOfUpdatableParents);
//        String selectedUpdate = listOfUpdatableParents[Utility.getSelection(sc, listOfUpdatableParents.length)];
//
//        if (selectedUpdate.equals("FirstName")) {
//            String newFName = Utility.inputString("Updated First Name", sc);
//            String [] parentName={newFName,parent.getLName()};
//            Parent check=Parent.searchParent(parentName,parentsList);
//            if(check==null) {parent.setFName(newFName); return true;}
//        }
//
//        if (selectedUpdate.equals("LastName")) {
//            String newLName = Utility.inputString("Updated Last Name", sc);
//            String [] parentName={parent.getFName(),newLName};
//            Parent check=Parent.searchParent(parentName,parentsList);
//            if(check==null) {parent.setFName(newLName); return true;}
//        }
//
//        return false;
//
//    }






    @Override
    public String toString(){
        if(listOfChildren !=null && !listOfChildren.isEmpty()){
            String children="";
            for (Child child: listOfChildren) {
                children+=child.toString();
            }
            return String.format("{\n FirstName:%s,\n LastName:%s,\n Address:%s,\n Children: \n %s \n},",this.getFName(),this.getLName(),this.getAddress(),children);
        }
        return String.format("{\n FirstName:%s,\n LastName:%s,\n Address:%s\n}",this.getFName(),this.getLName(),this.getAddress());
    }


    private class Child extends Entity implements Serializable{

        private Child(String FName, String LName) {
            super(FName, LName);
        }

        @Override
        public String toString(){

            String space="          ";
            return String.format(" { \n %s FirstName:%s \n %s LastName:%s \n }\n",
                    space,this.getFName(),space,this.getLName());

        }

    }


}
