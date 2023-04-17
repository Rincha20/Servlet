package model;

import annotation.Url;
import etu2039.framework.vue.View;

public class Dept {

    @Url(url="dept-add")
    public View addDept(){
        return new View("views/test.jsp");
    }

    @Url(url="dept-del")
    public void deleteDept(){
        System.out.println("Effacer dept"); 
    }
}
