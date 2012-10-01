package br.app.sisau.beans;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jr
 */
@Entity
@Table(name = "prefeitura", catalog = "sisau", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PrefeituraBean.findAll", query = "SELECT p FROM PrefeituraBean p"),
    @NamedQuery(name = "PrefeituraBean.findByPkPrefeitura", query = "SELECT p FROM PrefeituraBean p WHERE p.pkPrefeitura = :pkPrefeitura"),
    @NamedQuery(name = "PrefeituraBean.findByCidade", query = "SELECT p FROM PrefeituraBean p WHERE p.cidade = :cidade"),
    @NamedQuery(name = "PrefeituraBean.findByEstado", query = "SELECT p FROM PrefeituraBean p WHERE p.estado = :estado"),
    @NamedQuery(name = "PrefeituraBean.findByNome", query = "SELECT p FROM PrefeituraBean p WHERE p.nome = :nome")})
public class PrefeituraBean implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "pk_prefeitura", nullable = false)
    private Long pkPrefeitura;
    @Size(max = 50)
    @Column(name = "cidade", length = 50)
    private String cidade;
    @Size(max = 50)
    @Column(name = "estado", length = 50)
    private String estado;
    @Size(max = 50)
    @Column(name = "nome", length = 50)
    private String nome;

    public PrefeituraBean() {
    }

    public PrefeituraBean(Long pkPrefeitura) {
        this.pkPrefeitura = pkPrefeitura;
    }

    public Long getPkPrefeitura() {
        return pkPrefeitura;
    }

    public void setPkPrefeitura(Long pkPrefeitura) {
        this.pkPrefeitura = pkPrefeitura;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkPrefeitura != null ? pkPrefeitura.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PrefeituraBean)) {
            return false;
        }
        PrefeituraBean other = (PrefeituraBean) object;
        if ((this.pkPrefeitura == null && other.pkPrefeitura != null) || (this.pkPrefeitura != null && !this.pkPrefeitura.equals(other.pkPrefeitura))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Código" + pkPrefeitura;
    }
    
}
