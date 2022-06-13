/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Vítor
 * @param <T>
 * @param <ID>
 */
public interface BaseDao<T, ID> {
    void saveOrAlter(T entidade, Session session) throws HibernateException;
    
    void delet (T entidade, Session session) throws HibernateException;
    
    T askPerId (ID id, Session session) throws HibernateException;
}
