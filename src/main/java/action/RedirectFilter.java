package action;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RedirectFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {
        // Méthode initiale du filtre (peut être laissée vide)
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        HttpSession session = httpRequest.getSession(false);
        String requestedURI = httpRequest.getRequestURI();
        String contextPath = httpRequest.getContextPath();
        String homePage = contextPath + "/Index.jsp"; // Spécifiez ici l'URL de votre page d'accueil

        boolean isHomePageRequested = requestedURI.equals(homePage);
        boolean isLoggedIn = session != null && session.getAttribute("code") != null; // Vérifiez ici si l'utilisateur est déjà connecté

        if (!isHomePageRequested && !isLoggedIn) {
            httpResponse.sendRedirect(homePage);
            return;
        }

        chain.doFilter(request, response);
    }

    public void destroy() {
        // Méthode de destruction du filtre (peut être laissée vide)
    }
}
