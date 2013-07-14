package com.msaure.jispel.builtin.primitive;

/**
 Explicitly request full garbage collection. The function will return
 immediately but request will be performed when the next safe point has been
 reached.  'gc' always returns true because of the delayed execution.
 */
public class GcCommand {
}
