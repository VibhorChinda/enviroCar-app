package org.envirocar.app.views.carselection.models;

import java.util.ArrayList;

public class ManufactureObject {

    private ArrayList<Links> links;
    private String name;
    private String hsn;


    public ManufactureObject() {
    }

    public ManufactureObject(ArrayList<Links> links, String name, String hsn) {
        this.links = links;
        this.name = name;
        this.hsn = hsn;
    }

    public ArrayList<Links> getLinks() {
        return links;
    }

    public void setLinks(ArrayList<Links> links) {
        this.links = links;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHsn() {
        return hsn;
    }

    public void setHsn(String hsn) {
        this.hsn = hsn;
    }
}
