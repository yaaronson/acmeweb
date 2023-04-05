package com.acme.statusmgr.beans;

public class jreVersion implements serverInfo{

    private final serverInfo info;

    public jreVersion(serverInfo info) {
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
        return info.getStatusDesc() + "and the JRE version is" + details.jreVersion();
    }

    @Override
    public Integer getRequestCost() {
        return info.getRequestCost() + 19;
    }

}
