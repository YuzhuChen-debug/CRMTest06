package com.huanaco.crm.filter;

import javax.servlet.*;
import java.io.IOException;

public class EcondingFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chian) throws IOException, ServletException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        chian.doFilter(req,resp);
    }
}
