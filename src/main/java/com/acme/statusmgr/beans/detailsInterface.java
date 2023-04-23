package com.acme.statusmgr.beans;

public interface detailsInterface {

     int availableProcessors();

     long freeJVMMemory();

     long totalJVMMemory();

     String jreVersion();

     String tempLocation();
}
