package com.msaure.jispel.eval;

/**
 *  Subclasses of Evaluator will usually process nodes returned by successive
 *  calls to some Lisp reader.
 * 
 *  The Evaluator in most general terms is some
 *  object that performs some processing on the graph-like structure composed
 *  by a reader and stored in a NodeFactory. The kind of processing performed
 *  by Evaluator instances can be, e.g., pretty-printing, compilation, direct
 *  evaluation, or whatever. So, if you want to turn the Lispel interpreter
 *  into a lispel pretty-printer, you just have to provide a new subclass of
 *  this class.<p>
 * 
 *  The current version of the evaluator maintains an explicit stack of
 *  binding environments to implement lexical binding (i.e. nested variable
 *  scopes). These methods have been added to compensate the lack of a 
 *  'real' activation stack. Once a proper implementation of an activation
 *  stack exists, the environment stack handling will be removed as it is 
 *  specific to recursive evaluator implementations.
 * 
 *  @version 0.2
 */
public interface Evaluator {
}
