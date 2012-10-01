package br.app.sisau.beans;

import java.io.Serializable;
import java.math.BigInteger;
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
@Table(name = "desen_gestantes", catalog = "sisau", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DesenGestantesBean.findAll", query = "SELECT d FROM DesenGestantesBean d"),
    @NamedQuery(name = "DesenGestantesBean.findByPkDesenGestantes", query = "SELECT d FROM DesenGestantesBean d WHERE d.pkDesenGestantes = :pkDesenGestantes"),
    @NamedQuery(name = "DesenGestantesBean.findByPeso", query = "SELECT d FROM DesenGestantesBean d WHERE d.peso = :peso"),
    @NamedQuery(name = "DesenGestantesBean.findByAltura", query = "SELECT d FROM DesenGestantesBean d WHERE d.altura = :altura"),
    @NamedQuery(name = "DesenGestantesBean.findBySemanasGestacao", query = "SELECT d FROM DesenGestantesBean d WHERE d.semanasGestacao = :semanasGestacao"),
    @NamedQuery(name = "DesenGestantesBean.findByDimensoesBarriga", query = "SELECT d FROM DesenGestantesBean d WHERE d.dimensoesBarriga = :dimensoesBarriga")})
public class DesenGestantesBean implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "pk_desen_gestantes", nullable = false)
    private Long pkDesenGestantes;
    @Column(name = "peso")
    private BigInteger peso;
    @Size(max = 50)
    @Column(name = "altura", length = 50)
    private String altura;
    @Size(max = 50)
    @Column(name = "semanas_gestacao", length = 50)
    private String semanasGestacao;
    @Size(max = 50)
    @Column(name = "dimensoes_barriga", length = 50)
    private String dimensoesBarriga;
    @JoinColumn(name = "fk_gestante", referencedColumnName = "pk_gestante")
    @ManyToOne
    private GestantesBean fkGestante;
    @OneToMany(mappedBy = "fkDesesnGestantes")
    private List<GestanteDesenGestanteBean> gestanteDesenGestanteList;

    public DesenGestantesBean() {
    }

    public DesenGestantesBean(Long pkDesenGestantes) {
        this.pkDesenGestantes = pkDesenGestantes;
    }

    public Long getPkDesenGestantes() {
        return pkDesenGestantes;
    }

    public void setPkDesenGestantes(Long pkDesenGestantes) {
        this.pkDesenGestantes = pkDesenGestantes;
    }

    public BigInteger getPeso() {
        return peso;
    }

    public void setPeso(BigInteger peso) {
        this.peso = peso;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public String getSemanasGestacao() {
        return semanasGestacao;
    }

    public void setSemanasGestacao(String semanasGestacao) {
        this.semanasGestacao = semanasGestacao;
    }

    public String getDimensoesBarriga() {
        return dimensoesBarriga;
    }

    public void setDimensoesBarriga(String dimensoesBarriga) {
        this.dimensoesBarriga = dimensoesBarriga;
    }

    public GestantesBean getFkGestante() {
        return fkGestante;
    }

    public void setFkGestante(GestantesBean fkGestante) {
        this.fkGestante = fkGestante;
    }

    @XmlTransient
    public List<GestanteDesenGestanteBean> getGestanteDesenGestanteList() {
        return gestanteDesenGestanteList;
    }

    public void setGestanteDesenGestanteList(List<GestanteDesenGestanteBean> gestanteDesenGestanteList) {
        this.gestanteDesenGestanteList = gestanteDesenGestanteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkDesenGestantes != null ? pkDesenGestantes.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DesenGestantesBean)) {
            return false;
        }
        DesenGestantesBean other = (DesenGestantesBean) object;
        if ((this.pkDesenGestantes == null && other.pkDesenGestantes != null) || (this.pkDesenGestantes != null && !this.pkDesenGestantes.equals(other.pkDesenGestantes))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Código.:" + pkDesenGestantes;
    }
    
}
