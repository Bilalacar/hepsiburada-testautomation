package com.hepsiburada.services.test;

import org.testng.annotations.Test;

import com.hepsiburada.controller.ServiceController;

public class HepsiburadaServicesTest extends ServiceController {

	@Test
	public void Hepsiburada_Services_CheckAllGrocery() {

		startTest().checkAllGrocery();
	}

	@Test
	public void Hepsiburada_Services_CheckGrapes() {

		startTest().checkGrapes("grapes");
	}

	@Test
	public void Hepsiburada_Services_AddGrocery() {

		startTest().addGrocery();
	}

	@Test
	public void Hepsiburada_Services_Check404NotFound() {

		startTest().checkNotFound("lemon");
	}

	@Test
	public void Hepsiburada_Services_Check400BadRequest() {

		startTest().checkBadRequest();
	}
}
