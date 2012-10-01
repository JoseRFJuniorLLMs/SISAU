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
@Table(name = "raca", catalog = "sisau", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RacaBean.findAll", query = "SELECT r FROM RacaBean r"),
    @NamedQuery(name = "RacaBean.findByPkRaca", query = "SELECT r FROM RacaBean r WHERE r.pkRaca = :pkRaca"),
    @NamedQuery(name = "RacaBean.findByDescriacao", query = "SELECT r FROM RacaBean r WHERE r.descriacao = :descriacao")})
public class RacaBean implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "pk_raca", nullable = false)
    private Long pkRaca;
    @Size(max = 50)
    @Column(name = "descriacao", length = 50)
    private String descriacao;
    @OneToMany(mappedBy = "fkRaca")
    private List<PessoaBean> pessoaList;

    public RacaBean() {
    }

    public RacaBean(Long pkRaca) {
        this.pkRaca = pkRaca;
    }

    public Long getPkRaca() {
        return pkRaca;
    }

    public void setPkRaca(Long pkRaca) {
        this.pkRaca = pkRaca;
    }

    public String getDescriacao() {
        return descriacao;
    }

    public void setDescriacao(String descriacao) {
        this.descriacao = descriacao;
    }

    @XmlTransient
    public List<PessoaBean> getPessoaList() {
        return pessoaList;
    }

    public void setPessoaList(List<PessoaBean> pessoaList) {
        this.pessoaList = pessoaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkRaca != null ? pkRaca.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RacaBean)) {
            return false;
        }
        RacaBean other = (RacaBean) object;
        if ((this.pkRaca == null && other.pkRaca != null) || (this.pkRaca != null && !this.pkRaca.equals(other.pkRaca))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Código" + pkRaca;
    }
    
}
