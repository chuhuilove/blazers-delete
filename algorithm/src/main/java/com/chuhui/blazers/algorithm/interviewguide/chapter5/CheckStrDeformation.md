# 判断两个字符串是否互为变形词


**题目**

给定两个字符串str1和str2,如果str1和str2中出现的字符种类一样且每种字符出现的次数也一样，那么str1和str2互为变形词。

**举例**

str1="123",str2="231" 返回`true`

str1="123",str2="2311" 返回`false`


### 自己的方式

   根据题意，有一点可以确定，如果字符串长度不相同，肯定返回`false`,如果两个字符串长度相同,并且`equals`后返回`true`，则返回`true`,比较特殊的变形词。
   
   设两个字符串分别为`str1`和`str2`，只要将`str1`中每一个字符的种类和数量统计出来，然后再统计`str2`中每一个字符的种类和数量，对比一下，就可以了。
   
   代码：[CheckStrDeformation.customerCheckStrDeformation](CheckStrDeformation.java)
   
   假设字符串的长度为N，则该算法时间复杂度为O(N^2)，空间复杂度为O(N)。
  
  
### 书上解答
    
   假设出现字符串的编码值在0-255之间，那么先申请一个长度为255的整型数组map,map[a]=b代表字符编码为a的字符出现了b次，初始map[0..255]的值都是0。然后遍历字符串str1,统计每一个字符出现的数量，比如遍历到字符'a'，其编码值为map[97]++。这样map就成了str1中每中字符的词频统计表。然后再遍历字符串str2，每遍历到一个字符都在map中把词频减下来，比如遍历到字符'a'，其编码值为97，则令map[97]--，如果减少后的值小于0，直接返回`false`。如果遍历完str2，map中没有出现负值，则返回true。
   
   代码：[CheckStrDeformation.isDeformation](CheckStrDeformation.java)
   
   
   如果字符串的长度为M，字符种类为M，该解法时间复杂度为O(N)，额外空间复杂度为O(M)。
   

   
    
    
   
    
    
    