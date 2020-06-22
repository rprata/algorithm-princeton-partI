## Interview Questions: Analysis of Algorithms (ungraded)

## 3-SUM in quadratic time

The best way is using a creating a sorted hash table. For example, let the sum be S, for each element i in the array, let W = S - i, start from the begin index and end index of array, if sum of two elements > W, decrease end index by 1, else increase begin index by 1, stop when the sum = W.

## Search in a bitonic array

```
if i < pivot, 
  if left bound of interval < i,
    search in left subarray
  if right bound of interval < i,
    search in right subarray
else if i > pivot,
  if pivot is in increasing trend,
    search in right subarray
  else,
    search in left subarray
```

## Egg drop

https://math.stackexchange.com/questions/835582/egg-drop-problem
