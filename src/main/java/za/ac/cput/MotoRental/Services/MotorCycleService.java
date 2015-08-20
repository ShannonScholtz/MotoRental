package za.ac.cput.MotoRental.Services;

import za.ac.cput.MotoRental.domain.MotorCycle;
import za.ac.cput.MotoRental.domain.Rental;

import java.util.List;

/**
 * Created by student on 2015/08/13.
 */
public interface MotorCycleService {
    List<MotorCycle> getMotorCycle();
    List<Rental> getRental(Long id);
}