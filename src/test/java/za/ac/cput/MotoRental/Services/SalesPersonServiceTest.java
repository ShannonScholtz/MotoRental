package za.ac.cput.MotoRental.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import za.ac.cput.MotoRental.App;
import za.ac.cput.MotoRental.config.factory.*;
import za.ac.cput.MotoRental.domain.Customer;
import za.ac.cput.MotoRental.domain.PaymentMethod;
import za.ac.cput.MotoRental.domain.Rental;
import za.ac.cput.MotoRental.domain.SalesPerson;
import za.ac.cput.MotoRental.repository.SalesPersonRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by student on 2015/08/20.
 */
@SpringApplicationConfiguration(classes = App.class)
@WebAppConfiguration
public class SalesPersonServiceTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private SalesPersonService service;
    private Long id;
    @Autowired
    private SalesPersonRepository repository;
    private List<Customer> customers;

    @BeforeMethod
    public void setUp() throws Exception {
        customers = new ArrayList();
    }

    @Test
    public void create() throws Exception {
        List<PaymentMethod> paymentMethods = new ArrayList();
        List<Rental> rentals = new ArrayList();

        PaymentMethod paymentMethod = new PaymentMethod.Builder("Credit").Price(4000.00).build();
        paymentMethods.add(paymentMethod);

        Rental rental = new Rental.Builder("22-07-2015").returnDate("28-07-2015").paymentMethod(paymentMethods).build();
        rentals.add(rental);

        Customer customer1 = new Customer.Builder("Scholtz").firstName("Shannon").phoneNumber("0823334292").addressEmbeddable(AddressEmbeddableFactory.createAddress("5 baten street Woodstock", "Cape Town", "890890"))
                .loginEmbeddable(LoginEmbeddableFactory.createLogin("user", "pass"))
                .rentals(rentals)
                .build();

        customers.add(customer1);

        Customer customer2 = new Customer.Builder("Paulse").firstName("Ali").phoneNumber("0833334292").addressEmbeddable(AddressEmbeddableFactory.createAddress("6 bat street Woodstock", "Cape Town", "8900"))
                .loginEmbeddable(LoginEmbeddableFactory.createLogin("jackychan", "passchan"))
                .rentals(rentals)
                .build();

        customers.add(customer2);

        SalesPerson salesPerson= SalesPersonFactory.createSalesPerson("Khaleesi", "Yonna", 8, 200.00, "kf", "fk100K", customers);

        repository.save(salesPerson);
        id = salesPerson.getId();

        Assert.assertNotNull(id);
    }

    @Test
    public void testGetSalespersons() throws Exception {

       List<Customer> customers = service.getCustomers(id);

        Assert.assertTrue(customers.size() == 2);
    }
}
