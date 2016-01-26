package pset.one;

/**
 * @author      Eric Crosson <eric.s.crosson@utexas.edu>
 * @author      William "Stormy" Mauldin <stormymauldin@utexas.edu>
 * @version     0.1
 * @since       2016-01-26
 */

import java.util.Arrays;

public class PSort implements Runnable {

    /**
     * Sort array A for int x with numThreads threads.
     *
     * @param  A The array to sort, from index begin to index end.
     * @param  begin The index to not sort before
     * @param  end The index to not sort after
     */
    public static void parallelSort(int[] A, int begin, int end) {
        int pivot = begin;
        Arrays.sort(A);
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
    }
}
