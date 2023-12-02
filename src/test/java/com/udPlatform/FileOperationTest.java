package com.udPlatform;

import junit.framework.TestCase;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class FileOperationTest extends TestCase {



    @Test
    public void testReadFromFile() throws IOException {
        assertTrue(FileOperation.readFromFile("src/main/java/com/udPlatform/database.txt") instanceof ListOfParents);
    }

    @Test
    public void testSaveToFile(){
        ListOfParents inputParentsList=new ListOfParents(new ArrayList<Parent>());
        String[] parentName={"FirstName","LastName"};
        Parent.addParent(inputParentsList,parentName,null,new Address("street","city","state",1204));
        FileOperation.saveToFile(inputParentsList,"src/main/java/com/udPlatform/database.txt");
        ListOfParents outputParentsList=FileOperation.readFromFile("src/main/java/com/udPlatform/database.txt");
        Parent inputParent=inputParentsList.getList().get(0);
        Parent outputParent=outputParentsList.getList().get(0);
        assertTrue(inputParent.getFName().equals(outputParent.getFName()) && inputParent.getLName().equals(outputParent.getLName()));
    }



}