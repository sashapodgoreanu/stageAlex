/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sql;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

;

/**
 *
 * @author SashaAlexandru
 */
public class App {

    public static void main(String args[]) {
        List<Integer> a = new ArrayList<Integer>();
        a.add(1);
        a.add(2);
        a.add(3);
        a.add(4);
        Iterator i = a.iterator();
        i.next();
        i.remove();
        i.next();
        i.remove();

    }
}
