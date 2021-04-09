# HW06_Dessert Desert

[![hackmd-github-sync-badge](https://hackmd.io/zCEiqRp5Qzub7BzPvm32wg/badge)](https://hackmd.io/zCEiqRp5Qzub7BzPvm32wg)


###### tags: `演算法` `Java`

## 題目

測資是一個二維陣列，裡面好幾個整數陣列(未排序)，請求出每個陣列符合條件下最多的區塊(block)數。

條件為: 每一個block裡面我們將他視作是已經排序好的，此時整個陣列也要是排序好的。

(跟題目名稱好像沒有任何關係!??)

## 解題想法
好了啦，我知道題目看起來很難懂，但實際想過後其實還好，我用簡單的方式說明:

![](https://i.imgur.com/qR4bkYD.jpg)

將陣列分為AB兩塊，本來A+B是沒有排序好的，但如果AB裡面各自是排序好的，那A+B也是排序好的，題目要求符合的條件就是這個。

觀察一下題目，發現幾個有趣的線索(超重要會考!!):
1. **我們並不需要知道整個block的值，只需知道block中的最大和最小值即可。**
2. **當 A的最大值 小於等於 B的最小值，那麼A+B就是排序好的。**

很神奇對吧，其實這方法常會用到，像是LeetCode某題 - 兩個排序好的陣列求中位數。

既然知道這個線索了，那就開始來講演算法吧!

### go back algorithm
第一次從頭掃到尾，並且記錄**每個元素的最大及最小值**。  

會遇到三種狀況:  
(1) **如果新元素 小於 最小值，則將最小值設為新元素。**  
(2) **如果新元素 大於等於 最大值，則將最小值及最大值都設為新元素。**  
(3) **非以上兩種，就什麼都不做。**

所以長這樣:  
![](https://i.imgur.com/5PAqFci.jpg)

現在發現陣列依照max切成幾個粗略的block，但還沒結束，還需要再往回找一次。

從尾跑回頭，紀錄從右邊數來的最小值(rmin)。  
只要**上一個元素的最大值 小於等於 rmin，block數就加一**，並且rmin設為新元素的最小值，結束前別忘記最後一個block也要算到。

為什麼是對的?  
因為每個block的最右邊的位元，他所記錄的最大最小值正是這個block的最大最小值，因此回想前面所說...

> **當 A的最大值 小於等於 B的最小值，那麼A+B就是排序好的**， 

再套到這個情況

> **當上一個元素(A)的最大值 小於等於 rmin(B的最小值)，那麼兩個就是排序好的。**

因為是從右邊找過來第一個合乎條件的情況，所以這個切的**block長度肯定是最小的**，也就是**block數量肯定是最多的**，故得證。

而改變rmin是因為下一個block也要做一樣的事情。

還要多考慮到沒有切的情況，沒有切代表 A 沒有合乎條件，因此 A 必須和 B merge，merge有兩種:  
1. 如果A的min比較小，則合併後的min為A的min
2. 如果B的min比較小，則合併後的min為B的min

總之就是合併後的min(rmin)取較小的。  
當初Wrong Ans就是少考慮這個，想半天找不出反例，最後終於找到一個: (3, 5, 2, 9, 4)

![](https://i.imgur.com/Gvc8FrE.jpg)

![](https://i.imgur.com/NEVOG76.jpg)

這個方法只需來回跑一遍陣列，顯然複雜度為**O(2N)**。

特別感謝 *@林新紘* 提供其演算法，真的幾乎都他想的，我只是吸收理解後寫成筆記。

## 版本
1. go back algorithm
2. go back algorithm + thread8
3. go back algorithm + thread32 + static
4. go back algorithm + thread32 + no static
5. go back algorithm + thread32 + static + volatile

## 排名
wrong answer