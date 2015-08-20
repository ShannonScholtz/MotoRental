package za.ac.cput.MotoRental.Services.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.MotoRental.Services.CustomerService;
import za.ac.cput.MotoRental.domain.Customer;
import za.ac.cput.MotoRental.domain.Rental;
import za.ac.cput.MotoRental.repository.CustomerRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by student on 2015/08/12.
 */
@Service
public class CustomerImpl implements CustomerService {
    @Autowired
    CustomerRepository repository;
    public List<Customer> getCustomer() {
        List<Customer> allCustomers = new ArrayList<Customer>();

        Iterable<Customer> customers = repository.findAll();
        for (Customer customer : customers) {
            allCustomers.add(customer);
        }
        return allCustomers;
    }

    @Override
    public List<Rental> getRental(Long id)
    {
        return repository.findOne(id).getRentals();
    }
}

