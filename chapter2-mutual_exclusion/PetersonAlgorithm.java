class PetersonAlgorithm implements Lock {
    boolean wantCS[] = {false, false};
    int turn = 1;
    public void requestCS(int i) {
        int j = 1 - i; 
        wantCS[i] = true; 
        turn = j;
        while (wantCS[j] && (turn == j)) ;
    }
    public void releaseCS(int i) {
        wantCS[i] = false;
    }
}
