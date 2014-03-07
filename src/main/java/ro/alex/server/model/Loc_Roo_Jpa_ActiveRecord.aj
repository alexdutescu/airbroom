// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package ro.alex.server.model;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;
import ro.alex.server.model.Loc;

privileged aspect Loc_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager Loc.entityManager;
    
    public static final EntityManager Loc.entityManager() {
        EntityManager em = new Loc().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long Loc.countLocs() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Loc o", Long.class).getSingleResult();
    }
    
    public static List<Loc> Loc.findAllLocs() {
        return entityManager().createQuery("SELECT o FROM Loc o", Loc.class).getResultList();
    }
    
    public static Loc Loc.findLoc(Long id) {
        if (id == null) return null;
        return entityManager().find(Loc.class, id);
    }
    
    public static List<Loc> Loc.findLocEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Loc o", Loc.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void Loc.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void Loc.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            Loc attached = Loc.findLoc(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void Loc.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void Loc.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public Loc Loc.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Loc merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}