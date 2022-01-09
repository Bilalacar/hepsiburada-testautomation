package com.hepsiburada.pages.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.hepsiburada.commons.WebCommons;

public class PageHepsiburadaAramaListesi extends PageHepsiburadaAnaSayfa {

	WebCommons lib;

	public PageHepsiburadaAramaListesi(WebCommons lib) {

		super(lib);
		this.lib = lib;
		PageFactory.initElements(this.lib.driver, this);
	}

	@FindBy(how = How.XPATH, using = "//div[@class='pagination']/a[@class='active ']")
	WebElement lblAktifSayfa;

	public PageHepsiburadaUrun uruneGit(Integer siraNo) {

		lib.click(By.xpath("//ul[@class='productListContent-wrapper productListContent-grid-0']/li[" + siraNo + "]"));

		return new PageHepsiburadaUrun(lib);
	}
}
