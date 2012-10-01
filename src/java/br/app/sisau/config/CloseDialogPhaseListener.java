package br.app.sisau.config;

import javax.faces.application.FacesMessage;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Herick
 */
public class CloseDialogPhaseListener implements PhaseListener {
    
    private static final Logger logger = Logger.getLogger(CloseDialogPhaseListener.class);

    @Override
    public void afterPhase(PhaseEvent phaseEvent) {}

    @Override
    public void beforePhase(PhaseEvent event) {
        logger.debug("Setando atributos para funcionamento de componentes dialog...");
        RequestContext requestContext = RequestContext.getCurrentInstance();
        if (requestContext != null) {
            boolean messageOccured = FacesMessage.SEVERITY_ERROR.equals(event.getFacesContext().getMaximumSeverity());
            requestContext.addCallbackParam("isValid", messageOccured);
        }
    }

    @Override
    public PhaseId getPhaseId() {        
        return PhaseId.RENDER_RESPONSE;
    }
}
