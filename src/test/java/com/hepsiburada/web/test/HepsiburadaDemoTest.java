package com.hepsiburada.web.test;

import org.testng.annotations.Test;

import com.hepsiburada.controller.WebController;

public class HepsiburadaDemoTest extends WebController {

	@Test
	public void Hepsiburada_Login_SepeteEkle() {

		startTest().anaSayfayaGit()
		           .girisYap()
		           .aramaYap("samsung")
		           .uruneGit(3)
		           .sepeteEkle()
		           .sepetiKontrolEt()
		           .sepetiBosalt();
	}

	@Test
	public void Hepsiburada_NoLogin_SepeteEkle() {

		startTest().anaSayfayaGit()
		           .aramaYap("apple")
		           .uruneGit(1)
		           .sepeteEkle()
		           .sepetiKontrolEt()
		           .sepetiBosalt();
	}
}
