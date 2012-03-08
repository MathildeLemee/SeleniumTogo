package fr.javafreelance.fluentlenium;

import org.fluentlenium.adapter.FluentTest;
import org.fluentlenium.core.annotation.Page;
import org.junit.Before;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class IntegrationJTest extends FluentTest {


    @Page
    HomePage page1;

    @Before
    public void before() {
        goTo(page1);
    }

    @Test
    public void when_come_to_first_page_nav_is_set() {
        page1.assertDisplayPaginationFromXToY(1, 10);
    }
    @Test
    public void when_click_to_next_then_print_next_page() {
        page1.assertDisplayPaginationFromXToY(1, 10);
        page1.clickOnNext();
        page1.assertDisplayPaginationFromXToY(11, 20);
    }

    @Test
    public void a_user_can_search() {
        page1.fillSearchBoxWith("Apple");
        page1.submitSearchBox();

        assertThat($("section h1").first().getText()).isEqualTo("13 computers found");
    }

    @Test
    public void when_no_discontinued_date_then_error() {

        page1.editComputerWithName("Apple II");
        page1.setDiscontinuedValue("10-10-2001");
        page1.validEditorForm();

        assertThat($("div.error").size()).isEqualTo(1);
        assertThat($("div.error label").first().getText()).isEqualTo("Discontinued date");
    }


    @Test
    public void when_wrond_discontinued_date_then_error() {
        page1.editComputerWithName("Apple II");

        page1.setDiscontinuedValue("xxx");
        page1.validEditorForm();

        assertThat($("div.error").size()).isEqualTo(1);
        assertThat($("div.error label").first().getText()).isEqualTo("Discontinued date");


    }

    @Test
    public void a_user_can_edit_a_computer() {
        page1.editComputerWithName("Apple II");

        page1.setDiscontinuedValue("");
        page1.validEditorForm();

        assertThat($("section h1").first().getText()).isEqualTo("574 computers found");
        assertThat($(".alert-message").first().getText()).isEqualTo("Done! Computer Apple II has been updated");
    }


}
