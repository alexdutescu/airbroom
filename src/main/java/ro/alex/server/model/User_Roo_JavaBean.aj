// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package ro.alex.server.model;

import ro.alex.server.model.User;

privileged aspect User_Roo_JavaBean {
    
    public String User.getNick() {
        return this.nick;
    }
    
    public void User.setNick(String nick) {
        this.nick = nick;
    }
    
    public String User.getParola() {
        return this.parola;
    }
    
    public void User.setParola(String parola) {
        this.parola = parola;
    }
    
}
