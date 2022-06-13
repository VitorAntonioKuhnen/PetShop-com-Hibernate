/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.dao;

import br.com.senac.entidade.Comportamento;
import java.io.Serializable;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author Vítor
 */
public class ComportamentoDaoImpl  extends BaseDaoImpl<Comportamento, Long> implements ComportamentoDao, Serializable{

    @Override
    public Comportamento askPerId(Long id, Session session) throws HibernateException {
        return session.find(Comportamento.class, id);
    }

    @Override
    public List<Comportamento> AllComportaments(Session session) throws HibernateException {
        Query<Comportamento> consult = session.createQuery("from Comportamento c");
        return consult.getResultList();
    }
    
}
