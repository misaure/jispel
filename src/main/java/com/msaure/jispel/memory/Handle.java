package com.msaure.jispel.memory;

import org.jetbrains.annotations.NotNull;

import com.msaure.jispel.core.Environment;

/**
 * Handle for NodeValue objects which also contains tag definitions.
 */
public class Handle {

	/** Marks a cell as non-writable. */
	public static final int CONSTFLAG = 0x00000001;

	/**
	 * When this flag is set for a Handle and the referenced value represents
	 * immediately executable code (closures and primitives) then none of the
	 * arguments passed to the function during evaluation will itself be
	 * evaluated. This allows adding special forms as needed.
	 */
	public static final int SPECIALFLAG = 0x00000002;

	public static final int WATCHFLAG = 0x00000004;

	/**
	 * Using this flag prevents a node from being recycled by the garbage
	 * collector.
	 */
	public static final int GCSAFEFLAG = 0x00000008;

	private final NodeType nodeType;
	private int refCount = 0;
	private final int flags;

	public enum NodeType {
		EMPTY, CHARACTER, BOOLEAN, INTEGER, DOUBLE,
		STRING, SYMBOL, KEYWORD, AVECTOR,
		ARRAY, OBJECT, PORT, CONS, CFUNC, CLOSURE, HASHTABLE, SET;
	}

	protected Handle(HandleBuilder<? extends Handle> b) {
		this(b.nodeType, b.flags);
	}

	protected Handle(NodeType nodeType, int flags) {
		this.nodeType = nodeType;
		this.flags = flags;
	}

	public NodeType type() {
		return this.nodeType;
	}

	public boolean checkFlag(int f) {
		return 0 != (flags & f);
	}

	public int getFlags() {
		return this.flags;
	}

	public boolean hasType(NodeType t) {
		return this.nodeType == t;
	}

	public boolean isNumber() {
		return (NodeType.DOUBLE == this.nodeType) || (NodeType.INTEGER == this.nodeType);
	}

	public boolean isNilRep() {
		return false;
	}

	public Handle car() throws TypeException {
		throw new TypeException("pair");
	}

	public Handle cdr() throws TypeException {
		throw new TypeException("pair");
	}

	/**
	 * Get the literal string value of a node.
	 *
	 * @exception TypeException Thrown if the node doesn't contain a string
	 * value.
	 */
	public String stringValue() throws TypeException {
		throw new TypeException( "string or symbol");
	}

	/**
	 * Get the literal double value of a node.
	 *
	 * @exception TypeException Thrown if the node doesn't contain a double
	 * value.
	 */
	public double doubleValue() throws TypeException {
		throw new TypeException( "double");
	}

	/**
     Get the integer value of a memory cell. Applicable only to ntINTEGER type
     cells.
     @exception TypeException Thrown if the cell isn't of type ntINTEGER.
	 */
	public int integerValue() throws TypeException {
		throw new TypeException( "integer");
	}

	public boolean booleanValue() throws TypeException {
		throw new TypeException( "boolean");
	}

	public Handle body() throws TypeException {
		throw new TypeException( "closure");
	}

	/**
	 * Get the NodeValue object referenced by this handle instance.
	 *
	 * Once immediate values (e.g. for storing double, boolean and integer
	 * values) will be used, the result of this operation might be
	 * <i>undefined</i>.
	 */
	public NodeValue typeImpl() {
		throw new UnsupportedOperationException("not implemented");
	}

	public void incRef() {

	}

    /**
     * Checks whether the memory cell has been tagged during the last mark-and-sweep garbage collection run.
     *
     * @return {@code true} if the cell has been tagged.
     */
    public boolean tagged() {
        return 0 != this.refCount;
    }

	/**
	 * Creates a new binding environment which contains the bindings of the
	 * actual parameters for a lambda application. The new environment will be
	 * a direct descendant of the environment stored upon creation of the
	 * closure (to implement lexical binding).
	 *
	 * @param argvalues Contains the actual parameter (values) for a application
	 * of a closure. The vector length must equal the closure's arity. Each of
	 * the values stored in this parameter will be entered into the new
	 * environment under the name of the corresponding formal parameter. This
	 * officially happens in an unspecified order (and, in reality, in the
	 * from left to right).
	 *
	 * @return A new binding environment in which the formal parameters of a
	 * closure are bound to the actual parameters computed for a specific
	 * application.
	 *
	 * @exception TypeException Will be thrown if the number of actual
	 * parameters doesn't match the number of formal parameters.
	 */
	public Environment bindArguments(Handle[] argvalues) throws TypeException {
		return null;
	}

	public static abstract class HandleBuilder<T extends Handle> {
		private NodeType nodeType;
		private int refCount = 0;
		private int flags;

		protected HandleBuilder<T> withNodeType(NodeType nodeType) {
			this.nodeType = nodeType;
			return this;
		}

		public HandleBuilder<T> withFlags(int flags) {
			this.flags = flags;
			return this;
		}

		public HandleBuilder<T> addFlag(int flag) {
			this.flags |= flag;
			return this;
		}

		@NotNull
		public abstract T build();
	}
}
