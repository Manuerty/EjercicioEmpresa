package edu.badpals;


import edu.badpals.Model.Departamento;
import edu.badpals.Model.ConnectionDDBB;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        ConnectionDDBB conectar = new ConnectionDDBB();
        Connection conector = conectar.createConection();

        //Ejercicio 2.1

        //Funciona con datos hardcodeados
        //Ejercicio2.updateSalarioDepartamento(conector, 1, 10000);

        //Funciona con datos hardcodeados
        //Departamento departamento = new Departamento(7, "Informatica", "7000007", "2021-01-01");
        //Ejercicio2.createDepartamento(conector, departamento);

        //Funciona con datos hardcodeados
        //Ejercicio2.deleteEmpledoDepartamento(conector, "0010010", 8);

        //Ejercicio 2.2
        //Funciona con datos hardcodeados
        Ejercicio2.listarEmpleadosLocalidades(conector, "Vigo");

    }
}


