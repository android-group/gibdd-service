package ru.android_studio.gibdd_servis.auto.model.dtp;

import java.util.List;

public class Accidents {
    /**
     * Дата и время происшествия
     */
    private String accidentDateTime;
    private String accidentNumber;
    /**
     * Тип происшествия
     */
    private String accidentType;
    /**
     * Регион происшествия
     */
    private String regionName;
    private String vehicleDamageState;
    /**
     * Марка (модель) ТС
     */
    private String vehicleMark;
    private String vehicleModel;
    /**
     * Год выпуска ТС
     */
    private String vehicleYear;
    private List<DamagePoints> damagePoints;

    public String getAccidentDateTime() {
        return accidentDateTime;
    }

    public void setAccidentDateTime(String accidentDateTime) {
        this.accidentDateTime = accidentDateTime;
    }

    public String getAccidentNumber() {
        return accidentNumber;
    }

    public void setAccidentNumber(String accidentNumber) {
        this.accidentNumber = accidentNumber;
    }

    public String getAccidentType() {
        return accidentType;
    }

    public void setAccidentType(String accidentType) {
        this.accidentType = accidentType;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getVehicleDamageState() {
        return vehicleDamageState;
    }

    public void setVehicleDamageState(String vehicleDamageState) {
        this.vehicleDamageState = vehicleDamageState;
    }

    public String getVehicleMark() {
        return vehicleMark;
    }

    public void setVehicleMark(String vehicleMark) {
        this.vehicleMark = vehicleMark;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public String getVehicleYear() {
        return vehicleYear;
    }

    public void setVehicleYear(String vehicleYear) {
        this.vehicleYear = vehicleYear;
    }

    public List<DamagePoints> getDamagePoints() {
        return damagePoints;
    }

    public void setDamagePoints(List<DamagePoints> damagePoints) {
        this.damagePoints = damagePoints;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Accidents accidents = (Accidents) o;

        if (accidentDateTime != null ? !accidentDateTime.equals(accidents.accidentDateTime) : accidents.accidentDateTime != null)
            return false;
        if (accidentNumber != null ? !accidentNumber.equals(accidents.accidentNumber) : accidents.accidentNumber != null)
            return false;
        if (accidentType != null ? !accidentType.equals(accidents.accidentType) : accidents.accidentType != null)
            return false;
        if (regionName != null ? !regionName.equals(accidents.regionName) : accidents.regionName != null)
            return false;
        if (vehicleDamageState != null ? !vehicleDamageState.equals(accidents.vehicleDamageState) : accidents.vehicleDamageState != null)
            return false;
        if (vehicleMark != null ? !vehicleMark.equals(accidents.vehicleMark) : accidents.vehicleMark != null)
            return false;
        if (vehicleModel != null ? !vehicleModel.equals(accidents.vehicleModel) : accidents.vehicleModel != null)
            return false;
        if (vehicleYear != null ? !vehicleYear.equals(accidents.vehicleYear) : accidents.vehicleYear != null)
            return false;
        return damagePoints != null ? damagePoints.equals(accidents.damagePoints) : accidents.damagePoints == null;

    }

    @Override
    public int hashCode() {
        int result = accidentDateTime != null ? accidentDateTime.hashCode() : 0;
        result = 31 * result + (accidentNumber != null ? accidentNumber.hashCode() : 0);
        result = 31 * result + (accidentType != null ? accidentType.hashCode() : 0);
        result = 31 * result + (regionName != null ? regionName.hashCode() : 0);
        result = 31 * result + (vehicleDamageState != null ? vehicleDamageState.hashCode() : 0);
        result = 31 * result + (vehicleMark != null ? vehicleMark.hashCode() : 0);
        result = 31 * result + (vehicleModel != null ? vehicleModel.hashCode() : 0);
        result = 31 * result + (vehicleYear != null ? vehicleYear.hashCode() : 0);
        result = 31 * result + (damagePoints != null ? damagePoints.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Accidents{" +
                "accidentDateTime='" + accidentDateTime + '\'' +
                ", accidentNumber='" + accidentNumber + '\'' +
                ", accidentType='" + accidentType + '\'' +
                ", regionName='" + regionName + '\'' +
                ", vehicleDamageState='" + vehicleDamageState + '\'' +
                ", vehicleMark='" + vehicleMark + '\'' +
                ", vehicleModel='" + vehicleModel + '\'' +
                ", vehicleYear='" + vehicleYear + '\'' +
                ", damagePoints=" + damagePoints +
                '}';
    }
}
