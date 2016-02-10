Run the test using class files in the command line:
    java CyclicBarrierTest

Run the test using the jar file in Eclipse:
    1. Copy the executable Jar to Eclipse and put it into the same project as your java files. You java files cannot be encapsulated in any package.
    2. Right click on the Jar file in Eclipse and select "Build Path" -> "Add to Build Path"
    3. Right click on the Jar file in Eclipse and select "Run As" -> "Java Application"

Sample output:
    Thread 12 is WAITING round:0
    Thread 11 is WAITING round:0
    Thread 10 is WAITING round:0
    Thread 10 got index:0
    Thread 10 is WAITING round:1
    Thread 12 got index:2
    Thread 11 got index:1
    Thread 12 is WAITING round:1
    Thread 11 is WAITING round:1
    Thread 11 got index:0
    Thread 10 got index:2
    Thread 12 got index:1
