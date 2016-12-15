Link to project description [csc481hw1writeup.pdf - github.com](https://github.com/cblupodev/csc481hw1/blob/master/csc481hw1writeup.pdf)

The subdirectories are in "src\csc481hw1"

Import the csc481hw1 folder in Eclipse

# Section 1
- Run the Section1.java file as a java application. A build should happen automatically
- Then the processing window should popup and you can interact with the left and right arrows and the space bar

# Section 2
- For each ForkExampleMod*.java file run as a java application
- Output is printed to the console

# Section 3
- Copy Client.java to another folder outside the eclipse workspace. You run into classpath problems if you run the java command inside the workspace folder
- Remove the line containing package information from Client.java, the first line. Running this in the terminal should do the trick:
    tail -n +2 Client.java
- Get three terminals open so you can run javac and java commands. I used Git Bash
- Run the Server.java file as a java application in eclipse
- Compile and run Client.java
    javac Client.java && java Client 127.0.0.1
- Output should be printed to the eclipse console

# Section 4
- Follow the exact same instructions as Section 3, but run however many terminals you want instead of just three
- Output should be printed to the eclipse console and each terminal window
