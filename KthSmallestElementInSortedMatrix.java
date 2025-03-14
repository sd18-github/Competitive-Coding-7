/*
 * TC: O ((log n^2) * (n log n))
 * SC: O (1)
 */
public class KthSmallestElementInSortedMatrix {
    int n;
    public int kthSmallest(int[][] matrix, int k) {
        n = matrix.length;
        int l = matrix[0][0];
        int r = matrix[n-1][n-1];
        int res = 0;
        while(l <= r) {
            // find a candidate (that may or may not be in the matrix)
            int m = l + (r - l) / 2;
            // get the number of elements smaller than the candidate in the matrix
            int count = countOfSmallerElements(matrix, m);
            // if this count is == k we might have a solution, but keep looking in the left half
            // if this count is > k keep looking in the left half
            // if this count is < k keep looking in the right half
            if(count >= k) {
                res = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return res;
    }
    // Find the number of elements smaller than val in the matrix
    int countOfSmallerElements(int[][] matrix, int val) {
        n = matrix.length;
        int count = 0;
        // for each row in the matrix, find the number of elements smaller than val
        for(int i = 0; i < n; i++) {
            count += binarySearch(matrix[i], val);
        }
        return count;
    }
    // Find the number of elements smaller than val in the array
    // using binary search upper bound approach
    int binarySearch(int[] arr, int val) {
        int l = 0;
        int r = n - 1;
        int res = n;
        while(l <= r) {
            int m = l + (r - l) / 2;
            // may be an answer
            if(arr[m] > val) {
                res = m;
                r = m - 1; //there may be a smaller ans, look to the left
            } else {
                l = m + 1; //look to the right
            }
        }
        return res;
    }
}