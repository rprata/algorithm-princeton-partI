## Interview Questions: Priority Queues (ungraded)

## Dynamic median
```
// 1) You can create two priority queues -> 1 minHeap, other maxHeap. 
// 2) Sort input array (a[]).
// 3) Adding size/2 firsts in maxHeap and size/2 - 1 other elements in minHeap.
// 4) if (maxHeap.size() == minHeap.size()), median = maxHeap.top() + minHeap.top() / 2, else, median = maxHeap.top()
// 5) To remove lower median --> median = maxHeap.pop()

public class PriorityQueue {
 
    class MediaHeap {
        private MaxPQ<Integer> left;
        private MinPQ<Integer> right;
        private int L;
        private int R;
 
        MediaHeap() {
            left = new MaxPQ<Integer>();
            right = new MinPQ<Integer>();
        }
 
        public double findMedian() {
            int L = left.size();
            int R = right.size();
            if (L == R)
                return ((double)left.max() + (double)right.min()) / 2;
            else if (L > R)
                return left.max();
            else
                return right.min();
        }
 
        public void insert(int key) {
            double median = findMedian();
            int L = left.size();
            int R = right.size();
            if (key <= median) {
                left.insert(key);
                if (L - R > 1)
                    right.insert(left.delMax());
            }
            else {
                right.insert(key);
                if (R - L > 1)
                    left.insert(right.delMin());
            }
        }
 
        public void removeMedian() {
            int L = left.size();
            int R = right.size();
            if (L > R) {
                left.delMax();
            }
            else {
                right.delMin();
            }
        }
 
    }
```

## Randomized priority queue
```
sample() -> sample from the underlying array
delRandom() -> swap with the last element, and then swim up or sink down, delete the last item(after swap)
```

## Taxicab numbers
```
1.quicksort all the sums and traverse from the beginning
2. https://stackoverflow.com/a/23658420/7801448
```