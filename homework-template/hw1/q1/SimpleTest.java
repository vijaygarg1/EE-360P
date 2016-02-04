import java.util.Arrays;

public class SimpleTest {
  public static void main (String[] args) {
    int[] A1 = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
    verifyParallelSort(A1);
    
    int[] A2 = {1, 3, 5, 7, 9};
    verifyParallelSort(A2);
    
    int[] A3 = {13, 59, 24, 18, 33, 20, 11, 11, 13, 50, 10999, 97};
    verifyParallelSort(A3);
  }

  static void verifyParallelSort(int[] A) {
    int[] B = new int[A.length];
    System.arraycopy(A, 0, B, 0, A.length);

    System.out.println("Verify Parallel Sort for array: ");
    printArray(A);

    Arrays.sort(A);
    PSort.parallelSort(B, 0, B.length);
   
    boolean isSuccess = true;
    for (int i = 0; i < A.length; i++) {
      if (A[i] != B[i]) {
        System.out.println("Your parallel sorting algorithm is not correct");
        System.out.println("Expect:");
        printArray(A);
        System.out.println("Your results:");
        printArray(B);
        isSuccess = false;
        break;
      }
    }

    if (isSuccess) {
      System.out.println("Great, your sorting algorithm works for this test case");
    }
    System.out.println("=========================================================");
  }

  public static void printArray(int[] A) {
    for (int i = 0; i < A.length; i++) {
      if (i != A.length - 1) {
        System.out.print(A[i] + " ");
      } else {
        System.out.print(A[i]);
      }
    }
    System.out.println();
  }
}
