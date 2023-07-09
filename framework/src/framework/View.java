package etu2039.framework.vue;

import java.util.HashMap;

public class View {
    String view;
    HashMap<String,Object> data;

    public View(){}

    public View(String view) {
        this.setView(view);
    }

    public void setView(String view) {
        this.view = view;
    }

    public String getView() {
        return this.view;
    }

    public HashMap<String, Object> getData() {
        return data;
    }

    public void setData(HashMap<String, Object> data) {
        this.data = data;
    }
    
    //le KEY misy anle nom d'attribut (ex: listDept) a getter (request.getAttribute("listDept")) any @ test.jsp 
    //d le VALUE misy anle liste any dept mi-correspondre @ le listDept 
    //any @ class Dpet anle test no mameno anty addItem ty (vue.additem('listDept', listAnyDept)), d le 'listDept' io zany zao le nom d'attribut ao @ KEY 
    //d reo rehetra reo (key, value) no miditra @ HashMap data anty class View ty
    //d ny valeur ao @ le data ndray no alaina any @ FrontServlet (request.setAttribute(data.getKey(), data.getValue());)
    public void addItem(String key, Object value){
        data = new HashMap<String,Object>();
        data.put(key, value);
    }
}
