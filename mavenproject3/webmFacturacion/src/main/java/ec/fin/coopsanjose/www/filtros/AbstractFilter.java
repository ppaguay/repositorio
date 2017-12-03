/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.fin.coopsanjose.www.filtros;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
 
public class AbstractFilter {
 
    public AbstractFilter() {
        super();
    }
 
    protected void doLogin(ServletRequest request, ServletResponse response, HttpServletRequest req) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher("/faces/index.xhtml?faces-redirect=true");
        rd.forward(request, response);
    }
 
    protected void accessDenied(ServletRequest request, ServletResponse response, HttpServletRequest req) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher("/faces/accesodenegado.xhtml?faces-redirect=true");
        rd.forward(request, response);
    }
   
}
