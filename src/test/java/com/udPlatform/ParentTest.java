package com.udPlatform;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;

public class ParentTest extends TestCase {



    @Test
    public void testAddParentSuccess(){
         ListOfParents parentsList=new ListOfParents(new ArrayList<Parent>());
         String[] parentName={"FirstName","LastName"};
         Parent.addParent(parentsList,parentName,null,new Address("street","city","state",1204));
         assertEquals(1,parentsList.getList().size());
    }

    public void testAddDuplicateParentFailed(){

        ListOfParents parentsList=new ListOfParents(new ArrayList<Parent>());
        String[] parentName={"FirstName","LastName"};
        Parent.addParent(parentsList,parentName,null,new Address("street","city","state",1204));
        Parent alreadyExistsParent=parentsList.getList().get(0);
        Parent.addParent(parentsList,parentName,alreadyExistsParent,new Address("street","city","state",1204));
        assertEquals(1,parentsList.getList().size());
    }

    @Test
    public void testSearchParentExists(){
        ListOfParents parentsList=new ListOfParents(new ArrayList<Parent>());
        String[] parentName={"FirstName","LastName"};
        Parent.addParent(parentsList,parentName,null,new Address("street","city","state",1204));
        Parent check=Parent.searchParent(parentName,parentsList);
        assertNotNull(check);
    }
    @Test
    public void testSearchParentNotExists(){
        ListOfParents parentsList=new ListOfParents(new ArrayList<Parent>());
        String[] parentName={"FirstName","LastName"};
        Parent.addParent(parentsList,parentName,null,new Address("street","city","state",1204));

        String[] alteredName={"NotFirstName","NotLastName"};
        Parent check=Parent.searchParent(alteredName,parentsList);
        assertNull(check);
    }

    @Test
    public void testAddChildSuccess(){
        ListOfParents parentsList=new ListOfParents(new ArrayList<Parent>());
        String[] parentName={"FirstName","LastName"};
        Parent.addParent(parentsList,parentName,null,new Address("street","city","state",1204));
        String[] childName={"ChildFirstName","ChildLastName"};
        Parent searchedParent=Parent.searchParent(parentName,parentsList);
        searchedParent.addChild(childName);
        assertEquals(1,searchedParent.getChildrenList().size());
    }

    @Test
    public void testChildExistSuccess(){
        ListOfParents parentsList=new ListOfParents(new ArrayList<Parent>());
        String[] parentName={"FirstName","LastName"};
        Parent.addParent(parentsList,parentName,null,new Address("street","city","state",1204));
        String[] childName={"ChildFirstName","ChildLastName"};
        Parent searchedParent=Parent.searchParent(parentName,parentsList);
        searchedParent.addChild(childName);

        assertNotNull(Parent.searchChild(childName,searchedParent.getChildrenList()));
    }

    public void testChildExistFail(){
        ListOfParents parentsList=new ListOfParents(new ArrayList<Parent>());
        String[] parentName={"FirstName","LastName"};
        Parent.addParent(parentsList,parentName,null,new Address("street","city","state",1204));
        String[] childName={"ChildFirstName","ChildLastName"};
        String[] alteredChildName={"NotChildFirstName","NotChildLastName"};
        Parent searchedParent=Parent.searchParent(parentName,parentsList);
        searchedParent.addChild(childName);
        assertNull(Parent.searchChild(alteredChildName,searchedParent.getChildrenList()));
    }

    @Test
    public void testUpdateParentFirstNameSuccess(){
        ListOfParents parentsList=new ListOfParents(new ArrayList<Parent>());
        String[] parentName={"FirstName","LastName"};
        Parent.addParent(parentsList,parentName,null,new Address("street","city","state",1204));


        Parent getCreatedParentFromList=parentsList.getList().get(0);

        Parent.updateParentName("NewFirstName","FirstName",getCreatedParentFromList,parentsList);

        String updatedFirstName=parentsList.getList().get(0).getFName();
        assertEquals("NewFirstName",updatedFirstName);
    }

    public void testUpdateParentLastNameSuccess(){
        ListOfParents parentsList=new ListOfParents(new ArrayList<Parent>());
        String[] parentName={"FirstName","LastName"};
        Parent.addParent(parentsList,parentName,null,new Address("street","city","state",1204));

        Parent getCreatedParentFromList=parentsList.getList().get(0);
        Parent.updateParentName("NewLastName","LastName",getCreatedParentFromList,parentsList);

        String updatedLastName=parentsList.getList().get(0).getLName();
        assertEquals("NewLastName",updatedLastName);
    }

    public void testUpdateParentWithDuplicateNameUsingFirstNameFail(){
        ListOfParents parentsList=new ListOfParents(new ArrayList<Parent>());
        String[] parentName={"FirstName","LastName"};
        Parent.addParent(parentsList,parentName,null,new Address("street","city","state",1204));

        String[] parentName2={"NotFirstName","LastName"};
        Parent.addParent(parentsList,parentName2,null,new Address("street","city","state",1204));

        Parent parentForCheck=Parent.searchParent(parentName2,parentsList);
        assertFalse(Parent.updateParentName("FirstName","FirstName",parentForCheck,parentsList));
    }

    public void testUpdateParentWithDuplicateNameUsingLastNameFail(){
        ListOfParents parentsList=new ListOfParents(new ArrayList<Parent>());
        String[] parentName={"FirstName","LastName"};
        Parent.addParent(parentsList,parentName,null,new Address("street","city","state",1204));

        String[] parentName2={"FirstName","NotLastName"};
        Parent.addParent(parentsList,parentName2,null,new Address("street","city","state",1204));

        Parent parentForCheck=Parent.searchParent(parentName2,parentsList);
        assertFalse(Parent.updateParentName("LastName","LastName",parentForCheck,parentsList));
    }


