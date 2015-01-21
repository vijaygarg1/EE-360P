class Attempt2 implements Lock {
    boolean wantCS[] = {false, false};
    public void requestCS(int i) { // entry protocol @\label{want}@
        wantCS[i] = true;   //declare intent @\label{busy}@
        while (wantCS[1 - i]) ; // busy wait
    }
    public void releaseCS(int i) {
        wantCS[i] = false;
    }
}
