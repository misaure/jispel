package com.msaure.jispel.memory.builder;

import com.msaure.jispel.memory.Handle;

public class HandleBuilder {

    public static ConsCellBuilder consCell() {
        return new ConsCellBuilder();
    }

    public static class ConsCellBuilder {
        private Handle car;
        private Handle cdr;

        public ConsCellBuilder withCar(Handle handleForCar) {
            this.car = handleForCar;
            return this;
        }

        public ConsCellBuilder withCdr(Handle handleForCdr) {
            this.cdr = handleForCdr;
            return this;
        }

        public Handle build() {
            return null;
        }
    }
}
