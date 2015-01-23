package com.msaure.jispel.eval;

/**
 * CompilingEvaluator transforms each newly allocated closure to an
 intermediate byte code representation which is based on the descriptions
 in Chapter 2 of 'Three Implementation Models for Scheme' by R. Kent Dybvig.
 This implementation differs in that it doesn't rely on the heap as strongly
 as the VM  described in the document. The machine's internal data will
 only be moved to heap when it is really needed, that is, when closures and
 continuations are created.
 */
public class CompilingEvaluator implements Evaluator {
}
