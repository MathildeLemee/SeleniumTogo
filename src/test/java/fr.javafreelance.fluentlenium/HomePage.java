package fr.javafreelance.fluentlenium;

import org.fluentlenium.core.*;
import org.fluentlenium.core.domain.FluentWebElement;
import static org.fest.assertions.Assertions.*;
import static org.fluentlenium.core.filter.FilterConstructor.withText;


public class HomePage extends FluentPage {
   private FluentWebElement searchbox;
   private FluentWebElement searchsubmit;

    public String getUrl() {
        return "http://192.168.56.1:9000";
    }



    public void assertAt() {
        assertThat($("header h1").first().getText()).isEqualTo("Play 2.0 sample application ? Computer database");
        assertThat($("section h1").first().getText()).isEqualTo("574 computers found");
    }
    public void assertDisplayPaginationFromXToY(int x,int y) {
        assertThat($("#pagination li.current").first().getText()).isEqualTo("Displaying " + x + " to " + y + " of 574");
    }

    public void clickOnNext() {
        $("#pagination li.next a").click();
    }

    public void fillSearchBoxWith(String text) {
        searchbox.text(text);
    }

     public void submitSearchBox() {
        searchsubmit.submit();
    }
    public void editComputerWithName(String name){
    fillSearchBoxWith(name);
    submitSearchBox();
    $("a", withText(name)).click();

    }

    public void validEditorForm() {
       $("input.primary").click();
    }

    public void setDiscontinuedValue(String text) {
        $("#discontinued").text(text);
    }
}
