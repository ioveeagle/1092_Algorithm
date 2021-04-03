s=1000
list = sample(x=-1000:1000,size=s,replace=T)
list2 = sample(x=-1000:1000,size=s,replace=T)

sink("D:\\雲端\\程式檔案\\Eclipse\\file\\1092_Algorithm\\src\\hw05\\testData.txt")

cat(s)
cat("\n")
i=1
while(i<=s){
  cat(list[i])
  cat(",")
  cat(list2[i])
  cat("\n")
  i=i+1
}

sink()

