package za.ac.cput.MotoRental.Services.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.MotoRental.Services.MotorCycleService;
import za.ac.cput.MotoRental.domain.MotorCycle;
import za.ac.cput.MotoRental.domain.Rental;
import za.ac.cput.MotoRental.repository.MotorCycleRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by student on 2015/08/13.
 */
@Service
public class MotorCycleImpl implements MotorCycleService {
    @Autowired
    MotorCycleRepository repository;
    public List<MotorCycle> getMotorCycle() {
        List<MotorCycle> allMotorCycles = new ArrayList<MotorCycle>();

        Iterable<MotorCycle> motorCycles = repository.findAll();
        for (MotorCycle motorCycle : motorCycles) {
            allMotorCycles.add(motorCycle);
        }
        return allMotorCycles;
    }

    @Override
    public List<Rental> getRental(Long id)
    {
        return repository.findOne(id).getRentals();
    }
}

