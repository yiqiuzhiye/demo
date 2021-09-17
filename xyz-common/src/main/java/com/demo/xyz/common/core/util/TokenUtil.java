/* Copyright 2004, 2005, 2006 Acegi Technology Pty Limited
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.demo.xyz.common.core.util;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NestedRuntimeException;
import org.springframework.util.Assert;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.spec.KeySpec;
import java.util.Calendar;

/**
 * token加解密
 * 
 * @author charles
 *
 */
public final class TokenUtil {

	private final static Logger logger = LoggerFactory.getLogger(TokenUtil.class);

	private TokenUtil() {
	}

	public static byte[] stringToByteArray(String input) {
		Assert.hasLength(input, "Input required");
		try {
			return input.getBytes("UTF-8");
		} catch (UnsupportedEncodingException fallbackToDefault) {
			return input.getBytes();
		}
	}

	public static String byteArrayToString(byte[] byteArray) {
		Assert.notNull(byteArray, "ByteArray required");
		Assert.isTrue(byteArray.length > 0, "ByteArray cannot be empty");
		try {
			return new String(byteArray, "UTF8");
		} catch (final UnsupportedEncodingException e) {
			return new String(byteArray);
		}
	}

	private static byte[] cipher(String key, byte[] passedBytes, int cipherMode) throws EncryptionException {
		try {
			final KeySpec keySpec = new DESedeKeySpec(stringToByteArray(key));
			final SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
			final Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
			final SecretKey secretKey = keyFactory.generateSecret(keySpec);
			cipher.init(cipherMode, secretKey);
			return cipher.doFinal(passedBytes);
		} catch (final Exception e) {
			throw new EncryptionException(e.getMessage(), e);
		}
	}

	private static String encrypt(String key, String inputString) throws EncryptionException {
		isValidKey(key);
		final byte[] cipherText = cipher(key, stringToByteArray(inputString), Cipher.ENCRYPT_MODE);
		return Hex.encodeHexString(cipherText);
	}

	private static String decrypt(String key, String inputString) throws EncryptionException {
		Assert.hasText(key, "A key is required to attempt decryption");

		byte[] cipherText = null;
		try {
			cipherText = cipher(key, Hex.decodeHex(inputString.toCharArray()), Cipher.DECRYPT_MODE);
		} catch (DecoderException e) {

			logger.error("TokenEncryptionUti decodeHex({})exception", inputString.toCharArray(), e);
			throw new EncryptionException(e.getMessage(), e);
		}
		return byteArrayToString(cipherText);
	}

	private static void isValidKey(String key) {
		Assert.hasText(key, "A key to perform the encryption is required");
		Assert.isTrue(key.length() >= 24, "Key must be at least 24 characters long");
	}

	public static String genUserToken(String loginName, String encryptKey, Integer expiredDays) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_WEEK, expiredDays);
		long expiresIn = calendar.getTimeInMillis();
		String plainText = loginName + "|" + expiresIn;
		String token = encrypt(encryptKey, plainText);
		logger.info("TokenEncryptionUtil genUserToken({}) for user({})", token, loginName);
		return token;
	}

	public static String genUserToken(String loginName, Integer orgid, String encryptKey, Integer expiredDays) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_WEEK, expiredDays);
		long expiresIn = calendar.getTimeInMillis();
		String plainText = loginName + "|" + expiresIn + "|" + orgid;
		String token = encrypt(encryptKey, plainText);
		logger.info("TokenEncryptionUtil genUserToken({}) for user({})", token, loginName);
		return token;
	}

	public static String decodeUserToken(String token, String encryptKey) {

		return decrypt(encryptKey, token);

	}

	public static String genUserPassword(String loginName, String encryptKey) {
		long createts = System.currentTimeMillis();
		String plainText = loginName + "|" + createts;
		String token = encrypt(encryptKey, plainText);
		logger.info("TokenEncryptionUtil genUserPassword({}) for user({})", token, loginName);
		return token;
	}

	public static String decodeUserPassword(String encryptPassword, String encryptKey) {

		return decrypt(encryptKey, encryptPassword);

	}

	/**
	 * 异常.
	 */
	public static class EncryptionException extends NestedRuntimeException {
		private static final long serialVersionUID = 1L;

		public EncryptionException(String message, Throwable t) {
			super(message, t);
		}

		public EncryptionException(String message) {
			super(message);
		}
	}

}
