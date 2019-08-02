package com.msaure.jispel.memory.type;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.data.Offset;
import org.junit.Before;
import org.junit.Test;

import com.msaure.jispel.memory.Handle;

public class DoubleHandleTest {

	DoubleHandle d;

	@Before
	public void setUp() {
		d = DoubleHandle.builder()
				.withDoubleValue(1.0)
				.build();
	}

	@Test
	public void thatBuilderDoesNotReturnNull() {
		assertThat(d).isNotNull();
	}

	@Test
	public void thatItHasTheExpectedNodeType() {
		assertThat(d.hasType(Handle.NodeType.DOUBLE)).isTrue();
	}

	@Test
	public void thatItCanBeConvertedToTheCorrectNativeValue() {
		assertThat(d.doubleValue()).isCloseTo(1.0, Offset.offset(0.00001));
	}
}