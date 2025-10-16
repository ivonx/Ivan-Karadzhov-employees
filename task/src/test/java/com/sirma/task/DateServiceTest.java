package com.sirma.task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class DateServiceTest {

    private DateService dateService;

    @BeforeEach
    void setUp() {
        this.dateService = new DateService();
    }

    @Test
    void testCalculateOverlapLeapYear() {
        long daysWorkedTogether = this.dateService.calculateDateOverlap(
                "2012-02-01",
                "2012-03-01",
                "2012-02-01",
                "2012-03-01");
        assertEquals(30L, daysWorkedTogether);
    }

    @Test
    void testCalculateOverlapWithEmptyDateAndOverlap() {
        long daysWorkedTogether = this.dateService.calculateDateOverlap(
                "2025-10-01",
                "",
                "2025-10-01",
                "2025-10-15");
        assertEquals(15L, daysWorkedTogether);
    }

    @Test
    void testCalculateOverlapWithNull() {
        long daysWorkedTogether = this.dateService.calculateDateOverlap(
                null,
                null,
                null,
                null);
        assertEquals(1L, daysWorkedTogether);
    }

    @Test
    void testCalculateOverlapWithNullAndEmptyDate() {
        long daysWorkedTogether = this.dateService.calculateDateOverlap(
                null,
                null,
                "",
                " ");
        assertEquals(1L, daysWorkedTogether);
    }

    @Test
    void testCalculateOverlapWithEmptyDateWithoutOverlap() {
        long daysWorkedTogether = this.dateService.calculateDateOverlap(
                "2025-10-01",
                "",
                "2024-10-01",
                "2024-10-15");
        assertEquals(0L, daysWorkedTogether);
    }

    @Test
    void testCalculateOverlapWithOverlap() {
        long daysWorkedTogether = this.dateService.calculateDateOverlap(
                "2025-10-01",
                "2025-10-15",
                "2025-10-01",
                "2025-10-15");
        assertEquals(15L, daysWorkedTogether);
    }

    @Test
    void testCalculateOverlapWithoutOverlap() {
        long daysWorkedTogether = this.dateService.calculateDateOverlap(
                "2022-10-01",
                "2023-10-15",
                "2023-10-16",
                "2023-10-20");
        assertEquals(0L, daysWorkedTogether);
    }

    @Test
    void testCalculateOverlapWithInvalidDates() {
        long daysWorkedTogether = this.dateService.calculateDateOverlap(
                "2025-10-01",
                "2023-10-15",
                "2025-10-01",
                "2023-10-15");
        assertEquals(0L, daysWorkedTogether);
    }

    @Test
    void testCalculateOverlapWithInvalidDatesForFirstEmployee() {
        long daysWorkedTogether = this.dateService.calculateDateOverlap(
                "2025-10-01",
                "2023-10-15",
                "2023-10-01",
                "2023-10-15");
        assertEquals(0L, daysWorkedTogether);
    }

    @Test
    void testCalculateOverlapWithDifferentDateFormatsWithOverlap() {
        long daysWorkedTogether = this.dateService.calculateDateOverlap(
                "2025.10.01",
                "2025/10/15",
                "01-10-2025",
                "15.10.2025");
        assertEquals(15L, daysWorkedTogether);
    }

    @Test
    void testCalculateOverlapWithDifferentDateFormatsWithoutOverlap() {
        long daysWorkedTogether = this.dateService.calculateDateOverlap(
                "01.10.25",
                "15/10/25",
                "01-10-25",
                "15.10.2025");
        assertEquals(15L, daysWorkedTogether);
    }
}
