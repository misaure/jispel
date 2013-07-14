/** 
 * Lisp Tagged Memory Cells
 * 
 * <p>The classes in this section implement the Lisp memory model which is
 * based on the notion of 'tagged memory cells'. All memory cells are
 * managed by a heap manager which keeps indirect references of the actual
 * values stored in a memory cell.</p>
 *
 * <p>Indirect references to the data of course impose certain overhead on
 * memory accesses, but on the other hand they make life easier for the
 * garbage collector because some garbage collector types depend on being
 * able to transparently move the data stored in the interpreters memory.</p>
 */
package com.msaure.jispel.memory;
