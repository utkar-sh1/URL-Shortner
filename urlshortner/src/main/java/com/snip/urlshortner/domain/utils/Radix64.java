package com.snip.urlshortner.domain.utils;

import org.springframework.stereotype.Component;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class Radix64 {

	private static final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz-_";
	private static final Map<Character, Integer> ALPHABET_INDEX = IntStream.range(0, ALPHABET.length())
			.boxed()
			.collect(Collectors.toMap(ALPHABET::charAt, i -> i));
	private static final int MAX_SAFE_STRING_LENGTH = 10;
	private static final long MAX_SAFE_NUMBER = 1152921504606846976L; // 64^10

	public static class Radix64Exception extends RuntimeException {
		public Radix64Exception(String message) {
			super(message);
		}
	}

	/**
	 * Encodes a number into a Radix64 string.
	 *
	 * @param number The number to encode
	 * @return The Radix64 encoded string
	 * @throws Radix64Exception if the number exceeds the maximum safe number
	 */
	public static String encode(@Positive final long number) {
		validateNumberForEncoding(number);

        StringBuilder result = new StringBuilder();
		long remainingNumber = number;

		while (remainingNumber > 0) {
			int remainder = (int) (remainingNumber % 64);
			result.append(ALPHABET.charAt(remainder));
			remainingNumber /= 64;
		}

		return result.reverse().toString();
	}

	/**
	 * Decodes a Radix64 string into a number.
	 *
	 * @param str The Radix64 string to decode
	 * @return The decoded number
	 * @throws Radix64Exception if the string is invalid or too long
	 */
	public static long decode(@NotBlank final String str) {
		validateStringForDecoding(str);

		return str.chars()
				.mapToObj(ch -> (char) ch)
				.reduce(0L, (result, c) -> {
					validateCharacter(c);
					return result * 64 + ALPHABET_INDEX.get(c);
				}, Long::sum);
	}

	private static void validateNumberForEncoding(long number) {
		if (number > MAX_SAFE_NUMBER) {
			throw new Radix64Exception("Number greater than " + MAX_SAFE_NUMBER);
		}
	}

	private static void validateStringForDecoding(String str) {
		if (str == null || str.isEmpty()) {
			throw new Radix64Exception("Input string cannot be null or empty");
		}
		if (str.length() > MAX_SAFE_STRING_LENGTH) {
			throw new Radix64Exception("String longer than " + MAX_SAFE_STRING_LENGTH + " characters");
		}
	}

	private static void validateCharacter(char c) {
		if (!ALPHABET_INDEX.containsKey(c)) {
			throw new Radix64Exception("Invalid character in Radix64 string: " + c);
		}
	}
}