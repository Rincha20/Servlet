package etu2039.framework.servlet;

import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLDecoder;

import javax.servlet.*;
import javax.servlet.http.*;

import annotation.Url;

import java.util.*;

import etu2039.framework.Mapping;

public class FrontServlet extends HttpServlet {
    HashMap<String,Mapping> mappingUrls = new HashMap<>();

    public void setMappingUrls(HashMap<String, Mapping> mappingUrls) {
        this.mappingUrls = mappingUrls;
    }

    public HashMap<String, Mapping> getMappingUrls() {
        return mappingUrls;
    }

    @Override
    public void init() throws ServletException{
        try {
            Class<? extends Annotation> annotationClass = Url.class; 
            List<Class<?>> classes = new ArrayList<Class<?>>();
            List<String> packageNames = Arrays.asList("model");
            for (String packageName : packageNames) {
                List<Class<?>> annotatedClasses = findAnnotatedClasses(packageName, annotationClass);
                classes.addAll(annotatedClasses);
            }

            HashMap<String,Mapping> map = new HashMap<>();
            for (Class<?> clazz : classes) {
                Method[] methods = clazz.getDeclaredMethods();
                for (Method method : methods) {
                    map.put(method.getAnnotation(Url.class).url(), new Mapping(clazz.getName(), method.getName())); 
                }
            }
            setMappingUrls(map);
            
        } catch (Exception e) {
            System.out.println(e.getMessage());;
            System.out.println(e.getCause());;
            e.printStackTrace();
        }
    }

    public static List<Class<?>> findAnnotatedClasses(String packageName, Class<? extends Annotation> annotationClass) throws ClassNotFoundException, IOException{
        List<Class<?>> classes = new ArrayList<Class<?>>();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String path = packageName.replace('.', '/');
        Enumeration<URL> resources = classLoader.getResources(path);
        List<URL> resourceList = Collections.list(resources);
        for (URL resource : resourceList) {
            if (resource != null) {
                String file = resource.getFile();
                if (file.contains("!")) {
                    file = file.substring(0, file.indexOf("!"));
                }
                file = URLDecoder.decode(file, "UTF-8");
                File folder = new File(file);
                File[] files = folder.listFiles();

                if (files != null) {
                    for (File f : files) {
                        if (f.isFile()) {
                            String fileName = f.getName();
                            if (fileName.endsWith(".class")) {
                                String className = packageName + '.' + fileName.substring(0, fileName.length() - 6);
                                Class<?> clazz = Class.forName(className);
                                Method[] methods = clazz.getDeclaredMethods();
                                for (Method method : methods) {
                                    if (method.isAnnotationPresent(annotationClass)) {
                                        classes.add(clazz);
                                        break;
                                    }
                                }
                                if (clazz.isAnnotationPresent(annotationClass)) {
                                    classes.add(clazz);
                                }
                            }
                        } else if (f.isDirectory()) {
                            String subPackageName = packageName + '.' + f.getName();
                            List<Class<?>> subAnnotatedClasses = findAnnotatedClasses(subPackageName, annotationClass);
                            classes.addAll(subAnnotatedClasses);
                        }
                    }
                }
            }
        }
        return classes;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            String url=request.getServletPath();
            String requete=request.getQueryString();
            if (requete!=null) {
                url=url+"?"+requete;
            }
            request.setAttribute("url",url);
            response.setContentType("text/plain");
            PrintWriter out = response.getWriter();
            out.println(url);

            // if (getMappingUrls() != null) {
            try {
                for(Map.Entry<String, Mapping> entry : getMappingUrls().entrySet()){
                    String key = entry.getKey();
                    Mapping value = entry.getValue();
                    out.println(key + value.getClassName() + value.getMethod() );
                }
            } catch (Exception e) {
                // TODO: handle exception
                out.println(e.getMessage());
                out.println(e.getCause());
                e.printStackTrace(out);
            }
            // } else {
            //     out.println("erreur");
            // }
            // RequestDispatcher dispat=request.getRequestDispatcher("control.jsp");
            // dispat.forward(request,response);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}