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
@Table(name = "posto", catalog = "sisau", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PostoBean.findAll", query = "SELECT p FROM PostoBean p order by p.nome"),
    @NamedQuery(name = "PostoBean.findByPkPosto", query = "SELECT p FROM PostoBean p WHERE p.pkPosto = :pkPosto"),
    @NamedQuery(name = "PostoBean.findByNome", query = "SELECT p FROM PostoBean p WHERE p.nome like :nome")})
public class PostoBean implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "posto_pk_posto_seq")
    @SequenceGenerator(name = "posto_pk_posto_seq", sequenceName = "posto_pk_posto_seq", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "pk_posto", nullable = false)
    private Long pkPosto;
    @Size(max = 2147483647)
    @Column(name = "nome", length = 2147483647)
    private String nome;
    @OneToMany(mappedBy = "fkPosto")
    private List<PostoMedicosBean> postoMedicosList;
    @OneToMany(mappedBy = "fkPostoDeSaude")
    private List<ConsultasBean> consultasList;

    public PostoBean() {
    }

    public PostoBean(Long pkPosto) {
        this.pkPosto = pkPosto;
    }

    public Long getPkPosto() {
        return pkPosto;
    }

    public void setPkPosto(Long pkPosto) {
        this.pkPosto = pkPosto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @XmlTransient
    public List<PostoMedicosBean> getPostoMedicosList() {
        return postoMedicosList;
    }

    public void setPostoMedicosList(List<PostoMedicosBean> postoMedicosList) {
        this.postoMedicosList = postoMedicosList;
    }

    @XmlTransient
    public List<ConsultasBean> getConsultasList() {
        return consultasList;
    }

    public void setConsultasList(List<ConsultasBean> consultasList) {
        this.consultasList = consultasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkPosto != null ? pkPosto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PostoBean)) {
            return false;
        }
        PostoBean other = (PostoBean) object;
        if ((this.pkPosto == null && other.pkPosto != null) || (this.pkPosto != null && !this.pkPosto.equals(other.pkPosto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Código" + pkPosto;
    }
    
}