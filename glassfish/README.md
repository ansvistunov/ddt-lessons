# ONLY FOR JAVA 8 VERSION

+ set JAVA_HOME=c:\Java\jdk1.8.0_281. check mvn --version
+ cd Billing
+ mvn install
+ copy war to appserver autodeploy
+ check application deployment
+ cd client
+ mvn install
+ start client by c:\Java\jdk1.8.0_281\java -classpath target/classes  soap.client.BillingClient