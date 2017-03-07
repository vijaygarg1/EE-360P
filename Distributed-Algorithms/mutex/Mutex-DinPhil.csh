#/bin/csh
xterm -sb -ls -T P0 -e java nLockTester mutexDin 0 5 DiningPhil &
xterm -sb -ls -T P1 -e java nLockTester mutexDin 1 5 DiningPhil &
xterm -sb -ls -T P2 -e java nLockTester mutexDin 2 5 DiningPhil &
xterm -sb -ls -T P3 -e java nLockTester mutexDin 3 5 DiningPhil &
xterm -sb -ls -T P4 -e java nLockTester mutexDin 4 5 DiningPhil &
