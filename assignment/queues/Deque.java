import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private static class Node<Item> {
        public Node<Item> prev;
        public Item item;
        public Node<Item> next;

        public Node(Node<Item> prev, Item item, Node<Item> next) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }
    }

    private Node<Item> sentinel;
    private int size;

    public Deque() {
        this.sentinel = new Node<Item>(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    /*
        1.
         __________              ___________
        | sentinel | ========>  |   first   |
        |__________| <========  |___________|

        2.
                      _________
             T-------| newNode | -----T
             |       |_________|      |
         ____V_____              _____V_____
        | sentinel | ========>  |   first   |
        |__________| <========  |___________|

        3.
                      _________
             T-------| newNode | ------T
             | T---->|_________| <---T |
         ____V_|___              ____|_V____
        | sentinel | ===xxx==>  |   first   |
        |__________| <==xxx===  |___________|
     */
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        Node<Item> newNode = new Node<Item>(sentinel, item, sentinel.next);
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
        size++;
    }

    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        Node<Item> newNode = new Node<>(sentinel.prev, item, sentinel);
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
        size++;
    }

    /**
     * Returns true if deque is empty, false otherwise.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the number of items in the deque.
     */
    public int size() {
        return size;
    }


    /**
     *  Removes and returns the item at the front of the deque.
     *  If no such item exists, returns null.
     */
    public Item removeFirst() {
        if (size == 0) {
            throw new NoSuchElementException();
        }

        Item item = sentinel.next.item;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        size--;
        return item;
    }

    /**
     * Removes and returns the item at the back of the deque.
     * If no such item exists, returns null.
     */
    public Item removeLast() {
        if (size == 0) {
            throw new NoSuchElementException();
        }

        Item item = sentinel.prev.item;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        size--;
        return item;
    }


    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        Node<Item> p = sentinel.next;

        @Override
        public boolean hasNext() {
            return p != sentinel;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            Item item = p.item;
            p = p.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        
    }


}
