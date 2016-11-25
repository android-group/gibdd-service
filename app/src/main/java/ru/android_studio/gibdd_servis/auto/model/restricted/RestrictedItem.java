package ru.android_studio.gibdd_servis.auto.model.restricted;

/**
 * Created by yuryandreev on 25/09/16.
 */
public class RestrictedItem {

    private String dateadd;
    /**
     * Дата наложения ограничения
     */
    private String dateogr;
    private String divid;
    /**
     * Кем наложено ограничение
     */
    private Organs divtype;
    private String gid;
    /**
     * Вид ограничения
     */
    private Ogrkod ogrkod;
    private String regid;
    /**
     * Регион инициатора ограничения:
     */
    private String regname;
    /**
     * Марка (модель) ТС
     */
    private String tsmodel;
    /**
     * Год выпуска ТС
     */
    private String tsyear;

    public String getDateadd() {
        return dateadd;
    }

    public void setDateadd(String dateadd) {
        this.dateadd = dateadd;
    }

    public String getDateogr() {
        return dateogr;
    }

    public void setDateogr(String dateogr) {
        this.dateogr = dateogr;
    }

    public String getDivid() {
        return divid;
    }

    public void setDivid(String divid) {
        this.divid = divid;
    }

    public Organs getDivtype() {
        return divtype;
    }

    public void setDivtype(Organs divtype) {
        this.divtype = divtype;
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public Ogrkod getOgrkod() {
        return ogrkod;
    }

    public void setOgrkod(Ogrkod ogrkod) {
        this.ogrkod = ogrkod;
    }

    public String getRegid() {
        return regid;
    }

    public void setRegid(String regid) {
        this.regid = regid;
    }

    public String getRegname() {
        return regname;
    }

    public void setRegname(String regname) {
        this.regname = regname;
    }

    public String getTsmodel() {
        return tsmodel;
    }

    public void setTsmodel(String tsmodel) {
        this.tsmodel = tsmodel;
    }

    public String getTsyear() {
        return tsyear;
    }

    public void setTsyear(String tsyear) {
        this.tsyear = tsyear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RestrictedItem that = (RestrictedItem) o;

        if (dateadd != null ? !dateadd.equals(that.dateadd) : that.dateadd != null) return false;
        if (dateogr != null ? !dateogr.equals(that.dateogr) : that.dateogr != null) return false;
        if (divid != null ? !divid.equals(that.divid) : that.divid != null) return false;
        if (divtype != that.divtype) return false;
        if (gid != null ? !gid.equals(that.gid) : that.gid != null) return false;
        if (ogrkod != that.ogrkod) return false;
        if (regid != null ? !regid.equals(that.regid) : that.regid != null) return false;
        if (regname != null ? !regname.equals(that.regname) : that.regname != null) return false;
        if (tsmodel != null ? !tsmodel.equals(that.tsmodel) : that.tsmodel != null) return false;
        return tsyear != null ? tsyear.equals(that.tsyear) : that.tsyear == null;

    }

    @Override
    public int hashCode() {
        int result = dateadd != null ? dateadd.hashCode() : 0;
        result = 31 * result + (dateogr != null ? dateogr.hashCode() : 0);
        result = 31 * result + (divid != null ? divid.hashCode() : 0);
        result = 31 * result + (divtype != null ? divtype.hashCode() : 0);
        result = 31 * result + (gid != null ? gid.hashCode() : 0);
        result = 31 * result + (ogrkod != null ? ogrkod.hashCode() : 0);
        result = 31 * result + (regid != null ? regid.hashCode() : 0);
        result = 31 * result + (regname != null ? regname.hashCode() : 0);
        result = 31 * result + (tsmodel != null ? tsmodel.hashCode() : 0);
        result = 31 * result + (tsyear != null ? tsyear.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RestrictedItem{" +
                "dateadd='" + dateadd + '\'' +
                ", dateogr='" + dateogr + '\'' +
                ", divid='" + divid + '\'' +
                ", divtype=" + divtype +
                ", gid='" + gid + '\'' +
                ", ogrkod=" + ogrkod +
                ", regid='" + regid + '\'' +
                ", regname='" + regname + '\'' +
                ", tsmodel='" + tsmodel + '\'' +
                ", tsyear='" + tsyear + '\'' +
                '}';
    }
}
