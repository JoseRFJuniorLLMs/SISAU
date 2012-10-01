/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.app.sisau.security.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
/**
 *
 * @author Artur
 */
@Entity
@Table(name = "objeto_protegido")
@NamedQueries({
    @NamedQuery(name = "ObjetoProtegidoBean.findAll", query = "SELECT o FROM ObjetoProtegidoBean o ORDER By o.nome"),
    @NamedQuery(name = "ObjetoProtegidoBean.findById", query = "SELECT o FROM ObjetoProtegidoBean o WHERE o.id = :id"),
    @NamedQuery(name = "ObjetoProtegidoBean.findByNome", query = "SELECT o FROM ObjetoProtegidoBean o WHERE o.nome = :nome"),
    @NamedQuery(name = "ObjetoProtegidoBean.findByKeyword", query = "SELECT o FROM ObjetoProtegidoBean o WHERE o.nome LIKE :nome")})
public class ObjetoProtegidoBean implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @SequenceGenerator(name="idOP", sequenceName="objeto_protegido_id_objeto_protegido_seq", allocationSize=1)
    @GeneratedValue (strategy=GenerationType.AUTO, generator="idOP")
    @Column(name = "id_objeto_protegido", nullable = false)
    private Integer id;

    @Basic(optional = false)
    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Basic(optional = true)
    @Column(name = "descricao")
    private String descricao;

    @ManyToMany(mappedBy="objetosProtegidos", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private List<PapelBean> papeis;

    public ObjetoProtegidoBean() {
        this.papeis = new ArrayList<PapelBean>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<PapelBean> getPapeis() {
        return papeis;
    }

    public void setPapeis(List<PapelBean> papeis) {
        this.papeis = papeis;
    }

    @Override
    public boolean equals(Object obj) {
        return this.id.intValue() == ((ObjetoProtegidoBean)obj).getId().intValue();
    }
    
    @Override
    public String toString(){
        return this.nome;
    }
}
