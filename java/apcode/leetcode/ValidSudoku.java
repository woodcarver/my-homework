package leetcode;
/**
 * No. 36
 * @author xiedandan
 *
 */
public class ValidSudoku {
    public boolean isValidSudoku(char[][] board) {
        int[][] rowValid=new int[9][9];
        int[][] colValid=new int[9][9];
        int[][] subValid=new int[9][9];
        for(int i=0; i<9; i++){
        	for(int j=0; j<9; j++){
        		int cell=board[i][j]-'1';
        		if(cell==('.'-'1')){
        			continue;
        		}
        		System.out.printf("cell--cell:%d [%d,%d,%d] ,row:%d,col:%d,sub:%d\n",
        				cell,i,j,(i/3)*3+j/3,
        				rowValid[i][cell],colValid[j][cell],subValid[(i/3)*3+j/3][cell]);
        		if(rowValid[i][cell]>0 || colValid[j][cell]>0 || subValid[(i/3)*3+j/3][cell]>0)
        			return false;
        		
        		rowValid[i][cell]++;
        		colValid[j][cell]++;
        		subValid[(i/3)*3+j/3][cell]++;
        	}
        }
        return true;
    }
    public char[][] toCharArray(String[] board){
    	char[][] temp=new char[9][9];
    	for(int i=0; i<9; i++){
    		System.out.println(board[i]);
    		for(int j=0; j<9; j++){
    			temp[i][j]=board[i].toCharArray()[j];
    		}
    	}
    	return temp;
    }
    public static void main(String[] args){
    	ValidSudoku sudoku=new ValidSudoku();
    	String[] board={".87654321","2........","3........","4........","5........","6........","7........","8........","9........"};
    	char[][] board2=sudoku.toCharArray(board);
    	System.out.println(sudoku.isValidSudoku(board2));
    }
}
