package com.acme.statusmgr.beans;

/**
 * Facade class that provides information about the current system environment
 */
public class detailsFacade implements detailsInterface {

    /**
     * @return the number of processors available
     */
    @Override
    public int availableProcessors(){
        return Runtime.getRuntime().availableProcessors();
    }

    /**
     * @return the number of free memory in the JVM
     */
    @Override
    public long freeJVMMemory(){
        return Runtime.getRuntime().freeMemory();
    }

    /**
     * @return the total amount of memory available to the JVM
     */
    @Override
    public long totalJVMMemory(){
        return Runtime.getRuntime().totalMemory();
    }

    /**
     *  @return the version of the JRE that is currently running
     */
    @Override
    public String jreVersion(){
        return System.getProperty("java.version");
    }

    /**
     * @return the path to the system's temporary directory
     */
    @Override
    public String tempLocation(){
        return System.getenv("TEMP");
    }
}
