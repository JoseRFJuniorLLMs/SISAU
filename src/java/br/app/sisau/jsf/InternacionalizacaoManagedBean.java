/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.app.sisau.jsf;

/**
 *
 * @author Herick
 */
import java.io.Serializable;
import java.util.Locale;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import org.apache.log4j.Logger;

@ManagedBean(name = "i18nMB")
@ApplicationScoped
public class InternacionalizacaoManagedBean implements Serializable {

    private static final Logger logger = Logger.getLogger(InternacionalizacaoManagedBean.class);
    private Locale currentLocale = new Locale("pt", "BR");

    public void englishLocale() {
        logger.debug("Locale US");
        UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();
        currentLocale = Locale.US;
        viewRoot.setLocale(currentLocale);
    }

    public void portugueseLocale() {
        logger.debug("Locale BR");
        UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();
        currentLocale = new Locale("pt", "BR");
        viewRoot.setLocale(currentLocale);
    }

    public Locale getCurrentLocale() {
        return currentLocale;
    }
}
