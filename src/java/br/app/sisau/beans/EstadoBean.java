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
@Table(name = "estado", catalog = "sisau", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadoBean.findAll", query = "SELECT e FROM EstadoBean e"),
    @NamedQuery(name = "EstadoBean.findByPkEstados", query = "SELECT e FROM EstadoBean e WHERE e.pkEstados = :pkEstados"),
    @NamedQuery(name = "EstadoBean.findByUf", query = "SELECT e FROM EstadoBean e WHERE e.uf = :uf"),
    @NamedQuery(name = "EstadoBean.findByDescricao", query = "SELECT e FROM EstadoBean e WHERE e.descricao = :descricao")})
public class EstadoBean implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "pk_estados", nullable = false)
    private Long pkEstados;
    @Size(max = 2147483647)
    @Column(name = "uf", length = 2147483647)
    private String uf;
    @Size(max = 2147483647)
    @Column(name = "descricao", length = 2147483647)
    private String descricao;
    @OneToMany(mappedBy = "fkEstado")
    private List<PessoaBean> pessoaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkEstado")
    private List<CidadeBean> cidadeList;

    public EstadoBean() {
    }

    public EstadoBean(Long pkEstados) {
        this.pkEstados = pkEstados;
    }

    public Long getPkEstados() {
        return pkEstados;
    }

    public void setPkEstados(Long pkEstados) {
        this.pkEstados = pkEstados;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @XmlTransient
    public List<PessoaBean> getPessoaList() {
        return pessoaList;
    }

    public void setPessoaList(List<PessoaBean> pessoaList) {
        this.pessoaList = pessoaList;
    }

    @XmlTransient
    public List<CidadeBean> getCidadeList() {
        return cidadeList;
    }

    public void setCidadeList(List<CidadeBean> cidadeList) {
        this.cidadeList = cidadeList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkEstados != null ? pkEstados.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoBean)) {
            return false;
        }
        EstadoBean other = (EstadoBean) object;
        if ((this.pkEstados == null && other.pkEstados != null) || (this.pkEstados != null && !this.pkEstados.equals(other.pkEstados))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Código" + pkEstados;
    }
    
}
