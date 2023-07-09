package model;

import java.util.ArrayList;

import annotation.Url;
import etu2039.framework.vue.View;

public class Dept {
    String nom;
    String manager;

    public Dept(){}

    public Dept(String nom, String manager) {
        setnom(nom);
        setmanager(manager);
    }

    @Url(url="dept-all")
    public View allDept(){
        Dept d1= new Dept("Departement 1", "RAKOTO");
        Dept d2 = new Dept("Departement 2", "RABE");
        Dept d3 = new Dept(this.getnom(), this.getmanager());
        ArrayList<Dept> listDept= new ArrayList<Dept>();
        listDept.add(d1);
        listDept.add(d2);
        listDept.add(d3);
        View vue = new View("test.jsp");
        vue.addItem("listDept", listDept);
        return vue;
    }

    // @Url(url="dept-del")
    // public void deleteDept(){
    //     System.out.println("Effacer dept"); 
    // }

    @Url(url="addDept")
    public void save(){
       System.out.println("nom: "+ getnom());
       System.out.println("manager: "+ getmanager());
    }

    public String getnom() {
        return nom;
    }

    public void setnom(String nom) {
        this.nom = nom;
    }

    public String getmanager() {
        return manager;
    }

    public void setmanager(String manager) {
        this.manager = manager;
    }
}
