package com.fancode.common;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

import com.fancode.config.Config;

public class TestBase {

	@BeforeClass
	public void setUp() {
		RestAssured.baseURI = Config.getBaseUri();
	}
}
