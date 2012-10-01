package br.app.sisau.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jr
 */
@Entity
@Table(name = "medicos", catalog = "sisau", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MedicosBean.findAll", query = "SELECT m FROM MedicosBean m"),
    @NamedQuery(name = "MedicosBean.findByPkMedico", query = "SELECT m FROM MedicosBean m WHERE m.pkMedico = :pkMedico"),
    @NamedQuery(name = "MedicosBean.findByNome", query = "SELECT m FROM MedicosBean m WHERE m.nome = :nome"),
    @NamedQuery(name = "MedicosBean.findByCrm", query = "SELECT m FROM MedicosBean m WHERE m.crm = :crm"),
    @NamedQuery(name = "MedicosBean.findByDataNascimento", query = "SELECT m FROM MedicosBean m WHERE m.dataNascimento = :dataNascimento")})
public class MedicosBean implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @SequenceGenerator(name = "pkMedico", sequenceName = "medicos_pk_medico_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "pkMedico")
    @Column(name = "pk_medico", nullable = false)
    private Long pkMedico;
    

        
    @Size(max = 50)
    @Column(name = "nome", length = 50)
    private String nome;
    @Size(max = 20)
    @Column(name = "crm", length = 20)
    private String crm;
    @Column(name = "data_nascimento")
    @Temporal(TemporalType.DATE)
    private Date dataNascimento;
    @OneToMany(mappedBy = "fkMedicos")
    private List<PessoaTipoBean> pessoaTipoList;

    @JoinColumn(name = "fk_especialidade", referencedColumnName = "pk_especialidade")
    @ManyToOne
    private EspecialidadesBean fkEspecialidade;
    
    @OneToMany(mappedBy = "fkMedicos")
    private List<MedicosAgendaBean> medicosAgendaList;
    
    @OneToMany(mappedBy = "fkMedicos")
    private List<PostoMedicosBean> postoMedicosList;
    
    @OneToMany(mappedBy = "fkMedicos")
    private List<EspecialidadesMedicosBean> especialidadesMedicosList;

    public MedicosBean() {
    }

    public MedicosBean(Long pkMedico) {
        this.pkMedico = pkMedico;
    }

    
    public Long getPkMedico() {
        return pkMedico;
    }

    public void setPkMedico(Long pkMedico) {
        this.pkMedico = pkMedico;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    @XmlTransient
    public List<PessoaTipoBean> getPessoaTipoList() {
        return pessoaTipoList;
    }

    public void setPessoaTipoList(List<PessoaTipoBean> pessoaTipoList) {
        this.pessoaTipoList = pessoaTipoList;
    }

    public EspecialidadesBean getFkEspecialidade() {
        return fkEspecialidade;
    }

    public void setFkEspecialidade(EspecialidadesBean fkEspecialidade) {
        this.fkEspecialidade = fkEspecialidade;
    }

    @XmlTransient
    public List<MedicosAgendaBean> getMedicosAgendaList() {
        return medicosAgendaList;
    }

    public void setMedicosAgendaList(List<MedicosAgendaBean> medicosAgendaList) {
        this.medicosAgendaList = medicosAgendaList;
    }

    @XmlTransient
    public List<PostoMedicosBean> getPostoMedicosList() {
        return postoMedicosList;
    }

    public void setPostoMedicosList(List<PostoMedicosBean> postoMedicosList) {
        this.postoMedicosList = postoMedicosList;
    }

    @XmlTransient
    public List<EspecialidadesMedicosBean> getEspecialidadesMedicosList() {
        return especialidadesMedicosList;
    }

    public void setEspecialidadesMedicosList(List<EspecialidadesMedicosBean> especialidadesMedicosList) {
        this.especialidadesMedicosList = especialidadesMedicosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkMedico != null ? pkMedico.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MedicosBean)) {
            return false;
        }
        MedicosBean other = (MedicosBean) object;
        if ((this.pkMedico == null && other.pkMedico != null) || (this.pkMedico != null && !this.pkMedico.equals(other.pkMedico))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return ""+pkMedico;
    }
    
}
