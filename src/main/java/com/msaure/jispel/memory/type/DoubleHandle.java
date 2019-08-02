package com.msaure.jispel.memory.type;

import org.jetbrains.annotations.NotNull;

import com.msaure.jispel.memory.Handle;

public class DoubleHandle extends Handle {

	private final double doubleValue;

	private DoubleHandle(DoubleBuilder b) {
		super(b);
		this.doubleValue = b.doubleValue;
	}

	@Override
	public double doubleValue() {
		return this.doubleValue;
	}

	@NotNull public static DoubleHandle valueOf(double doubleValue) {
		return builder()
				.withDoubleValue(doubleValue)
				.build();
	}

	@NotNull public static DoubleBuilder builder() {
		return new DoubleBuilder();
	}

	public static class DoubleBuilder extends HandleBuilder<DoubleHandle> {

		private double doubleValue;

		public DoubleBuilder() {
			withNodeType(NodeType.DOUBLE);
		}

		@NotNull
		public DoubleBuilder withDoubleValue(double v) {
			this.doubleValue = v;
			return this;
		}

		@NotNull
		@Override
		public DoubleHandle build() {
			return new DoubleHandle(this);
		}
	}
}
