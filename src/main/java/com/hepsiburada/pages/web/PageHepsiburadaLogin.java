package com.hepsiburada.pages.web;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.hepsiburada.commons.WebCommons;
import com.hepsiburada.data.GetData.Data;

public class PageHepsiburadaLogin {

	WebCommons lib;
	int delay;

	public PageHepsiburadaLogin(WebCommons lib) {

		this.lib = lib;
		PageFactory.initElements(this.lib.driver, this);
	}

	@FindBy(how = How.ID, using = "txtUserName")
	WebElement txtEmail;
	@FindBy(how = How.ID, using = "txtPassword")
	WebElement txtSifre;
	@FindBy(how = How.ID, using = "btnLogin")
	WebElement btnGirisYapEmail;
	@FindBy(how = How.ID, using = "btnEmailSelect")
	WebElement btnGirisYapSifre;

	public PageHepsiburadaAnaSayfa login(Data email, Data password) {

		emailGir(email.getValue()).girisYapaTiklaEmail()
		                          .sifreGir(password.getValue())
		                          .girisYapaTiklaSifre();

		return new PageHepsiburadaAnaSayfa(lib);
	}

	private PageHepsiburadaLogin emailGir(String email) {

		lib.sendKeys(txtEmail, email);
		return this;
	}

	private PageHepsiburadaLogin sifreGir(String sifre) {

		lib.sendKeys(txtSifre, sifre);
		return this;
	}

	private PageHepsiburadaLogin girisYapaTiklaEmail() {

		lib.click(btnGirisYapEmail);
		return this;
	}

	private PageHepsiburadaAnaSayfa girisYapaTiklaSifre() {

		lib.click(btnGirisYapSifre);
		return new PageHepsiburadaAnaSayfa(lib);
	}
}
