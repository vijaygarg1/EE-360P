public interface Lock {
    public void requestCS(int pid); //may block
    public void releaseCS(int pid);
}
