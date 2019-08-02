package com.msaure.jispel.memory;

import com.msaure.jispel.memory.type.BooleanHandle;

/**
 * Enumeration that creates singletons for special literal values.
 *
 * @since 0.0.1
 */
public enum Constants {

	NIL(new Handle(Handle.NodeType.CONS, 0) {
		@Override
		public boolean isNilRep() {
			return true;
		}
	}),
	TRUE(BooleanHandle.valueOf(true)),
	FALSE(BooleanHandle.valueOf(false));

	private Handle representation;

	private Constants(Handle representation) {
		this.representation = representation;
	}

	public Handle asHandle() {
		return representation;
	}
}
