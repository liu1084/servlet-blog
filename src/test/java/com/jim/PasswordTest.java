package com.jim;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.util.UUID;

/**
 * Created by jim on 2017/7/25.
 * This class is ...
 */
@Test
public class PasswordTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(PasswordTest.class);

	@Test
	public void encode() {
		String pass = "1";
		String salt = UUID.randomUUID().toString();
		LOGGER.debug(salt);
		LOGGER.debug(DigestUtils.shaHex(pass + salt));
	}

	@Test
	public void decode() {

	}
}
