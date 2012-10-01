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
@Table(name = "cliente", catalog = "sisau", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ClienteBean.findAll", query = "SELECT c FROM ClienteBean c"),
    @NamedQuery(name = "ClienteBean.findById", query = "SELECT c FROM ClienteBean c WHERE c.id = :id"),
    @NamedQuery(name = "ClienteBean.findByNome", query = "SELECT c FROM ClienteBean c WHERE c.nome = :nome"),
    @NamedQuery(name = "ClienteBean.findByCpf", query = "SELECT c FROM ClienteBean c WHERE c.cpf = :cpf"),
    @NamedQuery(name = "ClienteBean.findByEndereco", query = "SELECT c FROM ClienteBean c WHERE c.endereco = :endereco")})
public class ClienteBean implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id", nullable = false)
    private Long id;
    @Size(max = 2147483647)
    @Column(name = "nome", length = 2147483647)
    private String nome;
    @Size(max = 2147483647)
    @Column(name = "cpf", length = 2147483647)
    private String cpf;
    @Size(max = 2147483647)
    @Column(name = "endereco", length = 2147483647)
    private String endereco;

    public ClienteBean() {
    }

    public ClienteBean(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClienteBean)) {
            return false;
        }
        ClienteBean other = (ClienteBean) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Código.:" + id;
    }
    
}
