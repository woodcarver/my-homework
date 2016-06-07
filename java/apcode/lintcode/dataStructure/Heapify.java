package lintcode.dataStructure;

public class Heapify {
    public void heapify(int[] A) {
        // Why left child is A[i*2+1] and right child is A[i*2+2] of
        // A[i] in priority queue? How to prove it?
        // priority queue is complete tree, think about the
        // mathematical about complete tree.
        // 这不是就是实现以下heap的插入吗？
        if (A == null || A.length == 0) {
            return;
        }
        int len = A.length;
        // using in place strategy
        for (int i = 1; i < len; i++) {
            siftUp(A, i);
        }
    }
    private void siftUp(int[] A, int pos) {
        int parentPos = (pos - 1) / 2;
        // ba carefull it is pos > 0 not parentPos>0 or it may be endless
        while (pos > 0 && A[pos] < A[parentPos]) {
            int temp = A[pos];
            A[pos] = A[parentPos];
            A[parentPos] = temp;
            
            pos = parentPos;
            parentPos = (pos - 1) / 2;
        }
    }
}
