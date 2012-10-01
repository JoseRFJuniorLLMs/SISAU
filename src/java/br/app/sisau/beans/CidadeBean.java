package br.app.sisau.beans;

import java.io.Serializable;
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
@Table(name = "cidade", catalog = "sisau", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CidadeBean.findAll", query = "SELECT c FROM CidadeBean c"),
    @NamedQuery(name = "CidadeBean.findByPkCidade", query = "SELECT c FROM CidadeBean c WHERE c.pkCidade = :pkCidade"),
    @NamedQuery(name = "CidadeBean.findByMunicipio", query = "SELECT c FROM CidadeBean c WHERE c.municipio = :municipio")})
public class CidadeBean implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "pk_cidade", nullable = false)
    private Long pkCidade;
    @Size(max = 2147483647)
    @Column(name = "municipio", length = 2147483647)
    private String municipio;
    @OneToMany(mappedBy = "fkCidade")
    private List<PessoaBean> pessoaList;
    @JoinColumn(name = "fk_estado", referencedColumnName = "pk_estados", nullable = false)
    @ManyToOne(optional = false)
    private EstadoBean fkEstado;

    public CidadeBean() {
    }

    public CidadeBean(Long pkCidade) {
        this.pkCidade = pkCidade;
    }

    public Long getPkCidade() {
        return pkCidade;
    }

    public void setPkCidade(Long pkCidade) {
        this.pkCidade = pkCidade;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    @XmlTransient
    public List<PessoaBean> getPessoaList() {
        return pessoaList;
    }

    public void setPessoaList(List<PessoaBean> pessoaList) {
        this.pessoaList = pessoaList;
    }

    public EstadoBean getFkEstado() {
        return fkEstado;
    }

    public void setFkEstado(EstadoBean fkEstado) {
        this.fkEstado = fkEstado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkCidade != null ? pkCidade.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CidadeBean)) {
            return false;
        }
        CidadeBean other = (CidadeBean) object;
        if ((this.pkCidade == null && other.pkCidade != null) || (this.pkCidade != null && !this.pkCidade.equals(other.pkCidade))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Código" + pkCidade;
    }
    
}
