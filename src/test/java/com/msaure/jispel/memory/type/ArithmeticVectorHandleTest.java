package com.msaure.jispel.memory.type;

import static org.junit.Assert.*;

import com.msaure.jispel.memory.Handle;
import org.junit.Before;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

public class ArithmeticVectorHandleTest {

	ArithmeticVectorHandle handle;
	
	@Before
	public void setUp() {
		this.handle = ArithmeticVectorHandle.builder()
				.build();
	}
	
	@Test
	public void testIt() {
		assertFalse(handle.isNilRep());
	}

	@Test
	public void test2() {
		assertThat(handle.hasType(Handle.NodeType.AVECTOR)).isTrue();
	}
}
