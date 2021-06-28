package Compulsory;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLOutput;

/*
* Reflection
Create an application to analyze and test java classes.
The application will receive as input java classes and it will display their prototypes and perform the tests specified by the @Test annotation.

The main specifications of the application are:

Compulsory (1p)

The input will be a .class file, located anywhere in the file system.
Load the specified class in memory, identifying dynamically its package.
Using reflection, extract as many information about the class (at least its methods).
Using reflection, invoke the static methods, with no arguments, annotated with @Test.

* */
public class Main {
// main folder absolut , package
    public static void  main(String[] args) throws MalformedURLException, ClassNotFoundException {
        String absolutePath = "C:\\Users\\Robert\\OneDrive\\Documents\\GitHub\\Laborator12PA\\out\\production\\Laborator12PA\\Compulsory\\newpackage\\ClassDemo";
        boolean found = false;
        String pathToJarOrClasses = absolutePath.substring(0, absolutePath.lastIndexOf("\\"));
        String packageAndClass = absolutePath.substring(absolutePath.lastIndexOf("\\") + 1);

        try {
            File path = new File(pathToJarOrClasses);
            if (path.exists()) {
                URL url = path.toURI().toURL();
                MyClassloader classLoader = new MyClassloader();
                classLoader.addURL(url);
                Class cls = Class.forName(packageAndClass);
                System.out.println("Path: " + pathToJarOrClasses);
                System.out.println("PackageAndClass: " + packageAndClass);
            }
        } catch (ClassNotFoundException ex) {
            while (found == false) {
                try {
                    packageAndClass = pathToJarOrClasses.substring(pathToJarOrClasses.lastIndexOf("\\") + 1) + "." + packageAndClass;
                    pathToJarOrClasses = pathToJarOrClasses.substring(0, pathToJarOrClasses.lastIndexOf("\\"));
                    File path = new File(pathToJarOrClasses);
                    if (path.exists()) {
                        URL url = path.toURI().toURL();
                        MyClassloader classLoader = new MyClassloader();
                        classLoader.addURL(url);
                        Class cls = Class.forName(packageAndClass);
                        System.out.println("Classname: "+cls.getName());
                        System.out.println("Path: " + pathToJarOrClasses);
                        System.out.println("PackageAndClass: " + packageAndClass);
                        System.out.println("Methods:");
                        for(var method:Class.forName(packageAndClass).getMethods()){
                            System.out.printf("%s %s(",method.getReturnType(),method.getName());
                            for(var parameter:method.getParameterTypes()){
                                System.out.printf(" %s ",parameter.getCanonicalName());
                            }
                            System.out.printf(")\n");
                        }
                        System.out.println("\n\n\n");
                        System.out.println("Test methods:");
                        for(var method:Class.forName(packageAndClass).getMethods()){
                            if(method.isAnnotationPresent(Test.class)){
                                try{
                                    method.invoke(null);
                                }catch(Throwable exception){
                                    System.out.println(exception.getMessage());
                                }
                            }
                        }
                        break;
                    }
                } catch (ClassNotFoundException e) {
                }
            }
        }
    }

//        String pathToJarOrClasses="C:\\Test\\Laborator10PA_Server-master\\target\\classes";//\\Compulsory\\ClientThread.class";
//
//        File path = new File(pathToJarOrClasses);
//        if(path.exists()) {
//            URL url = path.toURI().toURL();
//            MyClassloader classLoader = new MyClassloader();
//            classLoader.addURL(url);
//            try {
//               // Class cls = Class.forName("Compulsory.Test.ClassDemo");
//                Class cls = Class.forName("Compulsory.MyClassloader");
//                // returns the ClassLoader object
//                ClassLoader cLoader = cls.getClassLoader();
//
//         /* returns the Class object associated with the class or interface
//            with the given string name, using the given classloader. */
//                Class cls2 = Class.forName("java.lang.Thread", true, cLoader);
//
//                // returns the name of the class
//                System.out.println("Class = " + cls.getName());
//                System.out.println("Class = " + cls2.getName());
//            } catch (ClassNotFoundException ex) {
//                System.out.println(ex.toString());
//            }
//        }

  //  }
}
