# HW07_Buy Phone

[![hackmd-github-sync-badge](https://hackmd.io/vKNw7zRnRo2J43psbRxhBQ/badge)](https://hackmd.io/vKNw7zRnRo2J43psbRxhBQ)


###### tags: `演算法` `Java`

## 題目

給你一個二維陣列((x~1~, y~1~), (x~2~, y~2~), ... , (x~n~, y~n~))，x代表手機的螢幕大小，y代表手機的效能表現，請列出無人能取代的手機。

**所謂無人能取代的手機，就是指沒有任何一台手機的x和y兩者都大於等於該手機的x和y。**

* output必須排序好
* 不會出現x和y同時一樣的測資

### example
```
Input: [[1,1],[2,4],[2,10],[5,4],[4,8],[5,5],[8,4],[10,2],[10,1]]

Output: [[2,10],[4,8],[5,5],[8,4],[10,2]]
```

## 解題想法
### 暴力解
兩個for迴圈了事，固定x，找有沒有x和y都比自己大的手機，複雜度為O(N^2^)

### Insertion Sort
先將陣列依照x排序(主)y排序(副)，不一定要用Insertion Sort，只是覺得助教的測資好像是接近排序好的測資，這樣的話Insertion Sort就會很快。

排好後，觀察排序好的陣列，從陣列尾向前掃描，讓最後一個數字的x=rx，y=ry，因為前面都是x比你小的數字，所以如果y小於ry，就是要被淘汰的了，但如果y比大於ry，則把他加入答案中，並讓他成為新的rx和ry。整個陣列掃完，答案區中的就是答案了。

時間複雜度: **Insertion Sort(best case: O(N), worst case: O((1/2)\*N^2^)) + scan array(N) = O(N ~ (1/2)\*N^2^)?)**

### Two Stack
Insertion Sort版本最花時間的部分就在於sort，所以我就開始想，可不可以不要sort呢?

**答案是可以的，解法是用兩個stack。**

概念是這樣的，stack1是用來存目前的答案，stack2則用來存暫時拿出去的手機。

因為在沒排序好的陣列中，可能會遇到新來手機的x比之前的x都還大，這時必須將新手機安插在stack1中的某個位置，此時就需要先將前面的手機暫存在stack2中，再放置新手機到指定位置，然後才將stack2的手機搬回stack1。

初始狀態

![](https://i.imgur.com/w5ymLml.jpg)

最後一個先push stack1 push

![](https://i.imgur.com/m2txNqx.jpg)

3小於9，但7大於2，所以push stack1

![](https://i.imgur.com/Pq6UQnW.jpg)

2小於3，且6小於7，所以什麼事都不做

![](https://i.imgur.com/ysIqMHt.jpg)

1小於3，但8大於7，所以push stack1

![](https://i.imgur.com/aM8CPfa.jpg)

因為8大於1，所以將(1,8) push stack2

![](https://i.imgur.com/UMKbb2G.jpg)

因為8還是大於3，所以將(3,7) push stack2

![](https://i.imgur.com/QJ7IOJj.jpg)

雖然8小於9，但1也小於2，所以(8,1)不能push stack1。  
最後將stack2所有值放回stack1。

![](https://i.imgur.com/Gv9T9j9.jpg)

stack1反過來就是答案了。

![](https://i.imgur.com/SHJa4iD.jpg)

小提示: 如果還是不懂，可以觀察答案會發現**x呈現遞增，而y呈現遞減**，從這個觀點會好想很多。

時間複雜度: **two stack(best case: O(N), worst case: O((1/2)\*N^2^)) = O(N ~ (1/2)\*N^2^)?)**

沒有sort雖然表面上是O(N)，但其實返回pop、push其實也要花時間，所以應該是取決於測資啦，測資越接近排好越有利(好像跟Insertion Sort一樣嘛😅，到底會不會比較快，明天見真章)

## 版本
1. Insertion Sort
2. Two Stack

## 排名
2021/04/25 6:00

![](https://i.imgur.com/1u9Qdoq.png)
