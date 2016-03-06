package com.mycms.utils;

import java.io.Serializable;

public class HostUrl implements Serializable {
	public final static String hostUrl = "http://localhost:9089/#/articleDetail/";

	public static String getHosturl() {
		return hostUrl;
	}
	
}
