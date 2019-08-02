package com.msaure.jispel.memory.type;

import com.msaure.jispel.memory.Handle;
import com.msaure.jispel.memory.TypeException;
import org.jetbrains.annotations.NotNull;

public class ConsHandle extends Handle {

    private final Handle car;
    private final Handle cdr;

    private ConsHandle(ConsBuilder b) {
        super(b);
        this.car = b.car;
        this.cdr = b.cdr;
    }

    @Override
    public Handle car() throws TypeException {
       return car;
    }

    @Override
    public Handle cdr() throws TypeException {
        return cdr;
    }

    @Override
    public boolean isNilRep() {
        return (null == car) && (null == cdr);
    }
    
    @NotNull public static ConsBuilder builder() {
        return new ConsBuilder();
    }

    public static class ConsBuilder extends HandleBuilder<ConsHandle> {

        private Handle car;
        private Handle cdr;

        @NotNull public ConsBuilder withCar(Handle car) {
            this.car = car;
            return this;
        }

        @NotNull public ConsBuilder withCdr(Handle cdr) {
            this.cdr = cdr;
            return this;
        }

        @NotNull
        @Override
        public ConsHandle build() {
            return new ConsHandle(this);
        }
    }
}
