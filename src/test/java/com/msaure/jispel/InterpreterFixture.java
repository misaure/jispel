package com.msaure.jispel;

import com.msaure.jispel.interp.ComponentFactory;
import com.msaure.jispel.interp.Context;
import com.msaure.jispel.interp.DefaultComponentFactory;
import org.mockito.Mockito;

public class InterpreterFixture {

    public static Interpreter mockInterpreter() {
        return Mockito.mock(Interpreter.class);
    }
    
    public static ComponentFactory defaultComponentFactory() {
        return new DefaultComponentFactory();
    }
    
    public static Context simpleIterativeContext(Interpreter interp) {
        final Context ctx = new Context(interp, defaultComponentFactory());
        
        return ctx;
    }
}
