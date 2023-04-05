package com.acme.statusmgr.beans;

public class detailsFacade {

    public int availableProcessors(){
        return Runtime.getRuntime().availableProcessors();
    }

    public long freeJVMMemory(){
        return Runtime.getRuntime().freeMemory();
    }

    public long totalJVMMemory(){
        return Runtime.getRuntime().totalMemory();
    }

    public String jreVersion(){
        return System.getProperty("java.version");
    }

    public String tempLocation(){
        return System.getenv("TEMP");
    }
}
