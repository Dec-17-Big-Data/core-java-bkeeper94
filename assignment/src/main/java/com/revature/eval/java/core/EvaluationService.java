package com.revature.eval.java.core;

import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class EvaluationService {

	/**
	 * 1. Without using the StringBuilder or StringBuffer class, write a method that
	 * reverses a String. Example: reverse("example"); -> "elpmaxe"
	 * 
	 * @param string
	 * @return
	 */
	public String reverse(String string) {
		char[] reversed = new char[string.length()];
		for (int i = reversed.length - 1, j=0; i >= 0; i--, j++) {
			reversed[j] = string.charAt(i);
		}
		return new String(reversed);
	}

	/**
	 * 2. Convert a phrase to its acronym. Techies love their TLA (Three Letter
	 * Acronyms)! Help generate some jargon by writing a program that converts a
	 * long name like Portable Network Graphics to its acronym (PNG).
	 * 
	 * @param phrase
	 * @return
	 */
	public String acronym(String phrase) {
		//Added import of ArrayList to complete this implementation
		//Create ArrayList to hold all strings that will form acronym 
		List<Character> sl = new ArrayList<Character>();
		//Add the first character to the ArrayList
		sl.add(phrase.charAt(0));
		//Add all the letters that immediately follow whitespace and hyphens to the ArrayList
		for (int i = 1; i < phrase.length(); i++) {
			if (i != phrase.length()-1) {
				if (phrase.charAt(i) == ' ' || phrase.charAt(i) == '-') {
					sl.add(phrase.charAt(i+1));
				}
			}
		}
		//Create StringBuilder to make the acronym from the elements added to the ArrayList
		StringBuilder sb = new StringBuilder(sl.size());
		for (Character c : sl) {
			sb.append(c);
		}
		//Use toString() and toUpperCase() to make the StringBuilder into the final result 
		return sb.toString().toUpperCase();
	}

	/**
	 * 3. Determine if a triangle is equilateral, isosceles, or scalene. An
	 * equilateral triangle has all three sides the same length. An isosceles
	 * triangle has at least two sides the same length. (It is sometimes specified
	 * as having exactly two sides the same length, but for the purposes of this
	 * exercise we'll say at least two.) A scalene triangle has all sides of
	 * different lengths.
	 *
	 */
	static class Triangle {
		private double sideOne;
		private double sideTwo;
		private double sideThree;

		public Triangle() {
			super();
		}

		public Triangle(double sideOne, double sideTwo, double sideThree) {
			this();
			this.sideOne = sideOne;
			this.sideTwo = sideTwo;
			this.sideThree = sideThree;
		}

		public double getSideOne() {
			return sideOne;
		}

		public void setSideOne(double sideOne) {
			this.sideOne = sideOne;
		}

		public double getSideTwo() {
			return sideTwo;
		}

		public void setSideTwo(double sideTwo) {
			this.sideTwo = sideTwo;
		}

		public double getSideThree() {
			return sideThree;
		}

		public void setSideThree(double sideThree) {
			this.sideThree = sideThree;
		}

		public boolean isEquilateral() {
			return (sideOne==sideTwo && sideTwo==sideThree && sideOne==sideThree); 
		}

		public boolean isIsosceles() {
			return (sideOne==sideTwo || sideOne==sideThree || sideTwo==sideThree);
		}

		public boolean isScalene() {
			return (sideOne!=sideTwo && sideTwo!=sideThree && sideOne!=sideThree);
		}

	}

	/**
	 * 4. Given a word, compute the scrabble score for that word.
	 * 
	 * --Letter Values-- Letter Value A, E, I, O, U, L, N, R, S, T = 1; D, G = 2; B,
	 * C, M, P = 3; F, H, V, W, Y = 4; K = 5; J, X = 8; Q, Z = 10; Examples
	 * "cabbage" should be scored as worth 14 points:
	 * 
	 * 3 points for C, 1 point for A, twice 3 points for B, twice 2 points for G, 1
	 * point for E And to total:
	 * 
	 * 3 + 2*1 + 2*3 + 2 + 1 = 3 + 2 + 6 + 3 = 5 + 9 = 14
	 * 
	 * @param string
	 * @return
	 */
	public int getScrabbleScore(String string) {
		String newString = string.toUpperCase();
		char [] ca = newString.toCharArray();
		int score = 0;
		for (int i = 0; i < ca.length; i++) {
			switch (ca[i]) {
				case 'D':
				case 'G':
					score += 2;
					break;
				case 'B':
				case 'C':
				case 'M':
				case 'P':
					score += 3;
					break;
				case 'F':
				case 'H':
				case 'V':
				case 'W':
				case 'Y':
					score += 4;
					break;
				case 'K':
					score += 5;
					break;
				case 'J':
				case 'X':
					score += 8;
					break;
				case 'Q':
				case 'Z':
					score += 10;
					break;
				default:
					score += 1; //all other letters
			}
		}
		return score;
	}

	/**
	 * 5. Clean up user-entered phone numbers so that they can be sent SMS messages.
	 * 
	 * The North American Numbering Plan (NANP) is a telephone numbering system used
	 * by many countries in North America like the United States, Canada or Bermuda.
	 * All NANP-countries share the same international country code: 1.
	 * 
	 * NANP numbers are ten-digit numbers consisting of a three-digit Numbering Plan
	 * Area code, commonly known as area code, followed by a seven-digit local
	 * number. The first three digits of the local number represent the exchange
	 * code, followed by the unique four-digit number which is the subscriber
	 * number.
	 * 
	 * The format is usually represented as
	 * 
	 * 1 (NXX)-NXX-XXXX where N is any digit from 2 through 9 and X is any digit
	 * from 0 through 9.
	 * 
	 * Your task is to clean up differently formatted telephone numbers by removing
	 * punctuation and the country code (1) if present.
	 * 
	 * For example, the inputs
	 * 
	 * +1 (613)-995-0253 613-995-0253 1 613 995 0253 613.995.0253 should all produce
	 * the output
	 * 
	 * 6139950253
	 * 
	 * Note: As this exercise only deals with telephone numbers used in
	 * NANP-countries, only 1 is considered a valid country code.
	 */
	public String cleanPhoneNumber(String string) {
		//remove country code if one exists
		if (string.substring(0, 2).compareTo("+1")==0) {
			StringBuilder sb = new StringBuilder(string);
			sb.deleteCharAt(1);
			string = sb.toString();
		}
		//clean up rest of phone number; used regex for keeping only numbers 0 thru 9
		if (string.replaceAll("[^0-9]", "").length() != 10) {
			throw new IllegalArgumentException();
		}
		return string.replaceAll("[^0-9]", "");
	}

	/**
	 * 6. Given a phrase, count the occurrences of each word in that phrase.
	 * 
	 * For example for the input "olly olly in come free" olly: 2 in: 1 come: 1
	 * free: 1
	 * 
	 * @param string
	 * @return
	 */
	public Map<String, Integer> wordCount(String string) {
		//make empty HashMap
		Map<String, Integer> m = new HashMap<String, Integer>();
		//create string array of all words contained in input phrase 
		String[] sa =string.split(",?[^a-zA-z]"); //regex for all characters except letters 
		//assumed hyphened words are one word
		//count the number of occurrences of each word and populate the HashMap
		for (int i = 0; i < sa.length; i++) {
			if (m.containsKey(sa[i])) {
				m.replace(sa[i], 1 + m.get(sa[i]));
			}
			else {
				m.put(sa[i], 1);
			}
		}
		return m;
	}

	/**
	 * 7. Implement a binary search algorithm.
	 * 
	 * Searching a sorted collection is a common task. A dictionary is a sorted list
	 * of word definitions. Given a word, one can find its definition. A telephone
	 * book is a sorted list of people's names, addresses, and telephone numbers.
	 * Knowing someone's name allows one to quickly find their telephone number and
	 * address.
	 * 
	 * If the list to be searched contains more than a few items (a dozen, say) a
	 * binary search will require far fewer comparisons than a linear search, but it
	 * imposes the requirement that the list be sorted.
	 * 
	 * In computer science, a binary search or half-interval search algorithm finds
	 * the position of a specified input value (the search "key") within an array
	 * sorted by key value.
	 * 
	 * In each step, the algorithm compares the search key value with the key value
	 * of the middle element of the array.
	 * 
	 * If the keys match, then a matching element has been found and its index, or
	 * position, is returned.
	 * 
	 * Otherwise, if the search key is less than the middle element's key, then the
	 * algorithm repeats its action on the sub-array to the left of the middle
	 * element or, if the search key is greater, on the sub-array to the right.
	 * 
	 * If the remaining array to be searched is empty, then the key cannot be found
	 * in the array and a special "not found" indication is returned.
	 * 
	 * A binary search halves the number of items to check with each iteration, so
	 * locating an item (or determining its absence) takes logarithmic time. A
	 * binary search is a dichotomic divide and conquer search algorithm.
	 * 
	 */
	static class BinarySearch<T extends Comparable<T>> {
		//need to extend Comparable for type T to use compareTo method in indexOf method
		private List<T> sortedList;

		public int indexOf(T t) {
			int start = 0;
			int end = sortedList.size()-1;
			while (start <= end) { 
				int middle = Math.floorDiv(start + end, 2); //need middle to be an int
				if (sortedList.get(middle).compareTo(t) < 0) {
					start = middle + 1; //moves to right sub-list since t > value at middle   
				}
				else if (sortedList.get(middle).compareTo(t) > 0) {
					end = middle - 1; //moves to left sub-list since t < value at middle
				}
				else {
					return middle;
				}
			}
			//return statement for case if t is not present in sortedList
			return -1;
		}

		public BinarySearch(List<T> sortedList) {
			super();
			this.sortedList = sortedList;
		}

		public List<T> getSortedList() {
			return sortedList;
		}

		public void setSortedList(List<T> sortedList) {
			this.sortedList = sortedList;
		}

	}

	/**
	 * 8. Implement a program that translates from English to Pig Latin.
	 * 
	 * Pig Latin is a made-up children's language that's intended to be confusing.
	 * It obeys a few simple rules (below), but when it's spoken quickly it's really
	 * difficult for non-children (and non-native speakers) to understand.
	 * 
	 * Rule 1: If a word begins with a vowel sound, add an "ay" sound to the end of
	 * the word. Rule 2: If a word begins with a consonant sound, move it to the end
	 * of the word, and then add an "ay" sound to the end of the word. There are a
	 * few more rules for edge cases, and there are regional variants too.
	 * 
	 * See http://en.wikipedia.org/wiki/Pig_latin for more details.
	 * 
	 * @param string
	 * @return
	 */
	public String toPigLatin(String string) { 
		//assume string could be a phrase so separate the words first
		String[] sa =string.split(",?[^a-zA-z]");
		String finalStr = "";
		//apply pig latin rules on all elements of sa
		for (int i = 0; i < sa.length; i++) {
			//account for special cases of sa[i] starting with qu or squ
			if (sa[i].substring(0 , 2).compareToIgnoreCase("qu") == 0) {
				sa[i] = sa[i].substring(2) + "quay";
			}
			else if (sa[i].substring(0 , 3).compareToIgnoreCase("squ") == 0) {
				sa[i] = sa[i].substring(3) + "squay";
			}
			else { //when sa[i] does not begin with qu or squ
				//find the first vowel of sa[i] 
				String current = sa[i];
				int vowelLoc = 0;
				for (int j = 0; j < current.length(); j++) {
					char c = current.charAt(j);
					if (c == 'a' || c == 'A' || c == 'e' || c == 'E' || c == 'i' || c == 'I'
							|| c == 'o' || c == 'O' || c == 'u' || c == 'U') {
						vowelLoc = j;
						break;
					}
				}
				sa[i] = current.substring(vowelLoc) + current.substring(0 , vowelLoc) + "ay";
			}
			if (sa.length == 1 || i == sa.length - 1) {
				finalStr = finalStr + sa[i];
			}
			else {
				finalStr = finalStr + sa[i] + " ";
			}
		}
		return finalStr;
	}

	/**
	 * 9. An Armstrong number is a number that is the sum of its own digits each
	 * raised to the power of the number of digits.
	 * 
	 * For example:
	 * 
	 * 9 is an Armstrong number, because 9 = 9^1 = 9 10 is not an Armstrong number,
	 * because 10 != 1^2 + 0^2 = 2 153 is an Armstrong number, because: 153 = 1^3 +
	 * 5^3 + 3^3 = 1 + 125 + 27 = 153 154 is not an Armstrong number, because: 154
	 * != 1^3 + 5^3 + 4^3 = 1 + 125 + 64 = 190 Write some code to determine whether
	 * a number is an Armstrong number.
	 * 
	 * @param input
	 * @return
	 */
	public boolean isArmstrongNumber(int input) {
		String inputStr = Integer.toString(input);
		int armstrongSum = 0;
		for (int i = 0; i < inputStr.length(); i++) {
			armstrongSum += Math.pow(Integer.parseInt(inputStr.substring(i, i+1)),
					inputStr.length());
		}
		return input == armstrongSum;
	}

	/**
	 * 10. Compute the prime factors of a given natural number.
	 * 
	 * A prime number is only evenly divisible by itself and 1.
	 * 
	 * Note that 1 is not a prime number.
	 * 
	 * @param l
	 * @return
	 */
	public List<Long> calculatePrimeFactorsOf(long l) {
		List<Long> primeFactors = new ArrayList<Long>();
		for (int i = 2; i <= l; i++) {
            while (l % i == 0) {
                primeFactors.add((long) i);
                l /= i;
            }
        }
		return primeFactors;
	}

	/**
	 * 11. Create an implementation of the rotational cipher, also sometimes called
	 * the Caesar cipher.
	 * 
	 * The Caesar cipher is a simple shift cipher that relies on transposing all the
	 * letters in the alphabet using an integer key between 0 and 26. Using a key of
	 * 0 or 26 will always yield the same output due to modular arithmetic. The
	 * letter is shifted for as many values as the value of the key.
	 * 
	 * The general notation for rotational ciphers is ROT + <key>. The most commonly
	 * used rotational cipher is ROT13.
	 * 
	 * A ROT13 on the Latin alphabet would be as follows:
	 * 
	 * Plain: abcdefghijklmnopqrstuvwxyz Cipher: nopqrstuvwxyzabcdefghijklm It is
	 * stronger than the Atbash cipher because it has 27 possible keys, and 25
	 * usable keys.
	 * 
	 * Ciphertext is written out in the same formatting as the input including
	 * spaces and punctuation.
	 * 
	 * Examples: ROT5 omg gives trl ROT0 c gives c ROT26 Cool gives Cool ROT13 The
	 * quick brown fox jumps over the lazy dog. gives Gur dhvpx oebja sbk whzcf bire
	 * gur ynml qbt. ROT13 Gur dhvpx oebja sbk whzcf bire gur ynml qbt. gives The
	 * quick brown fox jumps over the lazy dog.
	 */
	static class RotationalCipher {
		private int key;

		public RotationalCipher(int key) {
			super();
			this.key = key;
		}

		public String rotate(String string) {
			//create strings representing capital letters and lowercase letters
			String lowers = "abcdefghijklmnopqrstuvwxyz";
			String uppers = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
			//create char array of size string.length()
			char [] c = new char[string.length()];
			//iterate thru string and "rotate" the letter characters one at a time
			//makes sure to run a matcher so that non-alphabet characters are not
			//passed thru the cypher
			for (int i = 0; i < string.length(); i++) {
				String current = string.substring(i, i+1);
				if (current.matches("[^a-zA-z]")) {
					c[i] = string.charAt(i);
				}
				else {
					if (current.compareTo(current.toLowerCase()) == 0) {
						c[i] = lowers.charAt((lowers.indexOf(current) + key) % 26);
					}
					else if(current.compareTo(current.toUpperCase()) == 0) {
						c[i] = uppers.charAt((uppers.indexOf(current) + key) % 26);
					}
				}
			}
			return new String(c);
		}

	}

	/**
	 * 12. Given a number n, determine what the nth prime is.
	 * 
	 * By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see
	 * that the 6th prime is 13.
	 * 
	 * If your language provides methods in the standard library to deal with prime
	 * numbers, pretend they don't exist and implement them yourself.
	 * 
	 * @param i
	 * @return
	 */
	//helper method for determining if a value is prime 
	public boolean isPrime(long n) {
		//2 is the first prime number
		if (n==2) {
			return true;
		}
		//prime numbers are positive and are odd except for 2
		if (n <= 1 || n %2 ==0) {
			return false;
		}
		//checks for prime numbers for all n > 2
		for (int i = 3; i <= Math.sqrt(n); i++) {
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}
			
	public int calculateNthPrime(int i) {
		if (i <= 0) {
			throw new IllegalArgumentException();
		}
		int num = 1; //started at 1 because 1 is not prime (while loop skips 1)
		int count = 0; //started at 0 to allow count to increment to 1 at first iteration
		while (count < i) {
			num++;
			if (isPrime(num)) {
				count++;
			}
		}
		return num;
	}

	/**
	 * 13 & 14. Create an implementation of the atbash cipher, an ancient encryption
	 * system created in the Middle East.
	 * 
	 * The Atbash cipher is a simple substitution cipher that relies on transposing
	 * all the letters in the alphabet such that the resulting alphabet is
	 * backwards. The first letter is replaced with the last letter, the second with
	 * the second-last, and so on.
	 * 
	 * An Atbash cipher for the Latin alphabet would be as follows:
	 * 
	 * Plain: abcdefghijklmnopqrstuvwxyz Cipher: zyxwvutsrqponmlkjihgfedcba It is a
	 * very weak cipher because it only has one possible key, and it is a simple
	 * monoalphabetic substitution cipher. However, this may not have been an issue
	 * in the cipher's time.
	 * 
	 * Ciphertext is written out in groups of fixed length, the traditional group
	 * size being 5 letters, and punctuation is excluded. This is to make it harder
	 * to guess things based on word boundaries.
	 * 
	 * Examples Encoding test gives gvhg Decoding gvhg gives test Decoding gsvjf
	 * rxpyi ldmul cqfnk hlevi gsvoz abwlt gives thequickbrownfoxjumpsoverthelazydog
	 *
	 */
	static class AtbashCipher {

		/**
		 * Question 13
		 * 
		 * @param string
		 * @return
		 */
		public static String encode(String string) {
			//string containing the lowercase alphabet
			String alphabet = "abcdefghijklmnopqrstuvwxyz";
			//remove all characters from string that are not numbers or letters
			//and then make all letters lowercase
			string = string.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();	
			//encode the string character by character into a String ArrayList
			List<String> c = new ArrayList<String>();
			int elementsAdded = 0; //counter for tracking when to add a space
			int count = 0; //counters to make sure the while loop terminates properly
			int end = string.length();
			while (count < end) {
				if (elementsAdded > 0 && elementsAdded % 5 ==0) {
					c.add(" ");
					elementsAdded = 0;
					end++;
				}
				else {
					if (string.substring(count - (end - string.length()), 
							count - (end - string.length())+1).matches("[a-z]")) {
						String current = string.substring(count - (end - string.length()),
								count - (end - string.length()) + 1);
						int pos = alphabet.indexOf(current);
						current = alphabet.substring(25 - pos, 26 - pos);
						c.add(current);
					}
					else {
						c.add(string.substring(count - (end - string.length()),
								count - (end - string.length()) + 1));
					}
					elementsAdded++;
				}
				count++;
			}
			//concatenate elements of c together into final string
			String finalStr = "";
			for (String s : c) {
				finalStr += s;
			}
			return finalStr;
		}

		/**
		 * Question 14
		 * 
		 * @param string
		 * @return
		 */
		public static String decode(String string) {
			//create a string containing the lowercase alphabet
			String alphabet = "abcdefghijklmnopqrstuvwxyz";
			//remove all spaces
			string = string.replaceAll(" ", "");
			//decode the string character by character into a char array
			//ignore numbers and other non-alphabet characters
			char[] c = new char[string.length()];
			for (int i = 0; i < string.length(); i++) {
				if (string.substring(i, i+1).matches("[a-z]")) {
					c[i] = alphabet.charAt(25-alphabet.indexOf(string.substring(i, i+1)));
				}
				else {
					c[i] = string.charAt(i);
				}
			}
			return new String(c);
		}
	}

	/**
	 * 15. The ISBN-10 verification process is used to validate book identification
	 * numbers. These normally contain dashes and look like: 3-598-21508-8
	 * 
	 * ISBN The ISBN-10 format is 9 digits (0 to 9) plus one check character (either
	 * a digit or an X only). In the case the check character is an X, this
	 * represents the value '10'. These may be communicated with or without hyphens,
	 * and can be checked for their validity by the following formula:
	 * 
	 * (x1 * 10 + x2 * 9 + x3 * 8 + x4 * 7 + x5 * 6 + x6 * 5 + x7 * 4 + x8 * 3 + x9
	 * * 2 + x10 * 1) mod 11 == 0 If the result is 0, then it is a valid ISBN-10,
	 * otherwise it is invalid.
	 * 
	 * Example Let's take the ISBN-10 3-598-21508-8. We plug it in to the formula,
	 * and get:
	 * 
	 * (3 * 10 + 5 * 9 + 9 * 8 + 8 * 7 + 2 * 6 + 1 * 5 + 5 * 4 + 0 * 3 + 8 * 2 + 8 *
	 * 1) mod 11 == 0 Since the result is 0, this proves that our ISBN is valid.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isValidIsbn(String string) {
		//check for invalid input
		for (int i = 0; i < string.length(); i++) {
			if (string.substring(i,i+1).matches("[a-zA-WYZ]")) {
				return false;
			}
		}
		//remove hyphens from string
		string = string.replaceAll("-", "");
		//convert X at end of ID to 10 if applicable
		//and apply ISBN validation algorithm to all numbers in ID
		int [] vals = new int[string.length()];
		int count = 10;
		int sum = 0;
		for (int i = 0; i < string.length(); i++) {
			if (string.charAt(i) == 'X') {
				vals[i] = 10;
			}
			else {
				vals[i] = Integer.parseInt(string.substring(i, i+1));
			}
			sum += count*vals[i];
			count--;
		}
		return sum % 11 == 0; //returns result of algorithm
	}

	/**
	 * 16. Determine if a sentence is a pangram. A pangram (Greek: παν γράμμα, pan
	 * gramma, "every letter") is a sentence using every letter of the alphabet at
	 * least once. The best known English pangram is:
	 * 
	 * The quick brown fox jumps over the lazy dog.
	 * 
	 * The alphabet used consists of ASCII letters a to z, inclusive, and is case
	 * insensitive. Input will not contain non-ASCII symbols.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isPangram(String string) {
		//iterate thru the string and store each element into an ArrayList
		//before each addition, check if the current element already exists in the List
		//also, skip over spaces 
		List <String> sl = new ArrayList<String> ();
		for (int i = 0; i < string.length(); i++) {
			if (string.substring(i, i + 1).matches("[a-zA-Z]")) {
				if (!sl.contains(string.substring(i, i + 1))) {
					sl.add(string.substring(i, i + 1));
				}
			}
		}
		//return whether sl.size() is equal to 26
		return sl.size() == 26;
	}

	/**
	 * 17. Calculate the moment when someone has lived for 10^9 seconds.
	 * 
	 * A gigasecond is 109 (1,000,000,000) seconds.
	 * 
	 * @param given
	 * @return
	 */
	public Temporal getGigasecondDate(Temporal given) {
		//Skipping this problem
		// TODO Write an implementation for this method declaration
		return null;
	}

	/**
	 * 18. Given a number, find the sum of all the unique multiples of particular
	 * numbers up to but not including that number.
	 * 
	 * If we list all the natural numbers below 20 that are multiples of 3 or 5, we
	 * get 3, 5, 6, 9, 10, 12, 15, and 18.
	 * 
	 * The sum of these multiples is 78.
	 * 
	 * @param i
	 * @param set
	 * @return
	 */
	public int getSumOfMultiples(int i, int[] set) {
		//use an outer for loop to generate the numbers from 1 to i-1 individually
		//use an inner for loop to check if the ith int is a multiple of any member of set
		//populate a HashSet s with each element found as a multiple; uniqueness is taken
		//care of by definition of a Set
		int num = 0;
		Set<Integer> s = new HashSet<Integer> ();
		for (int j = 0; j < i-1; j++) {
			num++;
			for (int k = 0; k < set.length; k++) {
				if (num % set[k] == 0) {
					s.add(num);
				}
			}
		}
		//compute the sum of the elements in HashSet s
		int sum = 0;
		for (Integer n:s) {
			sum += n;
		}
		return sum;
	}

	/**
	 * 19. Given a number determine whether or not it is valid per the Luhn formula.
	 * 
	 * The Luhn algorithm is a simple checksum formula used to validate a variety of
	 * identification numbers, such as credit card numbers and Canadian Social
	 * Insurance Numbers.
	 * 
	 * The task is to check if a given string is valid.
	 * 
	 * Validating a Number Strings of length 1 or less are not valid. Spaces are
	 * allowed in the input, but they should be stripped before checking. All other
	 * non-digit characters are disallowed.
	 * 
	 * Example 1: valid credit card number 1 4539 1488 0343 6467 The first step of
	 * the Luhn algorithm is to double every second digit, starting from the right.
	 * We will be doubling
	 * 
	 * 4_3_ 1_8_ 0_4_ 6_6_ If doubling the number results in a number greater than 9
	 * then subtract 9 from the product. The results of our doubling:
	 * 
	 * 8569 2478 0383 3437 Then sum all of the digits:
	 * 
	 * 8+5+6+9+2+4+7+8+0+3+8+3+3+4+3+7 = 80 If the sum is evenly divisible by 10,
	 * then the number is valid. This number is valid!
	 * 
	 * Example 2: invalid credit card number 1 8273 1232 7352 0569 Double the second
	 * digits, starting from the right
	 * 
	 * 7253 2262 5312 0539 Sum the digits
	 * 
	 * 7+2+5+3+2+2+6+2+5+3+1+2+0+5+3+9 = 57 57 is not evenly divisible by 10, so
	 * this number is not valid.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isLuhnValid(String string) {
		//check for invalid inputs
		if (string.length() <= 1) {
			return false;
		}
		for (int i = 0; i < string.length(); i++) {
			if (string.substring(i,i+1).matches("[a-zA-Z-]")) {
				return false;
			}
		}
		//remove spaces from string
		//convert space-free string into array of ints
		string = string.replaceAll(" ", "");
		int [] vals = new int[string.length()];
		for (int i = 0; i < string.length(); i++) {
			vals[i] = Integer.parseInt(string.substring(i, i+1));
		}
		//alter values of int array according to algorithm and accrue the sum of the value
		int sum = 0;
		for (int i = vals.length - 1; i >= 0; i--) {
			//for even number length
			if (vals.length % 2 ==0) {
				if (i % 2 == 0) {
					vals[i] *= 2;
					if (vals[i] > 9) {
						vals[i] -= 9;
					}
				}
			}
			//for odd number length
			else {
				if (i % 2 != 0) {
					vals[i] *= 2;
					if (vals[i] > 9) {
						vals[i] -= 9;
					}
				}
			}
			sum += vals[i];
		}
		return sum % 10 == 0;
	}

	/**
	 * 20. Parse and evaluate simple math word problems returning the answer as an
	 * integer.
	 * 
	 * Add two numbers together.
	 * 
	 * What is 5 plus 13?
	 * 
	 * 18
	 * 
	 * Now, perform the other three operations.
	 * 
	 * What is 7 minus 5?
	 * 
	 * 2
	 * 
	 * What is 6 multiplied by 4?
	 * 
	 * 24
	 * 
	 * What is 25 divided by 5?
	 * 
	 * 5
	 * 
	 * @param string
	 * @return
	 */
	public int solveWordProblem(String string) {
		//update an int called flag that denotes the operation described
		//by the word problem in string
		int flag = 0; //flag = 1 is + , flag = 2 is - , flag = 3 is * , and flag = 4 is /
		if (string.contains("plus")) {
			flag = 1;
		}
		if (string.contains("minus")) {
			flag = 2;
		}
		if (string.contains("multiplied by")) {
			flag = 3;
		}
		if (string.contains("divided by")) {
			flag = 4;
		}
		//identify the first occurrence of a hyphen, number, or plus sign
		int firstNumDashPlus = 0;
		for (int i = 0; i < string.length(); i++) {
			if (string.substring(i,i+1).matches("-|[0-9]|[+]")) {
				firstNumDashPlus = i;
				break;
			}
		}
		//retain the substring from firstNumDashPlus onward and remove ending punctuation
		string = string.substring(firstNumDashPlus).replaceAll("[?]|[.]|[!]","");
		//split this new string into string array containing the signed numbers as elements
		String [] sa = string.split("[ plus ]|[ minus ]|[ multiplied by ]|[ divided by ]"); 
		//add non-empty elements (the signed numbers) to an ArrayList
		List<String> as = new ArrayList<String>();
		for (int i = 0; i < sa.length; i++) {
			if (sa[i].compareTo("") != 0) {
				as.add(sa[i]);
			}	
		}
		//iterate thru the ArrayList to convert all elements to their respective signed ints
		//and place them into an int array of size as.size()
		int [] finalNums = new int[as.size()];
		for (int i = 0; i < as.size(); i++) {
			String current = as.get(i);
			if (current.contains("-")) {
				current = current.substring(1);
				finalNums[i] = -1*(Integer.parseInt(current));
			}
			else if(current.contains("+")){
				current = current.substring(1);
				finalNums[i] = (Integer.parseInt(current));
			}
			else {
				finalNums[i] = (Integer.parseInt(current));
			}
		}
		//apply pairwise operation based on value of flag
		//unsure how to generalize this portion to two or more operations
		switch (flag) {
			case 1:
				return finalNums[0] + finalNums[1];
			case 2:
				return finalNums[0] - finalNums[1];
			case 3:
				return finalNums[0] * finalNums[1];
			case 4:
				return finalNums[0] / finalNums[1];
			default:
				return 0;
		}
	}

}
