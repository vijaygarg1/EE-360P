#/bin/csh
xterm -sb -ls -T P0 -e java nLockTester mutexTok 0 5 CircToken &
xterm -sb -ls -T P1 -e java nLockTester mutexTok 1 5 CircToken &
xterm -sb -ls -T P2 -e java nLockTester mutexTok 2 5 CircToken &
xterm -sb -ls -T P3 -e java nLockTester mutexTok 3 5 CircToken &
xterm -sb -ls -T P4 -e java nLockTester mutexTok 4 5 CircToken &
