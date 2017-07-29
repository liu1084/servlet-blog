package com.jim;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

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
		String salt = "222";
		LOGGER.debug(salt);
		LOGGER.debug(DigestUtils.md5Hex(pass + salt));
	}

	@Test
	public void decode() {

	}
}
