package fr.javafreelance.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static org.fest.assertions.Assertions.assertThat;

public class CheckWebSiteTestSeleniumGrid {

    WebDriver webDriver;


    @Test
    public void globalTest() throws MalformedURLException, InterruptedException {
        try {
            webDriver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), DesiredCapabilities.firefox());
            webDriver.get("http://192.168.56.1:9000");

            assertThat(webDriver.findElement(By.cssSelector("section h1")).getText()).isEqualTo("574 computers found");
            assertThat(webDriver.findElement(By.cssSelector("#pagination li.current")).getText()).isEqualTo("Displaying 1 to 10 of 574");

            webDriver.findElement(By.cssSelector("#pagination li.next a")).click();
            assertThat(webDriver.findElement(By.cssSelector("#pagination li.current")).getText()).isEqualTo("Displaying 11 to 20 of 574");

            webDriver.findElement(By.cssSelector("#searchbox")).sendKeys("Apple");
            webDriver.findElement(By.cssSelector("#searchbox")).submit();

            assertThat(webDriver.findElement(By.cssSelector("section h1")).getText()).isEqualTo("13 computers found");

            webDriver.findElement(By.linkText("Apple II Plus")).click();
            assertThat(webDriver.findElement(By.cssSelector("section h1")).getText()).isEqualTo("Edit computer");


            webDriver.findElement(By.id("discontinued")).sendKeys("xxx");
            webDriver.findElement(By.cssSelector("input.primary")).click();


            assertThat(webDriver.findElements(By.cssSelector("div.error")).size()).isEqualTo(1);
            assertThat(webDriver.findElement(By.cssSelector("div.error label")).getText()).isEqualTo("Discontinued date");

            webDriver.findElement(By.id("discontinued")).clear();
            webDriver.findElement(By.id("discontinued")).sendKeys("");
            webDriver.findElement(By.cssSelector("input.primary")).click();

            assertThat(webDriver.findElement(By.cssSelector("section h1")).getText()).isEqualTo("574 computers found");
            assertThat(webDriver.findElement(By.className("alert-message")).getText()).isEqualTo("Done! Computer Apple II Plus has been updated");

        } finally {
            webDriver.quit();
        }
        /*browser.$("#searchbox").text("Apple");
        browser.$("#searchsubmit").click();

        browser.$("a", withText("Apple II")).click();
        browser.$("input.danger").click();

        assertThat(browser.$("section h1").first().getText()).isEqualTo("573 computers found");
        assertThat(browser.$(".alert-message").first().getText()).isEqualTo("Done! Computer has been deleted");

        browser.$("#searchbox").text("Apple");
        browser.$("#searchsubmit").click();

        assertThat(browser.$("section h1").first().getText()).isEqualTo("12 computers found");

        page1.assertDisplayPaginationFromXToY(1, 10);
        page1.clickOnNext();
        page1.assertDisplayPaginationFromXToY(11, 20);*/
    }
    /*
    @Test
    public void a_user_can_search() {
        System.err.println("2");


        goTo("http://www.google.com");
        System.err.println("apres goto 2");
        page1.searchbox.text("Apple");
        page1.searchsubmit.click();
        assertThat($("section h1").first().getText()).isEqualTo("13 computers found");
    }

    @Test
    public void a_user_can_edit_computer() {
        Logger.of("play").info("meth3");
        goTo("http://localhost:3333");
        page1.searchbox.text("Apple");
        page1.searchsubmit.click();
        $("a", withText("Apple II")).click();

        assertThat($("section h1").first().getText()).isEqualTo("Edit computer");

        $("#discontinued").text("10-10-2001");
        $("input.primary").click();

        assertThat($("div.error").size()).isEqualTo(1);
        assertThat($("div.error label").first().getText()).isEqualTo("Discontinued date");

        $("#discontinued").text("xxx");
        $("input.primary").click();

        assertThat($("div.error").size()).isEqualTo(1);
        assertThat($("div.error label").first().getText()).isEqualTo("Discontinued date");

        $("#discontinued").text("");
        $("input.primary").click();

        assertThat($("section h1").first().getText()).isEqualTo("574 computers found");
        assertThat($(".alert-message").first().getText()).isEqualTo("Done! Computer Apple II has been updated");

    }

    @Test
    public void a_user_can_delete_a_computer() {
        Logger.of("play").info("meth4");

        goTo(page1);
        $("#searchbox").text("Apple");
        $("#searchsubmit").click();

        $("a", withText("Apple II")).click();
        $("input.danger").click();

        assertThat($("section h1").first().getText()).isEqualTo("573 computers found");
        assertThat($(".alert-message").first().getText()).isEqualTo("Done! Computer has been deleted");

        $("#searchbox").text("Apple");
        $("#searchsubmit").click();

        assertThat($("section h1").first().getText()).isEqualTo("12 computers found");


    }

    */

}
