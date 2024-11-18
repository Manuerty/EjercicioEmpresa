package edu.badpals;


import edu.badpals.Model.ConnectionDDBB;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        ConnectionDDBB conectar = new ConnectionDDBB();
        Connection conector = conectar.createConnection();

//        Ejercicio 2.1
//        Funciona con datos hardcodeados
//        Ejercicio2.updateSalarioDepartamento(conector, 1, 10000);
//
//        Funciona con datos hardcodeados
//        Departamento departamento = new Departamento(7, "ADMINISTRACIÓN", "7000007", "2021-01-01");
//        Ejercicio2.createDepartamento(conector, departamento);
//
//        Funciona con datos hardcodeados
//        Ejercicio2.deleteEmpledoDepartamento(conector, "0010010", 8);
//
//        Ejercicio 2.2
//        Funciona con datos hardcodeados
//        Ejercicio2.listarEmpleadosLocalidades(conector, "Vigo");
//
//        Ejercicio 2.3
//        -A
//        Funciona con datos hardcodeados
//        Ejercicio2.changeDepartamentoInProxecto(conector, "DESEÑO NOVA TENDA VIGO", "TÉCNICO");
//        -B
//        Funciona con datos hardcodeados
//        Ejercicio2.newProxecto(conector, new Proxecto(11, "Proyecto 9", "Vigo", 7));
//        -C
//        Funciona con datos hardcodeados
//        Ejercicio2.deleteProxecto(conector, 11);

//        Ejercicio 2.4
//        Funciona con datos hardcodeados
//        Ejercicio2.listProxetosOfDepartamentos(conector, "INFORMÁTICA");

//        Ejercicio 2.5
//        -A
//        Funciona con datos hardcodeados
//        Ejercicio2.cambioDomicilio(conector, "6565656", "Rua Nova", 10, "2B", "12345", "Vigo");

//        -B
//        Funciona con datos hardcodeados
//        Proxecto proxecto = Ejercicio2.datosProxecto(conector, 1);
//        System.out.println(proxecto);

//        -C
//        Funciona con datos hardcodeados
//        Ejercicio2.departControlaProxec(conector, 1);

//        -D
//        Funciona con datos hardcodeados
//        Ejercicio2.obtenerNumEmpleadosDepartamento(conector, "INFORMÁTICA");

//        Ejercicio 2.6
//        -A
//        Funciona con datos hardcodeados
//        Ejercicio2.visualizarTiposResultSet(conector);

//        -B
//        Funciona con datos hardcodeados
//        Ejercicio2.insertarProxecto(conector, new Proxecto(14, "Proyecto ResultSet2", "Vigo", 7));

//        -C
//        Funciona con datos hardcodeados
//        Ejercicio2.incrementarSalarioDepartamento(conector, 1, 1000);

//        -D
//        Funciona con datos hardcodeados
//        Ejercicio2.obtenerEmpleadosPorNumeroProyectos(conector, 1);


//        Ejercicio 3.1
//        Ejercicio3.showDatabaseInfo();

//        Ejercicio 3.2
//        -A
//        Ejercicio3.showUserTablesInfo();

//        -B
//        Ejercicio3.showTableColumns("empresa_db", "EMPREGADO");

//        -C
//        Ejercicio3.showProceduresInfo();

//        -D
//        Ejercicio3.showPrimaryKeys("empresa_db", "EMPREGADO");

//        -E
//        Ejercicio3.showForeignKeys("empresa_db", "EMPREGADO");

//        Ejercicio 3.3
//        Ejercicio3.showDatabaseFunctionsAndPrivileges();

//        Ejercicio 3.4
//        Ejercicio3.showAdditionalInformation();

//        Ejercicio 3.5
//        Ejercicio3.showTransactionDetails();

//        Ejercicio 3.6
//        Ejercicio3.showAdvancedSQLFeatures();

//        Ejercicio 3.7
//        String query = "SELECT * FROM proxecto";
//        Ejercicio3.showQueryColumnsDetails(query);










    }
}


