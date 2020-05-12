package com.planittesting.jupitertoys.model.table;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Table {

    private WebElement tableElement;

    public Table(WebElement tableElement) {
        this.tableElement = tableElement;
    }

    public WebElement getTableElement() { return this.tableElement; }

    public List<WebElement> getTableRows() {
        return tableElement.findElements(By.cssSelector("tbody tr"));
    }

    private String getColumnIndexByHeader(String headerValue) {
        List<WebElement> headers = tableElement.findElements(By.tagName("th"));
        for (WebElement header : headers) {
            if (header.getText().trim().equalsIgnoreCase(headerValue.trim())) {
                return Integer.toString(headers.indexOf(header) + 1);
            }
        }
        throw new IllegalArgumentException("Invalid header value");
    }

    private String getRowIndexBySearchItem(String searchItemColumnHeader, String searchItemValue) {
        List<WebElement> rows = getTableRows();
        for (WebElement row : rows) {
            WebElement searchItem = row.findElement(By.xpath("td[" + getColumnIndexByHeader(searchItemColumnHeader) + "]"));
            if (searchItem.getAttribute("innerText").trim().equalsIgnoreCase(searchItemValue)) {
                return Integer.toString(rows.indexOf(row) + 1);
            }
        }
        throw new IllegalArgumentException("Invalid searchItem value");
    }

    public WebElement getCell(String searchColumnHeader, String searchValue, String returnColumnHeader) {
        String rowIndex = getRowIndexBySearchItem(searchColumnHeader, searchValue);
        String columnIndex = getColumnIndexByHeader(returnColumnHeader);
        return tableElement.findElement(By.xpath("tbody/tr[" + rowIndex + "]/td[" + columnIndex + "]"));
    }

    public boolean isItemPresent(String searchItemColumnHeader, String searchItemValue) {
        List<WebElement> rows = getTableRows();
        for (WebElement row : rows) {
            WebElement searchItem = row.findElement(By.xpath("td[" + getColumnIndexByHeader(searchItemColumnHeader) + "]"));
            if (searchItem.getAttribute("innerText").trim().equalsIgnoreCase(searchItemValue)) {
                return true;
            }
        }
        return false;
    }
}
