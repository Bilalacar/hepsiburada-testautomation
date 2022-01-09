package com.hepsiburada.data;

public class GetData {

	public static String FILESYSDATE;
	public static final int DEFAULT_WAIT = 20;
	public static final int DEFAULT_WAIT_LOADERBOX = 40;

	public enum Url {
		HEPSIBURADA_URL,
		SERVICES_URL;

	}

	public enum Data {

		EMAIL,
		PASSWORD,
		AD,
		SOYAD;

		public String getValue() {

			return DataFinder.getData(this);
		}
	}
}
