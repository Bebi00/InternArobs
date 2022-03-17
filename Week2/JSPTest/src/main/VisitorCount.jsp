<%@ page import="java.util.concurrent.atomic.AtomicInteger" %>
<html>
    <head>
        <h1><title>Page</title></h1>
    </head>

    <body>

        <% Integer visitCounter = (Integer) application.getAttribute("visitCounter");
        if(visitCounter == null || visitCounter == 0){
                visitCounter =1;
        }
        else {
            visitCounter++;
        }
        application.setAttribute("visitCounter",visitCounter);
        %>
        <center>
            You are the <%= visitCounter%> visitor
        </center>


    </body>
</html>