package it.prova.pizzastore.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.pizzastore.model.Utente;

@WebFilter(filterName = "CheckAuthFilter", urlPatterns = { "/*" })
public class CheckAuthFilter implements Filter {

	private static final String HOME_PATH = "";
	private static final String[] EXCLUDED_URLS = {"/login.jsp","/LoginServlet","/LogoutServlet","/assets/"};
	private static final String[] ADMIN_URLS = {"/admin/", };
	private static final String[] PIZZAIOLO_URLS = {"/pizzaiolo/", };
	private static final String[] FATTORINO_URLS = {"/fattorino/", };

	public CheckAuthFilter() {
	}

	public void init(FilterConfig filterConfig) throws ServletException {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("Nel filtro di check user in session");

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		//prendo il path della request che sta passando in questo momento es. /LoginServlet
		String pathAttuale = httpRequest.getServletPath();
		System.out.println("Invocazione di: " + pathAttuale);
		
		//vediamo se il path risulta tra quelli 'liberi di passare'
		boolean isInWhiteList = isPathInWhiteList(pathAttuale);
		
		//se non lo e' bisogna controllare sia sessione che percorsi protetti
		if (!isInWhiteList) {
			Utente utenteInSession = (Utente)httpRequest.getSession().getAttribute("userInfo");
			
			
			//intanto verifico se utente in sessione
			if (utenteInSession == null) {
				httpResponse.sendRedirect("/pizzastore/login.jsp");
				return;
			}
			System.out.println(utenteInSession.isFattorino());
			//controllo che utente abbia ruolo admin se nel path risulta presente /admin/
			if(isPathForOnlyAdministrators(pathAttuale) && utenteInSession.isAdmin()) {
				httpRequest.getRequestDispatcher(httpRequest.getContextPath()).forward(httpRequest, httpResponse);
				return;
			}
			if(isPathForFattorini(pathAttuale) && utenteInSession.isFattorino()) {
				httpRequest.getRequestDispatcher(httpRequest.getContextPath()).forward(httpRequest, httpResponse);
				return;
			}
			if(isPathForPizzaioli(pathAttuale) && utenteInSession.isPizzaiolo()) {
				System.out.println(httpRequest.getContextPath());
				httpRequest.getRequestDispatcher(httpRequest.getContextPath()).forward(httpRequest, httpResponse);
				return;
			}
		}
	
		chain.doFilter(request, response);
	}
	
	private boolean isPathInWhiteList(String requestPath) {
		//bisogna controllare che se il path risulta proprio "" oppure se 
		//siamo in presenza un url 'libero'
		if(requestPath.equals(HOME_PATH))
			return true;
		
		for (String urlPatternItem : EXCLUDED_URLS) {
			if (requestPath.contains(urlPatternItem)) {
				return true;
			}
		}
		return false;
	}
	
	private boolean isPathForOnlyAdministrators(String requestPath) {
		for (String urlPatternItem : ADMIN_URLS) {
			if (requestPath.contains(urlPatternItem)) {
				return true;
			}
		}
		return false;
	}
	private boolean isPathForPizzaioli(String requestPath) {
		for (String urlPatternItem : PIZZAIOLO_URLS) {
			if (requestPath.contains(urlPatternItem)) {
				return true;
			}
		}
		return false;
	}
	private boolean isPathForFattorini(String requestPath) {
		for (String urlPatternItem : FATTORINO_URLS) {
			if (requestPath.contains(urlPatternItem)) {
				return true;
			}
		}
		return false;
	}

	public void destroy() {
	}

}
