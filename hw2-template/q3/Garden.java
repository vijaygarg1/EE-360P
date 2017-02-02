public class Garden {
    
  public Garden() {   }; 
  public synchronized void startDigging() {   }; 
  public synchronized void doneDigging() {   }; 
  public synchronized void startSeeding() {   };
  public synchronized void doneSeeding() {   }; 
  public synchronized void startFilling() {   }; 
  public synchronized void doneFilling() {   }; 
 
    /*
    * The following methods return the total number of holes dug, seeded or 
    * filled by Newton, Benjamin or Mary at the time the methods' are 
    * invoked on the garden class. */
   public synchronized int totalHolesDugByNewton() {   }; 
   public synchronized int totalHolesSeededByBenjamin() {   }; 
   public synchronized int totalHolesFilledByMary() {   }; 
}
