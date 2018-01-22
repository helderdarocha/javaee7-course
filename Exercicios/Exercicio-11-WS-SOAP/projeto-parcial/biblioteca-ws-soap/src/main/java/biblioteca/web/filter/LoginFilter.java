/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.web.filter;

import biblioteca.web.FakeLoginBean;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author helderdarocha
 */
@WebFilter(filterName = "LoginFilter", urlPatterns = {"/*"})
public class LoginFilter implements Filter {
    
    @Inject
    FakeLoginBean loginBean;

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        boolean loggedIn = loginBean.getUsuarioLogado() != null;
        String loginURL = request.getContextPath() + "/fake-login.xhtml";

        if (!loggedIn && !loginOrService(request, loginURL)) {
            response.sendRedirect(loginURL);
        } else {
            chain.doFilter(request, response);
        }
    }
    
    private boolean loginOrService(HttpServletRequest request, String loginURL) {
        if(request.getRequestURI().endsWith("Service") || request.getRequestURI().equals(loginURL)) {
            return true;
        }
        return false;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }

}
