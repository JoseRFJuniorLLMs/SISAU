package br.app.sisau.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jr
 */
@Entity
@Table(name = "agenda_medico", catalog = "sisau", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AgendaMedicoBean.findAll", query = "SELECT a FROM AgendaMedicoBean a"),
    @NamedQuery(name = "AgendaMedicoBean.findByPkAgenda", query = "SELECT a FROM AgendaMedicoBean a WHERE a.pkAgenda = :pkAgenda"),
    @NamedQuery(name = "AgendaMedicoBean.findByDataMarcacao", query = "SELECT a FROM AgendaMedicoBean a WHERE a.dataMarcacao = :dataMarcacao"),
    @NamedQuery(name = "AgendaMedicoBean.findByDataAtendimento", query = "SELECT a FROM AgendaMedicoBean a WHERE a.dataAtendimento = :dataAtendimento"),
    @NamedQuery(name = "AgendaMedicoBean.findByStatus", query = "SELECT a FROM AgendaMedicoBean a WHERE a.status = :status")})
public class AgendaMedicoBean implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "pk_agenda", nullable = false)
    private Long pkAgenda;
    @Column(name = "data_marcacao")
    @Temporal(TemporalType.DATE)
    private Date dataMarcacao;
    @Column(name = "data_atendimento")
    @Temporal(TemporalType.DATE)
    private Date dataAtendimento;
    @Size(max = 50)
    @Column(name = "status", length = 50)
    private String status;
    @OneToMany(mappedBy = "fkAgenda")
    private List<MedicosAgendaBean> medicosAgendaList;
    @JoinColumn(name = "especialidade", referencedColumnName = "pk_especialidade")
    @ManyToOne
    private EspecialidadesBean especialidade;

    public AgendaMedicoBean() {
    }

    public AgendaMedicoBean(Long pkAgenda) {
        this.pkAgenda = pkAgenda;
    }

    public Long getPkAgenda() {
        return pkAgenda;
    }

    public void setPkAgenda(Long pkAgenda) {
        this.pkAgenda = pkAgenda;
    }

    public Date getDataMarcacao() {
        return dataMarcacao;
    }

    public void setDataMarcacao(Date dataMarcacao) {
        this.dataMarcacao = dataMarcacao;
    }

    public Date getDataAtendimento() {
        return dataAtendimento;
    }

    public void setDataAtendimento(Date dataAtendimento) {
        this.dataAtendimento = dataAtendimento;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @XmlTransient
    public List<MedicosAgendaBean> getMedicosAgendaList() {
        return medicosAgendaList;
    }

    public void setMedicosAgendaList(List<MedicosAgendaBean> medicosAgendaList) {
        this.medicosAgendaList = medicosAgendaList;
    }

    public EspecialidadesBean getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(EspecialidadesBean especialidade) {
        this.especialidade = especialidade;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkAgenda != null ? pkAgenda.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AgendaMedicoBean)) {
            return false;
        }
        AgendaMedicoBean other = (AgendaMedicoBean) object;
        if ((this.pkAgenda == null && other.pkAgenda != null) || (this.pkAgenda != null && !this.pkAgenda.equals(other.pkAgenda))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Código.:" + pkAgenda;
    }
    
}
