package com.udPlatform;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Parent extends Entity implements Serializable {


    private Address address;
    private ArrayList<Child> listOfChildrens;

    public Parent() {
        super();
    }

    public Parent(String fName, String lName, Address address) {
        super(fName,lName);
        this.address=address;
    }


    public static void createParent(ListOfParents parentsList,Scanner sc){
        Parent createdParent=Parent.addParentInfo(sc);
        Parent status=Parent.parentExist(createdParent.getFName().toLowerCase(), createdParent.getLName().toLowerCase(), parentsList);
        if(status==null){
            parentsList.getList().add(createdParent);
        }else{
            System.out.println("Entered Parent already exists");
        }
    }


    public static Parent addParentInfo(Scanner sc){
        String firstName = Utility.inputString("First Name", sc);
        String lastName = Utility.inputString("Last Name", sc);
        Address address=addAddressInfo(sc);
        Parent parent = new Parent(firstName, lastName, address);
        return parent;
    }

    public static Address addAddressInfo(Scanner sc){
        String street = Utility.inputString("Street", sc);
        String city = Utility.inputString("City", sc);
        String state = Utility.inputString("State", sc);
        int zipCode = Utility.inputInt("Zip", sc);
        Address address = new Address(street, city, state, zipCode);
        return address;
    }

    public static Parent searchParent(ListOfParents parentsList, Scanner sc){
        String[] name=getParentInfo(sc);
        String firstName = name[0];
        String lastName =name[1];
        return parentExist(firstName,lastName,parentsList);
    }

    public static String[] getParentInfo(Scanner sc){
        String[] name=new String[2];
        name[0]= Utility.inputString("First Name of Parent", sc).toLowerCase();
        name[1] = Utility.inputString("Last Name of Parent", sc).toLowerCase();
        return name;
    }




    public static Parent parentExist(String firstName,String lastName,ListOfParents parentsList){
        Parent tempParent1 = parentsList.getList().stream().filter(fName -> fName.getFName().toLowerCase().equals(firstName)).findAny().orElse(null);
        Parent tempParent2= parentsList.getList().stream().filter(LName -> LName.getLName().toLowerCase().equals(lastName)).findAny().orElse(null);

        if(tempParent1==null){
            return null;
        }

        if(tempParent1.equals(tempParent2)){
            return tempParent1;
        }

        return null;

    }


    public static void createChild(ListOfParents parentsList, Scanner sc){
        System.out.println("You have to select a parent to add a Child!\n");
        Parent searchedParent= Parent.searchParent(parentsList,sc);

        if(searchedParent!=null){
            System.out.println("Success");
            String[] name=getChildInfo(sc);
            String childrenFName = name[0];
            String childrenLName = name[1];
            Entity status=Parent.childExist(childrenFName,childrenLName,searchedParent.getChildrensList());

            if(status==null){
                searchedParent.addChildren(childrenFName,childrenLName);
            }else{
                System.out.println("Entered Parent already exists");
            }
            System.out.println("Operation Successful");
        }else{
            System.out.println("Cannot find Parent");
        }
    }

    public static String[] getChildInfo(Scanner sc){
        String[] name=new String[2];
        name[0]= Utility.inputString("First Name of Children", sc).toLowerCase();
        name[1] = Utility.inputString("Last Name of Children", sc).toLowerCase();
        return name;
    }

    public void addChildren(String fName,String lName){
        if(listOfChildrens ==null){
            listOfChildrens =new ArrayList<Child>();

        }
        listOfChildrens.add(new Child(fName,lName));
    }

    public static Entity childExist(String firstName, String lastName, ArrayList<Child> listOfChildrens){
        if(listOfChildrens!=null){
            Child tempChild1 = listOfChildrens.stream().filter(fName -> fName.getFName().toLowerCase().equals(firstName)).findAny().orElse(null);
            Child tempChild2= listOfChildrens.stream().filter(LName -> LName.getLName().toLowerCase().equals(lastName)).findAny().orElse(null);

            if(tempChild1==null){
                return null;
            }
            if(tempChild1.equals(tempChild2)){
                return tempChild1;
            }

            return null;

        }
        return null;

    }





    public static void updateParent(Parent status,String[] listOfUpdatableParents,Scanner sc){
        Utility.listOfSelection("What kind of update you want to do?", listOfUpdatableParents);
        String selectedUpdate = listOfUpdatableParents[Utility.getSelection(sc, listOfUpdatableParents.length)];

        if (selectedUpdate.equals("FirstName")) {
            String newFName = Utility.inputString("Updated First Name", sc);
            status.setFName(newFName);
        }

        if (selectedUpdate.equals("LastName")) {
            String newFName = Utility.inputString("Updated Last Name", sc);
            status.setLName(newFName);
        }

        if (selectedUpdate.equals("Address")) {
            Address newAddress = Parent.addAddressInfo(sc);
            status.setAddress(newAddress);
        }
    }

    public static void updateChild(Entity childExist, String[] listOfUpdatableChildren, Scanner sc){

        Utility.listOfSelection("What kind of update you want to do?", listOfUpdatableChildren);
        String update = listOfUpdatableChildren[Utility.getSelection(sc, listOfUpdatableChildren.length)];

        if (update.equals("FirstName")) {
            String newFName = Utility.inputString("Updated First Name", sc);
            childExist.setFName(newFName);
        } else {
            String newLName = Utility.inputString("Updated Last Name", sc);
            childExist.setLName(newLName);
        }
    }

    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }
    public ArrayList<Child> getChildrensList() {
        return listOfChildrens;
    }



    @Override
    public String toString(){
        if(listOfChildrens !=null && !listOfChildrens.isEmpty()){
            String children="";
            for (Child child: listOfChildrens) {
                children+=child.toString();
            }
            return String.format("{\n FirstName:%s,\n LastName:%s,\n Address:%s,\n Childrens: \n %s \n},",this.getFName(),this.getLName(),this.getAddress(),children);
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
            return String.format(" { \n %s FirstName:%s \n %s LastName:%s \n }",
                    space,this.getFName(),space,this.getLName());

        }

    }


}
