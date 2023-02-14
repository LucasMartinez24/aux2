package ar.com.edu.unju.edm;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class Autentication implements AuthenticationSuccessHandler{
  private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,Authentication authentication) throws IOException, ServletException {
    Boolean estudiante=false,docente=false;
    Collection<?extends GrantedAuthority> authorities = authentication.getAuthorities();
    for(GrantedAuthority grantedAuthority:authorities){
      if(grantedAuthority.getAuthority().equals("Estudiante")){
        estudiante=true;
        break;
      }else{
        if(grantedAuthority.getAuthority().equals("Docente")){
          docente=true;
          break;
        }
      }
    }
    if(estudiante){
      redirectStrategy.sendRedirect(request, response, "/elegirNivel");
    }else{
      if(docente){
        redirectStrategy.sendRedirect(request, response, "/listarusuarios");
      }
    }
  }
  
}