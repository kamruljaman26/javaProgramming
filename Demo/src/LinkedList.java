public class LinkedList<ElementType> implements MyLinkedListInterface<ElementType> {

    private MyLinkedListNode<ElementType> head;
    private int length = 0;

    public LinkedList() {
        head = null;
    }

    public MyLinkedListNode<ElementType> getHeadNode() {
        return head;
    }


    // append to tail by value
    public void appendToTail(ElementType value) throws LinkedListValueExistsException {
        try {
            MyLinkedListNode<ElementType> node = searchByValue(value);
            if (node != null) throw new LinkedListValueExistsException("Exception: value already Exists");
        } catch (LinkedListListEmptyException e) {
//            e.printStackTrace();
        }

        if (head == null) {
            head = new MyLinkedListNode<>(value);
            length++;
        } else {
            MyLinkedListNode<ElementType> current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(new MyLinkedListNode<>(value));
            length++;
        }
    }


    // append to tail by node
    public void appendToTail(MyLinkedListNode<ElementType> node) throws LinkedListValueExistsException {
        appendToTail(node.getValue());
    }


    // length
    public int length() {
        return length;
    }

    // delete by value
    public void deleteByValue(ElementType value) throws LinkedListListEmptyException {
        // if head is null or list is empty
        if (head == null) throw new LinkedListListEmptyException("Exception: List is empty.");

        // if not found return
        if (searchByValue(value) == null) return;

        // if value in head
        if (head.getValue().equals(value)) {
            head = head.getNext();
            length--;
        } else {

            MyLinkedListNode<ElementType> current = head; // node pointer

            while (current.getNext() != null) {
                // search from beginning
                if (current.getValue().equals(value)) {
                    ElementType e = current.getNext().getValue();
                    current.setNext(current.getNext().getNext()); // skip next
                    current.setValue(e); // update current value to next one

                    length--;
                    return;
                } else {
                    // if last node is have the value
                    if(current.getNext().getNext() == null && current.getNext().getValue().equals(value)){
                        current.setNext(null);
                    }else {
                        // move to next one
                        current = current.getNext();
                    }
                }
            }
        }
    }


    // search buy value
    public MyLinkedListNode<ElementType> searchByValue(ElementType value) throws LinkedListListEmptyException {

        // if head is null or list is empty
        if (head == null) throw new LinkedListListEmptyException("Exception: List is empty.");

        MyLinkedListNode<ElementType> current = head; // node pointer

        while (current.getNext() != null) {

            // search from beginning
            if (current.getValue().equals(value)) {
                return current;
            }

            // move to next one
            current = current.getNext();
        }

        // search in last element
        if (current.getValue() != null && current.getValue().equals(value)) {
            return current;
        }

        // if not found return null
        return null;
    }


    // to string
    public String toString() {

        // if head is null or list is empty
        if (head == null) return "[ LIST IS EMPTY ]";

        StringBuilder sb = new StringBuilder();

        MyLinkedListNode<ElementType> current = head;
        while (current.getNext() != null) {
            sb.append(current.getValue()).append(" -> ");
            current = current.getNext();
        }
        if (current.getValue() != null) {
            sb.append(current.getValue());
        }
        return sb.toString();
    }


    public static void main(String[] args) throws LinkedListValueExistsException, LinkedListListEmptyException {
        LinkedList<String> list = new LinkedList<>();
        list.appendToTail(new MyLinkedListNode<>("Kamrul"));
        list.appendToTail(new MyLinkedListNode<>("Jaman"));
        list.appendToTail(new MyLinkedListNode<>("Japan"));
        list.appendToTail(new MyLinkedListNode<>("Rokit"));
        list.appendToTail(new MyLinkedListNode<>("Demo"));

        System.out.println("--------------- Delete Node");
        System.out.println(list);
        System.out.println(list.length);
        list.deleteByValue("Jaman");
        list.deleteByValue("Kamrul");
        System.out.println(list);
        System.out.println(list.length);

        list.appendToTail("Hi");
        System.out.println(list);
        System.out.println(list.length);
    }
}