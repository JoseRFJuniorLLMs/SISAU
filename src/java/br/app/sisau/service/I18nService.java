/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.app.sisau.service;

import java.util.Locale;
import java.util.ResourceBundle;
import javax.faces.context.FacesContext;
import org.apache.log4j.Logger;

/**
 *
 * @author Herick
 */
public class I18nService {

    private static final Logger logger = Logger.getLogger(I18nService.class);
    /*
     * Objeto para implementacao do singleton
     */
    private static I18nService instance = new I18nService();

    /*
     * Metodo construtor privado, para evitar
     * multipla instanciacao
     */
    private I18nService() {
    }
    /*
     * Metodo que retorna a unica instancia da classe
     */

    public static I18nService getInstance() {
        return instance;
    }

    public String getKey(String chave) {
        Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
        ResourceBundle bundle = ResourceBundle.getBundle("br.com.integrator.messages", locale);
        return bundle.getString(chave);
    }
}
