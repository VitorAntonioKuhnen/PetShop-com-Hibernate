/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.dao;

import br.com.senac.entidade.Cachorro;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Vítor
 */
public interface CachorroDao extends BaseDao<Cachorro, Long>{
    List<Cachorro> askPerName(String nome, Session session) throws HibernateException;
    List<Cachorro> treinados(Session session) throws HibernateException;
}
