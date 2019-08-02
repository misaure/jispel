package com.msaure.jispel;

import com.msaure.jispel.interp.ComponentFactory;
import com.msaure.jispel.interp.Context;
import com.msaure.jispel.interp.DefaultComponentFactory;
import org.jetbrains.annotations.NotNull;
import org.mockito.Mockito;

public class InterpreterFixture {

    public static Interpreter mockInterpreter() {
        return Mockito.mock(Interpreter.class);
    }
    
    public static ComponentFactory defaultComponentFactory() {
        return new DefaultComponentFactory();
    }

    @NotNull
    public static Context simpleIterativeContext() {
        final Context ctx = new Context(defaultComponentFactory());
        
        return ctx;
    }
}
