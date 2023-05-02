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
    
    public void addItem(String key, Object value){
        data = new HashMap<String,Object>();
        data.put(key, value);
    }
}
