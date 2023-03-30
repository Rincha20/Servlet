package model;

import annotation.Url;

public class Dept {

    @Url(url="dept-all")
    public void addDept(){
        System.out.println("Ajout dept"); 
    }
}
