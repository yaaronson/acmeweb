package com.acme.statusmgr.beans;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class jreVersion implements serverInfo{

    private final serverInfo info;

    public jreVersion(serverInfo info) {
        this.info = info;
    }

    private static  detailsInterface details = new detailsFacade();

    public static void setter(detailsInterface details){
        jreVersion.details = details;
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
        Logger logger = LoggerFactory.getLogger("JRE Version");
        logger.info("Requesting to get the JRE version details");
        return info.getStatusDesc() + ", and the JRE version is " + details.jreVersion();
    }

    @Override
    public Integer getRequestCost() {
        return info.getRequestCost() + 19;
    }

}
