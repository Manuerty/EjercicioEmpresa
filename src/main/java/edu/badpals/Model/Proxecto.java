package edu.badpals.Model;

public class Proxecto {

    private int Num_proxecto;
    private String Nome_proxecto;
    private String Lugar;
    private int Num_departamento_controla;

    public Proxecto(int Num_proxecto, String Nome_proxecto, String Lugar, int Num_departamento_controla) {
        this.Num_proxecto = Num_proxecto;
        this.Nome_proxecto = Nome_proxecto;
        this.Lugar = Lugar;
        this.Num_departamento_controla = Num_departamento_controla;
    }

    public int getNum_proxecto() {
        return Num_proxecto;
    }

    public void setNum_proxecto(int Num_proxecto) {
        this.Num_proxecto = Num_proxecto;
    }

    public String getNome_proxecto() {
        return Nome_proxecto;
    }

    public void setNome_proxecto(String Nome_proxecto) {
        this.Nome_proxecto = Nome_proxecto;
    }

    public String getLugar() {
        return Lugar;
    }

    public void setLugar(String Lugar) {
        this.Lugar = Lugar;
    }

    public int getNum_departamento_controla() {
        return Num_departamento_controla;
    }

    public void setNum_departamento_controla(int Num_departamento_controla) {
        this.Num_departamento_controla = Num_departamento_controla;
    }

    @Override
    public String toString() {
        return "Proxecto{" + "Num_proxecto=" + Num_proxecto + ", Nome_proxecto=" + Nome_proxecto + ", Lugar=" + Lugar + ", Num_departamento_controla=" + Num_departamento_controla + '}';
    }

}
