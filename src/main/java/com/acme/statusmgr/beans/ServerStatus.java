package com.acme.statusmgr.beans;

import com.acme.servermgr.ServerManager;

/**
 * A POJO that represents Server Status and can be returned to Spring as the result of a request.
 */
public class ServerStatus implements serverInfo {
    private long id;                // Unique identifier of request, sequential number
    private String contentHeader;   // Some info about the request
    /**
     * requestCost constant will have to be changed if we change costs or calc dynamically
     */
    private final Integer requestCost = 1;  // the cost in pennies of this request.

    /**
     * Construct a ServerStatus using info passed in for identification.
     * This class must return a pretty, english-like representation of the server status.
     *
     * @param id            a numeric identifier/counter of which request this is
     * @param contentHeader info about the request
     */
    public ServerStatus(long id, String contentHeader) {
        this.id = id;
        this.contentHeader = contentHeader;
    }

    public ServerStatus() {

    }

    /**
     * get the id of this request
     *
     * @return a numeric id that increases during life of server for each request .
     */
    @Override
    public long getId() {
        return id;
    }

    /**
     * Get the content header that was specified by the request
     *
     * @return some string
     */
    @Override
    public String getContentHeader() {
        return contentHeader;
    }

    /**
     * Get an english-like description of the server's current status,
     * obtained from the appropriate Manager class.
     *
     * @return A string describing status
     */
    @Override
    public String getStatusDesc() {
        // Obtain current status of server
        return "Server is " + ServerManager.getCurrentServerStatus();
    }

    /**
     * Get the cost of this request
     * @return Integer representing the cost of request as number of pennies
     */
    @Override
    public Integer getRequestCost() {
        return requestCost;
    }

}
