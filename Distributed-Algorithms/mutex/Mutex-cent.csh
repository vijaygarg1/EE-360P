#/bin/csh
xterm -sb -ls -T P0 -e java CentMutexTester mutexCent 0 5 &
xterm -sb -ls -T P1 -e java CentMutexTester mutexCent 1 5 &
xterm -sb -ls -T P2 -e java CentMutexTester mutexCent 2 5 &
xterm -sb -ls -T P3 -e java CentMutexTester mutexCent 3 5 &
xterm -sb -ls -T P4 -e java CentMutexTester mutexCent 4 5 &
