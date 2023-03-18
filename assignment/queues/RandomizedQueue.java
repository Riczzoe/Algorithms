import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] items;
    private int firstIndex;
    private int size;
    private int capacity;

    public RandomizedQueue() {
        items = (Item[]) new Object[1];
        size = 0;
        capacity = 1;
        firstIndex = 0;
    }

    private void resize(int newSize) {
        Item[] newItems = (Item[]) new Object[newSize];
        for (int i = 0; i < size; i++) {
            newItems[i] = items[(firstIndex + i) % capacity];
        }
        firstIndex = 0;
        capacity = newSize;
        items = newItems;
    }


    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        if (size == capacity) {
            resize(2 * size);
        }

        items[(firstIndex + size) % capacity] = item;
        size++;
    }

    public Item sample() {
        if (size == 0) {
            throw new NoSuchElementException();
        }

        int randomNum = StdRandom.uniformInt(firstIndex, firstIndex + size);
        randomNum %= capacity;
        return items[randomNum];
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
    public Item dequeue() {
        if (size == 0) {
            throw new NoSuchElementException();
        }

        if (size <= capacity / 4) {
            resize(capacity / 2);
        }

        Item first = items[firstIndex++];
        firstIndex %= capacity;
        size--;
        return first;
    }


    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private int index = 0;
        private int[] indices = new int[size];

        public RandomizedQueueIterator() {
            for (int i = 0; i < size; i++) {
                indices[i] = (i + firstIndex) % capacity;
            }

            StdRandom.shuffle(indices);
        }

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            return items[indices[index++]];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
    
    public static void main(String[] args) {

    }
}
