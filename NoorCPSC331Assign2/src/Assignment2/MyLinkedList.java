package Assignment2;


public class MyLinkedList<ElementType> implements MyLinkedListInterface<ElementType> {
    
    private MyLinkedListNode<ElementType> head;

    public MyLinkedList(){
         head = null;
    }

    public MyLinkedListNode<ElementType> getHeadNode() {
        // Write some assignment code here!
        return null;
    }

    public void appendToTail(ElementType value) throws LinkedListValueExistsException {
        // Write some assignment code here!
    }

    public void appendToTail(MyLinkedListNode<ElementType> node) throws LinkedListValueExistsException {
		// Write some assignment code here!
		// Note - It is a good idea not to make changes to the .next value of the input node here

    }

    public int length() {
        // Write some assignment code here!
        return -1;
    }

    public void deleteByValue(ElementType value) throws LinkedListListEmptyException {
        // Write some assignment code here!
    }

    public MyLinkedListNode<ElementType> searchByValue(ElementType value) throws LinkedListListEmptyException {
        // Write some assignment code here!
        return null;
    }   

    public String toString() {
        // Write some assignment code here!
        return "";
    }
}