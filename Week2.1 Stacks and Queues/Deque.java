import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node first = null;
    private Node last = null;
    private int count = 0;

    private class Node {
        Node prev;
        Node next;
        Item item;
    }

    // construct an empty deque
    public Deque() {

    }

    // is the deque empty?
    public boolean isEmpty() {
        return count == 0;
    }

    // return the number of items on the deque
    public int size() {
        return count;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null)
            throw new IllegalArgumentException();

        Node oldfirst = first;

        first = new Node();
        first.item = item;
        first.next = oldfirst;
        first.prev = null;

        if (oldfirst != null)
            oldfirst.prev = first;
        else // Empty
            last = first;

        count++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null)
            throw new IllegalArgumentException();

        Node oldlast = last;

        last = new Node();
        last.item = item;
        last.prev = oldlast;
        last.next = null;

        if (oldlast != null)
            oldlast.next = last;
        else // Empty
            first = last;

        count++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (first == null)
            throw new NoSuchElementException();

        Item item = first.item;

        first = first.next;
        if (first != null)
            first.prev = null;
        else // Empty
            last = null;

        count--;

        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (first == null)
            throw new NoSuchElementException();

        Item item = last.item;

        last = last.prev;
        if (last != null)
            last.next = null;
        else
            first = null;

        count--;

        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (current == null)
                throw new NoSuchElementException();

            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<Integer>();

        StdOut.println(deque.isEmpty());
        deque.addFirst(1);
        StdOut.println(deque.isEmpty());
        StdOut.println(deque.removeLast());
        deque.addLast(2);
        StdOut.println(deque.removeFirst());

        deque.addFirst(1);
        deque.addLast(2);
        deque.addFirst(3);

        for (int i : deque) {
            StdOut.println(i);
        }

        StdOut.println(deque.size());
    }

}