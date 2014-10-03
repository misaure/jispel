package com.msaure.jispel.interp;

import com.msaure.jispel.Interpreter;
import com.msaure.jispel.core.Environment;
import com.msaure.jispel.eval.Evaluator;
import com.msaure.jispel.memory.Handle;
import com.msaure.jispel.memory.NodeFactory;

public class Context {
    public NodeFactory factory;
    public Environment toplevel;
    public Environment currentEnv;
    public Evaluator eval;
    public Interpreter interp;
    public Handle NIL;
    public Handle TRUE;
    public Handle FALSE;
}
