package my;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.persistence.EntityManager;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class CustomerDAOTest {

    private CustomerDAO dao;

    @Mock
    private EntityManager mockEm;

    @Before
    public void setUp() {
        dao = new CustomerDAO(mockEm);
    }

    @Test
    public void finding_existing_customer_should_return_customer() throws Exception {
        // Given
        long expectedId = 10;
        String expectedName = "John Doe";
        String expectedAddress = "21 Main Street";
        Customer expectedCustomer = new Customer(expectedId, expectedName, expectedAddress);

        when(mockEm.find(Customer.class, expectedId))
                .thenReturn(expectedCustomer);

        // When
        Optional<Customer> actualCustomer = dao.findById(expectedId);

        // Then
        assertTrue(actualCustomer.isPresent());
        assertEquals(expectedId, actualCustomer.get().getId());
        assertEquals(expectedName, actualCustomer.get().getName());
        assertEquals(expectedAddress, actualCustomer.get().getAddress());
    }

    @Test
    public void invoking_mock_with_unexpected_argument_returns_null() throws Exception {
        // Given
        long expectedId = 10L;
        long unexpectedId = 20L;
        String expectedName = "John Doe";
        String expectedAddress = "21 Main Street";
        Customer expectedCustomer = new Customer(expectedId, expectedName, expectedAddress);

        when(mockEm.find(Customer.class, expectedId)).thenReturn(expectedCustomer);

        // When
        Optional<Customer> actualCustomer = dao.findById(unexpectedId);

        // Then
        assertFalse(actualCustomer.isPresent());
    }

    @Test
    public void invoking_mock_with_different_argument_returns_different_customers() throws Exception {
        // Given
        long expectedId1 = 10L;
        String expectedName1 = "John Doe";
        String expectedAddress1 = "21 Main Street";
        Customer expectedCustomer1 = new Customer(expectedId1, expectedName1, expectedAddress1);

        long expectedId2 = 20L;
        String expectedName2 = "Jane Deer";
        String expectedAddress2 = "46 High Street";
        Customer expectedCustomer2 = new Customer(expectedId2, expectedName2, expectedAddress2);

        when(mockEm.find(Customer.class, expectedId1)).thenReturn(expectedCustomer1);
        when(mockEm.find(Customer.class, expectedId2)).thenReturn(expectedCustomer2);

        // When
        Optional<Customer> actualCustomer1 = dao.findById(expectedId1);
        Optional<Customer> actualCustomer2 = dao.findById(expectedId2);

        // Then
        assertEquals(expectedName1, actualCustomer1.get().getName());
        assertEquals(expectedName2, actualCustomer2.get().getName());
    }

    @Test
    public void invoking_mock_with_chained_stubs_returns_different_customers() throws Exception {
        // Given
        long expectedId1 = 10L;
        String expectedName1 = "John Doe";
        String expectedAddress1 = "21 Main Street";
        Customer expectedCustomer1 = new Customer(expectedId1, expectedName1, expectedAddress1);

        long expectedId2 = 20L;
        String expectedName2 = "Jane Deer";
        String expectedAddress2 = "46 High Street";
        Customer expectedCustomer2 = new Customer(expectedId2, expectedName2, expectedAddress2);

        when(mockEm.find(Customer.class, expectedId1))
                .thenReturn(expectedCustomer1)
                .thenReturn(expectedCustomer2);

        // When
        Optional<Customer> actualCustomer1 = dao.findById(expectedId1);
        Optional<Customer> actualCustomer2 = dao.findById(expectedId1);

        // Then
        assertEquals(expectedName1, actualCustomer1.get().getName());
        assertEquals(expectedName2, actualCustomer2.get().getName());
    }

    @Test
    public void finding_missing_customer_should_return_null() throws Exception {
        // Given
        long expectedId = 10L;
        when(mockEm.find(Customer.class, expectedId)).thenReturn(null);

        // When
        Optional<Customer> actualCustomer = dao.findById(expectedId);

        // Then
        assertFalse(actualCustomer.isPresent());
    }
}