/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unito.model;

import com.unito.model.UserDetails.UserDetails;
import com.unito.model.repository.TableRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;



@Component
@Scope(value="session",
proxyMode=ScopedProxyMode.TARGET_CLASS)
public class UserSession {
    
    TableRepository tableRepository;
    private UserDetails userdetails;
    private List<Table> tables;
    private List<Table> openTables;

    @Autowired
    public UserSession(TableRepository tableRepository) {
        this.tableRepository = tableRepository;
    }

    public List<Table> getOpenTables() {
        return openTables;
    }

    public void setOpenTables(List<Table> openTables) {
        this.openTables = openTables;
    }

    
    public TableRepository getTableRepository() {
        return tableRepository;
    }

    public void setTableRepository(TableRepository tableRepository) {
        this.tableRepository = tableRepository;
    }

    public UserDetails getUserdetails() {
        return userdetails;
    }

    public void setUserdetails(UserDetails userdetails) {
        this.userdetails = userdetails;
        this.tables = tableRepository.getAllTables(userdetails);
    }

    public List<Table> getTables() {
        return tables;
    }

    public void setTables(List<Table> tables) {
        this.tables = tables;
    }
    
    public List<Table> getOpenedTables(){
        this.tables = tableRepository.getAllTables(userdetails);
        this.openTables = tableRepository.getAllTables(userdetails);
        List<Table> retVal = new ArrayList<>();
        for(Table t : this.tables){
            if (t.isOpened())
                retVal.add(t);
        }
        return retVal;
    }

    @Override
    public String toString() {
        return "UserSession{" + "userdetails=" + userdetails.toString() + '}';
    }

    public int registerNewTable(Table newTable) {
        newTable.setOwner(this.getUserdetails().getId());
        int newtableId = tableRepository.saveTable(newTable);
        if (newtableId != -1){
            newTable.setID(newtableId);
            newTable.setOpened(true);
            //todo insert check control from db
            tableRepository.addUserOnTable(newTable, userdetails);
        }
        this.tables.add(newTable);
        return newtableId;
    }

    public boolean closeTable(Table closeTable) {
        return tableRepository.closeTable(userdetails,closeTable);
        
    }

    
    
    
}
