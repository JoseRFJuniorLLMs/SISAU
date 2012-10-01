/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.app.sisau.beans;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jr
 */
@Entity
@Table(name = "criancas_desen_criancas", catalog = "sisau", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CriancasDesenCriancasBean.findAll", query = "SELECT c FROM CriancasDesenCriancasBean c"),
    @NamedQuery(name = "CriancasDesenCriancasBean.findByPkCriancasDesenCriancas", query = "SELECT c FROM CriancasDesenCriancasBean c WHERE c.pkCriancasDesenCriancas = :pkCriancasDesenCriancas")})
public class CriancasDesenCriancasBean implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "pk_criancas_desen_criancas", nullable = false)
    private Long pkCriancasDesenCriancas;
    @JoinColumn(name = "fk_desen_criancas", referencedColumnName = "pk_desenvolvimento")
    @ManyToOne
    private DesenCriancasBean fkDesenCriancas;
    @JoinColumn(name = "fk_criancas", referencedColumnName = "pk_criancas")
    @ManyToOne
    private CriancasBean fkCriancas;

    public CriancasDesenCriancasBean() {
    }

    public CriancasDesenCriancasBean(Long pkCriancasDesenCriancas) {
        this.pkCriancasDesenCriancas = pkCriancasDesenCriancas;
    }

    public Long getPkCriancasDesenCriancas() {
        return pkCriancasDesenCriancas;
    }

    public void setPkCriancasDesenCriancas(Long pkCriancasDesenCriancas) {
        this.pkCriancasDesenCriancas = pkCriancasDesenCriancas;
    }

    public DesenCriancasBean getFkDesenCriancas() {
        return fkDesenCriancas;
    }

    public void setFkDesenCriancas(DesenCriancasBean fkDesenCriancas) {
        this.fkDesenCriancas = fkDesenCriancas;
    }

    public CriancasBean getFkCriancas() {
        return fkCriancas;
    }

    public void setFkCriancas(CriancasBean fkCriancas) {
        this.fkCriancas = fkCriancas;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkCriancasDesenCriancas != null ? pkCriancasDesenCriancas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CriancasDesenCriancasBean)) {
            return false;
        }
        CriancasDesenCriancasBean other = (CriancasDesenCriancasBean) object;
        if ((this.pkCriancasDesenCriancas == null && other.pkCriancasDesenCriancas != null) || (this.pkCriancasDesenCriancas != null && !this.pkCriancasDesenCriancas.equals(other.pkCriancasDesenCriancas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.CriancasDesenCriancas[ pkCriancasDesenCriancas=" + pkCriancasDesenCriancas + " ]";
    }
    
}
