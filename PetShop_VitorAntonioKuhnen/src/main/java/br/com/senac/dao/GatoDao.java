/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.dao;

import br.com.senac.entidade.Comportamento;
import br.com.senac.entidade.Gato;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Vítor
 */
public interface GatoDao extends BaseDao<Gato, Long>{
    List<Gato> askPerName(String nome, Session session) throws HibernateException;
    List<Gato> aksPerComportament(Comportamento comportamento, Session session) throws HibernateException;
}
