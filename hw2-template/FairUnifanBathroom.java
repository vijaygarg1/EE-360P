// EID 1
// EID 2

public class FairUnifanBathroom {   
	
  public synchronized void enterBathroomUT() {
    // Called when a UT fan wants to enter bathroom	
  }
	
	public synchronized void enterBathroomOU() {
    // Called when a OU fan wants to enter bathroom
	}
	
	public synchronized void leaveBathroomUT() {
    // Called when a UT fan wants to leave bathroom
	}

	public synchronized void leaveBathroomOU() {
    // Called when a OU fan wants to leave bathroom
	}
}
	
