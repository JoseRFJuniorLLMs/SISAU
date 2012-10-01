/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.app.sisau.jsf;


import br.app.sisau.beans.PessoaBean;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.apache.log4j.Logger;

/**
 *
 * @author Herick
 */
@ManagedBean(name = "userSessionMB")
@SessionScoped
public class UserSessionManagedBean implements Serializable {
    
    private static final Logger logger = Logger.getLogger(UserSessionManagedBean.class);
    private PessoaBean loggedUser;    
    private Object interPageParameter;

    public UserSessionManagedBean() {
        logger.debug("Instanciando classe");
    }

    public PessoaBean getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(PessoaBean loggedUser) {
        this.loggedUser = loggedUser;
    }

    public Object getInterPageParameter() {
        return interPageParameter;
    }

    public void setInterPageParameter(Object interPageParameter) {
        this.interPageParameter = interPageParameter;
    }
}
