package com.oreilly.sacon.library.controllers;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.oreilly.sacon.library.SaconLibraryApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SaconLibraryApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IndexControllerTest {

    @LocalServerPort
    private int randomServerPort;

    @Test
    public void shouldShowCatalogWhenRequestingIndex() throws Exception {
        HtmlPage page = new WebClient().getPage("http://127.0.0.1:" + randomServerPort + "/");

        assertThat(page.getBody().getTextContent()).contains("Catalog");
    }
}