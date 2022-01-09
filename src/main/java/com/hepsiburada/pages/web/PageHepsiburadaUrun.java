package com.hepsiburada.pages.web;

import java.util.ArrayList;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.hepsiburada.commons.WebCommons;
import com.hepsiburada.utility.log;

public class PageHepsiburadaUrun extends PageHepsiburadaAnaSayfa {

	WebCommons lib;

	public PageHepsiburadaUrun(WebCommons lib) {

		super(lib);
		this.lib = lib;
		PageFactory.initElements(this.lib.driver, this);
	}

	@FindBy(how = How.ID, using = "addToCart")
	WebElement btnSepeteEkle;
	@FindBy(how = How.CLASS_NAME, using = "checkoutui-SalesFrontCash-XeG2a")
	WebElement lblUrunSepettePopup;
	@FindBy(how = How.XPATH, using = "//a[@class='checkoutui-Modal-2iZXl']")
	WebElement btnPopupKapat;
	@FindBy(how = How.XPATH, using = "//button[@class='add-to-basket button small']")
	WebElement btnSepeteEkleFarkliSatici;
	@FindBy(how = How.CLASS_NAME, using = "deny-text")
	WebElement btnOnarimPaketiIstemiyorum;
	@FindBy(how = How.XPATH, using = "//button[contains(text(),'Sepete git')]")
	WebElement btnSepeteGit;
	@FindBy(how = How.XPATH, using = "//div[@class='checkoutui-SalesFrontCash-1js7M']/h6")
	WebElement lblUrunAdi;
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Onarım Paketi')]")
	WebElement lblOnarimPaketi;

	public PageHepsiburadaSepet sepeteEkle() {

		urunTabGecisi();
		lib.click(btnSepeteEkle);
		kontrolSepeteEkleme();
		setIlkUrun();
		lib.click(btnPopupKapat);
		lib.click(btnSepeteEkleFarkliSatici);
		kontrolOnarimPaketi();
		kontrolSepeteEkleme();
		setIkinciUrun();
		lib.click(btnSepeteGit);
		return new PageHepsiburadaSepet(lib);
	}

	private void setIlkUrun() {

		lib.ilkUrun = lblUrunAdi.getText();
	}

	private void setIkinciUrun() {

		lib.ikinciUrun = lblUrunAdi.getText();
	}

	private void urunTabGecisi() {

		ArrayList<String> newTb = new ArrayList<String>(lib.driver.getWindowHandles());
		//switch to new tab
		lib.driver.switchTo()
		          .window(newTb.get(1));
		log.info("Açılan Ürün tabına geçildi.");
	}

	private void kontrolSepeteEkleme() {

		lib.waitForElement(lblUrunSepettePopup);
		lib.Control(lib.getTextOfElement(lblUrunSepettePopup)
		               .contains("Ürün sepetinizde"), "Ürün Sepete Eklendi", "Ürün Sepete Eklenemedi!");
	}

	private void kontrolOnarimPaketi() {

		Boolean checkOnarimPaketi = lib.isElementExist(lblOnarimPaketi);
		if (Boolean.TRUE.equals(checkOnarimPaketi)) {
			lib.click(btnOnarimPaketiIstemiyorum);
		}
	}
}
