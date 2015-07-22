# leetcode日志

#### 2：Add Two Numbers

- 指针和struct的访问方式混淆，例如 struct node node和struct node *node的访问方式。
- 注意数据状态

	```
		overflow=0;

		if(digit>=10){

        overflow=digit/10;
        digit-=10;
    } 
	```

  和
  
	```
  		overflow=0;

  		if(digit>=10){
 
        digit-=10;
        overflow=digit/10;
    } 
	```

的却别。下面那个绝对是错的，因为overflow永远是0

#### 3：findMedianSortedArrays

- 注意在c语言里，负数是向0方向取整的（-0.5~0）,正数是向0方向取整（0.5~0）。但是在python和php里，全部都是想下取整（-0.5~-1，-1.5~-2）
  
- 这种用逻辑怎么证明每种情况都是符合，即证明其完备性
  
  ``` 
    if((med2==-1) || nums1[med1]>nums2[med2])
        median=(median+nums1[med1])/2.0;
    else
        median=(median+nums2[med2])/2.0;
  ```

#### 6：zigzag

- step=numRows-1; for(k=r; k<len; k+=step) ------>    step=numRows<=1?1:numRows-1; for(k=r; k<len; k+=step)
  
  > 确保for中的step一定是大于零的，<=0都会造成死循环。所以遇到对stemp做减法等操作，一定要注意。

这是打脸的节奏吗？

![Alt text](./img/leetcode6.png =700x220)

记住，字符串一定要控制长度，防止overflow，特别是要及时截止

`out[len]='\0';`

我来个去，发现我居然局部解题了。列举方法只适用一种情况，有没有办法检查出来，怎么证明方法的适用范围？

这个题说来说去，是我理解有问题。但是后来理解了，但是发现自己还真不会做，所找了答案，发现很不错。

这种操作下标志的题其实是很难的。一不小心就陷入谜潭了。

#### 7:Reverse Integer

- 学会变量跟踪，即变量状态变化
  
- 探测溢出真不容易，特别是有符号的探测
  
#### 8:String to Integer(atoi)
  
- To deal with overflow, inspect the current number before multiplication. If the current number is greater than 214748364, we know it is going to overflow. On the other hand, if the current number is equal to 214748364, we know that it will overflow only when the current digit is greater than or equal to **8**.

	```
	// out*10+digit <= INT_MAX, out*10+digit >= INT_MIN. but 	INT_MAX/10==INT_MIN/10

	//out>INT_MAX/10 predicate is not enough ,think about the case:out=214748364,digit=8
	if(out<INT_MAX/10 || (out==INT_MAX/10 && 
    	str[i]-'0'<=(INT_MAX-INT_MAX/10*10)))
    	out=out*10+(str[i]-'0');
	else
    	return sign>0?INT_MAX:INT_MIN;//not zero in c is true, include -1!!
	```