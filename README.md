# GameCollector
Our web application is built on the SSM framework which means Spring, Spring MVC, and MyBatis.
The environment for Java Web is IntelliJ IDEA and MySQL Workbench.
We crawled game data from Twitch.tv in the form of excel. In order to make it easy to search and maintain,
we restore these data in MySQL database by pymysql library in Python. Then we can operate game data by MyBatis
and implement interactions between front-end and back-end. This part is separated from the SSM framework.
If we want to update the game data with new dates, the only thing we need to do is using python to CRUD database.
And it will not interrupt the running of our web application. 

For semantic analysis part, we still use python application to train classifiers and get analysis results of some specific
inputs because python is more efficient and capable for data mining. As a result, we integrate Java with Python in the 
context of SSM framework using Java Runtime and Process class. These two class can make it possible to invoke Python application 
in runtime which is much faster than Jython. The result of semantic analysis will be sent back by python application and transfer
to Java application by classes in java.io package. We can read these results and encapsulate as response data to show in the front end.

There are 3 primary functionalities in our application. 
In tab1, by selecting a data interval and a time interval, we can see the top 6 most popular games with the number of their viewers 
during this time range.
In tab2, we can see the trend of one or more games during our crawling process.
In tab3, we are able to input a review and our application will send the review words to the NLP module to get a score for this review.
