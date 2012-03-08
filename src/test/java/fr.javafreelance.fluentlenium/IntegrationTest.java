package fr.javafreelance.fluentlenium;

import org.fluentlenium.adapter.FluentTestNg;
import org.fluentlenium.core.annotation.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static org.fest.assertions.Assertions.assertThat;

public class IntegrationTest extends FluentTestNg {


    @Page
    HomePage page1;

    @Override
    public WebDriver getDefaultDriver() {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setBrowserName("firefox");
        try {
            return new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), desiredCapabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void when_come_to_first_page_nav_is_set() {
        goTo(page1);
        page1.assertDisplayPaginationFromXToY(1, 10);
    }

    @Test
    public void when_click_to_next_then_print_next_page() {
        goTo(page1);
        page1.assertDisplayPaginationFromXToY(1, 10);
        page1.clickOnNext();
        page1.assertDisplayPaginationFromXToY(11, 20);
    }

    @Test
    public void a_user_can_search() {
        goTo(page1);
        page1.fillSearchBoxWith("Apple");
        page1.submitSearchBox();

        assertThat($("section h1").first().getText()).isEqualTo("13 computers found");
    }


    @Test
    public void when_wrond_discontinued_date_then_error() {
        goTo(page1);
        page1.editComputerWithName("Apple II");

        page1.setDiscontinuedValue("xxx");
        page1.validEditorForm();

        assertThat($("div.error").size()).isEqualTo(1);
        assertThat($("div.error label").first().getText()).isEqualTo("Discontinued date");


    }

    @Test
    public void a_user_can_edit_a_computer() {
        goTo(page1);
        page1.editComputerWithName("Apple II");

        page1.setDiscontinuedValue("");
        page1.validEditorForm();

        assertThat($("section h1").first().getText()).isEqualTo("574 computers found");
        assertThat($(".alert-message").first().getText()).isEqualTo("Done! Computer Apple II has been updated");
    }


}
