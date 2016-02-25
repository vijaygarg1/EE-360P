Run the test using class files in the command line:
    java ReadWriteLockTest

Run the test using the jar file in Eclipse:
    1. Copy the executable Jar to Eclipse and put it into the same project as your java files. You java files cannot be encapsulated in any package.
    2. Right click on the Jar file in Eclipse and select "Build Path" -> "Add to Build Path"
    3. Right click on the Jar file in Eclipse and select "Run As" -> "Java Application"

Sample output:
    Test Start
    Reader reads 1
    Reader reads 8
    Writer writes 10
    Writer writes 5
    Writer writes 7
    Writer writes 0
    Writer writes 15
    Writer writes 6
    Reader reads 9
    Reader reads 13
    Reader reads 2
    Reader reads 12
    Reader reads 4
    Writer writes 11
    Writer writes 3
    Reader reads 14
    Test End
