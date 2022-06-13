/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.dao;

import br.com.senac.entidade.Gato;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.junit.Test;
import static br.com.senac.util.GeradorUtil.*;
import org.hibernate.query.Query;
import static org.junit.Assert.*;

/**
 *
 * @author Vítor
 */
public class GatoDaoImplTest {

    private Session session;
    private Gato gato;
    private GatoDao gatoDao;

    public GatoDaoImplTest() {
        gatoDao = new GatoDaoImpl();
    }

    @Test
    public void testSave() {
        System.out.println("Save");
        ComportamentoDaoImplTest cdit = new ComportamentoDaoImplTest();
        Date data = new Date();
        gato = new Gato(false, false, gerarNome(), data, 1.7, "Macho", "Só Arranha");
        gato.setComportamento(cdit.buscarComportamentoBd());
        session = HibernateUtil.abrirConexao();
        gatoDao.saveOrAlter(gato, session);
        session.close();

        assertNotNull(gato.getId());
    }

//    @Test
    public void testAlter() {
        System.out.println("Alterar");
        buscarGatoBd();
        gato.setFelv(true);
        gato.setObservacao("Possui marcas de bom tratos e de extrema gordura");
        session = HibernateUtil.abrirConexao();
        gatoDao.saveOrAlter(gato, session);

        Gato gatoAlt = gatoDao.askPerId(gato.getId(), session);
        session.close();

        assertEquals(gato.getObservacao(), gatoAlt.getObservacao());
    }

//    @Test
    public void testDelete() {
        System.out.println("Delete");
        buscarGatoBd();
        session = HibernateUtil.abrirConexao();
        gatoDao.delet(gato, session);

        Gato gatoDel = gatoDao.askPerId(gato.getId(), session);
        session.close();

        assertNull(gatoDel);
    }

//    @Test
    public void testAskPerId() {
        System.out.println("askPerId");
        buscarGatoBd();
        session = HibernateUtil.abrirConexao();
        Gato gatoPerId = gatoDao.askPerId(gato.getId(), session);
        session.close();
        assertNotNull(gatoPerId);

        System.out.println("Nome: " + gatoPerId.getNome());
    }

//    @Test
    public void testAskPerName() {
        System.out.println("askPerName");
        buscarGatoBd();
        session = HibernateUtil.abrirConexao();
        List<Gato> gatoDados = gatoDao.askPerName(gato.getNome(), session);
        session.close();
        assertFalse(gatoDados.isEmpty());
//        Pega todos os cachorros com o mesmo nome
        for (Gato gatoDado : gatoDados) {
            System.out.println("Nome: " + gatoDado.getNome() + " Descição: " + gatoDado.getComportamento().getDescricao() + " Tipo: " + gatoDado.getComportamento().getTipo());

        }
    }

//    @Test
    public void testBuscarGatoPorTipo() {
        System.out.println("BuscarGatoPorTipo");
        buscarGatoBd();
        System.out.println(gato.getComportamento().getTipo());
        session = HibernateUtil.abrirConexao();
        List<Gato> gatoTip = gatoDao.aksPerComportament(gato.getComportamento(), session);
        session.close();
        for (Gato gato1 : gatoTip) {
            System.out.println(gato1.getId() + " " + gato1.getComportamento().getId() + " " + gato1.getComportamento().getTipo());
        }

    }

    public Gato buscarGatoBd() {
        session = HibernateUtil.abrirConexao();
        Query<Gato> consult = session.createQuery("from Gato g");
        List<Gato> gatos = consult.getResultList();
        session.close();
        if (gatos.isEmpty()) {
            testSave();
        } else {
            gato = gatos.get(0);
        }
        return gato;
    }

}
