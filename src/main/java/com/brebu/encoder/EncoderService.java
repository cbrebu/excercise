package com.brebu.encoder;

import lombok.extern.log4j.Log4j2;

/**
 * Service for encoding strings using a simple run-length encoding algorithm.
 */
@Log4j2
public class EncoderService {

    /**
     * Encode the given string using a simple run-length encoding algorithm.
     * @param plainText string to encode
     * @return encoded string
     */
    public String encode(String plainText) {

        if (plainText == null) {
            log.error("Decoded string cannot be null");
            throw new IllegalArgumentException("Decoded string cannot be null");
        }

        if (plainText.isEmpty()) {
            log.info("Encoded string: {}", "");
            return "";
        }

        StringBuilder result = new StringBuilder();
        int[] codePoints = plainText.codePoints().toArray();

        int currentCodePoint = codePoints[0];
        int count = 1;

        for (int i = 1; i <= codePoints.length; i++) {
            if (i < codePoints.length && codePoints[i] == currentCodePoint) {
                count++;
            } else {
                result.appendCodePoint(currentCodePoint).append(count);
                if (i < codePoints.length) {
                    currentCodePoint = codePoints[i];
                    count = 1;
                }
            }
        }

        log.info("Encoded string: {}", result.toString());
        return result.toString();
    }

}

