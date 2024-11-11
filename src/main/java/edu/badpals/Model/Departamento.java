package edu.badpals.Model;

public class Departamento {

    private int Num_Departamento;
    private String Nome_departamento;
    private String NSS_dirige;
    private String Data_direccion;

    public Departamento(int Num_Departamento, String Nome_departamento, String NSS_dirige, String Data_direccion) {
        this.Num_Departamento = Num_Departamento;
        this.Nome_departamento = Nome_departamento;
        this.NSS_dirige = NSS_dirige;
        this.Data_direccion = Data_direccion;
    }

    public int getNum_Departamento() {
        return Num_Departamento;
    }

    public void setNum_Departamento(int Num_Departamento) {
        this.Num_Departamento = Num_Departamento;
    }

    public String getNome_departamento() {
        return Nome_departamento;
    }

    public void setNome_departamento(String Nome_departamento) {
        this.Nome_departamento = Nome_departamento;
    }

    public String getNSS_dirige() {
        return NSS_dirige;
    }

    public void setNSS_dirige(String NSS_dirige) {
        this.NSS_dirige = NSS_dirige;
    }

    public String getData_direccion() {
        return Data_direccion;
    }

    public void setData_direccion(String Data_direccion) {
        this.Data_direccion = Data_direccion;
    }

    @Override
    public String toString() {
        return "Departamento{" + "Num_Departamento=" + Num_Departamento + ", Nome_departamento=" + Nome_departamento + ", NSS_dirige=" + NSS_dirige + ", Data_direccion=" + Data_direccion + '}';
    }
}
