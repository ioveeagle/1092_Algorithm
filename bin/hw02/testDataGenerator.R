s = 1000

list = sample(x=-10000:10000,size=s,replace=F)

sink("test_data.txt")
for (i in list){
  cat(i)
  cat('\n')
}
sink()
