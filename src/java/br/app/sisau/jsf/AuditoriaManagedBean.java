package br.app.sisau.jsf;

import br.app.sisau.beans.AuditoriaBeanBean;
import br.app.sisau.service.Service;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.apache.log4j.Logger;

/**
 *
 * @author herick
 */
@ManagedBean(name = "auditoriaMB")
@ViewScoped
public class AuditoriaManagedBean implements Serializable {

    private static final Logger logger = Logger.getLogger(AuditoriaManagedBean.class);
    private List<AuditoriaBeanBean> listaAuditoria = new ArrayList<AuditoriaBeanBean>();

    @PostConstruct
    public void postConstruct() {
        listaAuditoria = Service.getInstance().listarAuditorias();
    }

    public List<AuditoriaBeanBean> getListaAuditoria() {
        return listaAuditoria;
    }

    public void setListaAuditoria(List<AuditoriaBeanBean> listaAuditoria) {
        this.listaAuditoria = listaAuditoria;
    }
}
