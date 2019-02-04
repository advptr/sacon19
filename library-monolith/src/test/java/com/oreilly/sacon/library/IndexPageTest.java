package com.oreilly.sacon.library;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.oreilly.sacon.library.catalog.Catalog;
import com.oreilly.sacon.library.dao.Item;
import com.oreilly.sacon.library.controllers.IndexController;
import com.oreilly.sacon.library.models.Book;
import com.oreilly.sacon.library.repositories.BookRepository;
import com.oreilly.sacon.library.controllers.CatalogController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@RunWith(SpringRunner.class)
@WebMvcTest({IndexController.class, CatalogController.class})
public class IndexPageTest {

    @Autowired
    private WebClient webClient;

    @MockBean
    private BookRepository bookRepository;

    @MockBean
    private Catalog catalog;

    @Test
    public void shouldShowCatalogWhenRequestingIndex() throws Exception {
        Book book = mock(Book.class);
        given(catalog.getAllBooks()).willReturn(Arrays.asList(book));

        HtmlPage page = this.webClient.getPage("/");
        assertThat(page.getBody().getTextContent()).contains("Catalog");
    }

}