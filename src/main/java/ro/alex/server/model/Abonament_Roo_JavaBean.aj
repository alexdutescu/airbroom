// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package ro.alex.server.model;

import ro.alex.server.model.Abonament;

privileged aspect Abonament_Roo_JavaBean {
    
    public String Abonament.getSerie() {
        return this.serie;
    }
    
    public void Abonament.setSerie(String serie) {
        this.serie = serie;
    }
    
    public Integer Abonament.getKilometri() {
        return this.kilometri;
    }
    
    public void Abonament.setKilometri(Integer kilometri) {
        this.kilometri = kilometri;
    }
    
    public Double Abonament.getCost() {
        return this.cost;
    }
    
    public void Abonament.setCost(Double cost) {
        this.cost = cost;
    }
    
}