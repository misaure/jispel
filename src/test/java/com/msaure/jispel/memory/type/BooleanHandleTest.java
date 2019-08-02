package com.msaure.jispel.memory.type;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.msaure.jispel.memory.Handle;

public class BooleanHandleTest {

	@Test
	public void test1() {
		BooleanHandle b = BooleanHandle.builder()
				.withBooleanValue(true)
				.build();

		assertThat(b).isNotNull();
		assertThat(b.toBoolean()).isTrue();
	}

	public void test2() {
		BooleanHandle b = BooleanHandle.builder()
				.withBooleanValue(false)
				.build();

		assertThat(b).isNotNull();
		assertThat(b.toBoolean()).isFalse();
	}

	public void test3() {
		BooleanHandle b = BooleanHandle.builder()
				.withBooleanValue(true)
				.build();

		assertThat(b.hasType(Handle.NodeType.BOOLEAN)).isTrue();
	}
}