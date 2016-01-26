import java.util.concurrent.Callable;

/**
 * @author      Eric Crosson <eric.s.crosson@utexas.edu>
 * @author      William "Stormy" Mauldin <stormymauldin@utexas.edu>
 * @version     0.1
 * @since       2016-01-26
 */

public class PSearch implements Callable {

    /**
     * Search array A for int x with numThreads threads.
     *
     * @param  x The search element
     * @param  A The search space
     * @param  numThreads The number of searcher threads to spawn
     * @return The index of x in array A
     */
    public static int parallelSearch(int x, int[] A, int numThreads) {
        return 0;		//Needs to return -1 or index of x
		
    }
	
    @Override
    public Object call() throws Exception {
        // TODO Auto-generated method stub
        return null;
    }
}
