import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;


@WebFilter(urlPatterns = "/login")
public class VisitorCountFilter implements Filter {
    AtomicInteger visitCounter = new AtomicInteger(0);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        visitCounter.incrementAndGet();
        servletRequest.setAttribute("visitCounter", visitCounter);
        filterChain.doFilter(servletRequest, servletResponse);
//        RequestDispatcher requestDispatcher =servletRequest.getRequestDispatcher("login");
//        requestDispatcher.forward(servletRequest, servletResponse);

    }


    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
