package com.hepsiburada.pages.web;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.hepsiburada.commons.WebCommons;
import com.hepsiburada.data.GetData.Data;
import com.hepsiburada.data.GetData.Url;

public class PageHepsiburadaAnaSayfa {

	WebCommons lib;

	public PageHepsiburadaAnaSayfa(WebDriver driver) {

		lib = new WebCommons(driver);
		PageFactory.initElements(driver, this);
	}

	public PageHepsiburadaAnaSayfa(WebCommons lib) {

		this.lib = lib;
		PageFactory.initElements(this.lib.driver, this);
	}

	@FindBy(how = How.ID, using = "myAccount")
	WebElement btnGirisveyaUyeOl;
	@FindBy(how = How.ID, using = "login")
	WebElement btnGirisYap;
	@FindBy(how = How.XPATH, using = "//input[@class='desktopOldAutosuggestTheme-input']")
	WebElement txtArama;
	@FindBy(how = How.CLASS_NAME, using = "SearchBoxOld-buttonContainer")
	WebElement btnAra;
	@FindBy(how = How.CLASS_NAME, using = "sf-OldMyAccount-1k66b")
	WebElement lblAdSoyad;
	@FindBy(how = How.CSS, using = ".logo-hepsiburada")
	WebElement lblHepsiBurada;
	@FindBy(how = How.XPATH, using = "//a[@title='Hesabım']")
	WebElement lblHesabim;
	@FindBy(how = How.XPATH, using = "//div[@class='searchResultSummaryBar-mainText']")
	WebElement lblAramaSonuc;

	public PageHepsiburadaAnaSayfa anaSayfayaGit() {

		lib.navigateTo(Url.HEPSIBURADA_URL);

		lib.Control(lib.isElementExist(lblHepsiBurada), "'Ana Sayfa' sayfası açıldı.", "'Ana Sayfa' sayfası açılamadı!");
		return this;
	}

	public PageHepsiburadaAnaSayfa girisYap() {

		girisEkraninaGit().login(Data.EMAIL, Data.PASSWORD)
		                  .kontrolKullaniciGirisi()
		                  .kontrolAdSoyad();

		return this;
	}

	public PageHepsiburadaAramaListesi aramaYap(String arama) {

		lib.sendKeys(txtArama, arama);
		lib.click(btnAra);
		lib.Control(lib.isElementExist(lblAramaSonuc), "'"
		                                               + arama
		                                               + "' için sonuç bulundu.", "'"
		                                                                          + arama
		                                                                          + "' için sonuç bulunamadı!");

		return new PageHepsiburadaAramaListesi(lib);
	}

	private PageHepsiburadaLogin girisEkraninaGit() {

		lib.click(btnGirisveyaUyeOl);
		lib.click(btnGirisYap);
		return new PageHepsiburadaLogin(lib);
	}

	private PageHepsiburadaAnaSayfa kontrolKullaniciGirisi() {

		lib.Control(lib.isElementExist(lblHesabim), "Kullanıcı Girişi Başarılı", "Kullanıcı Girişi Başarısız");
		return this;
	}

	private PageHepsiburadaAnaSayfa kontrolAdSoyad() {

		lib.Control(lib.getTextOfElement(lblAdSoyad)
		               .contains(Data.AD.getValue()
		                         + " "
		                         + Data.SOYAD.getValue()), "Ad Soyad Kontrolu Başarılı. Ad/Soyad: "
		                                                   + lblAdSoyad.getText(), "Ad Soyad Kontrolu Başarısız");
		return this;
	}
}
