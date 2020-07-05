## Interview Questions: MergeSort (ungraded)

## Merging with smaller auxiliary array

```
1) Compare subarray1[i] with subarray2[i]. And add the lowest value in auxiliary array
2) After compare this auxiliary array with first subarray. If is different,  update subarray1 and subarray2
3) In other hands, copy only the left half into the auxiliary array.
```

## Counting inversions

```
Write merge sort algorithm and count number of swaps in merge.
```

## Shuffling a linked list

```
shuffle(list):
    if list contains a single element
        return list

    list1,list2 = [],[]
    while list not empty:
        move front element from list to list1
        if list not empty: move front element from list to list2

    shuffle(list1)
    shuffle(list2)

    if length(list2) < length(list1):
        i = pick a number uniformly at random in [0..length(list2)]             
        insert a dummy node into list2 at location i 

    # merge
    while list1 and list2 are not empty:
        if coin flip is Heads:
            move front element from list1 to list
        else:
            move front element from list2 to list

    if list1 not empty: append list1 to list
    if list2 not empty: append list2 to list

    remove the dummy node from list
```