package my;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.atLeast;
import static org.mockito.internal.verification.VerificationModeFactory.atLeastOnce;
import static org.mockito.internal.verification.VerificationModeFactory.atMost;
import static org.mockito.internal.verification.VerificationModeFactory.only;
import static org.mockito.internal.verification.VerificationModeFactory.times;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PrinterTest {

    @Mock
    private my.verification.Printer printer;

    @Test
    public void simple_interaction_verification() {
        // Given

        // When
        printer.printTestPage();

        // Then
        verify(printer).printTestPage();
    }

    @Test
    public void simple_interaction_verification_times_1() {
        // Given

        // When
        printer.printTestPage();

        // Then
        verify(printer, times(1)).printTestPage();
    }

    @Test
    public void simple_interaction_verification_times_3() {
        // Given

        // When
        printer.printTestPage();
        printer.printTestPage();
        printer.printTestPage();

        // Then
        verify(printer, times(3)).printTestPage();
    }

    @Test
    public void simple_interaction_verification_atleastonce() {
        // Given

        // When
        printer.printTestPage();
        printer.printTestPage();

        // Then
        verify(printer, atLeastOnce()).printTestPage();
    }

    @Test
    public void simple_interaction_verification_atleast_2() {
        // Given

        // When
        printer.printTestPage();
        printer.printTestPage();
        printer.printTestPage();

        // Then
        verify(printer, atLeast(2)).printTestPage();
    }

    @Test
    public void simple_interaction_verification_atmost_3() {
        // Given

        // When
        printer.printTestPage();
        printer.printTestPage();

        // Then
        verify(printer, atMost(3)).printTestPage();
    }

    @Test
    public void simple_interaction_verification_never() {
        // Given

        // When

        // Then
        verify(printer, never()).printTestPage();
    }

    @Test
    public void simple_interaction_verification_only() {
        // Given

        // When
        printer.printTestPage();

        // Then
        verify(printer, only()).printTestPage();
    }

    @Test
    public void simple_interaction_verification_only_fails() {
        // Given

        // When
        printer.printTestPage();
        printer.turnOff();

        // Then
        verify(printer, only()).printTestPage();
    }
}