    public void testDeleteParentSuccess(){
        ListOfParents parentsList=new ListOfParents(new ArrayList<Parent>());
        String[] parentName={"FirstName","LastName"};
        Parent.addParent(parentsList,parentName,null,new Address("street","city","state",1204));

        Parent.deleteParent(Parent.searchParent(parentName,parentsList),parentsList);
        assertTrue(parentsList.getList().isEmpty());
    }

    public void testDeleteParentFail(){
        ListOfParents parentsList=new ListOfParents(new ArrayList<Parent>());
        String[] parentName={"FirstName","LastName"};
        Parent.addParent(parentsList,parentName,null,new Address("street","city","state",1204));

        String[] modifiedName={"NotFirstName","NotLastName"};

        Parent.deleteParent(Parent.searchParent(modifiedName,parentsList),parentsList);
        assertFalse(parentsList.getList().isEmpty());
    }


    public void testCreateChildSuccess(){
        ListOfParents parentsList=new ListOfParents(new ArrayList<Parent>());
        String[] parentName={"FirstName","LastName"};
        Parent.addParent(parentsList,parentName,null,new Address("street","city","state",1204));

        String [] childName={"ChildFirstName","ChildLastName"};
        Parent parent=parentsList.getList().get(0);

        Parent.createChild(parent,childName);

        assertEquals(1,parent.getChildrenList().size());
    }

    public void testCreateChildWithSameNameFails(){
        ListOfParents parentsList=new ListOfParents(new ArrayList<Parent>());
        String[] parentName={"FirstName","LastName"};
        Parent.addParent(parentsList,parentName,null,new Address("street","city","state",1204));

        String [] childName={"ChildFirstName","ChildLastName"};
        Parent parent=Parent.searchParent(parentName,parentsList);
        Parent.createChild(parent,childName);
        Parent.createChild(parent,childName);
        assertEquals(1,parent.getChildrenList().size());
    }


    public void testUpdateChildWithFirstNameSuccess(){

        ListOfParents parentsList=new ListOfParents(new ArrayList<Parent>());
        String[] parentName={"FirstName","LastName"};
        Parent.addParent(parentsList,parentName,null,new Address("street","city","state",1204));

        String [] childName={"ChildFirstName","ChildLastName"};
        Parent parent=parentsList.getList().get(0);

        Parent.createChild(parent,childName);

        Entity child=Parent.searchChild(childName,parent.getChildrenList());

        Parent.updateChild("FirstName","UpdatedFirstName",child,parent);

        String[] updatedChildName={"UpdatedFirstName","ChildLastName"};

        assertNotNull(Parent.searchChild(updatedChildName,parent.getChildrenList()));
    }


    public void testUpdateChildRemoveDuplicateWithFirstName(){

        ListOfParents parentsList=new ListOfParents(new ArrayList<Parent>());
        String[] parentName={"FirstName","LastName"};
        Parent.addParent(parentsList,parentName,null,new Address("street","city","state",1204));

        String [] childName={"ChildFirstName","ChildLastName"};
        String [] childName2={"NotChildFirstName","ChildLastName"};

        Parent parent=parentsList.getList().get(0);
        Parent.createChild(parent,childName);
        Parent.createChild(parent,childName2);

        Entity child=Parent.searchChild(childName2,parent.getChildrenList());

        assertFalse(Parent.updateChild("FirstName","ChildFirstName",child,parent));
    }

    public void testUpdateChildRemoveDuplicateWithLastName(){

        ListOfParents parentsList=new ListOfParents(new ArrayList<Parent>());
        String[] parentName={"FirstName","LastName"};
        Parent.addParent(parentsList,parentName,null,new Address("street","city","state",1204));

        String [] childName={"ChildFirstName","ChildLastName"};
        String [] childName2={"ChildFirstName","NotChildLastName"};

        Parent parent=parentsList.getList().get(0);
        Parent.createChild(parent,childName);
        Parent.createChild(parent,childName2);

        Entity child=Parent.searchChild(childName2,parent.getChildrenList());

        assertFalse(Parent.updateChild("LastName","ChildLastName",child,parent));
    }

    public void testUpdateChildWithLastNameSuccess(){

        ListOfParents parentsList=new ListOfParents(new ArrayList<Parent>());
        String[] parentName={"FirstName","LastName"};
        Parent.addParent(parentsList,parentName,null,new Address("street","city","state",1204));

        String [] childName={"ChildFirstName","ChildLastName"};
        Parent parent=parentsList.getList().get(0);

        Parent.createChild(parent,childName);

        Entity child=Parent.searchChild(childName,parent.getChildrenList());

        Parent.updateChild("LastName","UpdatedLastName",child,parent);

        String[] updatedChildName={"ChildFirstName","UpdatedLastName"};

        assertNotNull(Parent.searchChild(updatedChildName,parent.getChildrenList()));
    }


    public void testDeleteChildSuccess(){

        ListOfParents parentsList=new ListOfParents(new ArrayList<Parent>());
        String[] parentName={"FirstName","LastName"};
        Parent.addParent(parentsList,parentName,null,new Address("street","city","state",1204));

        String [] childName={"ChildFirstName","ChildLastName"};
        Parent parent=parentsList.getList().get(0);

        Parent.createChild(parent,childName);
        Entity child=Parent.searchChild(childName,parent.getChildrenList());

        Parent.deleteChild(parent,child);

        assertTrue(parent.getChildrenList().isEmpty());
    }




}