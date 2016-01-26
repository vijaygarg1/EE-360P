import java.util.Arrays;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import pset.one.PSort;

/**
 * @author      Eric Crosson <eric.s.crosson@utexas.edu>
 * @author      William "Stormy" Mauldin <stormymauldin@utexas.edu>
 * @version     0.1
 * @since       2016-01-26
 */

public class PSortTest {

    /**
     * Test parallelSort under a variety of Arrays.
     */
    @Test
    public void testParallelSort() {
        int[] A1 = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        verifyParallelSort(A1);

        int[] A2 = {1, 3, 5, 7, 9};
        verifyParallelSort(A2);

        int[] A3 = {13, 59, 24, 18, 33, 20, 11, 11, 13, 50, 10999, 97};
        verifyParallelSort(A3);
    }

    /**
     * Verify parallelSort's ability to sorty array A.
     *
     * @param  A The test array to verify
     */
    void verifyParallelSort(int[] A) {
        int[] B = new int[A.length];
        System.arraycopy(A, 0, B, 0, A.length);

        /* System.out.println("Verify Parallel Sort for array: "); */
        /* printArray(A); */

        Arrays.sort(A);
        PSort.parallelSort(B, 0, B.length);

        boolean isSuccess = true;
        for (int i = 0; i < A.length; i++) {
            if (A[i] != B[i]) {
                System.out.println(
                    "Your parallel sorting algorithm is incorrect");
                System.out.println("Expect:");
                printArray(A);
                System.out.println("Your results:");
                printArray(B);
                isSuccess = false;
                break;
            }
        }

        /* if (isSuccess) { */
        /*     System.out.println( */
        /*         "Great, your sorting algorithm works for this test case"); */
        /* } */
        System.out.println(
            "=========================================================");
    }

    /**
     * Print int array A to System.out.
     *
     * @param  A The int array to display
     */
    void printArray(int[] A) {
        for (int i = 0; i < A.length; i++) {
            System.out.print(A[i] + ((i != A.length - 1) ? " " : ""));
        }
        System.out.println();
    }
}
