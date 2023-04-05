package com.acme.statusmgr.beans;

public class totalJVMMemory implements serverInfo{

    private final serverInfo info;

    public totalJVMMemory(serverInfo info) {
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
        return  info.getStatusDesc() + "and there is a total of" + details.totalJVMMemory() + "bytes of JVM memory";
    }

    @Override
    public Integer getRequestCost() {
        return info.getRequestCost() + 13;
    }
}
