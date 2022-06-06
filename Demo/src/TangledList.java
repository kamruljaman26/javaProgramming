

class TangledList {

    public static void removeSharedLinkedListNodes(LinkedList<String> list0, LinkedList<String> list1) throws MyLinkedListInterface.LinkedListListEmptyException, MyLinkedListInterface.LinkedListListEmptyException {
        int lenghtDiffernce;

        // setp 3
        MyLinkedListInterface.MyLinkedListNode deleteNode;
        MyLinkedListInterface.MyLinkedListNode node1 = list0.getHeadNode();
        MyLinkedListInterface.MyLinkedListNode node2 = list1.getHeadNode();

        int n = 0;

        lenghtDiffernce = list0.length() - list1.length();
        deleteNode = list1.getHeadNode();
        n = 1;

        while (lenghtDiffernce != 0) {
            node1 = node1.getNext();
            lenghtDiffernce--;
        }

        // circular move
        int index = 0;
        while (node1 != null && node2 != null && !node1.getValue().equals(node2.getValue())) {
            node2 = node2.getNext();
//            node1 = node1.getNext();
            node2 = node2.getNext();
            index++;
        }

        // if not null
        if (node1 == null) {
        } else {
            // delete common node
            while (index != 0) {
                deleteNode = deleteNode.getNext();
                index--;
            }

            //
            if (n == 1) {
                list1.deleteByValue((String) deleteNode.getValue());
            }

            if (n == 2) {
                list0.deleteByValue((String) deleteNode.getValue());
            }
        }

    }

    public static int detectCycleAndPeriod(LinkedList<String> list0) {
        MyLinkedListInterface.MyLinkedListNode head = list0.getHeadNode();

        MyLinkedListInterface.MyLinkedListNode tortoise = head;
        MyLinkedListInterface.MyLinkedListNode hare = head;

        do {
            if (head == null || head.getNext() == null) {
                return 0;
            }
            tortoise = tortoise.getNext();
            hare = hare.getNext().getNext();
        } while (hare != tortoise);

        int period = 0;
        hare = head;

        while (tortoise != hare)
            period++;

        return period;
    }

    public static void removeCycle(LinkedList<String> list0, int period) {
        // Write some assignemnt code here!
    }


    public static void removeLinkedListCycles(LinkedList<String> list0) {
        int period = detectCycleAndPeriod(list0);
        if (period != 0) {
            System.out.println("Cycle Detected. Period =" + period);
            removeCycle(list0, period);
            System.out.println("Cycle Removed.");
        }
    }

    // Code to setup one test case for eliminating shared nodes from two linked lists
    public static void createAndTestSharedNode() {
        // Your assignment submission should have more specific error handling
        try {
            LinkedList<String> stage0 = new LinkedList<String>();
            stage0.appendToTail("Arkells");
            stage0.appendToTail("Bruno Mars");
            stage0.appendToTail("Coldplay");
            stage0.appendToTail("David Bowie");
            stage0.appendToTail("Earth, Wind & Fire");

            LinkedList<String> stage1 = new LinkedList<String>();
            stage1.appendToTail("Foo Fighters");
            stage1.appendToTail("Gorillaz");

            MyLinkedListInterface.MyLinkedListNode<String> node;
            node = stage0.searchByValue("Coldplay");
            MyLinkedListInterface.MyLinkedListNode new_node = new MyLinkedListInterface.MyLinkedListNode<>(node.getValue());
            stage1.appendToTail(new_node);

            System.out.println("Shared Nodes: Stage 0 Lineup");
            System.out.println(stage0.toString());
            System.out.println("Shared Nodes: Stage 1 Lineup");
            System.out.println(stage1.toString());
            System.out.println("\n");

            removeSharedLinkedListNodes(stage0, stage1);

            System.out.println("Shared Nodes Fixed: Stage 0 Lineup");
            System.out.println(stage0.toString());
            System.out.println("Shared Nodes Fixed: Stage 1 Lineup");
            System.out.println(stage1.toString());
            System.out.println("\n");

        } catch (Exception e) {
        }
    }

    // Code to setup one test case for eliminating cycles from a linked list
    public static void createAndTestCycle() {
        // Your assignment submission should have more specific error handling
        try {
            LinkedList<String> stage0 = new LinkedList<String>();
            stage0.appendToTail("Arkells");
            stage0.appendToTail("Bruno Mars");
            stage0.appendToTail("Coldplay");
            stage0.appendToTail("David Bowie");
            stage0.appendToTail("Earth, Wind & Fire");
            stage0.appendToTail("Foo Fighters");
            stage0.appendToTail("Gorillaz");

            MyLinkedListInterface.MyLinkedListNode<String> loopToNode;
            loopToNode = stage0.searchByValue("Coldplay");

            MyLinkedListInterface.MyLinkedListNode<String> tail;
            tail = stage0.searchByValue("Gorillaz");
            if (tail != null)
                tail.setNext(loopToNode);


            System.out.println("Cyclic Nodes: Stage 0 Lineup");
            MyLinkedListInterface.MyLinkedListNode<String> currentNode;
            currentNode = stage0.searchByValue("Arkells");

            // If we use the toString method it will never terminate.
            // Most linked list operations on this list will not work, so be careful!
            for (int i = 0; i < 10 && currentNode != null; i++) {
                System.out.println("Element: " + currentNode.toString());
                currentNode = currentNode.getNext();
            }
            System.out.println("\n");

            removeLinkedListCycles(stage0);

        } catch (Exception e) {
        }
    }

    public static void main(String[] args) {
        //Feel free to set up different tests, your code should be general, and will be tested against other cases
        createAndTestSharedNode();
        createAndTestCycle();
    }
}