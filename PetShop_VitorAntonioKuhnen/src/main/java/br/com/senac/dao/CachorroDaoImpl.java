/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.dao;

import br.com.senac.entidade.Cachorro;
import java.io.Serializable;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author Vítor
 */
public class CachorroDaoImpl extends BaseDaoImpl<Cachorro, Long> implements CachorroDao, Serializable{

    @Override
    public Cachorro askPerId(Long id, Session session) throws HibernateException {
        return session.find(Cachorro.class, id);
    }

    @Override
    public List<Cachorro> askPerName(String nome, Session session) throws HibernateException {
        Query<Cachorro> consult = session.createQuery("from Cachorro c where c.nome like :nome");
        consult.setParameter("nome", "%" + nome + "%");
        return consult.getResultList();
    }

    @Override
    public List<Cachorro> treinados(Session session) throws HibernateException {
        Query<Cachorro> consult = session.createQuery("from Cachorro c where c.treinado = TRUE");
        return consult.getResultList();
    }
    
}
