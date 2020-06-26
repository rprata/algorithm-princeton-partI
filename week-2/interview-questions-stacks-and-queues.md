## Interview Questions: Stacks and Queues (ungraded)

## Queue with two stacks

```c++
#include <stack> 
using namespace std; 
  
struct Queue { 
    stack<int> s1, s2; 
  
    void enQueue(int x) 
    { 
        // Move all elements from s1 to s2 
        while (!s1.empty()) { 
            s2.push(s1.top()); 
            s1.pop(); 
        } 
  
        // Push item into s1 
        s1.push(x); 
  
        // Push everything back to s1 
        while (!s2.empty()) { 
            s1.push(s2.top()); 
            s2.pop(); 
        } 
    } 
  
    // Dequeue an item from the queue 
    int deQueue() 
    { 
        // if first stack is empty 
        if (s1.empty()) { 
            cout << "Q is Empty"; 
            exit(0); 
        } 
  
        // Return top of s1 
        int x = s1.top(); 
        s1.pop(); 
        return x; 
    } 
};  
```

## Stack with max 
```
1) Iterate over stack to find max element. Time: O(n), Space: O(1)
2) Keep an additional list of items in sorted order. Max element is the top of the sorted list. Complex to remove elements on pop. Time: O(1) for max, O(N) for pop, Space: O(n)
3) Keep an additional binary tree of elements. Time: O(lg N), Space: O(N)
```

## Java generics

Java generics are a compile time only type check. The type is "erased" during
compilation and a result the runtime does not know what type a generic object is.
Creating a generic array would mean the runtime cannot know the type of the array.
This would mean the runtime cannot perform type safety checks. This is not a
problem for other generic objects since they have their base type.

ArrayList<String> has the same type as ArrayList<Int>

http://www.angelikalanger.com/GenericsFAQ/FAQSections/ParameterizedTypes.html#FAQ104