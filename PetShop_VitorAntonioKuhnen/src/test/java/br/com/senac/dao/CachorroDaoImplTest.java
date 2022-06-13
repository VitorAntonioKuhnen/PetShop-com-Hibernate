/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.dao;

import br.com.senac.entidade.Cachorro;
import br.com.senac.entidade.Comportamento;
import java.util.List;
import org.hibernate.Session;
import org.junit.Test;
import static br.com.senac.util.GeradorUtil.*;
import java.util.ArrayList;
import java.util.Date;
import org.hibernate.query.Query;
import static org.junit.Assert.*;

/**
 *
 * @author Vítor
 */
public class CachorroDaoImplTest {

    private Cachorro cachorro;
    private Session session;
    private CachorroDao cachorroDao;

    public CachorroDaoImplTest() {
        cachorroDao = new CachorroDaoImpl();
    }

//    @Test
    public void testSave() {
        System.out.println("Save");
        ComportamentoDaoImplTest cdit = new ComportamentoDaoImplTest();
        Date data = new Date();
        cachorro = new Cachorro(false, gerarNome(), data, 2.4, "Macho", gerarAleatorio());
        cachorro.setComportamento(cdit.buscarComportamentoBd());
        session = HibernateUtil.abrirConexao();
        cachorroDao.saveOrAlter(cachorro, session);
        session.close();

        assertNotNull(cachorro.getId());
    }

//    @Test
    public void testAlter() {
        System.out.println("Alterar");
        buscarCachorroBd();
        cachorro.setTreinado(true);
        cachorro.setObservacao("Possui marcas de bom tratos");
        session = HibernateUtil.abrirConexao();
        cachorroDao.saveOrAlter(cachorro, session);

        Cachorro cachorroAlt = cachorroDao.askPerId(cachorro.getId(), session);
        session.close();

        assertEquals(cachorro.getObservacao(), cachorroAlt.getObservacao());
    }

//    @Test
    public void testDelete() {
        System.out.println("Delete");
        buscarCachorroBd();
        session = HibernateUtil.abrirConexao();
        cachorroDao.delet(cachorro, session);

        Cachorro cachorroDel = cachorroDao.askPerId(cachorro.getId(), session);
        session.close();

        assertNull(cachorroDel);
    }

//    @Test
    public void testAskPerId() {
        System.out.println("AskPerId");
        buscarCachorroBd();
        session = HibernateUtil.abrirConexao();
        Cachorro cachorroPerId = cachorroDao.askPerId(cachorro.getId(), session);
        session.close();
        assertNotNull(cachorroPerId);

        System.out.println("Nome: " + cachorroPerId.getNome());
    }

//    @Test
    public void testAskPerName() {
        System.out.println("askPerName");
        buscarCachorroBd();
        session = HibernateUtil.abrirConexao();
        List<Cachorro> cachorrosDados = cachorroDao.askPerName(cachorro.getNome(), session);
        session.close();
        assertFalse(cachorrosDados.isEmpty());
//        Pega todos os cachorros com o mesmo nome
        for (Cachorro cachorrosDado : cachorrosDados) {
                System.out.println("Nome: " + cachorrosDado.getNome() + " Descição: " + cachorrosDado.getComportamento().getDescricao() + " Tipo: " + cachorrosDado.getComportamento().getTipo());
            }
    }

//    @Test
    public void testCachorrosTreinados() {
        System.out.println("CachorrosTreinados");
        session = HibernateUtil.abrirConexao();
        List<Cachorro> treinados = cachorroDao.treinados(session);
        session.close();

        assertNotNull(treinados.isEmpty());
        
//        Apresenta todos os cachorros que são Treinados
        for (Cachorro treinado : treinados) {
                System.out.println("Nome: " + treinado.getNome() + " Descição: " + treinado.getComportamento().getDescricao() + " Tipo: " + treinado.getComportamento().getTipo());
        }

    }

    public Cachorro buscarCachorroBd() {
        session = HibernateUtil.abrirConexao();
        Query<Cachorro> consult = session.createQuery("from Cachorro c");
        List<Cachorro> cachorros = consult.getResultList();
        session.close();
        if (cachorros.isEmpty()) {
            testSave();
        } else {
            cachorro = cachorros.get(0);
        }
        return cachorro;
    }

}
