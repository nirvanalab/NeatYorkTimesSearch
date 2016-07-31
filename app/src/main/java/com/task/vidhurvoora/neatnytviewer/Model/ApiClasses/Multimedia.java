
package com.task.vidhurvoora.neatnytviewer.Model.ApiClasses;

import org.parceler.Parcel;

//@Generated("org.jsonschema2pojo")
@Parcel
public class Multimedia {

    private int width;
    private String url;
    private int height;
    private String subtype;
  //  private Legacy legacy;
    private String type;
//    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private String baseUrl = "http://nytimes.com/";
    /**
     * 
     * @return
     *     The width
     */
    public int getWidth() {
        return width;
    }

    /**
     * 
     * @param width
     *     The width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * 
     * @return
     *     The url
     */
    public String getUrl() {

        if ( url == null ) {
            return null;
        }
        return baseUrl+url;
    }

    /**
     * 
     * @param url
     *     The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 
     * @return
     *     The height
     */
    public int getHeight() {
        return height;
    }

    /**
     * 
     * @param height
     *     The height
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * 
     * @return
     *     The subtype
     */
    public String getSubtype() {
        return subtype;
    }

    /**
     * 
     * @param subtype
     *     The subtype
     */
    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    /**
     * 
     * @return
     *     The legacy
     */
//    public Legacy getLegacy() {
//        return legacy;
//    }

    /**
     * 
     * @param legacy
     *     The legacy
     */
//    public void setLegacy(Legacy legacy) {
//        this.legacy = legacy;
//    }

    /**
     * 
     * @return
     *     The type
     */
    public String getType() {
        return type;
    }

    /**
     * 
     * @param type
     *     The type
     */
    public void setType(String type) {
        this.type = type;
    }

//    public Map<String, Object> getAdditionalProperties() {
//        return this.additionalProperties;
//    }

//    public void setAdditionalProperty(String name, Object value) {
//        this.additionalProperties.put(name, value);
//    }

}
