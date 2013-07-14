package com.msaure.jispel.interp;

import com.msaure.jispel.memory.Handle;

/**
 * User: msaure
 * Date: 13.07.13
 * Time: 15:10
 */
public class BatchExecutionInterface implements UserInterface {

    @Override
    public void displayPrompt() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void displayExpression(Handle expr) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void error(String message) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void warning(String message) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
