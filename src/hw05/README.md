# HW05_LLK

[![hackmd-github-sync-badge](https://hackmd.io/5uAJy3JEQiugYyPJblppqQ/badge)](https://hackmd.io/5uAJy3JEQiugYyPJblppqQ)


###### tags: `演算法` `Java`

## 題目
測資是n組二維座標，請找出是否存在三點共線，是的話回傳true，否則回傳false。

* 3 < n < 1000

## 解題想法
這題和前面幾題很類似，都是找到true就可以提前結束，因此很吃運氣。

### 暴力解
直接三層for迴圈，取三點A、B、C，並求AB和AC向量的外積，如果為0則三點貢線，複雜度為O(1/3\*N^3)，就是垃圾。

### 斜率法
先固定一點A，然後求A到其他點的斜率，如果有兩點具有一樣的斜率，則代表有共線。

斜率的求法為: **Y1 - Y2 / X1 - X2**

但聰明的人一看，就知道整數除以整數會出現什麼問題了。  
所以比較聰明的方法是將斜率以最簡分數的形式儲存。

### 斜率_最簡分數法
跟上面不一樣的地方在於，我們會分開兩個值去記錄到A點到其他點斜率，deltaX為分母，deltaY為分子:

**deltaX = X1 - X2 / gcd(deltaX, deltaY)**  
**deltaY = Y1 - Y2 / gcd(deltaX, deltaY)**  

除以GCD(最大公因數)的目的是為了取最簡整數，我們希望deltaX可以保持正數，所以如果deltaX為負，則將deltaX和deltaY都乘上負數。

### 斜率的儲存方式
基本上有兩種，一種是MergeSort，一種是HashMap，兩種方法我都會講:

#### MergeSort
因為我們想要知道是否存在兩個斜率相同，但前面存斜率的方法是雜亂的，因此需要先將斜率先排序過，再檢查看看是否存在連續兩個斜率相同。

這樣的時間複雜度為: O(1/2\*N^2^*NlogN)

#### HashMap
HashMap有一對一之性質，因此適合來檢查是否存在重複值，如果存入值時發現該位置已經存有值了，則代表存在重複值。

優點在於不需要排序，時間複雜度為:O(1/2\*N^2^)
