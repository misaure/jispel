package com.msaure.jispel.memory;

import com.msaure.jispel.core.Environment;
import com.msaure.jispel.interp.Context;

public abstract class SpecialValue extends CommandImpl {

    public abstract Handle execute( Context ctx, Environment env, Handle args);

}
