## Interview Questions: MergeSort (ungraded)

## Nuts and bolts
```
Modify partitioning algorithm in randomized quicksort - http://web.cs.ucla.edu/~rafail/PUBLIC/17.pdf:
1) Pick a random bolt.
2) Compare it to all the nuts.
3) Spliting nuts in 3 parts: smaller, exactly fit and bigger than bolt.
4) Compare the matching nut found above and compare to rest of remaining (n - 1 bolts), 
thus splitting bolt in 3 parts: looser, exactly fit and tigher.
5) Thus spliting the problem into two problems, one consisting of the nuts and bolts
smaller than the matched pair and one consisting of the larger ones.
6) Make recursive calls (quicksort)
```

## Selection in two sorted arrays
```
Partition and select (similar quick sort). Use median_a and median_b as pivot.
```

## Decimal dominants
```
3-way-partition (quickselect): 

def three_way_partition(arr, start, end, dominants):
    if (end - start + 1) >= int(len(arr)/10):
        lt = start
        gt = end
        i = lt
        while True:
            if arr[i] < arr[lt]:
                arr[lt], arr[i] = arr[i], arr[lt]
                i += 1
                lt += 1
            elif arr[i] > arr[lt]:
                arr[gt], arr[i] = arr[i], arr[gt]
                gt -= 1
            else:
                i += 1
            if i > gt:
                break
        if gt - lt + 1 > int(len(arr)/10):
            dominants.append(arr[lt])
        three_way_partition(arr, start, lt-1, dominants)
        three_way_partition(arr, gt + 1, end,  dominants)


def decimal_dominants(arr):
    dominants = []
    three_way_partition(arr, 0, len(arr)-1, dominants)
    return dominants
```