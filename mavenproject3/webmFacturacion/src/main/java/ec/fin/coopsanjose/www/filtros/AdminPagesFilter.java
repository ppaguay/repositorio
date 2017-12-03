/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.fin.coopsanjose.www.filtros;

import ec.fin.coopsanjose.www.rnegocio.entidades.Usuario;
import java.io.IOException;
 
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
 
 
public class AdminPagesFilter extends AbstractFilter implements Filter {
 
    @Override
    public void destroy() {
 
    }
 
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        Usuario user = (Usuario) req.getSession(true).getAttribute("usuario");
 
        if (!user.isEstado()) {
            accessDenied(request, response, req);
            return;
        }
 
        chain.doFilter(request, response);
    }
 
    @Override
    public void init(FilterConfig arg0) throws ServletException {
 
    }
}
