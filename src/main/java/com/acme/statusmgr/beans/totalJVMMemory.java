package com.acme.statusmgr.beans;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class totalJVMMemory implements serverInfo{

    private final serverInfo info;

    public totalJVMMemory(serverInfo info) {
        this.info = info;
    }

    private static detailsInterface details = new detailsFacade();

    public static void setter(detailsInterface details){
        totalJVMMemory.details = details;
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
        Logger logger = LoggerFactory.getLogger("totalJVMMemory");
        logger.info("Requesting the total amount of bytes for the JVM memory ");
        return  info.getStatusDesc() + ", and there is a total of " + details.totalJVMMemory() + " bytes of JVM memory";
    }

    @Override
    public Integer getRequestCost() {
        return info.getRequestCost() + 13;
    }
}
