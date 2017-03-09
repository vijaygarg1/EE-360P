This directory contains distributed algorithms.
name:		NameServer
dist:		Basic Routines for sending and receiving messages
clocks:		Clock Algorithms
mutex:		Mutex Algorithms
snapshot:	Snapshot Algorithms


All algorithms depend upon the NameServer to be running.
This is the first step in running any algorithm.
You can run it as 
  java name/NameServer


The address of NameServer should be in name/Symbols.java.
By default it is assumed to be running on localhost:7033.


All algorithms extend the class dist.Process.
Any such algorithm can then send messages using sendMsg(destId, object..).
It should also override a mathod called handleMsg which is called whenever a message
is received.



Let us say you want to run Lamport's mutex algorithms on 3 processes.
   
   Start three processes as follows:

  java mutex/LamportMutex p 0 3  // machine 0
  java mutex/LamportMutex p 1 3  // machine 1
  java mutex/LamportMutex p 2 3  // machine 2

  The meaning of the arguments (p 0 3) is as follows:
  p : the name of the process group. 
  0 : process id
  3: total number of processes in the group

  Thus, the above commands starts three processes p0, p1 and p2 on different machines. They find each other
  using the NameServer.

