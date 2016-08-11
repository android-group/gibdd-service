package ru.android_studio.gibdd_servis.auto.model.history;

public class RequestResult {

    // "ownershipPeriods"
    private OwnershipPeriods ownershipPeriods;
    // "vehiclePassport"
    private VehiclePassport vehiclePassport;
    // "vehicle"
    private Vehicle vehicle;

    public OwnershipPeriods getOwnershipPeriods() {
        return ownershipPeriods;
    }

    public void setOwnershipPeriods(OwnershipPeriods ownershipPeriods) {
        this.ownershipPeriods = ownershipPeriods;
    }

    public VehiclePassport getVehiclePassport() {
        return vehiclePassport;
    }

    public void setVehiclePassport(VehiclePassport vehiclePassport) {
        this.vehiclePassport = vehiclePassport;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
