/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sql;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.logging.Level;
import java.util.logging.Logger;

;

/**
 *
 * @author SashaAlexandru
 */
public class App {

    public static void main(String args[]) {

        String command1 = "cd C:\\Users\\SashaAlexandru\\GlassFish_Server\\javadb\\bin";

        try {
            Process pr = Runtime.getRuntime().exec("C:\\Users\\SashaAlexandru\\GlassFish_Server\\javadb\\bin\\startNetworkServer.bat");
            Process p = Runtime.getRuntime().exec("C:\\Users\\SashaAlexandru\\GlassFish_Server\\javadb\\bin\\stopNetworkServer.bat");
            OutputStream out = pr.getOutputStream();
            out = p.getOutputStream();
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }

        ClassLoader cl = ClassLoader.getSystemClassLoader();

        URL[] urls = ((URLClassLoader) cl).getURLs();

        for (URL url : urls) {
            System.out.println(url.getFile());
        }

    }
}
