package leetcode;
//此问题有坑，特别注意其中的同一个字母不能映射到多个字母上
//教训：也许我直接用hashmap，既不会出现这种双向问题
public class IsomorphicStrings {
    public boolean isIsomorphic(String s, String t) {
    	int[] charMapS=new int[256];//for 26 characters,assumption there is only characters, BUT I am wrong!
    	int[] charMapT=new int[256];
    	for(int i=0,len=s.length(); i< len; i++){
    		int slotS=s.charAt(i);
    		int slotT=t.charAt(i);
    		if(0==charMapS[slotS]){
    			charMapS[slotS]=t.charAt(i)+1;//must add 1 for t.charAt(i)'s value is 0
    		}
    		if(0==charMapT[slotT]){
    			charMapT[slotT]=s.charAt(i)+1;
    		}
    		
    		if(((t.charAt(i)+1) != charMapS[slotS]) || ((s.charAt(i)+1) != charMapT[slotT])){
    			return false;
    		}
    	}
    	return true;
    }
    //very clever thing to using third number to replace the truly value.
    public boolean isIsomorphic2(String s, String t) {
	    int counter = 1;
	    int poS, poT;
	    char[] sa = s.toCharArray();
	    char[] ta = t.toCharArray();
	
	    int[] arrS = new int[256];
	    int[] arrT = new int[256];
	
	    for(int i = 0; i != s.length(); i++){
	        poS = sa[i];
	        poT = ta[i];
	        if(arrS[poS] != arrT[poT]) {
	            return false;
	        } else if(arrS[poS] == 0) {
	            arrS[poS] = arrT[poT] = counter++;//Great
	        } 
	    }
	    return true;   
	}
    public static void main(String[] args){
    	IsomorphicStrings isomorphic=new IsomorphicStrings();
//    	String[] s={"egg","add"};
//    	String[] s={"foo", "bar"};
//    	String[] s={"paper", "title"};
//    	String[] s={"", ""};
//    	String[] s={"paper", "title"};
//    	String[] s={"13","42"};
    	String[] s={"ab","aa"};//Tricky part
    	System.out.println(isomorphic.isIsomorphic(s[0], s[1]));
    }
}
