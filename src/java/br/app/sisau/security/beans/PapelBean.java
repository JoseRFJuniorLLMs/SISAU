package br.app.sisau.security.beans;

import br.app.sisau.beans.PessoaBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;


/**
 *
 * @author Artur
 */
@Entity
@Table(name = "papel")
@NamedQueries({
    @NamedQuery(name = "PapelBean.findAll", query = "SELECT p FROM PapelBean p"),
    @NamedQuery(name = "PapelBean.findById", query = "SELECT p FROM PapelBean p WHERE p.id = :id"),
    @NamedQuery(name = "PapelBean.findByNome", query = "SELECT p FROM PapelBean p WHERE p.nome = :nome"),
    @NamedQuery(name = "PapelBean.findByKeyword", query = "SELECT p FROM PapelBean p WHERE p.nome LIKE :nome")})
    
public class PapelBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @SequenceGenerator(name="idPapel", sequenceName="papel_id_papel_seq", allocationSize=1)
    @GeneratedValue (strategy=GenerationType.AUTO, generator="idPapel")
    @Column(name = "id_papel", nullable = false)
    private Integer id;

    @Basic(optional = false)
    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @ManyToMany(mappedBy="papeis", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @OrderBy("nome")
    private List<PessoaBean> pessoas;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinTable(name = "permissao",
        joinColumns =
            @JoinColumn(name = "fk_papel", referencedColumnName = "id_papel"),
        inverseJoinColumns =
            @JoinColumn(name = "fk_objeto_protegido", referencedColumnName = "id_objeto_protegido")
    )
    private List<ObjetoProtegidoBean> objetosProtegidos;

    public PapelBean() {
        this.objetosProtegidos = new ArrayList<ObjetoProtegidoBean>();
        this.pessoas = new ArrayList<PessoaBean>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<PessoaBean> getPessoas() {
        return pessoas;
    }

    public void setPessoas(List<PessoaBean> pessoas) {
        this.pessoas = pessoas;
    }

    public List<ObjetoProtegidoBean> getObjetosProtegidos() {
        return objetosProtegidos;
    }

    public void setObjetosProtegidos(List<ObjetoProtegidoBean> objetosProtegidos) {
        this.objetosProtegidos = objetosProtegidos;
    }

    @Override
    public String toString() {
        return this.getNome();
    }

    @Override
    public boolean equals(Object obj) {
         // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(obj instanceof PapelBean)) {
            return false;
        }
        PapelBean other = (PapelBean) obj;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 29 * hash + (this.nome != null ? this.nome.hashCode() : 0);
        hash = 29 * hash + (this.pessoas != null ? this.pessoas.hashCode() : 0);
        hash = 29 * hash + (this.objetosProtegidos != null ? this.objetosProtegidos.hashCode() : 0);
        return hash;
    }
}
