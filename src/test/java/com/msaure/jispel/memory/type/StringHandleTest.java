package com.msaure.jispel.memory.type;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.msaure.jispel.memory.Handle;
import com.msaure.jispel.memory.TypeException;

public class StringHandleTest {

	static final String STRING_VALUE = "test string value";

	StringHandle handle;

	@Before
	public void setUp() {
		handle = StringHandle.builder()
				.withStringValue(STRING_VALUE)
				.build();
	}

	@Test
	public void test1() throws TypeException {
		assertThat(handle.stringValue()).isEqualTo(STRING_VALUE);
	}

	@Test
	public void test2() {
		assertThat(handle.hasType(Handle.NodeType.STRING)).isTrue();
	}

	@Test
	public void test3() {
		assertThat(StringHandle.valueOf(STRING_VALUE)).isNotNull();
	}

	@Test
	public void test4() throws TypeException {
		assertThat(StringHandle.valueOf(STRING_VALUE).stringValue()).isEqualTo(STRING_VALUE);
	}
}