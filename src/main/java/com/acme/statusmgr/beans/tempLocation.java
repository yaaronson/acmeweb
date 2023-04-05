package com.acme.statusmgr.beans;

public class tempLocation implements serverInfo{

    private final serverInfo info;

    public tempLocation(serverInfo info) {
        this.info = info;
    }

    private static final detailsFacade details = new detailsFacade();

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
        return info.getStatusDesc() + ", and the server's temp file location is " + details.tempLocation();
    }

    @Override
    public Integer getRequestCost() {
        return info.getRequestCost() + 29;
    }
}
