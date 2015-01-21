class HWMutex implements Lock {
    TestAndSet lockFlag;
    public void requestCS(int i) { // entry protocol
        while (lockFlag.testAndSet(1) == 1) ;
    }
    public void releaseCS(int i) { // exit protocol
        lockFlag.testAndSet(0);
    }
}
