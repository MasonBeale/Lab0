package labspack;

import java.util.*;

public class Fraction implements INumber<Fraction> {
	private int numerator;
	private int denominator;
	
	/**
	 * The constructor for making a fraction
	 * @param num The numerator as an int
	 * @param den The denominator as an int
	 */
	public Fraction(int num, int den){
		numerator = num;
		denominator = den;
	}
	/**
	 * Adds one fraction to another
	 * @param The second Fraction
	 * @return The sum of the fractions
	 */
	public Fraction plus(Fraction input) {
		if(denominator==input.getDenominator()) {
			return new Fraction(numerator+input.getNumerator(),denominator);
		}else {
			int newDen = denominator*input.getDenominator();
			int num1 = numerator*input.getDenominator();
			int num2 = input.getNumerator()*denominator;
			Fraction out = new Fraction((num1+num2), newDen);
			return out;
		}
	}
	 /**
	 * Subtracts one fraction to another
	 * @param The second Fraction
	 * @return The difference of the fractions
	 */
	public Fraction minus(Fraction input) {
		if(denominator==input.getDenominator()) {
			return new Fraction(numerator-input.getNumerator(),denominator);
		}else {
			int newDen = denominator*input.getDenominator();
			int num1 = numerator*input.getDenominator();
			int num2 = input.getNumerator()*denominator;
			Fraction end = new Fraction((num1-num2), newDen);
			return end;
		}
	}
	/**
	 * Divides one fraction from another
	 * @param The second Fraction
	 * @return The dividend of the fractions
	 */
	public Fraction divide(Fraction input) {
		Fraction end = new Fraction(numerator*input.getDenominator(),denominator*input.getNumerator());
		return end;
	}
	/**
	 * Multiplies one fraction with another
	 * @param The second Fraction
	 * @return The product of the fractions
	 */
	public Fraction multiply(Fraction input) {
		int newNum = numerator*input.getNumerator();
		int newDen = denominator*input.getDenominator();
		Fraction end = new Fraction(newNum,newDen);
		return end;
	}
	/**
	 * Prints the fraction
	 */
	public void print() {
		System.out.println(numerator+"/"+denominator);
	}
	/**
	 * Used to be in compliance with java.util methods
	 */
	public String toString() {
		return numerator+"/"+denominator;
	}
	/**
	 * Gets the numerator
	 * @return The numerator of the fraction
	 */
	public int getNumerator() {
		return numerator;
	}
	/**
	 * Gets the denominator
	 * @return The denominator of the fraction
	 */
	public int getDenominator() {
		return denominator;
	}
	/**
	 * Checks two fractions to see if they are equivalent
	 * @param frac2 The second fraction
	 * @return True if the two fractions are equivalent
	 */
	public boolean hasSameValue(Fraction frac2) {
		if(denominator==frac2.getDenominator() && numerator==frac2.getNumerator()) {
			return true;
		}else {
			int num1 = numerator*frac2.getDenominator();
			int num2 = frac2.getNumerator()*denominator;
			if(num1==num2) {
				return true;
			}else {
				return false;
			}
		}
	}
	/**
	 * Helper method used in insertionSort to determine which fraction is greater
	 * @param frac2 The second fraction
	 * @return 0 if they are the same, 1 if this is greater, -1 otherwise
	 */
	public int compare(Fraction frac2) {
		if(this.hasSameValue(frac2)) {
			return 0;
		}else {
			int num1 = numerator*frac2.getDenominator();
			int num2 = frac2.getNumerator()*denominator;
			if(num1>num2) {
				return 1;
			}else {
				return -1;
			}
		}
	}
	/**
	 * Finds the most frequent value in an array of fractions
	 * @param array The array to check
	 * @return The value of the most common fraction
	 */
	public static Fraction findFrequent(Fraction[] array) {
		Fraction most = null;
		int numOccurance = 0;
		for(Fraction current: array) {
			int score = 0;
			for(Fraction check: array) {
				if(current.hasSameValue(check)) {
					score++;
				}
			}
			if(score>numOccurance) {
				numOccurance = score;
				most = current;
			}
		}
		return most;
	}
	/**
	 * Sorts the array using an insertion sort algorithm
	 * @param input The array to be sorted
	 */
	public static void insertionSort(Fraction[] input) {
		//parse input
		for(int i =1;i<input.length;i++) {
			/*
			 * start at index i
			 * if item before is greater swap them
			 * start j one item lower to stay with item being inserted
			 */
			for(int j=i;j>0 && input[j-1].compare(input[j]) ==1;j--) {
				Fraction keep = input[j-1];
				input[j-1]=input[j];
				input[j]= keep;
			}
		}
		System.out.println(Arrays.toString(input));
		
	}
	/**
	 * Prints the array in a reverse order recursively
	 * @param inputArray The array to reverse
	 */
	public static void printReverse(Fraction[] inputArray) {
		if(inputArray.length>1) {
			Fraction last = inputArray[inputArray.length-1];
			System.out.print(last+", ");
			Fraction[] next = new Fraction[inputArray.length-1];
			for (int i = 0; i < inputArray.length - 1; i++) {
                next[i] = inputArray[i];
            }
			printReverse(next);
		}else {
			System.out.println(inputArray[0]);
		}
	}

	public static void main(String[] args) {
		Fraction tester1 = new Fraction (1,2);
		Fraction tester2 = new Fraction (1,3);

		Fraction[] fractionArray = new Fraction[100];
		for(int i=0; i<fractionArray.length; i++) {
			fractionArray[i] = new Fraction(i, i+1);
		}
		fractionArray[10] = new Fraction(55,56);
		System.out.print(tester1+" plus "+tester2+" = ");
		tester1.plus(tester2).print();
		System.out.print(tester1+" minus "+tester2+" = ");
		tester1.minus(tester2).print();
		System.out.print(tester1+" divided by "+tester2+" = ");
		tester1.divide(tester2).print();
		System.out.print(tester1+" multiplied by "+tester2+" = ");
		tester1.multiply(tester2).print();
		
		System.out.println("The most frequent fraction is: "+findFrequent(fractionArray));
		System.out.println("The sorted array:");
		insertionSort(fractionArray);
		System.out.println("The array in reverse order:");

		printReverse(fractionArray);
		
	}

}
