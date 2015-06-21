# 计算机的数值表示（number representations）

## 各种类型占用大小(date size)

C declaration| 32-bit| 64-bit
--------------|-------|--------
char          |1      |1
short int     |2      |2
int           |4      |4
long int      |4      |8
long logn int |8      |8
char *        |4      |8
float         |4      |4
double        |8      |8

Figure 1: Sizes (in bytes) of C numeric data types. The number of bytes allocated varies with machine and compiler. This chart shows the values typical of 32-bit and 64-bit machines.

## 现实中的数怎么在计算中表示
大家都是知道现实中的十进制需要转化成二进制才能在计算机中表示(至于是怎么想出来要用二进制，这么天才的想法，也许需要更深的历史了解)。那么其实就很简单。
那么就进行简单的转换就行了，如**正整数**10,用二进制表示是1010。原因
$$10 = 2^3 + 2^1 = 1 \times 2^3 + 0 \times 2^2 + 0 \times 2^1 + 0 \times 2^0 = \sum\_{k=0}^3 x 2^k$$
好到这步我们发现正整数的表示工作都完成了，so far so good!
表示完了，下一步自然是对数进行操作了。

- 加法：
- 减法：
- 乘法：
- 除法：

## 使用数据化模型
上面的公式中的x，可以用一个向量来表示
$$\vec{\mathbf{x}}=[x_w,x_{w-1},...,x_1,x_0]$$

## 接下来负数该怎么表示
上面看到全是正数和0怎么用二进制来表，现在把数域扩展到整数范围，问题就出现了。怎么用二进制来表示负数？
因为在计算机中只有高低电平，也就是只有0和1，并没有类似于数学中的-符号，所以必须找到一种方法来表示这个符号。一个很直观的方法就是利用数位的信息来表示符号。贡献出数的第一位bit来作为符号，0表示正数，1表示负数。例如，对于4bit宽度的数来说：
$$ 1 = 0001 $$
$$ -1 = 1001$$

## 二补码的由来
