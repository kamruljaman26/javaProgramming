package Assignment2;

import java.util.ArrayList;
import Assignment2.MyLinkedListInterface.MyLinkedListNode;

class TangledList {

    public static void removeSharedLinkedListNodes(MyLinkedList<String> list0, MyLinkedList<String> list1) {
        // Write some assignemnt code here!
    }

    public static int detectCycleAndPeriod(MyLinkedList<String> list0) {
        // Write some assignemnt code here!
        return -1;
    }

    public static void removeCycle(MyLinkedList<String> list0, int period) {
        // Write some assignemnt code here!
    }
    public static void removeLinkedListCycles(MyLinkedList<String> list0) {
        int period = detectCycleAndPeriod(list0);
        removeCycle(list0, period);
    }

    // Code to setup one test case for eliminating shared nodes from two linked lists
    public static void createAndTestSharedNode() {
        // Your assignment submission should have more specific error handling
        try{
        MyLinkedList<String> stage0 = new MyLinkedList<String>();
        stage0.appendToTail("Arkells");
        stage0.appendToTail("Bruno Mars");
        stage0.appendToTail("Coldplay");
        stage0.appendToTail("David Bowie");
        stage0.appendToTail("Earth, Wind & Fire");
        
        MyLinkedList<String> stage1 = new MyLinkedList<String>();
        stage1.appendToTail("Foo Fighters");
        stage0.appendToTail("Gorillaz");

        MyLinkedListNode<String> node;
        node = stage0.searchByValue("Coldplay");
        stage1.appendToTail(node);

        System.out.println("Shared Nodes: Stage 0 Lineup");
        System.out.println(stage0.toString());
        System.out.println("Shared Nodes: Stage 0 Lineup");
        System.out.println(stage1.toString());
        System.out.println("\n");

        removeSharedLinkedListNodes(stage0, stage1);
        
        System.out.println("Shared Nodes Fixed: Stage 0 Lineup");
        System.out.println(stage0.toString());
        System.out.println("Shared Nodes Fixed: Stage 0 Lineup");
        System.out.println(stage1.toString());
        System.out.println("\n");

        } catch (Exception e) {}
    }
    
    // Code to setup one test case for eliminating cycles from a linked list
    public static void createAndTestCycle() {
        // Your assignment submission should have more specific error handling
        try {
        MyLinkedList<String> stage0 = new MyLinkedList<String>();
        stage0.appendToTail("Arkells");
        stage0.appendToTail("Bruno Mars");
        stage0.appendToTail("Coldplay");
        stage0.appendToTail("David Bowie");
        stage0.appendToTail("Earth, Wind & Fire");
        stage0.appendToTail("Foo Fighters");
        stage0.appendToTail("Gorillaz");
        
        MyLinkedListNode<String> loopToNode;
        loopToNode = stage0.searchByValue("Coldplay");

        MyLinkedListNode<String> tail;
        tail = stage0.searchByValue("Gorillaz");
        if(tail != null)
            tail.setNext(loopToNode);


        System.out.println("Cyclic Nodes: Stage 0 Lineup");
        MyLinkedListNode<String> currentNode;
        currentNode = stage0.searchByValue("Arkells");

        // If we use the toString method it will never terminate. 
        // Most linked list operations on this list will not work, so be careful!
        for(int i = 0; i<10 & currentNode != null; i++) { 
            System.out.println("Element: " + currentNode.toString());
            currentNode = currentNode.getNext();
        }
        System.out.println("\n");

        removeLinkedListCycles(stage0);
        
        } catch (Exception e) {}
    }

    public static void main(String[] args) {
        //Feel free to set up different tests, your code should be general, and will be tested against other cases
        createAndTestSharedNode();
        createAndTestCycle();
    }
}