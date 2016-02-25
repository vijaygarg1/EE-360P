Run the test using class files in the command line:
    java PriorityQueueTester

Run the test using the jar file in Eclipse:
    1. Copy the executable Jar to Eclipse and put it into the same project as your java files. You java files cannot be encapsulated in any package.
    2. Right click on the Jar file in Eclipse and select "Build Path" -> "Add to Build Path"
    3. Right click on the Jar file in Eclipse and select "Run As" -> "Java Application"

Sample output:
    Insert 2 w/ priority 3 SUCEED!
    Insert 4 w/ priority 4 SUCEED!
    Insert 8 w/ priority 3 SUCEED!
    Insert 6 w/ priority 1 SUCEED!
    2 is located at 1
    Pop first: 4
    Insert 6 w/ priority 3 FAILED!
    Insert 8 w/ priority 8 FAILED!
    8 is located at 1
    4 is located at -1
    Insert 6 w/ priority 3 FAILED!
    Insert 3 w/ priority 2 SUCEED!
    Insert 2 w/ priority 9 FAILED!
    Insert 6 w/ priority 6 FAILED!
    6 is located at 3
    Pop first: 2
    Pop first: 8
    3 is located at 0
    Pop first: 3
    Pop first: 6
