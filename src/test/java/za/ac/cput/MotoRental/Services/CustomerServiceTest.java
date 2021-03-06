package za.ac.cput.MotoRental.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import za.ac.cput.MotoRental.App;
import za.ac.cput.MotoRental.config.factory.CustomerFactory;
import za.ac.cput.MotoRental.config.factory.RentalFactory;
import za.ac.cput.MotoRental.domain.Customer;
import za.ac.cput.MotoRental.domain.PaymentMethod;
import za.ac.cput.MotoRental.domain.Rental;
import za.ac.cput.MotoRental.repository.CustomerRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by student on 2015/08/20.
 */
@SpringApplicationConfiguration(classes = App.class)
@WebAppConfiguration
public class CustomerServiceTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private CustomerService service;
    private Long id;
    @Autowired
    private CustomerRepository repository;
    private List<Rental> rental;

    @BeforeMethod
    public void setUp() throws  Exception
    {
        rental = new ArrayList();
    }

    @Test
    public void create() throws Exception
    {
        List<PaymentMethod> paymentMethods1 = new ArrayList();
        List<PaymentMethod> paymentMethods2 = new ArrayList();


        PaymentMethod paymentMethod1 = new PaymentMethod.Builder("Credit").Price(400.00).build();
        paymentMethods1.add(paymentMethod1);

        PaymentMethod paymentMethod2 = new PaymentMethod.Builder("Credit").Price(1200.00).build();
        paymentMethods2.add(paymentMethod2);

        Rental rental1 = RentalFactory.createRental("11/05/2015", "12-05-2015", paymentMethods1);
        Rental rental2 = RentalFactory.createRental("11/08/2015", "16-08-2015", paymentMethods2);

        rental.add(rental1);
        rental.add(rental2);

        Customer customer = CustomerFactory.createCustomer("Jabaar", "Faizel", "0843024829", "5 Gimpie street Woodstock", "Cape Town", "8001", "fjabaar", "passfj", rental);

        repository.save(customer);
        id = customer.getId();

        Assert.assertNotNull(id);
    }

    @Test
    public void testGetRentals() throws Exception
    {
        List <Rental> rentals = service.getRental(id);

        Assert.assertTrue(rentals.size() == 2);
    }
}
