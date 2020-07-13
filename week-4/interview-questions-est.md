## Interview Questions: Elemetary Symbol Tables (ungraded)

## Java autoboxing and equals
```
https://stackoverflow.com/questions/12559634/java-autoboxing-rules/12559675#12559675:

double x1 = 0.0, y1 = -0.0;
Double a1 = x1, b1 = y1;
StdOut.println(x1 == y1);
StdOut.println(a1.equals(b1));

double x2 = 0.0/0.0, y2 = 0.0/0.0;
Double a2 = x2, b2 = y2;
StdOut.println(x2 != y2);
StdOut.println(!a2.equals(b2));
```

## Check if a binary tree is a BST
```
Design a recursive function isBST(Nodex,Keymin,Keymax) that determines whether x is the root of a binary search tree with all keys between min and max. 
```

## Inorder traversal with constant extra space
```
1. Initialize current as root 
2. While current is not NULL
   If the current does not have left child
      a) Print current’s data
      b) Go to the right, i.e., current = current->right
   Else
      a) Make current as the right child of the rightmost 
         node in current's left subtree
      b) Go to this left child, i.e., current = current->left

> https://www.geeksforgeeks.org/inorder-tree-traversal-without-recursion-and-without-stack/:
```

## Web tracking
```
Symbol table of symbol table
```