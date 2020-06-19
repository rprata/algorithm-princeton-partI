## Interview Questions: Union–Find (ungraded)

## Social network connectivity

```c++
#include <iostream>
#include <vector>

std::vector<int> id;
std::vector<int> size;
int total;

typedef struct socialnetworklog
{
    long timestamp; //timestamp connected
    int p; //user p
    int q; //user q
} socialnetworklog;

std::vector<socialnetworklog> logs;

void init(int n) {
    total = n;
    id.reserve(n);
    size.reserve(n);
    for (int i = 0; i < n; i++) {
        id[i] = i;
        size[i] = 1;
    }
}

int root(int i) {
    while(id[i] != i) {
        id[i] = id[id[i]];
        i = id[i];
    }
    return i;
}

void connect(int p, int q) {
    int i = root(p);
    int j = root(q);
    if(i == j) return;
    if(size[i] > size[j]){
        id[j] = i;
        size[i] += size[j];
    } else {
        id[i] = j;
        size[j] += size[i];
    }
}

bool connected(int p, int q) {
    return root(q) == root(p);
}

long earliestTime() {
    for(auto& log : logs) {
        if (!connected(log.p, log.q)) {
            connect(log.p, log.q);
            total--;
        }
        if (total == 1) {
            return log.timestamp;
        }
    }
    return -1;
}

/* sample input:
10
1592600270 0 1
1592600270 4 5
1592600270 8 9
1592600270 2 4
1592600270 5 6
1592600270 7 8
1592600270 2 5
1592600270 6 7
1592600270 1 2
1592600270 0 3
1592600270 1 9
1592600270 3 7

*/

int main(void) {
    long l_timestamp;
    int l_p, l_q, N;
    std::cin >> N;
    init(N);
    while (std::cin >> l_timestamp >> l_p >> l_q)
    {
        socialnetworklog log = {
            .timestamp = l_timestamp,
            .p = l_p,
            .q = l_q
        };
        logs.push_back(log);
    }
    std::cout << earliestTime() << std::endl;
}
```

## Union-find 
```c++
#include <iostream>
#include <algorithm>
#include <vector>

std::vector<int> id;
std::vector<int> size;
std::vector<int> arrmax;

void init(int n) {
    id.reserve(n);
    size.reserve(n);
    arrmax.reserve(n);
    for (int i = 0; i < n; i++) {
        id[i] = arrmax[i] = i;
        size[i] = 1;
    }
}

int root(int i) {
    while(id[i] != i) {
        id[i] = id[id[i]];
        i = id[i];
    }
    return i;
}

void connect(int p, int q) {
    int i = root(p);
    int j = root(q);
    if(i == j) return;
    if(size[i] > size[j]){
        id[j] = i;
        size[i] += size[j];
        arrmax[i] = std::max(arrmax[i], arrmax[j]);
    } else {
        id[i] = j;
        size[j] += size[i];
        arrmax[j] = std::max(arrmax[i], arrmax[j]);
    }
}

bool connected(int p, int q) {
    return root(q) == root(p);
}

int find(int i){
    return root(i);
}

int getMax(int i) {
    return arrmax[find(i)];
}
```

## Successor with delete

```c++
#include <iostream>
#include <algorithm>
#include <vector>

std::vector<int> id;
std::vector<bool> removed;
int total;
void init(int n) {
    total = n;
    id.reserve(n);
    removed.reserve(n);
    for (int i = 0; i < n; i++) {
        id[i] = i;
        removed[i] = false;
    }
}

int root(int i) {
    while(id[i] != i) {
        id[i] = id[id[i]];
        i = id[i];
    }
    return i;
}

void connect(int p, int q) {
    int i = root(p);
    int j = root(q);
    if(i == j) return;
    if(i > j){
        id[j] = i;
    } else {
        id[i] = j;
    }
}

bool connected(int p, int q) {
    return root(q) == root(p);
}

int find(int i){
    return root(i);
}

void remove(int i){
    removed[i] = true;
    if (i - 1 >= 0 && removed[i - 1]){
        connect(i, i - 1);
    }else if(i + 1 < total && removed[i + 1]){
        connect(i, i + 1);
    }
}

int successor(int x){
    int num = find(x) + 1;
    if(num < total){
        return num;
    }
    return -1;
}
```