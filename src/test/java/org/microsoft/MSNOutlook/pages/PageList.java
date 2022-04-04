package org.microsoft.MSNOutlook.pages;

import org.openqa.selenium.WebElement;

import java.util.List;

public class PageList {
    public List<WebElement> pages;

    PageList(List<WebElement> pages) {
        this.pages = pages;
    }

    public List<WebElement> getPages() {
        return pages;
    }
}