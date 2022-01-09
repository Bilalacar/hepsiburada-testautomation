package com.hepsiburada.pages.web;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.hepsiburada.commons.WebCommons;
import com.hepsiburada.utility.log;

public class PageHepsiburadaSepet extends PageHepsiburadaAnaSayfa {

	WebCommons lib;

	public PageHepsiburadaSepet(WebCommons lib) {

		super(lib);
		this.lib = lib;
		PageFactory.initElements(this.lib.driver, this);
	}

	@FindBy(how = How.XPATH, using = "//div[@class='product_name_3Lh3t']")
	List<WebElement> lblUrunler;
	@FindBy(how = How.CLASS_NAME, using = "delete_all_2uTds")
	WebElement btnSecilenleriSil;
	@FindBy(how = How.CLASS_NAME, using = "red_3m-Kl")
	WebElement btnSil;

	public PageHepsiburadaSepet sepetiKontrolEt() {

		log.info("Sepetteki Ürün Sayısı: " + lblUrunler.size());
		lib.Control(lib.getTextOfElement(lblUrunler.get(0))
		               .contains(lib.ilkUrun)
		        && lib.getTextOfElement(lblUrunler.get(1))
		              .contains(lib.ikinciUrun), "Ürünlerin Sepete Eklendiği Görüldü", "Ürünleri Sepete Ekleme Başarısız");
		return this;
	}

	public PageHepsiburadaSepet sepetiBosalt() {

		lib.click(btnSecilenleriSil);
		lib.click(btnSil);
		return this;
	}
}
