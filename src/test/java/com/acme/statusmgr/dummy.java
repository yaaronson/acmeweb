package com.acme.statusmgr;

import com.acme.statusmgr.beans.detailsInterface;

/**
 * Mock class used for unit testing
 */
public class dummy implements detailsInterface {

    @Override
    public int availableProcessors() {
        return 4;
    }

    @Override
    public long freeJVMMemory() {
        return 127268272;
    }

    @Override
    public long totalJVMMemory() {
        return 159383552;
    }

    @Override
    public String jreVersion() {
        return "15.0.2+7-27";
    }

    @Override
    public String tempLocation() {
        return "M:\\\\AppData\\\\Local\\\\Temp";
    }
}
