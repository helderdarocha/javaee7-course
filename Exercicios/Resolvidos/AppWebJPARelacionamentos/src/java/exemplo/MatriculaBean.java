/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exemplo;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author helderdarocha
 */
@ManagedBean
@RequestScoped
public class MatriculaBean {
    @PersistenceContext(unitName = "AppWebJPARelacionamentosPU")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;

    
    
    public String getMatricula() {
        Aluno a1 = new Aluno();
        Turma t1 = new Turma();
        
        a1.setNome("Helder");
        t1.setCodigo("T123");
        
        a1.addTurma(t1);
        t1.addAluno(a1);
        
        List<Aluno> alunos = new ArrayList<>();
        t1.setAlunos(alunos);
        
        merge(a1);
        merge(t1);
        
        return "funcionou";
    }

    public void merge(Object object) {
        try {
            utx.begin();
            em.merge(object);
            utx.commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            throw new RuntimeException(e);
        }
    }
    
}
