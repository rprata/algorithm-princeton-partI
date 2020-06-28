## Interview Questions: Elementary sorts (ungraded)

## Intersection of two sets

```
1) Sort  one array (for example b[]). 
2) After that, find each value in unsorted array in sorted array.
This algorithm is (n^2) (worst case of selection sort)
```

## Permutation 
```
1) Sort two arrays
2) Compare  arrays.If equal, one is a permutation of the other.
```

## Dutch national flag

The best way to solve this problem is using 3-way-partition (https://en.wikipedia.org/wiki/Dutch_national_flag_problem):

```java
// red == -1
// blue == 1
// white == 0
void threeWayPartition(int[] a) {
	int i = 0, j = 0;
	int size = a.size();
	while(j < size) {
		if (a[i] == -1) {
			swap(i, j);
			i++;
			j++;
		} else if (a[i] == 1) {
			size--;
			swap(j, size);
		} else {
			j++;
		}
	}
}
```