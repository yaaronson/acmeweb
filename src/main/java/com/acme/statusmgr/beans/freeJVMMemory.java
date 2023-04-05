package com.acme.statusmgr.beans;

public class freeJVMMemory implements serverInfo{

    private final serverInfo info;

    public freeJVMMemory(serverInfo info) {
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
        return info.getStatusDesc() + "and there are" + details.freeJVMMemory() +  " bytes of JVM memory free";
    }

    @Override
    public Integer getRequestCost() {
        return info.getRequestCost() + 7;
    }
}
