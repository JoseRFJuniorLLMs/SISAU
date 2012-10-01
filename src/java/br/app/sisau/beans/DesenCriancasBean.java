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
@Table(name = "desen_criancas", catalog = "sisau", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DesenCriancasBean.findAll", query = "SELECT d FROM DesenCriancasBean d"),
    @NamedQuery(name = "DesenCriancasBean.findByPkDesenvolvimento", query = "SELECT d FROM DesenCriancasBean d WHERE d.pkDesenvolvimento = :pkDesenvolvimento"),
    @NamedQuery(name = "DesenCriancasBean.findByPeso", query = "SELECT d FROM DesenCriancasBean d WHERE d.peso = :peso"),
    @NamedQuery(name = "DesenCriancasBean.findByAltura", query = "SELECT d FROM DesenCriancasBean d WHERE d.altura = :altura"),
    @NamedQuery(name = "DesenCriancasBean.findByIdade", query = "SELECT d FROM DesenCriancasBean d WHERE d.idade = :idade"),
    @NamedQuery(name = "DesenCriancasBean.findByDataMedicao", query = "SELECT d FROM DesenCriancasBean d WHERE d.dataMedicao = :dataMedicao"),
    @NamedQuery(name = "DesenCriancasBean.findByImc", query = "SELECT d FROM DesenCriancasBean d WHERE d.imc = :imc"),
    @NamedQuery(name = "DesenCriancasBean.findByPaSistolica", query = "SELECT d FROM DesenCriancasBean d WHERE d.paSistolica = :paSistolica"),
    @NamedQuery(name = "DesenCriancasBean.findByPaDistolica", query = "SELECT d FROM DesenCriancasBean d WHERE d.paDistolica = :paDistolica")})
public class DesenCriancasBean implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "pk_desenvolvimento", nullable = false)
    private Long pkDesenvolvimento;
    @Column(name = "peso")
    private BigInteger peso;
    @Column(name = "altura")
    private BigInteger altura;
    @Column(name = "idade")
    private Integer idade;
    @Column(name = "data_medicao")
    @Temporal(TemporalType.DATE)
    private Date dataMedicao;
    @Column(name = "imc")
    private Integer imc;
    @Column(name = "pa_sistolica")
    private BigInteger paSistolica;
    @Column(name = "pa_distolica")
    private BigInteger paDistolica;
    @JoinColumn(name = "fk_crianca", referencedColumnName = "pk_criancas")
    @ManyToOne
    private CriancasBean fkCrianca;
    @OneToMany(mappedBy = "fkDesenCriancas")
    private List<CriancasDesenCriancasBean> criancasDesenCriancasList;

    public DesenCriancasBean() {
    }

    public DesenCriancasBean(Long pkDesenvolvimento) {
        this.pkDesenvolvimento = pkDesenvolvimento;
    }

    public Long getPkDesenvolvimento() {
        return pkDesenvolvimento;
    }

    public void setPkDesenvolvimento(Long pkDesenvolvimento) {
        this.pkDesenvolvimento = pkDesenvolvimento;
    }

    public BigInteger getPeso() {
        return peso;
    }

    public void setPeso(BigInteger peso) {
        this.peso = peso;
    }

    public BigInteger getAltura() {
        return altura;
    }

    public void setAltura(BigInteger altura) {
        this.altura = altura;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public Date getDataMedicao() {
        return dataMedicao;
    }

    public void setDataMedicao(Date dataMedicao) {
        this.dataMedicao = dataMedicao;
    }

    public Integer getImc() {
        return imc;
    }

    public void setImc(Integer imc) {
        this.imc = imc;
    }

    public BigInteger getPaSistolica() {
        return paSistolica;
    }

    public void setPaSistolica(BigInteger paSistolica) {
        this.paSistolica = paSistolica;
    }

    public BigInteger getPaDistolica() {
        return paDistolica;
    }

    public void setPaDistolica(BigInteger paDistolica) {
        this.paDistolica = paDistolica;
    }

    public CriancasBean getFkCrianca() {
        return fkCrianca;
    }

    public void setFkCrianca(CriancasBean fkCrianca) {
        this.fkCrianca = fkCrianca;
    }

    @XmlTransient
    public List<CriancasDesenCriancasBean> getCriancasDesenCriancasList() {
        return criancasDesenCriancasList;
    }

    public void setCriancasDesenCriancasList(List<CriancasDesenCriancasBean> criancasDesenCriancasList) {
        this.criancasDesenCriancasList = criancasDesenCriancasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkDesenvolvimento != null ? pkDesenvolvimento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DesenCriancasBean)) {
            return false;
        }
        DesenCriancasBean other = (DesenCriancasBean) object;
        if ((this.pkDesenvolvimento == null && other.pkDesenvolvimento != null) || (this.pkDesenvolvimento != null && !this.pkDesenvolvimento.equals(other.pkDesenvolvimento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Código" + pkDesenvolvimento;
    }
    
}
