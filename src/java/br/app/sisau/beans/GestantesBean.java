package br.app.sisau.beans;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jr
 */
@Entity
@Table(name = "gestantes", catalog = "sisau", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GestantesBean.findAll", query = "SELECT g FROM GestantesBean g"),
    @NamedQuery(name = "GestantesBean.findByPkGestante", query = "SELECT g FROM GestantesBean g WHERE g.pkGestante = :pkGestante"),
    @NamedQuery(name = "GestantesBean.findByCodigoGestacao", query = "SELECT g FROM GestantesBean g WHERE g.codigoGestacao = :codigoGestacao"),
    @NamedQuery(name = "GestantesBean.findByDataNascimento", query = "SELECT g FROM GestantesBean g WHERE g.dataNascimento = :dataNascimento")})
public class GestantesBean implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "pk_gestante", nullable = false)
    private Long pkGestante;
    @Column(name = "codigo_gestacao")
    private BigInteger codigoGestacao;
    @Column(name = "data_nascimento")
    @Temporal(TemporalType.DATE)
    private Date dataNascimento;
    @OneToMany(mappedBy = "fkGestante")
    private List<DesenGestantesBean> desenGestantesList;
    @OneToMany(mappedBy = "fkGestante")
    private List<PessoaTipoBean> pessoaTipoList;
    @OneToMany(mappedBy = "fkGestantes")
    private List<GestantesVitaminasBean> gestantesVitaminasList;
    @OneToMany(mappedBy = "fkGestante")
    private List<GestanteDesenGestanteBean> gestanteDesenGestanteList;
    @OneToMany(mappedBy = "fkGestantes")
    private List<PessoasConsultasBean> pessoasConsultasList;
    @OneToMany(mappedBy = "fkGestantes")
    private List<GestantesDoencasBean> gestantesDoencasList;

    public GestantesBean() {
    }

    public GestantesBean(Long pkGestante) {
        this.pkGestante = pkGestante;
    }

    public Long getPkGestante() {
        return pkGestante;
    }

    public void setPkGestante(Long pkGestante) {
        this.pkGestante = pkGestante;
    }

    public BigInteger getCodigoGestacao() {
        return codigoGestacao;
    }

    public void setCodigoGestacao(BigInteger codigoGestacao) {
        this.codigoGestacao = codigoGestacao;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    @XmlTransient
    public List<DesenGestantesBean> getDesenGestantesList() {
        return desenGestantesList;
    }

    public void setDesenGestantesList(List<DesenGestantesBean> desenGestantesList) {
        this.desenGestantesList = desenGestantesList;
    }

    @XmlTransient
    public List<PessoaTipoBean> getPessoaTipoList() {
        return pessoaTipoList;
    }

    public void setPessoaTipoList(List<PessoaTipoBean> pessoaTipoList) {
        this.pessoaTipoList = pessoaTipoList;
    }

    @XmlTransient
    public List<GestantesVitaminasBean> getGestantesVitaminasList() {
        return gestantesVitaminasList;
    }

    public void setGestantesVitaminasList(List<GestantesVitaminasBean> gestantesVitaminasList) {
        this.gestantesVitaminasList = gestantesVitaminasList;
    }

    @XmlTransient
    public List<GestanteDesenGestanteBean> getGestanteDesenGestanteList() {
        return gestanteDesenGestanteList;
    }

    public void setGestanteDesenGestanteList(List<GestanteDesenGestanteBean> gestanteDesenGestanteList) {
        this.gestanteDesenGestanteList = gestanteDesenGestanteList;
    }

    @XmlTransient
    public List<PessoasConsultasBean> getPessoasConsultasList() {
        return pessoasConsultasList;
    }

    public void setPessoasConsultasList(List<PessoasConsultasBean> pessoasConsultasList) {
        this.pessoasConsultasList = pessoasConsultasList;
    }

    @XmlTransient
    public List<GestantesDoencasBean> getGestantesDoencasList() {
        return gestantesDoencasList;
    }

    public void setGestantesDoencasList(List<GestantesDoencasBean> gestantesDoencasList) {
        this.gestantesDoencasList = gestantesDoencasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkGestante != null ? pkGestante.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GestantesBean)) {
            return false;
        }
        GestantesBean other = (GestantesBean) object;
        if ((this.pkGestante == null && other.pkGestante != null) || (this.pkGestante != null && !this.pkGestante.equals(other.pkGestante))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Código.:" + pkGestante;
    }
    
}
