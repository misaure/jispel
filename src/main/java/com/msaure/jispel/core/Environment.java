package com.msaure.jispel.core;

import com.msaure.jispel.memory.Handle;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import java.util.Set;

/**
 * Environments are the place where the interpreter stores all named
 * values. Environments are ordered in a hierachy to aid the implementation
 * of scoping. Environments can be used to build hierachical structures
 * through parent links provided for each Environment instance.
 *
 * @version 0.3
 *
 * @since 0.0.1
 */
public class Environment {

    protected static Set<Environment> ENVS = new HashSet<>();

    private final Map<String,Handle> entries = new HashMap<>();
    private Environment parent;
    
    public Environment() {
        this(null);
    }

    /**
     * Create a new Environment instance with a given parent environment.
     * @param parent The parent environment.
     */
    public Environment(Environment parent) {
        this.parent = parent;
        registerEnv(this);
    }

    /**
     * Retrieve a value bound to the name given.
     * @param name Name to which a value is connected.
     * @return A value of end() when name doesn't exist.
     */
    @Nullable
    public Handle lookup(String name) {
        Environment current = this;
        for (;;) {
            if (null == current) {
                return null;
            }
            final Handle h = this.entries.get(name);
            if (null != h) {
                return h;
            }
            current = current.getParent();
        }
    }

    /**
     * Checks if a named value exists. Traverses the parent 
     * environment chain if the value does not exist in the
     * current environment.
     * 
     * @return true if a value is bound to name
     */
    public boolean exists(String name) {
        Environment current = this;
        for (;;) {
            if (null == current) {
                return false;
            }
            if (entries.containsKey(name)) {
                return true;
            } else {
                current = current.getParent();
            }
        }
    }

    /**
     * Insert a named value to the environment.
     */
    public void put(String name, Handle value) {
        this.entries.put(name, value);
    }

    /**
     * Removes all entries from the environment (not from parent environments).
     */
    public void clear() {
        this.entries.clear();
    }

    /**
     * Get the parent environment.
     * @return The parent environment.
     */
    public Environment getParent() {
        return this.parent;
    }

    /**
     * Create a new Environment instance which has the current Environment
     * instance as parent.
     * 
     * TODO rename to newChildEnvironment()
     */
    public Environment makeChildEnvironment() {
        return new Environment(this);
    }

    /**
     * Set the parent environment.
     */
    public void setParent(Environment parent) {
        this.parent = parent;
    }

    public boolean isToplevelEnvironment() {
        return null == getParent();
    }

    /**
     Delete all Environment instances which aren't referenced from outside
     anymore.
     @param active A set containing all Environment instances currently active
     (i.e. referenced from somewhere outside)
     */
    public void recycleUnusedEnvs(Set<Environment> active) {

    }

    /**
     Called in each of the Environment constructors.
     */
    protected static void registerEnv(Environment env) {

    }
}
