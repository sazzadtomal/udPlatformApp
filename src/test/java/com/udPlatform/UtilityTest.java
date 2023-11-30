package com.udPlatform;

import junit.framework.TestCase;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

public class UtilityTest extends TestCase {

    InputStream sysInBackup = System.in;
    @Test
    public void testCheckInputString(){
        ByteArrayInputStream in = new ByteArrayInputStream("ValidInput".getBytes());
        System.setIn(in);
        assertEquals("ValidInput",Utility.inputString("Check", new Scanner(System.in)));
        System.setIn(sysInBackup);
    }

    public void testValidInt(){
        ByteArrayInputStream in = new ByteArrayInputStream("5".getBytes());
        System.setIn(in);
        assertEquals(5,Utility.inputInt("Check", new Scanner(System.in)));
        System.setIn(sysInBackup);
    }

    public void testValidGetSelection(){
        ByteArrayInputStream in = new ByteArrayInputStream("4".getBytes());
        System.setIn(in);
        assertEquals(4,Utility.getSelection(new Scanner(System.in),5));
        System.setIn(sysInBackup);
    }

    public void testGetValidIntWithValid(){
        assertTrue(Utility.getValidInt("4"));
    }

    public void testGetValidIntWithInValid(){
        assertFalse(Utility.getValidInt("A string that cannot to parsed to Integer"));
    }


}