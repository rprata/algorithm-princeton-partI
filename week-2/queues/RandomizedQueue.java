import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int size;
    private Item[] rQueue;

    public RandomizedQueue() {
        rQueue = (Item[]) new Object[1];
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (size == rQueue.length) {
            resize(rQueue.length * 2);
        }
        rQueue[size++] = item;
    }

    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int rd = StdRandom.uniform(size);
        Item item = rQueue[rd];
        if (rd != size - 1) {
            rQueue[rd] = rQueue[size - 1];
        }
        rQueue[size - 1] = null;
        size--;
        if (size > 0 && size == rQueue.length / 4) {
            resize(rQueue.length / 2);
        }
        return item;
    }

    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int rd = StdRandom.uniform(size);
        Item item = rQueue[rd];
        return item;
    }

    private void resize(int capacity) {
        if (capacity < size) {
            throw new IllegalArgumentException();
        }
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            copy[i] = rQueue[i];
        }
        rQueue = copy;
        copy = null;
    }

    @Override
    public Iterator<Item> iterator() {

        return new Iterator<Item>() {
            private Item[] copy = rQueue.clone();
            private int copySize = size;

            @Override
            public boolean hasNext() {
                return copySize > 0;
            }

            @Override
            public Item next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                };
                int rd = StdRandom.uniform(copySize);
                Item item = copy[rd];
                if (rd != copySize - 1)
                    copy[rd] = copy[copySize - 1];
                copy[copySize - 1] = null;
                copySize--;
                return item;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    public static void main(String[] args) {
        RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<Integer>();
        randomizedQueue.enqueue(1);
        randomizedQueue.dequeue();
        randomizedQueue.enqueue(2);
        randomizedQueue.enqueue(3);
        randomizedQueue.enqueue(10);
        randomizedQueue.enqueue(0);
        Iterator<Integer> it = randomizedQueue.iterator();
        while (it.hasNext()) {
            StdOut.println(it.next());
        }

    }
}
