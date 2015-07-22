#include<stdio.h>
bool isPalindrome(int x) {
    //input: 1,1234321, 11122111
    //123,567,1111231111
    // 0
    // -1,-111  ---> leetcode thinks that negative integers should have no palindromes
    //-789,-010
    //precodition:?
    int len,left,right,left_pow,right_pow,right_digit,left_digit;
    int cx=x;

    if(x==0)return true;
    if(x<0)return false;
    len=0;
    while(cx){
        len++;
        cx/=10;
    }
    left=0;
    right=len-1;
    printf("len:%d\n",len);
    while(left<=right){
        right_pow=(int)pow(10,right);//right=0 ^ right_pow=1
        right_digit=x/right_pow-x/right_pow/10*10;
        left_pow=(int)pow(10,left);//left=0 ^ left_pow=1
        left_digit=x/left_pow-x/left_pow/10*10;

        //printf("left:%d:%d,right:%d,%d\n",left,left_digit,right,right_digit);
        if(right_digit!=left_digit)
            return false;
        left++;
        right--;
    }
    //what is the poscondition?
    return true;
}
int main()
{
    printf("%d is %d\n", 0, isPalindrome(0) );
    printf("%d is %d\n", -101, isPalindrome(-101) );
    printf("%d is %d\n", 1001, isPalindrome(1001) );
    printf("%d is %d\n", 1234321, isPalindrome(1234321) );
    printf("%d is %d\n", 2147447412, isPalindrome(2147447412) );
    printf("%d is %d\n", 2142, isPalindrome(2142) );
}
