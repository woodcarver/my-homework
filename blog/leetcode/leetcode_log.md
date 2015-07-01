# leetcode日志
#### 2：Add Two Numbers
- 指针和struct的访问方式混淆，例如 struct node node和struct node *node的访问方式。
- 注意数据状态
    
		overflow=0;
		if(digit>=10){
            overflow=digit/10;
            digit-=10;
        } 
  和
  
  		overflow=0;
  		if(digit>=10){
            digit-=10;
            overflow=digit/10;
        } 
的却别。下面那个绝对是错的，因为overflow永远是0