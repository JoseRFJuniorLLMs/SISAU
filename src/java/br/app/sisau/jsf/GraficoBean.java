package br.app.sisau.jsf;

import br.app.sisau.beans.PessoaBean;
import br.app.sisau.daos.PessoaJpaDao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.ManagedBean;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author jr
 */
@ManagedBean
//@RequestScoped
public class GraficoBean implements Serializable {
    
    private PieChartModel modelo;
    private List<PessoaBean> listaPessoa;

    public GraficoBean() {
        criarModeloGrafico();
    }

    public PieChartModel getModelo() {
        return modelo;
    }

    public void setModelo(PieChartModel modelo) {
        this.modelo = modelo;
    }

    private void criarModeloGrafico() {
        
        modelo = new PieChartModel();
        List<PessoaBean> listaPessoa = new ArrayList<PessoaBean>();
        PessoaJpaDao pessoaJpaDao = new PessoaJpaDao();
        listaPessoa = pessoaJpaDao.todos();
        for (PessoaBean pessoabean : listaPessoa) {
           //modelo.set(pessoabean.getNome(),pessoabean.getSobrenome());
           // modelo.set(pessoabean.getNome(),pessoabean.getSobrenome());
        }
    }
}

    

