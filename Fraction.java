package labspack;

import java.util.*;

public class Fraction implements INumber<Fraction> {
	//fields
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

	public Fraction divide(Fraction input) {
		Fraction end = new Fraction(numerator*input.getDenominator(),denominator*input.getNumerator());
		return end;
	}

	public Fraction multiply(Fraction input) {
		int newNum = numerator*input.getNumerator();
		int newDen = denominator*input.getDenominator();
		Fraction end = new Fraction(newNum,newDen);
		return end;
	}

	public void print() {
		System.out.println(numerator+"/"+denominator);
	}
	public String toString() {
		return numerator+"/"+denominator;
	}
	public int getNumerator() {
		return numerator;
	}
	public int getDenominator() {
		return denominator;
	}
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
	
	public int compare(Fraction frac2) {
		if(denominator==frac2.getDenominator() && numerator==frac2.getNumerator()) {
			return 0;
		}else {
			int num1 = numerator*frac2.getDenominator();
			int num2 = frac2.getNumerator()*denominator;
			if(num1==num2) {
				return 1;
			}else {
				return -1;
			}
		}
	}
	
	public Fraction findFrequent(Fraction[] array) {
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
		Fraction tester = new Fraction (0,1);
		Fraction[] fractionArray = new Fraction[100];
		for(int i=0; i<fractionArray.length; i++) {
			fractionArray[i] = new Fraction(i, i+1);
		}
		insertionSort(fractionArray);
		printReverse(fractionArray);
		


	}

}
