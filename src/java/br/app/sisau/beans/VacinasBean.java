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
@Table(name = "vacinas", catalog = "sisau", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VacinasBean.findAll", query = "SELECT v FROM VacinasBean v"),
    @NamedQuery(name = "VacinasBean.findByPkVacinas", query = "SELECT v FROM VacinasBean v WHERE v.pkVacinas = :pkVacinas"),
    @NamedQuery(name = "VacinasBean.findByDataUltilizacao", query = "SELECT v FROM VacinasBean v WHERE v.dataUltilizacao = :dataUltilizacao")})
public class VacinasBean implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "pk_vacinas", nullable = false)
    private Long pkVacinas;
    @Size(max = 50)
    @Column(name = "vacina", length = 50)
    private String vacina;
    @Size(max = 50)
    @Column(name = "dose", length = 50)
    private String dose;
    @Column(name = "data_marcada")
    @Temporal(TemporalType.DATE)
    private Date dataMarcada;
    @Size(max = 50)
    @Column(name = "lote", length = 50)
    private String lote;
    @Size(max = 50)
    @Column(name = "unidade", length = 50)
    private String unidade;
    @Size(max = 50)
    @Column(name = "obs", length = 50)
    private String obs;
    @Size(max = 50)
    @Column(name = "data_aplicacao", length = 50)
    private String dataAplicacao;
    @Column(name = "data_validade")
    @Temporal(TemporalType.DATE)
    private Date dataValidade;
    @Size(max = 50)
    @Column(name = "status", length = 50)
    private String status;
    @Column(name = "data_ultilizacao")
    @Temporal(TemporalType.DATE)
    private Date dataUltilizacao;
    @JoinColumn(name = "fk_doencas", referencedColumnName = "pk_doencas")
    @ManyToOne
    private DoencasBean fkDoencas;
    @OneToMany(mappedBy = "fkVacinas")
    private List<CriancasVacinasBean> criancasVascinasList;
    @OneToMany(mappedBy = "fkVacinas")
    private List<IdososVacinaBean> idososVacinaList;
    @OneToMany(mappedBy = "fkVacinas")
    private List<VacinasDoencasBean> vascinasDoencasList;

    public VacinasBean() {
    }

    public VacinasBean(Long pkVacinas) {
        this.pkVacinas = pkVacinas;
    }

    public Long getPkVacinas() {
        return pkVacinas;
    }

    public void setPkVacinas(Long pkVacinas) {
        this.pkVacinas = pkVacinas;
    }

    public String getVacina() {
        return vacina;
    }

    public void setVacina(String vacina) {
        this.vacina = vacina;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public Date getDataMarcada() {
        return dataMarcada;
    }

    public void setDataMarcada(Date dataMarcada) {
        this.dataMarcada = dataMarcada;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public String getDataAplicacao() {
        return dataAplicacao;
    }

    public void setDataAplicacao(String dataAplicacao) {
        this.dataAplicacao = dataAplicacao;
    }

    public Date getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(Date dataValidade) {
        this.dataValidade = dataValidade;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDataUltilizacao() {
        return dataUltilizacao;
    }

    public void setDataUltilizacao(Date dataUltilizacao) {
        this.dataUltilizacao = dataUltilizacao;
    }

    public DoencasBean getFkDoencas() {
        return fkDoencas;
    }

    public void setFkDoencas(DoencasBean fkDoencas) {
        this.fkDoencas = fkDoencas;
    }

    @XmlTransient
    public List<CriancasVacinasBean> getCriancasVascinasList() {
        return criancasVascinasList;
    }

    public void setCriancasVascinasList(List<CriancasVacinasBean> criancasVascinasList) {
        this.criancasVascinasList = criancasVascinasList;
    }

    @XmlTransient
    public List<IdososVacinaBean> getIdososVacinaList() {
        return idososVacinaList;
    }

    public void setIdososVacinaList(List<IdososVacinaBean> idososVacinaList) {
        this.idososVacinaList = idososVacinaList;
    }

    @XmlTransient
    public List<VacinasDoencasBean> getVascinasDoencasList() {
        return vascinasDoencasList;
    }

    public void setVascinasDoencasList(List<VacinasDoencasBean> vascinasDoencasList) {
        this.vascinasDoencasList = vascinasDoencasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkVacinas != null ? pkVacinas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VacinasBean)) {
            return false;
        }
        VacinasBean other = (VacinasBean) object;
        if ((this.pkVacinas == null && other.pkVacinas != null) || (this.pkVacinas != null && !this.pkVacinas.equals(other.pkVacinas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Código" + pkVacinas;
    }
    
}
