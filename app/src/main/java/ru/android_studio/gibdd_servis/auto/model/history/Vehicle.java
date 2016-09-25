package ru.android_studio.gibdd_servis.auto.model.history;

/**
 * Created by yuryandreev on 20/09/16.
 */
public class Vehicle {
    //Рабочий объем (см³):
    private String engineVolume;
    //Цвет
    private String color;
    //Кузов
    private String bodyNumber;
    //Год выпуска
    private String year;
    // Номер двигателя
    private String engineNumber;
    // VIN
    private String vin;
    //Марка, модель
    private String model;
    private String category;
    private AutoType type;
    // Мощность кВт:
    private String powerKwt;
    // Мощность л.с.
    private String powerHp;

    public String getEngineVolume() {
        return engineVolume;
    }

    public void setEngineVolume(String engineVolume) {
        this.engineVolume = engineVolume;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBodyNumber() {
        return bodyNumber;
    }

    public void setBodyNumber(String bodyNumber) {
        this.bodyNumber = bodyNumber;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getEngineNumber() {
        return engineNumber;
    }

    public void setEngineNumber(String engineNumber) {
        this.engineNumber = engineNumber;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public AutoType getType() {
        return type;
    }

    public void setType(AutoType type) {
        this.type = type;
    }

    public String getPowerKwt() {
        return powerKwt;
    }

    public void setPowerKwt(String powerKwt) {
        this.powerKwt = powerKwt;
    }

    public String getPowerHp() {
        return powerHp;
    }

    public void setPowerHp(String powerHp) {
        this.powerHp = powerHp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vehicle vehicle = (Vehicle) o;

        if (engineVolume != null ? !engineVolume.equals(vehicle.engineVolume) : vehicle.engineVolume != null)
            return false;
        if (color != null ? !color.equals(vehicle.color) : vehicle.color != null) return false;
        if (bodyNumber != null ? !bodyNumber.equals(vehicle.bodyNumber) : vehicle.bodyNumber != null)
            return false;
        if (year != null ? !year.equals(vehicle.year) : vehicle.year != null) return false;
        if (engineNumber != null ? !engineNumber.equals(vehicle.engineNumber) : vehicle.engineNumber != null)
            return false;
        if (vin != null ? !vin.equals(vehicle.vin) : vehicle.vin != null) return false;
        if (model != null ? !model.equals(vehicle.model) : vehicle.model != null) return false;
        if (category != null ? !category.equals(vehicle.category) : vehicle.category != null)
            return false;
        if (type != vehicle.type) return false;
        if (powerKwt != null ? !powerKwt.equals(vehicle.powerKwt) : vehicle.powerKwt != null)
            return false;
        return powerHp != null ? powerHp.equals(vehicle.powerHp) : vehicle.powerHp == null;

    }

    @Override
    public int hashCode() {
        int result = engineVolume != null ? engineVolume.hashCode() : 0;
        result = 31 * result + (color != null ? color.hashCode() : 0);
        result = 31 * result + (bodyNumber != null ? bodyNumber.hashCode() : 0);
        result = 31 * result + (year != null ? year.hashCode() : 0);
        result = 31 * result + (engineNumber != null ? engineNumber.hashCode() : 0);
        result = 31 * result + (vin != null ? vin.hashCode() : 0);
        result = 31 * result + (model != null ? model.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (powerKwt != null ? powerKwt.hashCode() : 0);
        result = 31 * result + (powerHp != null ? powerHp.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "engineVolume='" + engineVolume + '\'' +
                ", color='" + color + '\'' +
                ", bodyNumber='" + bodyNumber + '\'' +
                ", year='" + year + '\'' +
                ", engineNumber='" + engineNumber + '\'' +
                ", vin='" + vin + '\'' +
                ", model='" + model + '\'' +
                ", category='" + category + '\'' +
                ", type=" + type +
                ", powerKwt='" + powerKwt + '\'' +
                ", powerHp='" + powerHp + '\'' +
                '}';
    }
}