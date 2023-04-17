package model;

import annotation.Url;

public class Dept {

    @Url(url="dept-add")
    public void addDept(){
        System.out.println("Ajout dept"); 
    }

    @Url(url="dept-del")
    public void deleteDept(){
        System.out.println("Effacer dept"); 
    }
}
