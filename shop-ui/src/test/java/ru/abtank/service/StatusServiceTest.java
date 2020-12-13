package ru.abtank.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.abtank.persist.model.Status;
import ru.abtank.persist.repositories.StatusRepository;
import ru.abtank.representations.StatusRepr;
import ru.abtank.servises.StatusService;
import ru.abtank.servises.StatusServiceImpl;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StatusServiceTest {

    private StatusRepository statusRepository;
    private StatusService statusService;

    @BeforeEach
    public void init() {
        statusRepository = mock(StatusRepository.class);
        statusService = new StatusServiceImpl(statusRepository);
    }

    @Test
    public void testFindById() {
        Status expectedStatus = new Status();
        expectedStatus.setId(1);
        expectedStatus.setName("Status test name");

        when(statusRepository.findById(eq(1)))
                .thenReturn(Optional.of(expectedStatus));

        Optional<StatusRepr> opt = statusService.findById(1);

        assertTrue(opt.isPresent());
        assertEquals(expectedStatus.getId(), opt.get().getId());
        assertEquals(expectedStatus.getName(), opt.get().getName());

    }
}
