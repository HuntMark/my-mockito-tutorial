package my;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.doThrow;

@RunWith(MockitoJUnitRunner.class)
public class StringProcessorTest {

    @Spy
    private SysoutPrinter printer;

    @Test(expected = PrinterNotConnectedException.class)
    public void print_not_connected_exception_should_be_thrown() throws Exception {
        // Given
        StringProcessor processor = new StringProcessor(this.printer);
        // call doNothing when doesn't need to call some method of spied object
        doThrow(new PrinterNotConnectedException()).when(printer).printTestPage();

        // When
        Optional<String> actualBuffer = processor.statusAndTest();

        // Then
        assertFalse(actualBuffer.isPresent());
    }

}