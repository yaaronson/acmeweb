package com.acme.statusmgr.beans;

public interface serverInfo {

    /**
     * @return the ID associated with this object.
     */
    long getId();

    /**
     * @return the content header associated with this object.
     */
    String getContentHeader();

    /**
     * @return the status description associated with this object.
     */
    String getStatusDesc();

    /**
     * @return the cost of the request associated with this object as an Integer.
     */
    Integer getRequestCost();


}
