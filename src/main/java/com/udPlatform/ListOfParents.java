package com.udPlatform;

import java.io.Serializable;
import java.util.ArrayList;

public class ListOfParents implements Serializable {


    ArrayList<Parent> list;
    public ListOfParents(ArrayList list) {
        this.list=list;
    }
    public ArrayList<Parent> getList() {
        return list;
    }


    @Override
    public String toString(){
        String out="";

        for (Parent parent:list) {
            out+=parent.toString()+"\n";
        }

        return String.format("[\n %s \n]\n",out);
    }
    }

