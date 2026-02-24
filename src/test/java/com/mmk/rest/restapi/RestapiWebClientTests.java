package com.mmk.rest.restapi;

import org.htmlunit.WebClient;
import org.htmlunit.html.DomNode;
import org.htmlunit.html.HtmlPage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
class RestapiWebClientTests {  //Test app like ahuman using it (HTML page) Selenium like browser based testing

    @Autowired
    WebClient webClient;

    @Test
    void testGetAllPage() throws Exception {

        HtmlPage page = webClient.getPage("/index.html");
        DomNode html = page.querySelector("html");
        DomNode p = html.querySelector("p");

        assertThat(html).isNotNull();
        assertThat(p.getTextContent().equals("Products list"));
    }

}
