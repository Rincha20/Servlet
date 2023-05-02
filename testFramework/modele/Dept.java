package model;

import java.util.ArrayList;

import annotation.Url;
import etu2039.framework.vue.View;

public class Dept {
    String nomDept;
    String manager;

    public Dept(){}

    public Dept(String nomDept, String manager) {
        setNomDept(nomDept);
        setManager(manager);
    }

    @Url(url="dept-all")
    public View allDept(){
        Dept d1= new Dept("Departement 1", "RAKOTO");
        Dept d2 = new Dept("Departement 2", "RABE");
        ArrayList<Dept> listDept = new ArrayList<Dept>();
        listDept.add(d1);
        listDept.add(d2);
        View vue = new View("views/test.jsp");
        vue.addItem("listDept", listDept);
        return vue;
    }

    // @Url(url="dept-del")
    // public void deleteDept(){
    //     System.out.println("Effacer dept"); 
    // }

    public String getNomDept() {
        return nomDept;
    }

    public void setNomDept(String nomDept) {
        this.nomDept = nomDept;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }
}
