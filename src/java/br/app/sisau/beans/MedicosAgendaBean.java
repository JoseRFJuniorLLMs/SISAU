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
@Table(name = "medicos_agenda", catalog = "sisau", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MedicosAgendaBean.findAll", query = "SELECT m FROM MedicosAgendaBean m"),
    @NamedQuery(name = "MedicosAgendaBean.findByPkMedicosAgenda", query = "SELECT m FROM MedicosAgendaBean m WHERE m.pkMedicosAgenda = :pkMedicosAgenda")})

public class MedicosAgendaBean implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "pk_medicos_agenda", nullable = false)
    private Long pkMedicosAgenda;
    @JoinColumn(name = "fk_medicos", referencedColumnName = "pk_medico")
    @ManyToOne
    private MedicosBean fkMedicos;
    @JoinColumn(name = "fk_agenda", referencedColumnName = "pk_agenda")
    @ManyToOne
    private AgendaMedicoBean fkAgenda;

    public MedicosAgendaBean() {
    }

    public MedicosAgendaBean(Long pkMedicosAgenda) {
        this.pkMedicosAgenda = pkMedicosAgenda;
    }

    public Long getPkMedicosAgenda() {
        return pkMedicosAgenda;
    }

    public void setPkMedicosAgenda(Long pkMedicosAgenda) {
        this.pkMedicosAgenda = pkMedicosAgenda;
    }

    public MedicosBean getFkMedicos() {
        return fkMedicos;
    }

    public void setFkMedicos(MedicosBean fkMedicos) {
        this.fkMedicos = fkMedicos;
    }

    public AgendaMedicoBean getFkAgenda() {
        return fkAgenda;
    }

    public void setFkAgenda(AgendaMedicoBean fkAgenda) {
        this.fkAgenda = fkAgenda;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkMedicosAgenda != null ? pkMedicosAgenda.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MedicosAgendaBean)) {
            return false;
        }
        MedicosAgendaBean other = (MedicosAgendaBean) object;
        if ((this.pkMedicosAgenda == null && other.pkMedicosAgenda != null) || (this.pkMedicosAgenda != null && !this.pkMedicosAgenda.equals(other.pkMedicosAgenda))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Código" + pkMedicosAgenda;
    }
    
}
