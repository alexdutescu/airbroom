// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package ro.alex.server.model;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;
import ro.alex.server.model.Persoana;

privileged aspect Persoana_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager Persoana.entityManager;
    
    public static final EntityManager Persoana.entityManager() {
        EntityManager em = new Persoana().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long Persoana.countPersoanas() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Persoana o", Long.class).getSingleResult();
    }
    
    public static List<Persoana> Persoana.findAllPersoanas() {
        return entityManager().createQuery("SELECT o FROM Persoana o", Persoana.class).getResultList();
    }
    
    public static Persoana Persoana.findPersoana(Long id) {
        if (id == null) return null;
        return entityManager().find(Persoana.class, id);
    }
    
    public static List<Persoana> Persoana.findPersoanaEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Persoana o", Persoana.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void Persoana.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void Persoana.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            Persoana attached = Persoana.findPersoana(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void Persoana.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void Persoana.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public Persoana Persoana.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Persoana merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
