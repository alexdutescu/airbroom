// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package ro.alex.server.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import ro.alex.server.model.Oras;

privileged aspect Oras_Roo_Jpa_Entity {
    
    declare @type: Oras: @Entity;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long Oras.id;
    
    @Version
    @Column(name = "version")
    private Integer Oras.version;
    
    public Long Oras.getId() {
        return this.id;
    }
    
    public void Oras.setId(Long id) {
        this.id = id;
    }
    
    public Integer Oras.getVersion() {
        return this.version;
    }
    
    public void Oras.setVersion(Integer version) {
        this.version = version;
    }
    
}
