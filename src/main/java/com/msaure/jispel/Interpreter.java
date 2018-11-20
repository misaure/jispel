package com.msaure.jispel;

import com.msaure.jispel.core.Environment;
import com.msaure.jispel.eval.Evaluator;
import com.msaure.jispel.gc.GarbageCollector;
import com.msaure.jispel.interp.ComponentFactory;
import com.msaure.jispel.interp.Context;
import com.msaure.jispel.interp.DefaultComponentFactory;
import com.msaure.jispel.interp.UserInterface;
import com.msaure.jispel.memory.BuiltinValue;
import com.msaure.jispel.memory.Handle;
import com.msaure.jispel.memory.NodeFactory;
import com.msaure.jispel.memory.SpecialValue;

import java.io.Reader;

/**
 * This class acts as facade for the various components making up a complete
 * interpreter. All commonly used methods of the various components are
 * exported as methods of the Interpreter class, so this class acts as the
 * interpreters high-level API. To get deeper inside the interpreter's
 * internal, the components actually making up the interpreter can be accessed
 * directly, thus giving access to the low-level APIs.
 * 
 * <p>All of the interpreter's components are exchangeable with alternative
 * implementations. To determine what component types will actually be used,
 * you can pass a ComponentFactory to the interpreter's constructor. All
 * components used by the interpreter will be created using that factory
 * object. If you don't specify any factory object, an instance of the
 * DefaultComponentFactory will be used.</p>
 * 
 * @version 0.1
 */
public class Interpreter {

    private Context ctx;
    private static boolean LEXER_INITIALIZED;
    private boolean exitRequested;
    private boolean gcRequested;
    private GarbageCollector gc;
    private final Evaluator evaluator;
    
    //private static Interpreter INSTANCE;

    public Interpreter() {
        this(new DefaultComponentFactory());
    }

    public Interpreter(ComponentFactory factory) {
        ctx = new Context(this, factory);
        
        gc = factory.createGC( ctx);

        if (!LEXER_INITIALIZED) {
            // TODO initLexerModule();
            LEXER_INITIALIZED = true;
        }
        exitRequested = gcRequested = false;
        
        this.evaluator = factory.createEvaluator(ctx);

    }

    /**
     * repl is an acronym for 'Read-Eval-Print loop'. This method reads and
     * executes a Lispel program read from the given stream until either an
     * end-of-file occurs or the program is explicitly halted.
     * 
     * @param scriptSource The stream from which the program should be read.
     * 
     * @return false if an error occurred, true else.
     */
    public boolean repl(Reader scriptSource, UserInterface ui) {
        throw new UnsupportedOperationException("not implemented");
    }

    /**
     * Load and execute a Lispel source file. If any exception occurs during
     * program execution it won't be caught by this method so that the user
     * code can more exactly determine the type of error.
     * 
     * @return true when loading and executing the program succeeded or false if
     * opening the input file failed.
     */

    public boolean source(Reader scriptSource) {
        throw new UnsupportedOperationException("not implemented");
    }

    /**
     * Establish a variable binding in the global binding environment.
     * 
     * @param name Name under which the value will be available
     * @param value Can be any kind of valid memory cell.
     */
    public void toplevelDefine( String name, Handle value) {

    }

    /**
     Look up a value in the global binding environment.
     * 
     @param name Name of the value to look up.
     @return A handle to the value stored with key or zero if no such value
     exists.
     */
    public Handle toplevelLookup(String name) {
        throw new UnsupportedOperationException("not implemented");
    }

    /**
     Add a new builtin to the interpreter. All builtins are defined in the
     toplevel binding environment and, in contrast to closures, don't keep
     an environment of their own as part of their data structure.
     * 
     @param name Specifies the name for the new builtin. Any data already
     existing under the same name will be overwritten. So you might want
     to do a toplevelLookup first, before you add your new command.
     * 
     @param cmdimpl A subclass of BuiltinValue implementing the new command.
     * 
     @return A pointer to the memory cell allocated for the new command or
     0 if the operation failed.
     */
    public Handle addBuiltin( String name, BuiltinValue cmdimpl) {
        throw new UnsupportedOperationException("not implemented");
    }

    //public Handle addBuiltin( String name, CBuiltinAdapter value) {
    //}

    /**
     This method is in operation very similar to addBuiltin except that it
     adds a special form, i.e. a builtin which receives unevaluated
     arguments.
     */
    public Handle addSpecial(String name, SpecialValue commandImpl) {
        throw new UnsupportedOperationException("not implemented");
    }

    /**
     Returns a globally unique instance of the class Interpreter. This
     instance will be created using default interpreter components.
     */
    public static Interpreter instance() {
        throw new UnsupportedOperationException("not implemented");
    }

    /** Perform garbage collection, if available. */
    public void gc() {

    }

    /** Get the number of memory cells currently in use. */
    public int cellCount() {
        throw new UnsupportedOperationException("not implemented");
    }

    public void exit() {
        this.exitRequested = true;
    }

    public Environment getToplevel() {
        return this.ctx.toplevel;
    }

    public NodeFactory getNodeFactory() {
        return this.ctx.factory;
    }

    public Evaluator getEvaluator() {
        return this.evaluator;
   }

    public Context getContext() {
        return this.ctx;
    }

}
