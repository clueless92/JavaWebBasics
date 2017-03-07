package bg.tilchev.filters;

import bg.tilchev.models.view.UserCurrent;
import bg.tilchev.utils.Listen;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(Listen.CART + "/*")
public class CartFilter implements Filter {

    @Override
    public void destroy() {

    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = request.getSession();
        UserCurrent user = (UserCurrent) session.getAttribute("user");
        if(null == user){
            response.sendRedirect(Listen.LOGIN);
            return;
        }
        chain.doFilter(req, resp);
    }

    @Override
    public void init(FilterConfig config) throws ServletException {

    }
}
