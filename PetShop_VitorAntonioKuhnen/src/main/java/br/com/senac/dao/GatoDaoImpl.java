/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.dao;

import br.com.senac.entidade.Cachorro;
import br.com.senac.entidade.Comportamento;
import br.com.senac.entidade.Gato;
import java.io.Serializable;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author Vítor
 */
public class GatoDaoImpl extends BaseDaoImpl<Gato, Long> implements GatoDao, Serializable{

    @Override
    public Gato askPerId(Long id, Session session) throws HibernateException {
        return session.find(Gato.class, id);
    }

    @Override
    public List<Gato> askPerName(String nome, Session session) throws HibernateException {
        Query<Gato> consult = session.createQuery("from Gato g where g.nome like :nome");
        consult.setParameter("nome", "%" + nome + "%");
        return consult.getResultList();
    }

    @Override
    public List<Gato> aksPerComportament(Comportamento comportamento, Session session) throws HibernateException {
        Query<Gato> consult = session.createQuery("From Gato g where g.comportamento = :comportamento");
        consult.setParameter("comportamento", comportamento);
        return consult.getResultList();
    }
    
}
