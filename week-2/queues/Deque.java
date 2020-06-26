import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private class Node {
        private Item item;
        private Node previous;
        private Node next;
    }

    private Node first, last;
    private int size;

    public Deque() {
        size = 0;
        first = null;
        last = null;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node tmp = first;
        first = new Node();
        first.item = item;
        first.previous = null;
        first.next = tmp;
        if (tmp != null) {
            tmp.previous = first;
        } else {
            last = first;
        }
        size++;
    }

    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node tmp = last;
        last = new Node();
        last.item = item;
        last.previous = tmp;
        last.next = null;
        if (tmp != null) {
            tmp.next = last;
        } else {
            first = last;
        }
        size++;
    }

    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Node tmp = first;
        first = first.next;
        if (first != null) {
            first.previous = null;
        } else {
            last = null;
        }
        size--;
        return tmp.item;
    }

    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Node tmp = last;
        last = last.previous;
        if (last != null) {
            last.next = null;
        } else {
            first = null;
        }
        size--;
        return tmp.item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {

            private Node current = first;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public Item next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Item item = current.item;
                current = current.next;
                return item;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<Integer>();
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);
        deque.addFirst(10);
        deque.addLast(0);
        deque.removeFirst();
        deque.removeLast();
        deque.removeLast();
        Iterator<Integer> it = deque.iterator();
        while (it.hasNext()) {
            StdOut.println(it.next());
        }
    }
}
