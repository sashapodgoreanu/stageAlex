/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unito.model;

import com.unito.TagView;
import com.unito.model.UserDetails.UserDetails;
import com.unito.model.repository.PropertieRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 *
 * @author Alexandru Podgoreanu
 */
@Component
@Scope(value = "request",
        proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TagPersonal implements TagView {

    @Autowired
    PropertieRepository pr;
    @Autowired
    TagLiked tl;
    @Autowired
    TagUnLiked tul;
    private String objId;//Object ID
    private String forUserId;//UserDetails ID

    public TagPersonal() {
    }

    public String getObjId() {
        return objId;
    }

    public void setObjId(String objId) {
        this.objId = objId;
    }

    public String getForUserId() {
        return forUserId;
    }

    public void setForUserId(String forUserId) {
        this.forUserId = forUserId;
    }

    @Override
    public List<Propertie> getTags() {
        List<Propertie> personal = pr.getPersonalProperties(forUserId, objId);
        List<Propertie> liked = tl.getTags();
        //add to personal tags all liked tags
        for (Propertie l : liked) {
            boolean trovato = false;
            for (Propertie p : personal) {
                if (p.getId() == l.getId()) {
                    trovato = true;
                }
            }
            if (!trovato) {
                personal.add(l);
            }
        }
        return personal;
    }

}
