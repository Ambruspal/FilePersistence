package hu.lechnerkozpont.ambruspal.vehicle.filePersistence;

import hu.lechnerkozpont.ambruspal.vehicle.interactor.entity.Vehicle;
import hu.lechnerkozpont.ambruspal.vehicle.interactor.interfaces.data.VehicleDataAccessInterface;
import org.json.JSONObject;

import java.io.*;

public class VehicleDataAccessStorage implements VehicleDataAccessInterface {

    @Override
    public JSONObject saveVehicle(Vehicle newVehicle) {
        String inputs = "";
        inputs = newVehicle.getRegistrationNumber() + "," + newVehicle.getMake() + "," + newVehicle.getModel() + ",";
        inputs = inputs + newVehicle.getNumberOfSeats() + "," + newVehicle.getVehicleType();
        System.out.println(inputs);
        JSONObject jsonObject = new JSONObject();

        try {
            File csvFile = new File("./file-storage.csv");
            FileWriter fileWriter = new FileWriter(csvFile);

            fileWriter.write(inputs);
            fileWriter.close();

            jsonObject.put("message", "Successfully Saved");
        } catch (Exception exc) {
            try {
                jsonObject.put("error", "File save error!");
            } catch (Exception e) {
                exc.printStackTrace();
            }
        }

        return jsonObject;
    }

    @Override
    public Vehicle getVehicleByRegistrationNumber(String registrationNumber) {
        System.out.println("findVehicle.getRegistrationNumber(): " + registrationNumber);
        String line = "";
        String splitBy = ",";

        try {
            BufferedReader br = new BufferedReader(new FileReader("file-storage.csv"));
            while ((line = br.readLine()) != null)   //returns a Boolean value
            {
                String[] vehicle = line.split(splitBy);    // use comma as separator
                System.out.println("Vehicle [registrationNumber=" + vehicle[0] + ", make=" + vehicle[1] + ", model=" + vehicle[2] + ", numberOfSeats=" + vehicle[3] + ", vehicleType= " + vehicle[4]);
                if (vehicle[0].equals(registrationNumber)) {
                    System.out.println("Found");

                    Vehicle entityVehicle = new Vehicle();

                    entityVehicle.setRegistrationNumber(vehicle[0]);
                    entityVehicle.setMake(vehicle[1]);
                    entityVehicle.setModel(vehicle[2]);
                    entityVehicle.setNumberOfSeats(vehicle[3]);
                    entityVehicle.setVehicleType(vehicle[4]);

                    return entityVehicle;
                }
            }
        } catch (Exception exc) {

        }

        throw new VehicleNotFoundException();
    }
}
