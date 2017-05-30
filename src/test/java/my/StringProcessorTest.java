package my;

import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.doNothing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class StringProcessorTest {

    @Spy
    private SysoutPrinter printer;

    @Test
    public void internal_buffer_should_be_absent_after_construction() {
        // Given
        StringProcessor processor = new StringProcessor(this.printer);
        // call doNothing when doesn't need to call some method of spied object
        doNothing().when(printer).printTestPage();

        // When
        Optional<String> actualBuffer = processor.statusAndTest();

        // Then
        assertFalse(actualBuffer.isPresent());
    }

}