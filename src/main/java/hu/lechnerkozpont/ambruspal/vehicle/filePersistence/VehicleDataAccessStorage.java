package hu.lechnerkozpont.ambruspal.vehicle.filePersistence;

import hu.lechnerkozpont.ambruspal.vehicle.interactor.interfaces.data.VehicleDataAccessInterface;
import hu.lechnerkozpont.ambruspal.vehicle.interactor.entity.Vehicle;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class VehicleDataAccessStorage implements VehicleDataAccessInterface {

    @Override
    public String saveVehicle(JSONObject JsonObjectVehicle) {
        try {
            FileWriter fileWriter = new FileWriter("./vehicles.json");
            fileWriter.write(JsonObjectVehicle.toString());
            fileWriter.close();

            return "Successfully Saved";
        } catch (IOException exc) {
            return "Save failed!";
        }
    }

    @Override
    public Vehicle getVehicleByRegistrationNumber(String registrationNumber) {
        Vehicle gottenVehicleMock = new Vehicle("AA:BC-123", "Opel", "Vectra", "4", "L3e");
        return gottenVehicleMock;
    }
}
