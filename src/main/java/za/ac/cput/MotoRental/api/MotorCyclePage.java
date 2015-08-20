package za.ac.cput.MotoRental.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import za.ac.cput.MotoRental.Services.MotorCycleService;
import za.ac.cput.MotoRental.config.factory.EngineTypeEmbeddableFactory;
import za.ac.cput.MotoRental.config.factory.MotorBikeCondtionFactory;
import za.ac.cput.MotoRental.domain.MotorCycle;
import za.ac.cput.MotoRental.domain.Rental;
import za.ac.cput.MotoRental.module.MotorCycleResources;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by student on 2015/08/20.
 */
@RestController
@RequestMapping(value="/motorcycle/**")
public class MotorCyclePage {

    @Autowired
    private MotorCycleService service;

    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    public List<Rental> getRentals(@PathVariable Long id)
    {
        return service.getRental(id);
    }

    @RequestMapping(value="/motorcycles", method=RequestMethod.GET)

    public List<MotorCycleResources> getMotorcycles()
    {
        List<MotorCycleResources> hateoas = new ArrayList();
        List<MotorCycle> motorCycles = service.getMotorCycle();

        for (MotorCycle m: motorCycles)
        {
            MotorCycleResources res = new MotorCycleResources
                    .Builder(m.getSerialNumber())
                    .Make(m.getMake())
                    .Model(m.getModel())
                    .year(m.getYear())
                    .motorBikeCondition(m.getMotorBikeCondition())
                    .engineTypeEmbeddable(m.getEngineTypeEmbeddable())
                    .rentals(m.getRentals())
                    .build();

            Link branches = new Link("http://localhost:8080/motorcycles" + res.getResid().toString())
                    .withRel("moto");
            res.add(branches);
            hateoas.add(res);
        }

        return hateoas;
    }
}
