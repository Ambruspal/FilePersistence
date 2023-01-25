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
    public String getVehicleByRegistrationNumber(Vehicle findVehicle) {
        String id = findVehicle.getRegistrationNumber();
        System.out.println("findVehicle.getRegistrationNumber(): " + id);
        String line = "";
        String splitBy = ",";

        try {
            BufferedReader br = new BufferedReader(new FileReader("file-storage.csv"));
            while ((line = br.readLine()) != null)   //returns a Boolean value
            {
                String[] vehicle = line.split(splitBy);    // use comma as separator
                System.out.println("Vehicle [registrationNumber=" + vehicle[0] + ", make=" + vehicle[1] + ", model=" + vehicle[2] + ", numberOfSeats=" + vehicle[3] + ", vehicleType= " + vehicle[4]);
                if (vehicle[0].equals(id)) {
                    System.out.println("Found");
                }
            }
        } catch (Exception exc) {
            return "File not found!";
        }

        return "Valami";
    }
}
