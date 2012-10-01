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
@Table(name = "vascinas_doencas", catalog = "sisau", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VacinasDoencasBean.findAll", query = "SELECT v FROM VacinasDoencasBean v"),
    @NamedQuery(name = "VacinasDoencasBean.findByPkVascinasDoencas", query = "SELECT v FROM VacinasDoencasBean v WHERE v.pkVascinasDoencas = :pkVascinasDoencas")})
public class VacinasDoencasBean implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "pk_vascinas_doencas", nullable = false)
    private Long pkVascinasDoencas;
    @JoinColumn(name = "fk_vacinas", referencedColumnName = "pk_vacinas")
    @ManyToOne
    private VacinasBean fkVacinas;
    @JoinColumn(name = "fk_doencas", referencedColumnName = "pk_doencas")
    @ManyToOne
    private DoencasBean fkDoencas;

    public VacinasDoencasBean() {
    }

    public VacinasDoencasBean(Long pkVascinasDoencas) {
        this.pkVascinasDoencas = pkVascinasDoencas;
    }

    public Long getPkVascinasDoencas() {
        return pkVascinasDoencas;
    }

    public void setPkVascinasDoencas(Long pkVascinasDoencas) {
        this.pkVascinasDoencas = pkVascinasDoencas;
    }

    public VacinasBean getFkVacinas() {
        return fkVacinas;
    }

    public void setFkVacinas(VacinasBean fkVacinas) {
        this.fkVacinas = fkVacinas;
    }

    public DoencasBean getFkDoencas() {
        return fkDoencas;
    }

    public void setFkDoencas(DoencasBean fkDoencas) {
        this.fkDoencas = fkDoencas;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkVascinasDoencas != null ? pkVascinasDoencas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VacinasDoencasBean)) {
            return false;
        }
        VacinasDoencasBean other = (VacinasDoencasBean) object;
        if ((this.pkVascinasDoencas == null && other.pkVascinasDoencas != null) || (this.pkVascinasDoencas != null && !this.pkVascinasDoencas.equals(other.pkVascinasDoencas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Código" + pkVascinasDoencas;
    }
    
}
