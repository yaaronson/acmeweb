package com.acme.statusmgr.beans;

public class availableProcessors implements serverInfo {

    private final serverInfo info;

    private static  detailsInterface details = new detailsFacade();

    public static void setter(detailsInterface details){

        availableProcessors.details = details;
    }

    public availableProcessors(serverInfo info) {
        this.info = info;
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
        return info.getStatusDesc() + ", and there are " + details.availableProcessors() + " processors available";
    }

    @Override
    public Integer getRequestCost() {
        return info.getRequestCost() + 3;
    }
}
