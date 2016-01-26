import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import pset1.PSort;

/**
 * @author      Eric Crosson <eric.s.crosson@utexas.edu>
 * @author      William "Stormy" Mauldin <stormymauldin@utexas.edu>
 * @version     0.1
 * @since       2016-01-26
 */

public class PSortTest {

    @Test
    public void testParallelSort() {
        int[] A = { 1, 3, 2 };
        int[] sorted = { 1, 2, 3 };
        parallelSort(A, 0, A.length);
        assertEquals(sorted, A);
    }
}
