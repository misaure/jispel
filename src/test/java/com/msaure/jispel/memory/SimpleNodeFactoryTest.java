package com.msaure.jispel.memory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.msaure.jispel.memory.type.ConsHandle;
import com.msaure.jispel.memory.type.IntegerHandle;

public class SimpleNodeFactoryTest {

	SimpleNodeFactory factory;

	@Before
	public void setUp() {
		this.factory = new SimpleNodeFactory();
	}

	@Test
	public void thatMakeNilReturnsEmptyConsHandle() {
		Handle h = factory.makeNil();
		assertNotNull(h);
		//assertTrue(h instanceof ConsHandle);
		assertTrue(h.hasType(Handle.NodeType.CONS));
	}

	@Test
	public void thatMakeSymbolReturnsValidSymbol() throws TypeException {
		Handle h = factory.makeSymbol("mysym");
		assertNotNull(h);
		assertTrue(h.hasType(Handle.NodeType.SYMBOL));
		assertEquals("mysym", h.stringValue());
	}

	@Test
	public void thatConsWithCarOnlyReturnsValidSymbol() throws TypeException {
		ConsHandle h = factory.makeCons(IntegerHandle.valueOf(1));

		assertThat(h).isNotNull();
		assertThat(h.car().integerValue()).isEqualTo(1);
		assertThat(h.cdr()).isEqualTo(Constants.NIL.asHandle());
	}

	@Test
	public void thatItCreatesANonNullNilHandle() {
		assertNotNull(factory.makeNil());
	}
}
