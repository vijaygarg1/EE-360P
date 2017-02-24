package clocks;
public class MatrixClock {
    int[][] M;
    int myId;
    int N;
    public MatrixClock(int numProc, int id) {
        myId = id;
        N = numProc;
        M = new int[N][N];
        for (int i = 0; i < N; i++) 
           for (int j = 0; j < N; j++) 
		M[i][j] = 0;
        M[myId][myId] = 1;
    }
    public void tick() {
        M[myId][myId]++;
    }
    public void sendAction() {
        //include the matrix in the message
        M[myId][myId]++;
    }
    public void receiveAction(int[][] W, int srcId) {
	// component-wise maximum of matrices
        for (int i = 0; i < N; i++)
	   if (i != myId) {
           	for (int j = 0; j < N; j++) 
              	     M[i][j] = Math.max(M[i][j], W[i][j]);
	   }

	// update the vector for this process
        for (int j = 0; j < N; j++) 
           M[myId][j] = Math.max(M[myId][j], W[srcId][j]);

        M[myId][myId]++;
    }
    public int getValue(int i, int j) {
        return M[i][j];
    }
}
