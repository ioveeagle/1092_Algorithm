# HW11_Group Counting

###### tags: `演算法` `Java`

## 題目

給你一張圖(Graph)，請求出圖有幾個component。

![](https://i.imgur.com/VtxcS1U.png)

## 解題想法
### DFS
我一開始看到這題時，心裡默默地想，作業09我不就已經做出類似的東西了嗎? 那我是不是可以開始躺了XD

但後來覺得不對，因為如果要用作業09的方法，我就必須建立一個adj matrix再DFS，時間上就輸別人一大截了，因此立刻開始想別的方法。

### Quick-Union
又回到了原點，使用Quick-Union的原因是因為Union可能有兩種情況：

假設兩個點(A、B)連在一起。
1. 只要是新的點(前面沒出現過的)，component數量加一。
2. 如果A和B是屬於同一個component，則component數量不變。
3. 如果A和B是屬於不同個component，則component數量減一。

![](https://i.imgur.com/Hn33yNV.png)

藍色表示新的邊。

另外，助教說這次作業的測資非常78，測資中點的名稱不一定是A、B、C，也不會按照順序，所以有可能點的名稱是這種的：`["B", "XXX", "Hello", "CC", "123"]`。

講義上教的方法，點的名稱就是點的index，顯然是通過不了測資的，因此需要另外想辦法。

### Hashmap
結果還是要用的hashmap，既然早晚都會用到，我就來研究一下。

HashMap 會用到 HashCode，所謂 HashCode 是每個物件都有的一個號碼，而且是隨機且分散的，HashMap 就是透過這個 HashCode 來找到對應的索引值，因為基本上是唯一的，所以可以實現在O(1)的時間內找到該物件。

HashCode 會和 HashMap 長度取餘數是為了避免 HashCode 超出範圍，但這樣做當兩個 HashCode 取餘數後剛好相同的時候，就會發生衝突(collision)。解決方法是用Linked List，將衝突的數值串成Linked List，先用HashCode找到這個Linked List，再掃過Linked List找到我們要的數值。

簡單講，HashMap就像你使用車站的置物櫃，你會知道使用櫃子的號碼，當你要取出行李時，照著號碼就可以找到了。

實際上還有很多細節，建議還是上網查清楚比較好。

好啦，那應用在這次作業中，我們會做出一個對應表，一個名稱對應到一個索引值，索引值就用在Quick-Union上。

使用Quick-Union的複雜度是O(N)，HashMap搜尋正常來說是O(1)，所以整體複雜度是O(N)。
