/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unito.model.UserDetails;

import java.util.Arrays;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.springframework.stereotype.Component;

/**
 *
 * @author SashaAlexandru
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDetails {

    private String id;

    private String verified;

    private Name name;

    private String circledByCount;
    
    private String etag;

    private Image image;
    
    private Emails[] emails;

    private String isPlusUser;

    private String kind;

    private String url;

    private String gender;

    private String language;

    private String displayName;

    private String objectType;
    
    private String idtoken;
    
    private String access_token;

    public UserDetails() {
        System.out.println("INSIDE");
    }

    
    public Emails[] getEmails() {
        return emails;
    }

    public void setEmails(Emails[] emails) {
        this.emails = emails;
    }

    
    
    public String getAccesstoken() {
        return access_token;
    }

    public void setAccesstoken(String accesstoken) {
        this.access_token = accesstoken;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getIsPlusUser() {
        return isPlusUser;
    }

    public void setIsPlusUser(String isPlusUser) {
        this.isPlusUser = isPlusUser;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVerified() {
        return verified;
    }

    public void setVerified(String verified) {
        this.verified = verified;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public String getCircledByCount() {
        return circledByCount;
    }

    public void setCircledByCount(String circledByCount) {
        this.circledByCount = circledByCount;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public String getIdtoken() {
        return idtoken;
    }

    public void setIdtoken(String idtoken) {
        this.idtoken = idtoken;
    }

    @Override
    public String toString() {
        return "UserDetails{" + "id=" + id + ", verified=" + verified + ", name=" + name + ", circledByCount=" + circledByCount + ", etag=" + etag + ", image=" + image + ", emails=" + Arrays.toString(emails) + ", isPlusUser=" + isPlusUser + ", kind=" + kind + ", url=" + url + ", gender=" + gender + ", language=" + language + ", displayName=" + displayName + ", objectType=" + objectType + ", idtoken=" + idtoken + ", accesstoken=" + access_token + '}';
    }
    
}
