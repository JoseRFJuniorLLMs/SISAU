/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.app.sisau.security.jsf.converter;

import br.app.sisau.security.beans.PapelBean;
import br.app.sisau.security.service.SecurityService;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Herick
 */
@FacesConverter(value = "papelConverter")
public class PapelConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        try {
            Integer id = new Integer(string);
            PapelBean papel = SecurityService.getInstance().buscarPapel(id);
            return papel;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        PapelBean papel = (PapelBean) o;
        if (papel != null) {
            return papel.getId().toString();
        } else {
            return null;
        }
    }
}
