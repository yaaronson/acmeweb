package com.acme.statusmgr.beans;

import com.acme.servermgr.ServerManager;

/**
 * A POJO that represents Server Status and can be returned as the result of a request.
 */
public class ServerStatus {


    private long id;                // Unique identifier of request, sequential number
    private String contentHeader;   // Some info about the request
    private String statusDesc = "Unknown";  // the status being returned
    private final Integer requestCost = 1;  // the cost in pennies of this request.

    /**
     * Construct a ServerStatus using info passed in for identification, and obtaining current
     * server status from the appropriate Manager class.
     * This class must return a pretty, english-like representation of the server status.
     *
     * @param id            a numeric identifier/counter of which request this is
     * @param contentHeader info about the request
     */
    public ServerStatus(long id, String contentHeader) {
        this.id = id;
        this.contentHeader = contentHeader;

        // Obtain current status of server
        this.statusDesc = "Server is " + ServerManager.getCurrentServerStatus();
    }

    public ServerStatus() {

    }

    /**
     * get the id of this request
     *
     * @return a numeric id that increases during life of server for each request .
     */
    public long getId() {
        return id;
    }

    /**
     * Get the content header that was specified by the request
     *
     * @return some string
     */
    public String getContentHeader() {
        return contentHeader;
    }

    /**
     * Get an english-like description of the server's status
     *
     * @return A string describing status
     */
    public String getStatusDesc() {
        return statusDesc;
    }

    /**
     * Get the cost of this request
     * @return Integer representing the cost of request as number of pennies
     */
    public Integer getRequestCost() {
        return requestCost;
    }

}
