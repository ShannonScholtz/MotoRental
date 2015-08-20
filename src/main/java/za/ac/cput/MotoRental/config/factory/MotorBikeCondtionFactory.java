package za.ac.cput.MotoRental.config.factory;

import za.ac.cput.MotoRental.domain.MotorBikeCondition;

/**
 * Created by student on 2015/08/19.
 */
public class MotorBikeCondtionFactory {
    public static MotorBikeCondition createAddress(String OdometreReading, String Gas, String BikeCondition)
    {
        MotorBikeCondition addressEmbeddable = new MotorBikeCondition
                .Builder(OdometreReading)
                .Gas(Gas)
                .BikeCondition(BikeCondition)
                .build();

        return addressEmbeddable;
    }
}