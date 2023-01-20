package hu.lechnerkozpont.ambruspal.vehicle.filePersistence;

import hu.lechnerkozpont.ambruspal.vehicle.interactor.DataAccessInterface;
import hu.lechnerkozpont.ambruspal.vehicle.interactor.Vehicle;

public class DataAccessStorage implements DataAccessInterface {

    @Override
    public void saveVehicle(Vehicle vehicleClass) {

    }

    @Override
    public Vehicle getVehicleByRegistrationNumber(String registrationNumber) {
        Vehicle gottenVehicleMock = new Vehicle("AA:BC-123", "Opel", "Vectra", 4, "L3e");
        return gottenVehicleMock;
    }
}