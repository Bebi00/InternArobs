import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "login", urlPatterns = "/login")

public class Login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        out.println("""
                <!DOCTYPE html>
                <html lang="en">
                <head>
                    <meta charset="UTF-8">
                    <title>Beautiful Page</title>
                </head>
                <body>
                    <form method="post" resource="/login">
                       Username: <input type="text" name="Username">
                       <br> Password:  <input type="Password" name="Password">
                        <br> <button>Login</button>
                    </form>
                </body>
                </html>
                """);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        out.println("Hello "+req.getParameter("user"));
        out.println("You are the "+req.getAttribute("visitCounter")+" visitor.");

        out.println("The page was loaded in "+req.getAttribute("time"));

    }




}
