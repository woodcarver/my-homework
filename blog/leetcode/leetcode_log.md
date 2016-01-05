# leetcode日志
## step to solution
1. Goal-Oriented Activity, 首先进行目标分析——make refine and precise pre- and posconditions. 现在还是没有能力写好前置，后置条件。
2. 

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
	
#### 11:Container With Most Water
- 一直在用联机的方法去解体，但是发现一直解出来。只找到了一个哦O(n^2)的方法。而且发现就是没办法更加优化。
- 后来看答案，发现使用了离线的方法去解题，反而轻轻松松。
- **问题**：到底什么时候用在线，什么时候去离线？怎么识别？

	> 优先在线，发现在联机基础假设基础上不能扩展，即不能使用假设。开始尝试用离线方法。
	
- 在线解法的起步：参考算法引论，做好假设和证明
- 离线的解法的起步：例举小例子

#### 13:Longest Common Prefix
- 如何判断一个二维数组是空的情况，如下：
	> char *strs[]={}
	
#### 14: 3Sum
- 如果证明跑遍了整个备选集合？如果是找特定的元素。怎么整么完备性？如果你证明不了，凭什么说你的方法是对的，可靠的？

#### 20: Valid Parentheses
- 当你写出一个了比较复杂的控制流逻辑，先尝试也出来，然后进行优化，一开始就优化很容易导致错误的逻辑。

#### 22: Generate Parentheses
- 一定要注意递归中的参数变化，压栈和出栈（restore）的印象
``` cpp
void generator(vector<string>& result, int left, int right, string s){
    if (left==0 && right==0){
        result.push_back(s);
        return;
    }
    if (left>0){
        generator(result, left-1, right, s+'(');
    }
    if (right>0 && right>left){
        generator(result, left, right-1, s+')');
    }
}
```
一定要注意s+'('和s+')'，这里并没有改变s，而是只传递给了下层递归。所以当调用完左子树后，会过来再调用右子树时，s从来没有被改变，所以不会出现重复添加(的情况。
c的写法
``` c
void generator2(char **result,int n,int left,int right,int level,char *tmp,int *returnSize){
    printf("left:%d,right:%d,level:%d\n",left,right,level);
    if(level==2*n){
        result[*returnSize]=tmp;
        printf("%s\n",tmp);

        tmp=malloc(2*n*sizeof(char)+1);
        strcpy(tmp,result[*returnSize]);
        tmp[2*n*sizeof(char)+1]='\0';
        (*returnSize)++;
    }

    if(left>0){
        tmp[level]='(';
        generator2(result,n,left-1,right,level+1,tmp,returnSize);
    }
    if(right>0 && right>left){
        tmp[level]=')';
        generator2(result,n,left,right-1,level+1,tmp,returnSize);
    }
}
```
注意其中level返回的时候从来都没有改变过。
上面已有个bug，就是重新分配tmp，而不是result。因为每次递归的话tmp很容易就被恢复成了原来的值，所result的所以元素都想指向同一个tmp。
