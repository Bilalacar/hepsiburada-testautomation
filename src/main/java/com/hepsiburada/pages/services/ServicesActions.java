package com.hepsiburada.pages.services;

import com.hepsiburada.commons.ServicesCommons;

public class ServicesActions {

	public ServicesCommons lib;

	public ServicesActions() {

		lib = new ServicesCommons();
	}

	public ServicesActions(ServicesCommons lib) {

		this.lib = lib;
	}

	public ServicesActions checkAllGrocery() {

		lib.servisCagirGet(lib.getServiceUrl(), "allGrocery", 200);

		return this;
	}

	public ServicesActions checkGrapes(String fruit) {

		String response = "";
		response = lib.servisCagirGet(lib.getServiceUrl(), "allGrocery/" + fruit, 200);
		String groceryName = lib.getGroceryName(response);
		lib.Control(groceryName.equals(fruit), "Grocery Name: "
		                                       + fruit
		                                       + " kontrolu başarılı", "Grocery Name kontrolu başarısız! Servisten gelen Grocery Name: "
		                                                               + groceryName
		                                                               + " Beklenen Değer: "
		                                                               + fruit);

		return this;
	}

	public ServicesActions addGrocery() {

		String request = "{\"id\": 4,\"name\": \"string\",\"price\": 12.3,\"stock\": 3}";

		lib.servisCagirPost(lib.getServiceUrl(), "allGrocery/add", request, 200);

		return this;
	}

	public ServicesActions checkNotFound(String fruit) {

		lib.servisCagirGet(lib.getServiceUrl(), "allGrocery/" + fruit, 404);

		return this;
	}

	public ServicesActions checkBadRequest() {

		String request = "hello world";

		lib.servisCagirPost(lib.getServiceUrl(), "allGrocery/add", request, 400);

		return this;
	}
}