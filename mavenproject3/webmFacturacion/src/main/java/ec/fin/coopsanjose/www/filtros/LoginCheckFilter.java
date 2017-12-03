/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.fin.coopsanjose.www.filtros;

import ec.fin.coopsanjose.www.rnegocio.entidades.Usuario;
import java.io.IOException;
import java.util.*;
 
import javax.servlet.*;
import javax.servlet.http.*;
 
 

public class LoginCheckFilter extends AbstractFilter implements Filter {
    private static List<String> allowedURIs;
 
    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {
        if(allowedURIs == null){
            allowedURIs = new ArrayList<String>();
            allowedURIs.add(fConfig.getInitParameter("loginActionURI"));
            allowedURIs.add("/webmFacturacion/faces/index.xhtml");
            allowedURIs.add("/webmFacturacion/faces/javax.faces.resource/jquery/jquery.js");
            allowedURIs.add("/webmFacturacion/faces/javax.faces.resource/main.css.");
            allowedURIs.add("/webmFacturacion/faces/javax.faces.resource/theme.css.");
            allowedURIs.add("/webmFacturacion/faces/javax.faces.resource/primefaces.js.");
            allowedURIs.add("/webmFacturacion/faces/javax.faces.resource/primefaces.css.");
            allowedURIs.add("/webmFacturacion/faces/javax.faces.resource/jquery/jquery.js.");
            allowedURIs.add("/webmFacturacion/faces/javax.faces.resource/messages/messages.png.");
            allowedURIs.add("/webmFacturacion/faces/javax.faces.resource/images/ui-icons_2e83ff_256x240.png.");
            allowedURIs.add("/webmFacturacion/faces/javax.faces.resource/images/ui-icons_38667f_256x240.png.");
            allowedURIs.add("/webmFacturacion/faces/javax.faces.resource/spacer/dot_clear.gif");
            
            allowedURIs.add("/webmFacturacion/faces/javax.faces.resource/spacer/dot_clear.gif");
            allowedURIs.add("/webmFacturacion/faces/javax.faces.resource/primefaces.js");
            allowedURIs.add("/webmFacturacion/faces/javax.faces.resource/jquery/jquery-plugins.js");
            allowedURIs.add("/webmFacturacion/faces/javax.faces.resource/primefaces.css");
            allowedURIs.add("/webmFacturacion/faces/javax.faces.resource/theme.css");
     
        }
    }
 
    /**
     * @see Filter#destroy()
     */
    public void destroy() {
    }
 
    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
 
        if (session.isNew()) {
            doLogin(request, response, req);
            return;
        }
 
        Usuario user = (Usuario) session.getAttribute("usuario");
        
        if (user == null && !allowedURIs.contains(req.getRequestURI())) {
            System.out.println(req.getRequestURI());
            doLogin(request, response, req);
            return;
        }
 
        chain.doFilter(request, response);
    }
}