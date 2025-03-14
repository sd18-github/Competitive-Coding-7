import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/*
 * TC: O (n log n)
 * SC: O (n)
 */
public class MinMeetingRooms {
    public int minMeetingRooms(int[][] intervals) {
        int l = intervals.length;
        if (l == 0 || l == 1) return l;
        // sort by start time
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        minHeap.offer(intervals[0][1]); // ending time of first meeting (after sorting)
        int i;
        int count = 1; // at least one room is required if intervals is not empty
        for(i = 1; i < l; i++) {
            // if the next meeting starts after the
            // earliest meeting gets over, we can reuse the same room
            if (intervals[i][0] >= minHeap.peek()) {
                minHeap.poll();
            } else {
                // else we need an additional room
                count++;
            }
            // put in the ending time of this meeting into the minHeap
            minHeap.offer(intervals[i][1]);
        }
        return count;
    }
}