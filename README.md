# eBay_Coding_Challenge
REST Web Service API that identifies whether or not an item is eligible for the new shipping program.

Pre-requisities:
1) System should have installed Java 8 and Maven 3.6.3

Building and Running the code:
1)	If you are using Eclipse IDE, Install Spring Tool Suite 4 from the Help-> Eclipse Marketplace.
2)	Download code from GitHub and unzip it.
3)	First import the <B>eBay_Coding_Challenge-master</B> unzipped file to the eclipse workspace.
4)	Open terminal, change directory to <B>eBay_Coding_Challenge-master</B> directory where you have placed your unzipped folder.
5)  Give command <B>mvnw clean install</B> to build the project.
5)	After successful build, it will create a jar (<B>ebay-shopping-1.0-SNAPSHOT.jar</B>) file into the <B>target</B> folder.
6)	Run this jar file through command: <B>java -jar target\ebay-shopping-1.0-SNAPSHOT.jar</B>
6)  Server will be started and application is deployed on 8083 port. For example- Endpoint: http://localhost:8083/shipping/seller
7)	SOAPUI/Postman or curl commands can be used to see all the working functionalities.
