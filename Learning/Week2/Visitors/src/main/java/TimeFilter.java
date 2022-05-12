import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebFilter(urlPatterns = "/time")
public class TimeFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        long startTime = System.currentTimeMillis();
        long stopTime = System.currentTimeMillis();

        servletRequest.setAttribute("time",stopTime-startTime);
        RequestDispatcher rd = servletRequest.getRequestDispatcher("login");
        rd.forward(servletRequest, servletResponse);

    }
}
