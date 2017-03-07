#/bin/csh
xterm -sb -ls -T P0 -e java nLockTester mutexRA 0 5 RAMutex &
xterm -sb -ls -T P1 -e java nLockTester mutexRA 1 5 RAMutex &
xterm -sb -ls -T P2 -e java nLockTester mutexRA 2 5 RAMutex &
xterm -sb -ls -T P3 -e java nLockTester mutexRA 3 5 RAMutex &
xterm -sb -ls -T P4 -e java nLockTester mutexRA 4 5 RAMutex &
