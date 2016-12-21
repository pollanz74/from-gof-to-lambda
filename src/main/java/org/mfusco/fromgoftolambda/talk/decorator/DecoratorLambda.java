package org.mfusco.fromgoftolambda.talk.decorator;

import java.util.function.DoubleUnaryOperator;
import java.util.stream.Stream;

public class DecoratorLambda {

	public static class DefaultSalaryCalculator implements DoubleUnaryOperator {

		@Override
		public double applyAsDouble(double grossAnnual) {
			return grossAnnual / 12;
		}

	}

	public static double calculate(double gross, DoubleUnaryOperator... fs) {
		return Stream.of(fs) //
				.reduce(DoubleUnaryOperator.identity(), DoubleUnaryOperator::andThen) //
				.applyAsDouble(gross);
	}

	public static void main(String[] args) {
		System.out.println(new DefaultSalaryCalculator() //
				.andThen(Taxes::generalTax) //
				.andThen(Taxes::regionalTax) //
				.andThen(Taxes::healthInsurance) //
				.applyAsDouble(30000.00));
		
		System.out.println(calculate(30000.00, new DefaultSalaryCalculator(), Taxes::generalTax, Taxes::regionalTax, Taxes::healthInsurance));
	}
}
