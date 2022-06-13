/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.entidade;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 *
 * @author Vítor
 */
@Entity
@Table (name = "cachorro")
@PrimaryKeyJoinColumn(name = "id_animal")
public class Cachorro extends Animal{
    
    @Column (nullable = false)
    private boolean treinado;

    public Cachorro() {
    }

    public Cachorro(boolean treinado, String nome, Date nascimento, double peso, String sexo, String observacao) {
        super(nome, nascimento, peso, sexo, observacao);
        this.treinado = treinado;
    }

    public boolean isTreinado() {
        return treinado;
    }

    public void setTreinado(boolean treinado) {
        this.treinado = treinado;
    }
    
    
}
