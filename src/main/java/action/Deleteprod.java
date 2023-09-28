package action;
import Tables.Articles_Stock;
import Tables.Users;
import DAO.ProduitDaolmp;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import com.opensymphony.xwork2.ActionSupport;
public class Deleteprod extends ActionSupport implements ServletRequestAware{
	private int idp;
	private  HttpServletRequest request;


	public int getIdp() {
		return idp;
	}
	public void setIdp(int idp) {
		this.idp = idp;
	}



	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public Deleteprod(int idp, HttpServletRequest request) {
		super();
		this.idp = idp;
		this.request = request;
	}
	public Deleteprod() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public void setServletRequest(HttpServletRequest request) {
	    this.request = request;
	}

	public String supprimer() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		Boolean login = (Boolean) session.getAttribute("login");

		if (session == null || login == null || !login) {
			return "login";
		}

		List<Articles_Stock> list = (List<Articles_Stock>) session.getAttribute("list");
		int idp = Integer.parseInt(request.getParameter("idp"));

		// Suppression de l'élément de la liste
		if (list.size() == 1 && list.get(0).getCodeArt() == idp) {
			list.clear(); // Supprimer le seul élément directement
		} else {
			// Recherche de l'élément avec l'ID correspondant
			Articles_Stock itemToRemove = null;
			for (Articles_Stock item : list) {
				if (item.getCodeArt() == idp) {
					itemToRemove = item;
					break;
				}
			}

			// Suppression de l'élément de la liste
			if (itemToRemove != null) {
				list.remove(itemToRemove);
			}
		}

		return "delete";
	}




}
