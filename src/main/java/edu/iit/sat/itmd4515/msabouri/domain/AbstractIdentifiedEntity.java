/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.msabouri.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Milad
 */
@MappedSuperclass
public class AbstractIdentifiedEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    public AbstractIdentifiedEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
}
