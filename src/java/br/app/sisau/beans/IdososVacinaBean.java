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
@Table(name = "idosos_vacina", catalog = "sisau", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IdososVacinaBean.findAll", query = "SELECT i FROM IdososVacinaBean i"),
    @NamedQuery(name = "IdososVacinaBean.findByPkIdososVacina", query = "SELECT i FROM IdososVacinaBean i WHERE i.pkIdososVacina = :pkIdososVacina")})
public class IdososVacinaBean implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "pk_idosos_vacina", nullable = false)
    private Long pkIdososVacina;
    @JoinColumn(name = "fk_vacinas", referencedColumnName = "pk_vacinas")
    @ManyToOne
    private VacinasBean fkVacinas;
    @JoinColumn(name = "fk_idosos", referencedColumnName = "pk_idosos")
    @ManyToOne
    private IdososBean fkIdosos;

    public IdososVacinaBean() {
    }

    public IdososVacinaBean(Long pkIdososVacina) {
        this.pkIdososVacina = pkIdososVacina;
    }

    public Long getPkIdososVacina() {
        return pkIdososVacina;
    }

    public void setPkIdososVacina(Long pkIdososVacina) {
        this.pkIdososVacina = pkIdososVacina;
    }

    public VacinasBean getFkVacinas() {
        return fkVacinas;
    }

    public void setFkVacinas(VacinasBean fkVacinas) {
        this.fkVacinas = fkVacinas;
    }

    public IdososBean getFkIdosos() {
        return fkIdosos;
    }

    public void setFkIdosos(IdososBean fkIdosos) {
        this.fkIdosos = fkIdosos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkIdososVacina != null ? pkIdososVacina.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IdososVacinaBean)) {
            return false;
        }
        IdososVacinaBean other = (IdososVacinaBean) object;
        if ((this.pkIdososVacina == null && other.pkIdososVacina != null) || (this.pkIdososVacina != null && !this.pkIdososVacina.equals(other.pkIdososVacina))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Código" + pkIdososVacina;
    }
    
}
