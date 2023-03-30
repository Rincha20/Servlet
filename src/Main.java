package run;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.io.*;
import java.util.*;
import java.net.*;

import annotation.Url;
import etu2039.framework.servlet.FrontServlet;

public class Main {
    
    public static void main(String[] args) throws ClassNotFoundException, IOException {
        Class<? extends Annotation> annotationClass = Url.class; // La classe d'annotation que vous voulez chercher
        
        List<Class<?>> classes = new ArrayList<Class<?>>();
        // Package[] packages = Package.getPackages();
        // for (Package pack : packages) {
        //     String packageName = pack.getName();
        //     List<Class<?>> annotatedClasses = findAnnotatedClasses(packageName, annotationClass);
        //     classes.addAll(annotatedClasses);
        //     // System.out.println(packageName);
        // }
        List<String> packageNames = Arrays.asList("model");
        for (String packageName : packageNames) {
            List<Class<?>> annotatedClasses = findAnnotatedClasses(packageName, annotationClass);
            classes.addAll(annotatedClasses);
        }

        for (Class<?> clazz : classes) {
            // System.out.println("La classe " + clazz.getName() + " a l'annotation " + annotationClass.getName());
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(annotationClass)) {
                    System.out.println(method);
                    System.out.println(method.getAnnotation(Url.class).url()); 
                    System.out.println(clazz.getName());
                    System.out.println(method.getName());
                }
            }
            }
    }
    
    public static List<Class<?>> findAnnotatedClasses(String packageName, Class<? extends Annotation> annotationClass) throws ClassNotFoundException, IOException{
        // System.out.println(annotationClass);
        List<Class<?>> classes = new ArrayList<Class<?>>();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        // ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        
        String path = packageName.replace('.', '/');
        Enumeration<URL> resources = classLoader.getResources(path);
        List<URL> resourceList = Collections.list(resources);
        for (URL resource : resourceList) {
            if (resource != null) {
                String file = resource.getFile();
                System.out.println(file);
                if (file.contains("!")) {
                    file = file.substring(0, file.indexOf("!"));
                }
                file = URLDecoder.decode(file, "UTF-8");
                File folder = new File(file);
                File[] files = folder.listFiles();

                if (files != null) {
                    for (File f : files) {
                        // System.out.println(f);
                        if (f.isFile()) {
                            String fileName = f.getName();
                            if (fileName.endsWith(".class")) {
                                String className = packageName + '.' + fileName.substring(0, fileName.length() - 6);
                                // System.out.println(className);
                                Class<?> clazz = Class.forName(className);
                                // System.out.println(clazz);
                                        classes.add(clazz);
                                    
                                
                                // if (clazz.isAnnotationPresent(annotationClass)) {
                                //     System.out.println(clazz);
                                //     classes.add(clazz);
                                // }
                            }
                        } else if (f.isDirectory()) {
                            String subPackageName = packageName + '.' + f.getName();
                            List<Class<?>> subAnnotatedClasses = findAnnotatedClasses(subPackageName, annotationClass);
                            for (Class<?> c : subAnnotatedClasses) {
                                System.out.println(c);
                            }
                            classes.addAll(subAnnotatedClasses);
                        }
                    }
                }
            }
        }
        return classes;
    }
}