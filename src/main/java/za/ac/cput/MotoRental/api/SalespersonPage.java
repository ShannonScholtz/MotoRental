package za.ac.cput.MotoRental.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import za.ac.cput.MotoRental.Services.SalesPersonService;
import za.ac.cput.MotoRental.domain.Customer;
import za.ac.cput.MotoRental.domain.SalesPerson;
import za.ac.cput.MotoRental.module.CustomerResource;
import za.ac.cput.MotoRental.module.SalespersonResource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by student on 2015/08/20.
 */
@RestController
@RequestMapping(value="/salesperson/**")
public class SalespersonPage {

    @Autowired
    private SalesPersonService service;

    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    public List<Customer> getCustom(@PathVariable Long id)
    {
        return service.getCustomers(id);
    }

    @RequestMapping(value="/salespersons", method=RequestMethod.GET)

    public List<SalespersonResource> getSalespersons()
    {
        List<SalespersonResource> hateoas = new ArrayList();
        List<SalesPerson> salesPersons = service.getSalesPerson();

        for (SalesPerson s: salesPersons)
        {
            SalespersonResource res = new SalespersonResource
                    .Builder(s.getLastName())
                    .firstName(s.getFirstName())
                    .hours(s.getHours())
                    .rate(s.getRate())
                    .customers(s.getCustomers())
                    .build();

            Link branches = new Link("http://localhost:8080/salesperson" + res.getResid().toString())
                    .withRel("sal");
            res.add(branches);
            hateoas.add(res);
        }

        return hateoas;
    }
}
