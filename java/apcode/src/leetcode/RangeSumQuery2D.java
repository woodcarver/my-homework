package leetcode;
/**
 * @author xiedandan
 * 问题：不会操作java中的二维数组
 * bug：1. 35 line把collen写成了rowlen
 * 假设很重要，写代码之前一定要考虑precondition
 * - You may assume that the matrix does not change.
 * - There are many calls to sumRegion function.
 * - You may assume that row1 ≤ row2 and col1 ≤ col2.
 * 
 * 看了答案后发现总有比我方法更优化的，总有一些很新颖的想法。
 * 最好使用最顺其自然的方法写，只有这样才能避免很多陷阱。
 */
import java.util.*;
public class RangeSumQuery2D {
	public static void main(String[] args){
		int[][] matrix={{1,2},{2,3},{3,4}};
//		int[][] matrix={};
		NumMatrix numMatrix=new NumMatrix(matrix);
		System.out.println(numMatrix.sumRegion(0, 0, 0, 0));
		System.out.println(numMatrix.sumRegion(1, 1, 2, 1));
		System.out.println(numMatrix.sumRegion(1, 0, 2, 1));
		
		NumMatrix2 numMatrix2=new NumMatrix2(matrix);
		System.out.println(numMatrix2.sumRegion(0, 0, 0, 0));
		System.out.println(numMatrix2.sumRegion(1, 1, 2, 1));
		System.out.println(numMatrix2.sumRegion(1, 0, 2, 1));
	}
}
class NumMatrix {
	private int[] sums;
	private int rowlen;
	private int collen;
    public NumMatrix(int[][] matrix) {
        if(null==matrix || 0==matrix.length){
        	rowlen=collen=0;
        }else{// bug2, forget add else
	        rowlen=matrix.length;
	        collen=matrix[0].length;
        }
        sums=new int[rowlen*collen+1];//bug!!!!!!, forget +1
        System.out.println("row:"+rowlen+","+"col:"+collen);
        //init the sums
        for(int i=0; i<rowlen; i++){
        	for(int j=0; j<collen; j++){
//        		System.out.println("----"+i+":"+j);
        		sums[i*collen+j+1]=sums[i*collen+j]+matrix[i][j]; //+1 for avoid the start element sums[0]
        	}
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
//    	int[][] regions=new int[rowRegionSize][];
    	if(row1>=rowlen || row2>=rowlen || col1>=collen || col2>=collen)
    		return 0;
    	int rowRegionSize=row2-row1+1;
    	int sum=0;
    	int left,right;
    	for(int i=0; i< rowRegionSize; i++){
    		left=(row1+i)*collen+col1; //left
    		right=(row1+i)*collen+col2+1; //right
    		
    		//计算sum
    		sum+=sums[right]-sums[left];
    	}
    	
        return sum;
    }
}
class NumMatrix2 {
	private int[][] sums;
	private int rowlen;
	private int collen;
    public NumMatrix2(int[][] matrix) {
        if(null==matrix || 0==matrix.length){
        	rowlen=collen=0;
        }else{
	        rowlen=matrix.length;
	        collen=matrix[0].length;
        }
        sums=new int[rowlen+1][collen+1];
        System.out.println("row:"+rowlen+","+"col:"+collen);
        //init the sums
        for(int i=0; i<rowlen; i++){
        	int lineSum=0;
        	for(int j=0; j<collen; j++){
        		lineSum+=matrix[i][j];
        		//坑点：需要lineSum， 是sums[i][j+1]而不是sums[i][j]
        		sums[i+1][j+1]=lineSum+sums[i][j+1]; //+1 for avoid the start element sums[0]
        	}
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
//    	int[][] regions=new int[rowRegionSize][];
    	if(row1>=rowlen || row2>=rowlen || col1>=collen || col2>=collen)
    		return 0;
    	int sum=0;
    	sum=sums[row2+1][col2+1]-sums[row1][col2+1]-sums[row2+1][col1]+sums[row1][col1];
    	
        return sum;
    }
}