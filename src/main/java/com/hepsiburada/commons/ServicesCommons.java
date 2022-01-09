package com.hepsiburada.commons;

import com.hepsiburada.basepages.ServicesAbstactBase;
import com.hepsiburada.data.DataFinder;
import com.hepsiburada.data.GetData.Url;

public class ServicesCommons extends ServicesAbstactBase {

	public ServicesCommons() {

		url = DataFinder.getUrl(Url.SERVICES_URL);
	}

	/**
	 * API servisi için common url döner.
	 * 
	 * @return string output.
	 * @author Bilal Acar
	 */
	public String getServiceUrl() {

		return DataFinder.getUrl(Url.SERVICES_URL);
	}
}
