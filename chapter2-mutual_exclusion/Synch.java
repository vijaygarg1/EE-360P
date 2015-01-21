public class Synch{
    public static synchronized void swap(Boolean m1, Boolean m2){
        Boolean temp = m1; 
        m1 = m2; 
        m2 = temp;
     }
}

