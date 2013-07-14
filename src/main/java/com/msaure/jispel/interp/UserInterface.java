package com.msaure.jispel.interp;

import com.msaure.jispel.memory.Handle;

/**
 * User: msaure
 * Date: 13.07.13
 * Time: 15:07
 */
public interface UserInterface {

    void displayPrompt();
    void displayExpression(Handle expr);
    void error(String message);
    void warning(String message);
}
