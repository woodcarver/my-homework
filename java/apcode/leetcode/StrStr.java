package leetcode;
import java.util.*;

public class StrStr {
	public int strStr(String haystack, String needle) {
		//朴素法
		System.out.println(haystack.length()+","+needle.length());
		if(haystack==null || needle==null){
			return -1;
		 }

		 //这些默认值是直接影响到根本不进入循环的情况，非常重要，一定要慎重
		 int posHaystack=0;
		 int posNeedle=0;
		 int needleLen=needle.length();
		 int haystackLen=haystack.length();
		 
		 while(posHaystack<haystackLen && posNeedle<needleLen){
			 System.out.println("posNeedle:"+posNeedle+",posHaystack:"+posHaystack);
			 if(haystack.charAt(posHaystack)!=needle.charAt(posNeedle)){
				 //0 1 2 3 4 5 6 7 8 9 10
				 //m i s s i s s i p p i
				 //  i s s i p
				 //          ^
				 //    i s s
				 //        ^
				 //        i s s i p
				 // 1=5-4
				 posHaystack=posHaystack-posNeedle+1;//特别注意这里也需要回溯,还需要前进一格
				 posNeedle=0;//backward
			 }else{
				 posNeedle++;
				 posHaystack++;
			 }
		}
		return posNeedle==needleLen?posHaystack-needleLen:-1;
	 }
	public int strStr2(String haystack, String needle) {
		//
		return 0;
	}
	public static void main(String[] args){
		 StrStr strstr=new StrStr();
//		 String haystack="mississippi";
//		 String needle="issip";
		 String haystack="sssttt";
		 String needle="ss";
		 System.out.println(strstr.strStr(haystack, needle));
	 }
}
