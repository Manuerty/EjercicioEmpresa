package edu.badpals.Model;

public class Empleado {

    public String nomeEmpregado;
    public String apelido1;
    public String apelido2;
    public String localidade;
    public float salario;
    public String dataNacemento;
    public String nomeXefe;
    public String nomeDepartamento;

    public Empleado(String nomeEmpregado, String apelido1, String apelido2, String localidade, float salario, String dataNacemento, String nomeXefe, String nomeDepartamento) {

        this.nomeEmpregado = nomeEmpregado;
        this.apelido1 = apelido1;
        this.apelido2 = apelido2;
        this.localidade = localidade;
        this.salario = salario;
        this.dataNacemento = dataNacemento;
        this.nomeXefe = nomeXefe;
        this.nomeDepartamento = nomeDepartamento;

    }

    public String getNomeEmpregado() {
        return nomeEmpregado;
    }

    public void setNomeEmpregado(String nomeEmpregado) {
        this.nomeEmpregado = nomeEmpregado;
    }

    public String getApelido1() {
        return apelido1;
    }

    public void setApelido1(String apelido1) {
        this.apelido1 = apelido1;
    }

    public String getApelido2() {
        return apelido2;
    }

    public void setApelido2(String apelido2) {
        this.apelido2 = apelido2;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public String getDataNacemento() {
        return dataNacemento;
    }

    public void setDataNacemento(String dataNacemento) {
        this.dataNacemento = dataNacemento;
    }

    public String getNomeXefe() {
        return nomeXefe;
    }

    public void setNomeXefe(String nomeXefe) {
        this.nomeXefe = nomeXefe;
    }

    public String getNomeDepartamento() {
        return nomeDepartamento;
    }

    public void setNomeDepartamento(String nomeDepartamento) {
        this.nomeDepartamento = nomeDepartamento;
    }


}
