package com.oreilly.sacon.library.availability;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AvailabilityTest {

    @Autowired
    private Availability availability;

    @MockBean
    private BookAvailabilityRepository bookAvailabilityRepository;

    @Before
    public void setUp() {
        initMocks(this);

        when(bookAvailabilityRepository.findOne(1L)).thenReturn(new Book(true));
        when(bookAvailabilityRepository.findOne(2L)).thenReturn(new Book(false));
    }

    @Test
    public void shouldChangeBookAvailability() {
        availability.borrow(1L);
        verify(bookAvailabilityRepository, atLeastOnce()).save(assertBookIsNotAvailable());

        availability.borrow(2L);
        verify(bookAvailabilityRepository, atLeastOnce()).save(assertBookIsNotAvailable());
    }

    private Book assertBookIsNotAvailable() {
        return argThat(new ArgumentMatcher<Book>() {
            @Override
            public boolean matches(Object argument) {
                if (!(argument instanceof Book)) {
                    return false;
                }
                return !((Book) argument).inStock();
            }
        });
    }
}