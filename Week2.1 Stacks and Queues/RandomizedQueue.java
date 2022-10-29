import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Node first = null;
    private Node last = null;
    private int count = 0;

    private class Node {
        Node prev;
        Node next;
        Item item;
    }

    // construct an empty randomized queue
    public RandomizedQueue() {

    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return count == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return count;
    }

    // add the item
    public void enqueue(Item item) {
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

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty())
            throw new NoSuchElementException();

        Item item;

        int n = StdRandom.uniformInt(size());
        if (n == 0) {
            item = first.item;

            first = first.next;
            if (first != null)
                first.prev = null;
            else // Empty
                last = null;
        }
        else if (n == size() - 1) {
            item = last.item;

            last = last.prev;
            if (last != null)
                last.next = null;
            else
                first = null;

        }
        else {
            Node node = first;
            for (int i = 0; i < n; i++) {
                node = node.next;
            }
            item = node.item;

            Node prevnode = node.prev;
            Node nextnode = node.next;

            prevnode.next = nextnode;
            nextnode.prev = prevnode;
        }

        count--;

        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty())
            throw new NoSuchElementException();

        int n = StdRandom.uniformInt(size());

        Node node = first;
        for (int i = 0; i < n; i++) {
            node = node.next;
        }

        return node.item;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIteraor();
    }

    private class RandomizedQueueIteraor implements Iterator<Item> {
        private Node current = first;
        private boolean[] visited = new boolean[size()];
        private int visitedCount = 0;

        public boolean hasNext() {
            return visitedCount < size();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();

            visitedCount++;

            int n;

            do {
                n = StdRandom.uniformInt(size());
            } while (visited[n]);

            visited[n] = true;

            Node node = first;
            for (int i = 0; i < n; i++) {
                node = node.next;
            }

            return node.item;
        }
    }


    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();

        rq.enqueue(1);
        rq.enqueue(2);
        rq.enqueue(3);

        for (int i : rq) {
            StdOut.println(i);
        }

        StdOut.println(rq.dequeue());
        StdOut.println(rq.dequeue());
        StdOut.println(rq.dequeue());
    }

}