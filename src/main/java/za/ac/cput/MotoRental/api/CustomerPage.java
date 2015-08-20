package za.ac.cput.MotoRental.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import za.ac.cput.MotoRental.Services.CustomerService;
import za.ac.cput.MotoRental.config.factory.AddressEmbeddableFactory;
import za.ac.cput.MotoRental.config.factory.LoginEmbeddableFactory;
import za.ac.cput.MotoRental.domain.Customer;
import za.ac.cput.MotoRental.domain.Rental;
import za.ac.cput.MotoRental.module.CustomerResource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by student on 2015/08/20.
 */
@RestController
@RequestMapping(value="/customer/**")
public class CustomerPage {

    @Autowired
    private CustomerService service;

    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    public List<Rental> getRentals(@PathVariable Long id)
    {
        return service.getRental(id);
    }

    @RequestMapping(value="/customers", method=RequestMethod.GET)

    public List<CustomerResource> getCustomers()
    {
        List<CustomerResource> hateoas = new ArrayList();
        List<Customer> customers = service.getCustomer();

        for (Customer c: customers)
        {
            CustomerResource res = new CustomerResource
                    .Builder(c.getLastName())
                    .firstName(c.getFirstName())
                    .phoneNumber(c.getPhoneNumber())
                    .addressEmbeddable(c.getAddressEmbeddable())
                    .loginEmbeddable(c.getLoginEmbeddable())
                    .rentals(c.getRentals())
                    .build();

            Link branches = new Link("http://localhost:8080/customer" + res.getResid().toString())
                    .withRel("cust");
            res.add(branches);
            hateoas.add(res);
        }

        return hateoas;
    }
}
