/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.dao;

import br.com.senac.entidade.Comportamento;
import br.com.senac.entidade.Gato;
import java.util.List;
import org.hibernate.Session;
import org.junit.Test;
import static br.com.senac.util.GeradorUtil.*;
import org.hibernate.Hibernate;
import org.hibernate.query.Query;
import static org.junit.Assert.*;

/**
 *
 * @author Vítor
 */
public class ComportamentoDaoImplTest {

    private Session session;
    private Comportamento comportamento;
    private ComportamentoDao comportamentoDao;

    public ComportamentoDaoImplTest() {
        comportamentoDao = new ComportamentoDaoImpl();
        
    }

    @Test
    public void testSalve() {
        System.out.println("Salvar");
        comportamento = new Comportamento(gerarTipo(), gerarAleatorio(), false);
        session = HibernateUtil.abrirConexao();
        comportamentoDao.saveOrAlter(comportamento, session);
        session.close();
        assertNotNull(comportamento.getId());
    }

//    @Test
    public void testAlter() {
        System.out.println("Alterar");
        buscarComportamentoBd();
        comportamento.setTipo("Traquilo hoje");
        comportamento.setDescricao("Hoje estava de boa não mordeu ninguém");
        session = HibernateUtil.abrirConexao();
        comportamentoDao.saveOrAlter(comportamento, session);

        Comportamento comportamentoAlt = comportamentoDao.askPerId(comportamento.getId(), session);
        session.close();

        assertEquals(comportamento.getDescricao(), comportamentoAlt.getDescricao());
    }

//    @Test
    public void testDelete() {
        System.out.println("Delete");
        buscarComportamentoBd();
        session = HibernateUtil.abrirConexao();
        comportamentoDao.delet(comportamento, session);
        Comportamento comportamentoDel = comportamentoDao.askPerId(comportamento.getId(), session);
        session.close();

        assertNull(comportamentoDel);
    }

//    @Test
    public void testAllComportaments() {
        System.out.println("AllComportaments");
        session = HibernateUtil.abrirConexao();
        List<Comportamento> comportamentosBd = comportamentoDao.AllComportaments(session);
        session.close();
        
        for (Comportamento comport : comportamentosBd) {
            System.out.println("Tipo: " + comport.getTipo() + " Descrição: " + comport.getDescricao());
        }

    }
    
//    @Test
    public void testAskPerId(){
        System.out.println("AskPerId");
        buscarComportamentoBd();
        session = HibernateUtil.abrirConexao();
        Comportamento comportPerId = comportamentoDao.askPerId(comportamento.getId(), session);
        session.close();
        assertNotNull(comportPerId);

        System.out.println("Tipo: " + comportPerId.getTipo() + " Descrição: " + comportPerId.getDescricao());
    }


    public Comportamento buscarComportamentoBd() {
        session = HibernateUtil.abrirConexao();
        Query<Comportamento> consult = session.createQuery("from Comportamento c");
        List<Comportamento> comportamentos = consult.getResultList();
        session.close();
        if (comportamentos.isEmpty()) {
            testSalve();
        } else {
            comportamento = comportamentos.get(0);
        }
        return comportamento;
    }

}
