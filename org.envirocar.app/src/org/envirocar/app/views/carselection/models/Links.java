package org.envirocar.app.views.carselection.models;

public class Links {

    private String href;
    private String type;
    private String title;
    private String rel;


    public Links() {
    }

    public Links(String href, String type, String title, String rel) {
        this.href = href;
        this.type = type;
        this.title = title;
        this.rel = rel;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }
}
