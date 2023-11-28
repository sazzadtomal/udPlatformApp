package com.udPlatform;

import java.io.Serializable;
import java.util.ArrayList;

public class ListOfParents implements CRUD<Parent>, Serializable {


    ArrayList<Parent> list;
    public ListOfParents(ArrayList list) {
        this.list=list;
    }


    @Override
    public void create(Parent parent) {
        list.add((Parent) parent);
    }

    @Override
    public void update(Parent parent) {
        list.add(parent);
    }

    @Override
    public void delete(Parent parent) {
        list.remove(parent);
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

        return String.format("[\n %s \n]",out);
    }
    }

