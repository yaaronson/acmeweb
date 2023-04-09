package com.acme.statusmgr.beans;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class tempLocation implements serverInfo{

    private final serverInfo info;

    public tempLocation(serverInfo info) {
        this.info = info;
    }

    private static  detailsInterface details = new detailsFacade();

    public static void setter(detailsInterface details){
        tempLocation.details = details;
    }

    @Override
    public long getId() {
        return info.getId();
    }

    @Override
    public String getContentHeader() {
        return info.getContentHeader();
    }

    @Override
    public String getStatusDesc() {
        Logger logger = LoggerFactory.getLogger("tempLocation");
        logger.info("Requesting the temp file location info");
        return info.getStatusDesc() + ", and the server's temp file location is " + details.tempLocation();
    }

    @Override
    public Integer getRequestCost() {
        return info.getRequestCost() + 29;
    }
}
