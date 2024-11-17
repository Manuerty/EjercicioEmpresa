package edu.badpals;

import edu.badpals.Model.Departamento;
import edu.badpals.Model.Empleado;
import edu.badpals.Model.Proxecto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Ejercicio2 {

    //Ejercicio 2.1
    public static void updateSalarioDepartamento(Connection conector, int departamento, float incremento) {
        try {
            String query = "UPDATE EMPREGADO SET Salario = Salario + ? WHERE Num_departamento_pertenece = ?";
            PreparedStatement preparedStatement = conector.prepareStatement(query);
            preparedStatement.setFloat(1, incremento);
            preparedStatement.setInt(2, departamento);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al actualizar el salario: " + e.getMessage());
        }
    }

    public static void createDepartamento(Connection conector, Departamento departamento) {
        try {
            String query = "INSERT INTO departamento (Num_departamento, Nome_departamento , NSS_dirige, Data_direccion) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = conector.prepareStatement(query);
            preparedStatement.setInt(1, departamento.getNum_Departamento());
            preparedStatement.setString(2, departamento.getNome_departamento());
            preparedStatement.setString(3, departamento.getNSS_dirige());
            preparedStatement.setString(4, departamento.getData_direccion());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al crear el departamento: " + e.getMessage());
        }
    }

    public static void deleteEmpledoDepartamento(Connection conector, String NSS_Empleado, int Num_Proxecto) {
        try {
            String query = "DELETE FROM EMPREGADO_PROXECTO WHERE NSS_EMPREGADO = ? AND Num_proxecto = ? ";
            PreparedStatement preparedStatement = conector.prepareStatement(query);
            preparedStatement.setString(1, NSS_Empleado);
            preparedStatement.setInt(2, Num_Proxecto);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al eliminar el empleado del proyecto: " + e.getMessage());
        }
    }

    //Ejercicio 2.2

    public static List<Empleado> viewEmpleadosLocalidades(Connection conector, String localidad) {
        List<Empleado> empleados = new ArrayList<>();
        String query = "SELECT E.Nome AS Nome_empregado, E.Apelido_1, E.Apelido_2, E.Localidade, E.Salario, E.Data_nacemento, J.Nome AS Nome_xefe, D.Nome_departamento FROM EMPREGADO E LEFT JOIN EMPREGADO J ON E.NSS_Supervisa = J.NSS JOIN DEPARTAMENTO D ON E.Num_departamento_pertenece = D.Num_departamento WHERE E.Localidade = ?";

        try (PreparedStatement preparedStatement = conector.prepareStatement(query)) {
            preparedStatement.setString(1, localidad);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    empleados.add(new Empleado(
                            resultSet.getString("Nome_empregado"),
                            resultSet.getString("Apelido_1"),
                            resultSet.getString("Apelido_2"),
                            resultSet.getString("Localidade"),
                            resultSet.getFloat("Salario"),
                            resultSet.getString("Data_nacemento"),
                            resultSet.getString("Nome_xefe"),
                            resultSet.getString("Nome_departamento")
                    ));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al ver los empleados de la localidad: " + e.getMessage());
        }

        return empleados;
    }

    public static void listarEmpleadosLocalidades(Connection conector, String localidad) throws SQLException {
        List<Empleado> empleados = viewEmpleadosLocalidades(conector, localidad);
        StringBuilder sb = new StringBuilder();

        for (Empleado empleado : empleados) {
            StringBuilder append = sb.append("- Nombre: ").append(empleado.getNomeEmpregado()).append(" ")
                    .append(", 1er Apellido: ").append(empleado.getApelido1()).append(" ")
                    .append(", 2o Apellido: ").append(empleado.getApelido2()).append(" ")
                    .append(", Localidad: ").append(empleado.getLocalidade()).append(" ")
                    .append(", Salario: ").append(empleado.getSalario()).append("€ ")
                    .append(", Fecha de nacimiento: ").append(empleado.getDataNacemento()).append(" ")
                    .append(", Jefe: ").append(empleado.getNomeXefe()).append(" ")
                    .append(", Departamento: ").append(empleado.getNomeDepartamento()).append(" ")
                    .append("\n");
        }

        System.out.println(sb.toString());

    }

    //Ejercicio 2.3
    // -A

    public static void changeDepartamentoInProxecto(Connection conector, String Nome_Proxecto, String Nome_departamento) {
        try {
            String query = "UPDATE PROXECTO SET NUM_DEPARTAMENTO = (SELECT NUM_DEPARTAMENTO FROM DEPARTAMENTO WHERE NOME_DEPARTAMENTO = ?) WHERE  NOME_PROXECTO = ?";
            PreparedStatement preparedStatement = conector.prepareStatement(query);
            preparedStatement.setString(1, Nome_departamento);
            preparedStatement.setString(2, Nome_Proxecto);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al cambiar el departamento en el proyecto: " + e.getMessage());
        }

    }


    //-B
    public static void newProxecto(Connection conector, Proxecto newProxecto) {
        try {
            String query = "INSERT INTO PROXECTO (Num_proxecto, Nome_proxecto, Lugar, NUM_DEPARTAMENTO) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = conector.prepareStatement(query);
            preparedStatement.setInt(1, newProxecto.getNum_proxecto());
            preparedStatement.setString(2, newProxecto.getNome_proxecto());
            preparedStatement.setString(3, newProxecto.getLugar());
            preparedStatement.setInt(4, newProxecto.getNum_departamento_controla());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al crear el proyecto: " + e.getMessage());
        }

    }

    //-C
    public static void deleteProxecto(Connection conector, int Num_Proxecto) {
        try {
            String query = "DELETE FROM PROXECTO WHERE Num_proxecto = ?";
            PreparedStatement preparedStatement = conector.prepareStatement(query);
            preparedStatement.setInt(1, Num_Proxecto);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al eliminar un proxecto" + e.getMessage());
        }

    }


    //Ejercicio 2.4
    public static List<Proxecto> proxectosOfDepartamento(Connection conector, String Nome_departamento) {
        List<Proxecto> proxectos = new ArrayList<>();
        String query = "SELECT P.Num_proxecto, P.Nome_proxecto, P.Lugar FROM PROXECTO P JOIN DEPARTAMENTO D ON P.NUM_DEPARTAMENTO = D.Num_departamento WHERE D.Nome_departamento = ?";
        try {
            PreparedStatement preparedStatement = conector.prepareStatement(query);
            preparedStatement.setString(1, Nome_departamento);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                proxectos.add(new Proxecto(
                        resultSet.getInt("Num_proxecto"),
                        resultSet.getString("Nome_proxecto"),
                        resultSet.getString("Lugar"),
                        0
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al ver los proyectos del departamento: " + e.getMessage());
        }
        System.out.println(proxectos);
        return proxectos;
    }

    public static void listProxetosOfDepartamentos(Connection conector, String Nome_departamento) throws SQLException {
        List<Proxecto> proxectos = proxectosOfDepartamento(conector, Nome_departamento);

        if (proxectos.isEmpty()) {
            System.out.println("No projects found for the department: " + Nome_departamento);
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (Proxecto proxecto : proxectos) {
            sb.append("- Num_proxecto: ").append(proxecto.getNum_proxecto()).append(" ")
                    .append(", Nome_proxecto: ").append(proxecto.getNome_proxecto()).append(" ")
                    .append(", Lugar: ").append(proxecto.getLugar()).append(" ")
                    .append("\n");
        }
        System.out.println(sb.toString());
    }

    //Ejercicio 2.5
    // -A

    public static void cambioDomicilio(Connection conector, String nss, String rua, Integer numeroRua, String piso, String cp, String localidade) {
        String query = "{CALL pr_cambioDomicilio(?, ?, ?, ?, ?, ?)}";
        try (CallableStatement callableStatement = conector.prepareCall(query)) {
            callableStatement.setString(1, nss);
            callableStatement.setString(2, rua);

            if (numeroRua != null) {
                callableStatement.setInt(3, numeroRua);
            } else {
                callableStatement.setNull(3, java.sql.Types.INTEGER);
            }

            callableStatement.setString(4, piso);
            callableStatement.setString(5, cp);
            callableStatement.setString(6, localidade);

            callableStatement.execute();
            System.out.println("Cambio de domicilio realizado con éxito para el empleado con NSS: " + nss);
        } catch (SQLException e) {
            System.err.println("Error al cambiar el domicilio: " + e.getMessage());
        }
    }

    // -B
    public static Proxecto datosProxecto(Connection conector, int numProxecto) {
        String query = "{CALL pr_DatosProxectos(?, ?, ?, ?)}";
        Proxecto proxecto = null;

        try (CallableStatement callableStatement = conector.prepareCall(query)) {

            callableStatement.setInt(1, numProxecto);


            callableStatement.registerOutParameter(2, java.sql.Types.VARCHAR); // Nome_proxecto
            callableStatement.registerOutParameter(3, java.sql.Types.VARCHAR); // Lugar
            callableStatement.registerOutParameter(4, java.sql.Types.INTEGER); // Num_departamento


            callableStatement.execute();


            String nomeProxecto = callableStatement.getString(2);
            String lugar = callableStatement.getString(3);
            int numDepartamento = callableStatement.getInt(4);


            proxecto = new Proxecto(numProxecto, nomeProxecto, lugar, numDepartamento);

        } catch (SQLException e) {
            System.err.println("Error al obtener los datos del proyecto: " + e.getMessage());
        }

        return proxecto; // Retorna el objeto Proxecto o null si ocurrió un error
    }

    // -C
    public static void departControlaProxec(Connection conector, int numProxectos) {
        String query = "{CALL pr_DepartControlaProxec(?)}";
        try (CallableStatement callableStatement = conector.prepareCall(query)) {
            callableStatement.setInt(1, numProxectos);

            boolean hasResultSet = callableStatement.execute(); // Ejecuta el procedimiento

            if (hasResultSet) {
                try (ResultSet resultSet = callableStatement.getResultSet()) {
                    while (resultSet.next()) {
                        String nomeDepartamento = resultSet.getString("Nome_departamento");
                        int numProxectosControlados = resultSet.getInt("Num_proxectos");
                        System.out.println("Departamento: " + nomeDepartamento + ", Número de Proyectos: " + numProxectosControlados);
                    }
                }
            } else {
                int affectedRows = callableStatement.getUpdateCount();
                System.out.println("Sentencia de actualización ejecutada. Filas afectadas: " + affectedRows);
            }

        } catch (SQLException e) {
            System.err.println("Error al ejecutar el procedimiento: " + e.getMessage());
        }
    }

    // -D
    public static void obtenerNumEmpleadosDepartamento(Connection conector, String nomeDepartamento) {
        String query = "{? = CALL fn_nEmpDepart(?)}"; // Llamada a la función con un parámetro de entrada y un valor de salida
        try (CallableStatement callableStatement = conector.prepareCall(query)) {
            // Registrar el parámetro de salida para la función
            callableStatement.registerOutParameter(1, java.sql.Types.INTEGER);

            // Asignar el valor del parámetro de entrada
            callableStatement.setString(2, nomeDepartamento);

            // Ejecutar la función
            callableStatement.execute();

            // Obtener el número de empleados retornado por la función
            int numEmpleados = callableStatement.getInt(1);

            // Visualizar el resultado
            System.out.println("El número de empleados en el departamento '" + nomeDepartamento + "' es: " + numEmpleados);
        } catch (SQLException e) {
            System.err.println("Error al ejecutar la función fn_nEmpDepart: " + e.getMessage());
        }
    }

    //Ejercicio 2.6





}
