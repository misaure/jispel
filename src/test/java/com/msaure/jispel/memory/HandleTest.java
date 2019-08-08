package com.msaure.jispel.memory;

import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class HandleTest {

    @Test
    public void thatNilRepresentationCanBeCreated() {
        Handle nil = new Handle(Handle.NodeType.CONS, 0) {
            @Override
            public boolean isNilRep() {
                return true;
            }
        };

        assertThat(nil.isNilRep()).isTrue();
        assertThat(nil.hasType(Handle.NodeType.CONS));
    }
}