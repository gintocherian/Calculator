Calculator Program
=====================
Implementation of Calculator program using Eclipse, Java 1.7, Maven, Junit 3, log4j, git and Jenkins

Main Program: Calculator.java
Run from command line(note jar file will have to be compiled): java -jar Calculator.jar "add(2, 2)"
Note I built it in eclipse: So in eclipse the file Calculator.java can be run with the parameter "add(2, 2)"


Logic: A recursive expression evaluator. If traverses to the smallest sub-expression that can be evaluated and then uses that value to solve the parent expression till it solves the full expression.
eg:  1) add(1    ,    2)
            1    +    3
                 3
     2) add(1,      mult(2   ,    3))
                         2   *    3
            1     +          6
                  7
     3)let(a,               5,            let(b, mult(a, 10), add(b, a)))            
           a				5			  let(b, mult(5, 10), add(b, 5))            -- Replaced a
           								  let(b, 50, add(b, 5))						-- calculated multiply
           								  let(b, 50, add(50, 5))					-- replaced b
           											55								-- calculated add	

Experience Report
=====================

This is really an interesting problem, it reads simpler that it is,  

Issues I encountered,
	1) I initially spend a little time to see if I could use an open source Java parser to solve the parsing but it was evident that I could probably take more time in evaluating options than for the actual coding. 	
	2) In whiteboarding the solution the let operator added a level of complexity. 
	3) It had been some time since I used log4j, and I had a couple of issues with messages getting repeated. 
	4) Cloudbees Jenkins server seems to use a JDK version below 1.7 even though I specified it in their configuration. So has to change my statement for "switch(string)" to an 	if....else if...else construct. I had other issues as well regarding granting public access. This was my first time using Jenkins, I spend more time on it that I bargained for.
	5) The last sample in the problem statement was missing a brace.

Things that I could have improved,
	1) Too many strings are passed back and forth in my solution. This can be reduced by creating an expression tree(with two types of nodes i.e one for let and another for the other operators) and evaluating expressions on it starting from the leaves	
	2) This recursive solution, in cases of very large expressions, could possibly cause the call stack to exceed memory limit. A non-recursive solution will be more scalable
 	3) A configurable way to add new operations and delimiters. Currently this is hard coded.
 	4) Currently I am checking syntax correctness(there could be more cases to cover) just before evaluation of a sub-expression. A method like given in point 1 could do the syntax checking as	the tree is being built. 
 	5) Better code styling and commenting by using a specific standard, currently I've commented what I thought is necessary

How I spend my time(6 hrs),
	1) Core logic - 3-4 hrs
	2) Setup mainly time on the Jenkins server - 2 hrs  	