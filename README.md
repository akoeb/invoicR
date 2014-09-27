= invoicR =

software tool to help track my time spent on projects and create invoices

= ATTENTION =

This thing is not ready for use yet!!

= description = 

This software is supposed to help me track my time spent with different projects of different customers and automate the creation of the invoices.

It is based on spring MVC, mainly because I wanted to try out spring. To make it easier usable, it comes with jetty embedded and packaged as jar, so you can simply run it without big installation steps.

= creation of the package =

1. get the source
2. navigate to the root directory where pom.xml lives
3. run mvn package (if you have maven installed, if not, get it)
4. you will find the generated jar file in the target/ directory

= run the package =

java -jar <package>.jar
