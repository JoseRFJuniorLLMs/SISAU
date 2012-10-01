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
@Table(name = "familias", catalog = "sisau", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FamiliasBean.findAll", query = "SELECT f FROM FamiliasBean f"),
    @NamedQuery(name = "FamiliasBean.findByPkFamilia", query = "SELECT f FROM FamiliasBean f WHERE f.pkFamilia = :pkFamilia"),
    @NamedQuery(name = "FamiliasBean.findByNome", query = "SELECT f FROM FamiliasBean f WHERE f.nome = :nome")})
public class FamiliasBean implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "pk_familia", nullable = false)
    private Long pkFamilia;
    @Size(max = 50)
    @Column(name = "nome", length = 50)
    private String nome;
    @OneToMany(mappedBy = "fkFamilia")
    private List<CriancasBean> criancasList;
    @OneToMany(mappedBy = "fkFamilias")
    private List<FamiliasBolsistasBean> familiasBolcistasList;
    @OneToMany(mappedBy = "fkFamilias")
    private List<FamiliasAlternativaAlimentaresBean> familiasAlternativaAlimentaresList;
    @OneToMany(mappedBy = "fkFamilia")
    private List<PessoaBean> pessoaList;
    @JoinColumn(name = "fk_bolsistas", referencedColumnName = "pk_bolsista")
    @ManyToOne
    private BolsistasBean fkBolcistas;
    @JoinColumn(name = "fk_areas_riscos", referencedColumnName = "pk_area")
    @ManyToOne
    private AreasRiscosBean fkAreasRiscos;
    @JoinColumn(name = "fk_alternativa", referencedColumnName = "pk_parceiros")
    @ManyToOne
    private AlternativasAlimentaresBean fkAlternativa;

    public FamiliasBean() {
    }

    public FamiliasBean(Long pkFamilia) {
        this.pkFamilia = pkFamilia;
    }

    public Long getPkFamilia() {
        return pkFamilia;
    }

    public void setPkFamilia(Long pkFamilia) {
        this.pkFamilia = pkFamilia;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @XmlTransient
    public List<CriancasBean> getCriancasList() {
        return criancasList;
    }

    public void setCriancasList(List<CriancasBean> criancasList) {
        this.criancasList = criancasList;
    }

    @XmlTransient
    public List<FamiliasBolsistasBean> getFamiliasBolcistasList() {
        return familiasBolcistasList;
    }

    public void setFamiliasBolcistasList(List<FamiliasBolsistasBean> familiasBolcistasList) {
        this.familiasBolcistasList = familiasBolcistasList;
    }

    @XmlTransient
    public List<FamiliasAlternativaAlimentaresBean> getFamiliasAlternativaAlimentaresList() {
        return familiasAlternativaAlimentaresList;
    }

    public void setFamiliasAlternativaAlimentaresList(List<FamiliasAlternativaAlimentaresBean> familiasAlternativaAlimentaresList) {
        this.familiasAlternativaAlimentaresList = familiasAlternativaAlimentaresList;
    }

    @XmlTransient
    public List<PessoaBean> getPessoaList() {
        return pessoaList;
    }

    public void setPessoaList(List<PessoaBean> pessoaList) {
        this.pessoaList = pessoaList;
    }

    public BolsistasBean getFkBolcistas() {
        return fkBolcistas;
    }

    public void setFkBolcistas(BolsistasBean fkBolcistas) {
        this.fkBolcistas = fkBolcistas;
    }

    public AreasRiscosBean getFkAreasRiscos() {
        return fkAreasRiscos;
    }

    public void setFkAreasRiscos(AreasRiscosBean fkAreasRiscos) {
        this.fkAreasRiscos = fkAreasRiscos;
    }

    public AlternativasAlimentaresBean getFkAlternativa() {
        return fkAlternativa;
    }

    public void setFkAlternativa(AlternativasAlimentaresBean fkAlternativa) {
        this.fkAlternativa = fkAlternativa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkFamilia != null ? pkFamilia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FamiliasBean)) {
            return false;
        }
        FamiliasBean other = (FamiliasBean) object;
        if ((this.pkFamilia == null && other.pkFamilia != null) || (this.pkFamilia != null && !this.pkFamilia.equals(other.pkFamilia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Código" + pkFamilia;
    }
    
}
