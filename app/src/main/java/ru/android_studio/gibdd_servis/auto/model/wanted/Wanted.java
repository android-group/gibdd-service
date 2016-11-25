package ru.android_studio.gibdd_servis.auto.model.wanted;

/**
 * Created by yuryandreev on 25/09/16.
 */
public class Wanted {
    /**
     * Дата постоянного учета в розыске:
     */
    private String w_data_pu;
    /**
     * Год выпуска ТС:
     */
    private String w_god_vyp;
    /**
     * Марка (модель) ТС:
     */
    private String w_model;
    private String w_rec;
    /**
     * Регион инициатора розыска:
     */
    private String w_reg_inic;
    private String w_un_gic;
    private String w_user;
    private String w_vid_uch;

    public String getW_data_pu() {
        return w_data_pu;
    }

    public void setW_data_pu(String w_data_pu) {
        this.w_data_pu = w_data_pu;
    }

    public String getW_god_vyp() {
        return w_god_vyp;
    }

    public void setW_god_vyp(String w_god_vyp) {
        this.w_god_vyp = w_god_vyp;
    }

    public String getW_model() {
        return w_model;
    }

    public void setW_model(String w_model) {
        this.w_model = w_model;
    }

    public String getW_rec() {
        return w_rec;
    }

    public void setW_rec(String w_rec) {
        this.w_rec = w_rec;
    }

    public String getW_reg_inic() {
        return w_reg_inic;
    }

    public void setW_reg_inic(String w_reg_inic) {
        this.w_reg_inic = w_reg_inic;
    }

    public String getW_un_gic() {
        return w_un_gic;
    }

    public void setW_un_gic(String w_un_gic) {
        this.w_un_gic = w_un_gic;
    }

    public String getW_user() {
        return w_user;
    }

    public void setW_user(String w_user) {
        this.w_user = w_user;
    }

    public String getW_vid_uch() {
        return w_vid_uch;
    }

    public void setW_vid_uch(String w_vid_uch) {
        this.w_vid_uch = w_vid_uch;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Wanted wanted = (Wanted) o;

        if (w_data_pu != null ? !w_data_pu.equals(wanted.w_data_pu) : wanted.w_data_pu != null)
            return false;
        if (w_god_vyp != null ? !w_god_vyp.equals(wanted.w_god_vyp) : wanted.w_god_vyp != null)
            return false;
        if (w_model != null ? !w_model.equals(wanted.w_model) : wanted.w_model != null)
            return false;
        if (w_rec != null ? !w_rec.equals(wanted.w_rec) : wanted.w_rec != null) return false;
        if (w_reg_inic != null ? !w_reg_inic.equals(wanted.w_reg_inic) : wanted.w_reg_inic != null)
            return false;
        if (w_un_gic != null ? !w_un_gic.equals(wanted.w_un_gic) : wanted.w_un_gic != null)
            return false;
        if (w_user != null ? !w_user.equals(wanted.w_user) : wanted.w_user != null) return false;
        return w_vid_uch != null ? w_vid_uch.equals(wanted.w_vid_uch) : wanted.w_vid_uch == null;

    }

    @Override
    public int hashCode() {
        int result = w_data_pu != null ? w_data_pu.hashCode() : 0;
        result = 31 * result + (w_god_vyp != null ? w_god_vyp.hashCode() : 0);
        result = 31 * result + (w_model != null ? w_model.hashCode() : 0);
        result = 31 * result + (w_rec != null ? w_rec.hashCode() : 0);
        result = 31 * result + (w_reg_inic != null ? w_reg_inic.hashCode() : 0);
        result = 31 * result + (w_un_gic != null ? w_un_gic.hashCode() : 0);
        result = 31 * result + (w_user != null ? w_user.hashCode() : 0);
        result = 31 * result + (w_vid_uch != null ? w_vid_uch.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Wanted{" +
                "w_data_pu='" + w_data_pu + '\'' +
                ", w_god_vyp='" + w_god_vyp + '\'' +
                ", w_model='" + w_model + '\'' +
                ", w_rec='" + w_rec + '\'' +
                ", w_reg_inic='" + w_reg_inic + '\'' +
                ", w_un_gic='" + w_un_gic + '\'' +
                ", w_user='" + w_user + '\'' +
                ", w_vid_uch='" + w_vid_uch + '\'' +
                '}';
    }
}
