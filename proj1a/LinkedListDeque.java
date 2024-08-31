public class LinkedListDeque<T> {

    //The data structure
    private class Node {
        T item;
        Node last;
        Node next;

        public Node(T item, Node last, Node next) {
            this.item = item;
            this.last = last;
            this.next = next;
        }
    }

    //basic information of the DLList
    private Node head = new Node(null, null, null);
    private Node tail = new Node(null, null, null);
    private int size;

    //initialize the DLList
    public LinkedListDeque() {
        head.next = tail;
        head.last = tail;
        tail.next = head;
        tail.last = head;
        size = 0;
    }

    //add a item in front of the list
    public void addFirst(T item) {
        Node newNode = new Node(item, head, head.next);
        head.next.last = newNode;
        head.next = newNode;
        size++;
    }

    //add a item in last of the list
    public void addLast(T item) {
        Node newNode = new Node(item, tail.last, tail);
        tail.last.next = newNode;
        tail.last = newNode;
        size++;
    }

    //jugde whether the list is empty
    public boolean isEmpty() {
        return size == 0;
    }

    //return the size of the list
    public int size() {
        return size;
    }

    //printout the whole list item
    public void printDeque() {
        Node temp = head.next;
        while (temp != tail) {
            System.out.print(temp.item + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    //delete the first element
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        Node temp = head.next;
        head.next = temp.next;
        temp.next.last = head;
        size--;
        return temp.item;
    }

    //delete the last element
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        Node temp = tail.last;
        temp.last.next = tail;
        tail.last = temp.last;
        size--;
        return temp.item;
    }

    private T getHelper(Node node, int index) {
        Node temp = node;
        while (index > 0) {
            index--;
            temp = temp.next;
        }
        return temp.item;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return getHelper(head.next, index);
    }


    private T getRecursiveHelper(Node node, int index) {
        if (index == 0) {
            return node.item;
        } else {
            return getRecursiveHelper(node.next, index - 1);
        }
    }

    public T getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return getRecursiveHelper(head.next, index);
    }
}
