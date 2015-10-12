/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sql;

import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;

;

/**
 *
 * @author SashaAlexandru
 */
public class App {

    public static void main(String args[]) {
        Map<String,String> gs = new HashMap<>();
        gs.put("keysss", "valuess");
        Gson gson = new Gson();
        
        System.out.println(gson.toJson(gs));;
    }
}
