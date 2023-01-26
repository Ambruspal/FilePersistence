package hu.lechnerkozpont.ambruspal.vehicle.filePersistence;

import hu.lechnerkozpont.ambruspal.vehicle.interactor.entity.Vehicle;
import hu.lechnerkozpont.ambruspal.vehicle.interactor.interfaces.data.VehicleDataAccessInterface;

import java.io.*;

public class VehicleDataAccessStorage implements VehicleDataAccessInterface {

    @Override
    public void saveVehicle(Vehicle newVehicle) {
        String inputs = "";
        inputs = newVehicle.getRegistrationNumber() + "," + newVehicle.getMake() + "," + newVehicle.getModel() + ",";
        inputs = inputs + newVehicle.getNumberOfSeats() + "," + newVehicle.getVehicleType();

        this.writeFile(inputs);
    }

    @Override
    public Vehicle getVehicleByRegistrationNumber(String registrationNumber) {
        System.out.println("findVehicle.getRegistrationNumber(): " + registrationNumber);
        String line = "";
        String splitBy = ",";

        try {
            BufferedReader br = new BufferedReader(new FileReader("file-storage.csv"));
            while ((line = br.readLine()) != null)
            {
                String[] vehicle = line.split(splitBy);
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
            exc.printStackTrace();
        }

        throw new VehicleNotFoundException();
    }

    private void writeFile(String inputs) {
        try {
            File csvFile = new File("./file-storage.csv");
            FileWriter fileWriter = new FileWriter(csvFile);

            fileWriter.write(inputs);
            fileWriter.close();
        } catch (Exception exc) {
            throw new VehicleSaveErrorException();
        }
    }
}
