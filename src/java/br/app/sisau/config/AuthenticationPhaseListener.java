package br.app.sisau.config;


import br.app.sisau.beans.PessoaBean;
import br.app.sisau.jsf.UserSessionManagedBean;
import java.util.Map;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import org.apache.log4j.Logger;

/**
 *
 * @author Herick
 *
 * http://www.rodrigolazoti.com.br/pt/2008/09/01/filtrando-usuarios-logados-em-jsf-com-phaselistener/
 */
public class AuthenticationPhaseListener implements PhaseListener {

    private static final Logger logger = Logger.getLogger(AuthenticationPhaseListener.class);

    @Override
    public void afterPhase(PhaseEvent event) {
        logger.debug("Verificando se usuário está logado...");

        FacesContext facesContext = event.getFacesContext();
        String currentPage = facesContext.getViewRoot().getViewId();

        boolean isLoginPage = (currentPage.lastIndexOf("login.xhtml") > -1);

        Map sessionMap = facesContext.getExternalContext().getSessionMap();
        UserSessionManagedBean userSessionMB = (UserSessionManagedBean) sessionMap.get("userSessionMB");
        PessoaBean pessoa = null;

        if (userSessionMB != null) {
            pessoa = userSessionMB.getLoggedUser();
        }

        if (!isLoginPage && pessoa == null) {
            NavigationHandler nh = facesContext.getApplication().getNavigationHandler();
            nh.handleNavigation(facesContext, null, "loginPage");
        }
    }

    @Override
    public void beforePhase(PhaseEvent event) {
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }
}
