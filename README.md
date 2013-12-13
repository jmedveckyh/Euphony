![Euphony logo](https://raw.github.com/tomillie/Euphony/master/Euphony/Euphony-WEB/src/main/webapp/img/euphony-logo.png)

the Music Library.
<br><br>

In folder Euphony-WEB execute this command to run Web and REST server modules 
~~~~
mvn clean install tomcat7:run tomcat7:deploy
~~~~

URL of web module: <a href="http://localhost:8080/pa165">http://localhost:8080/pa165/</a><br>
URL of REST server: <a href="http://localhost:8080/pa165/server">http://localhost:8080/pa165/server/</a><br><br>

In folder Euphony-client execute this command to run REST client module
~~~~
mvn clean install tomcat7:run tomcat7:deploy
~~~~

URL of REST client module: <a href="http://localhost:8081/pa165/client">http://localhost:8081/pa165/client/</a><br><br>

Test data can be automatically added by executing <a href="https://github.com/tomillie/Euphony/blob/master/Euphony/Euphony-WEB/inserts.sql">this SQL script</a>.
