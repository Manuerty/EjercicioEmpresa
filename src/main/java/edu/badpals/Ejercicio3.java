package edu.badpals;

import edu.badpals.Model.ConnectionDDBB;

import java.sql.*;

public class Ejercicio3 {

    private static ConnectionDDBB connectionDDBB = new ConnectionDDBB();
//  3.1
    public static void showDatabaseInfo() {
        ConnectionDDBB connectionDDBB = new ConnectionDDBB(); // Crear el objeto de conexión

        try (Connection connection = connectionDDBB.createConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();


            System.out.println("Nombre del SXBD: " + metaData.getDatabaseProductName());
            System.out.println("Versión del SXBD: " + metaData.getDatabaseProductVersion());
            System.out.println("Versión principal del SXBD: " + metaData.getDatabaseMajorVersion());
            System.out.println("Versión secundaria del SXBD: " + metaData.getDatabaseMinorVersion());


            System.out.println("Nombre del conector JDBC: " + metaData.getDriverName());
            System.out.println("Versión principal del conector JDBC: " + metaData.getDriverMajorVersion());
            System.out.println("Versión secundaria del conector JDBC: " + metaData.getDriverMinorVersion());
            System.out.println("Versión del conector JDBC: " + metaData.getDriverVersion());


            System.out.println("URL de la base de datos: " + metaData.getURL());
            System.out.println("Usuario conectado: " + metaData.getUserName());
            System.out.println("Base de datos en modo solo lectura: " + connection.isReadOnly());
        } catch (SQLException e) {
            System.err.println("Error al obtener información de la base de datos: " + e.getMessage());
            e.printStackTrace();
        }
    }

//    3.2
//    -A
public static void showUserTablesInfo() {
    try (Connection connection = connectionDDBB.createConnection()) {
        DatabaseMetaData metaData = connection.getMetaData();
        String schema ="empresa_db";

        ResultSet tables = metaData.getTables(null, schema, null, new String[]{"TABLE"});

        System.out.println("Información de todas las tablas de usuario:");
        while (tables.next()) {
            String tableName = tables.getString("TABLE_NAME");
            String tableType = tables.getString("TABLE_TYPE");
            String schemaName = tables.getString("TABLE_SCHEM");

            if (schemaName == null || schemaName.isEmpty()) {
                schemaName = schema != null ? schema : "No especificado";
            }

            System.out.printf("Tabla: %s, Tipo: %s, Esquema: %s%n", tableName, tableType, schemaName);
        }
    } catch (SQLException e) {
        System.err.println("Error al obtener información de las tablas de usuario: " + e.getMessage());
        e.printStackTrace();
    }
}



    //    -B
    public static void showTableColumns(String schema, String table) {
        try (Connection connection = connectionDDBB.createConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet columns = metaData.getColumns(null, schema, table, null);

            System.out.printf("Información de columnas para la tabla '%s' en el esquema '%s':%n", table, schema);
            while (columns.next()) {
                String columnName = columns.getString("COLUMN_NAME");
                String dataType = columns.getString("TYPE_NAME");
                int columnSize = columns.getInt("COLUMN_SIZE");
                String isNullable = columns.getString("IS_NULLABLE");

                // Formatea si permite o no nulos
                String nullable = isNullable.equals("YES") ? "Sí" : "No";
                System.out.printf("Columna: %s, Tipo: %s, Tamaño: %d, Admite Nulos: %s%n", columnName, dataType, columnSize, nullable);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener información de las columnas: " + e.getMessage());
            e.printStackTrace();
        }
    }


    //    -C
    public static void showProceduresInfo() {
        try (Connection connection = connectionDDBB.createConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet procedures = metaData.getProcedures(null, null, null);

            System.out.println("Información de todos los procedimientos:");
            while (procedures.next()) {
                String procedureName = procedures.getString("PROCEDURE_NAME");
                int procedureType = procedures.getInt("PROCEDURE_TYPE");

                // Convertir el tipo a nombre legible
                String procedureTypeName = (procedureType == 1) ? "Procedimiento" : "Función";
                System.out.printf("Procedimiento: %s, Tipo: %s%n", procedureName, procedureTypeName);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener información de los procedimientos: " + e.getMessage());
            e.printStackTrace();
        }
    }

    //    -D
    public static void showPrimaryKeys(String schema, String table) {
        try (Connection connection = connectionDDBB.createConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet primaryKeys = metaData.getPrimaryKeys(null, schema, table);

            System.out.printf("Claves primarias de la tabla '%s' en el esquema '%s':%n", table, schema);
            while (primaryKeys.next()) {
                String columnName = primaryKeys.getString("COLUMN_NAME");
                System.out.printf("Columna: %s%n", columnName);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener claves primarias: " + e.getMessage());
            e.printStackTrace();
        }
    }

    //    -E
    public static void showForeignKeys(String schema, String table) {
        try (Connection connection = connectionDDBB.createConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet foreignKeys = metaData.getImportedKeys(null, schema, table);

            System.out.printf("Claves foráneas de la tabla '%s' en el esquema '%s':%n", table, schema);
            while (foreignKeys.next()) {
                String fkColumnName = foreignKeys.getString("FKCOLUMN_NAME");
                String pkTableName = foreignKeys.getString("PKTABLE_NAME");
                String pkColumnName = foreignKeys.getString("PKCOLUMN_NAME");

                System.out.printf("Columna FK: %s, Referencia -> Tabla: %s, Columna: %s%n", fkColumnName, pkTableName, pkColumnName);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener claves foráneas: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // 3.3
    public static void showDatabaseFunctionsAndPrivileges() {
        try (Connection connection = connectionDDBB.createConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();


            System.out.println("Funciones de cadena soportadas:");
            System.out.println(metaData.getStringFunctions());

            System.out.println("Funciones de fecha y hora soportadas:");
            System.out.println(metaData.getTimeDateFunctions());

            System.out.println("Funciones matemáticas soportadas:");
            System.out.println(metaData.getNumericFunctions());

            System.out.println("Funciones del sistema soportadas:");
            System.out.println(metaData.getSystemFunctions());


            System.out.println("\nPalabras reservadas del SGBD:");
            String reservedWords = metaData.getSQLKeywords();
            System.out.println(reservedWords);


            System.out.println("\nCadena para delimitar los identificadores:");
            System.out.println(metaData.getIdentifierQuoteString());


            System.out.println("\nCadena de escape de caracteres comodines:");
            System.out.println(metaData.getSearchStringEscape());


            System.out.println("\n¿Puede el usuario llamar todos los procedimientos?");
            System.out.println(metaData.supportsStoredFunctionsUsingCallSyntax());


            System.out.println("\n¿Puede el usuario acceder a todas las tablas?");

            if (metaData.supportsResultSetConcurrency(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY)) {
                System.out.println("Sí, puede acceder a las tablas.");
            } else {
                System.out.println("No se puede comprobar el acceso a las tablas de forma directa.");
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener la información del SGBD: " + e.getMessage());
            e.printStackTrace();
        }
    }
    //3.4
    public static void showAdditionalInformation() {
        try (Connection connection = connectionDDBB.createConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();

            // Número de conexiones simultáneas
            System.out.println("Número de conexiones simultáneas soportadas:");
            int maxConnections = metaData.getMaxConnections();
            System.out.println(maxConnections > 0 ? maxConnections : "No disponible.");

            // Número máximo de sentencias simultáneas
            System.out.println("\nNúmero máximo de sentencias simultáneas soportadas:");
            int maxStatements = metaData.getMaxStatements();
            System.out.println(maxStatements > 0 ? maxStatements : "No disponible.");

            // Número máximo de tablas en una consulta SELECT
            System.out.println("\nNúmero máximo de tablas en una consulta SELECT:");
            int maxTablesInSelect = metaData.getMaxTablesInSelect();
            System.out.println(maxTablesInSelect > 0 ? maxTablesInSelect : "No disponible.");

            // Longitud máxima del nombre de una tabla
            System.out.println("\nLongitud máxima del nombre de una tabla:");
            int maxTableNameLength = metaData.getMaxTableNameLength();
            System.out.println(maxTableNameLength > 0 ? maxTableNameLength : "No disponible.");

            // Longitud máxima del nombre de una columna
            System.out.println("\nLongitud máxima del nombre de una columna:");
            int maxColumnNameLength = metaData.getMaxColumnNameLength();
            System.out.println(maxColumnNameLength > 0 ? maxColumnNameLength : "No disponible.");

            // Longitud máxima de una sentencia SQL
            System.out.println("\nLongitud máxima de una sentencia SQL:");
            int maxSQLLength = metaData.getMaxStatementLength();
            System.out.println(maxSQLLength > 0 ? maxSQLLength : "No disponible.");

            // Longitud máxima de una fila (número máximo de filas en respuesta)
            System.out.println("\nLongitud máxima de una fila (número máximo de filas en respuesta):");
            int maxRowLength = metaData.getMaxRowSize();
            System.out.println(maxRowLength > 0 ? maxRowLength : "No disponible.");

            // Longitud máxima del nombre de un procedimiento
            System.out.println("\nLongitud máxima del nombre de un procedimiento:");
            int maxProcedureNameLength = metaData.getMaxProcedureNameLength();
            System.out.println(maxProcedureNameLength > 0 ? maxProcedureNameLength : "No disponible.");

            // Número máximo de columnas en una cláusula ORDER, SELECT o GROUP BY
            System.out.println("\nNúmero máximo de columnas en una cláusula ORDER, SELECT o GROUP BY:");
            int maxColumnsInGroupBy = metaData.getMaxColumnsInGroupBy();
            int maxColumnsInOrderBy = metaData.getMaxColumnsInOrderBy();
            int maxColumnsInSelect = metaData.getMaxColumnsInSelect();

            System.out.println("Máximo en GROUP BY: " + (maxColumnsInGroupBy > 0 ? maxColumnsInGroupBy : "No disponible."));
            System.out.println("Máximo en ORDER BY: " + (maxColumnsInOrderBy > 0 ? maxColumnsInOrderBy : "No disponible."));
            System.out.println("Máximo en SELECT: " + (maxColumnsInSelect > 0 ? maxColumnsInSelect : "No disponible."));

        } catch (SQLException e) {
            System.err.println("Error al obtener la información del SGBD: " + e.getMessage());
            e.printStackTrace();
        }
    }
    //3.5
    public static void showTransactionDetails() {
        try (Connection connection = connectionDDBB.createConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();

            // Si el SGBD soporta transacciones
            System.out.println("¿El SGBD soporta transacciones?");
            System.out.println(metaData.supportsTransactions() ? "Sí" : "No");

            // Nivel de aislamiento de transacciones predeterminado
            System.out.println("\nNivel de aislamiento de transacciones predeterminado:");
            int isolationLevel = connection.getTransactionIsolation();
            switch (isolationLevel) {
                case Connection.TRANSACTION_READ_UNCOMMITTED:
                    System.out.println("Read Uncommitted");
                    break;
                case Connection.TRANSACTION_READ_COMMITTED:
                    System.out.println("Read Committed");
                    break;
                case Connection.TRANSACTION_REPEATABLE_READ:
                    System.out.println("Repeatable Read");
                    break;
                case Connection.TRANSACTION_SERIALIZABLE:
                    System.out.println("Serializable");
                    break;
                default:
                    System.out.println("Nivel de aislamiento desconocido");
            }

            // En JDBC, se puede asumir que si el SGBD soporta transacciones,
            // también soporta sentencias DML y DDL dentro de transacciones
            System.out.println("\n¿Soporta sentencias DML y DDL dentro de las transacciones?");
            if (metaData.supportsTransactions()) {
                System.out.println("Sí");
            } else {
                System.out.println("No");
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener la información de transacciones: " + e.getMessage());
            e.printStackTrace();
        }
    }
    //3.6
    public static void showAdvancedSQLFeatures() {
        try {
            Connection connection = connectionDDBB.createConnection();
            DatabaseMetaData dbmd = connection.getMetaData();

            System.out.println("=============================================");
            System.out.println("          Características Avanzadas SQL      ");
            System.out.println("=============================================");

            // Verificar si ALTER TABLE soporta ADD COLUMN y DROP COLUMN
            System.out.println("\n1. ¿Se puede utilizar ADD COLUMN y DROP COLUMN en ALTER TABLE?");
            System.out.println("   Respuesta: " + (dbmd.supportsAlterTableWithAddColumn() ? "Sí" : "No"));

            // Verificar si se puede usar 'AS' en los alias de columnas
            System.out.println("\n2. ¿Se puede utilizar la palabra 'AS' en los alias de columnas?");
            System.out.println("   Respuesta: " + (dbmd.supportsColumnAliasing() ? "Sí" : "No"));

            // Verificar el comportamiento de concatenar NULL con NOT NULL
            System.out.println("\n3. ¿El resultado de concatenar NULL con un valor NOT NULL da como resultado NULL?");
            System.out.println("   Respuesta: " + (dbmd.nullPlusNonNullIsNull() ? "Sí" : "No"));

            // Verificar si se soportan conversiones entre tipos de datos JDBC
            System.out.println("\n4. ¿Se soportan las conversiones entre tipos de datos JDBC?");
            System.out.println("   Respuesta: " + (dbmd.supportsConvert() ? "Sí" : "No"));

            // Verificar si se soportan nombres de tablas correlacionadas
            System.out.println("\n5. ¿Se soportan los nombres de tablas correlacionadas?");
            System.out.println("   Respuesta: " + (dbmd.supportsTableCorrelationNames() ? "Sí" : "No"));

            // Verificar si se permite usar una columna no incluida en SELECT en ORDER BY
            System.out.println("\n6. ¿Se permite usar una columna no incluida en la instrucción SELECT en una cláusula ORDER BY?");
            System.out.println("   Respuesta: " + (dbmd.supportsOrderByUnrelated() ? "Sí" : "No"));

            // Verificar si se soporta la cláusula GROUP BY
            System.out.println("\n7. ¿Se soporta la cláusula GROUP BY?");
            System.out.println("   Respuesta: " + (dbmd.supportsGroupBy() ? "Sí" : "No"));

            // Verificar si se puede usar una columna no incluida en SELECT en GROUP BY
            System.out.println("\n8. ¿Se permite el uso de una columna no incluida en la instrucción SELECT en una cláusula GROUP BY?");
            System.out.println("   Respuesta: " + (dbmd.supportsGroupByUnrelated() ? "Sí" : "No"));

            // Verificar si se soportan las cláusulas LIKE
            System.out.println("\n9. ¿Se soportan las cláusulas LIKE?");
            System.out.println("   Respuesta: " + (dbmd.supportsLikeEscapeClause() ? "Sí" : "No"));

            // Verificar si se soportan los outer joins
            System.out.println("\n10. ¿Se soportan los outer joins?");
            System.out.println("   Respuesta: " + (dbmd.supportsOuterJoins() ? "Sí" : "No"));

            // Verificar si se soportan las subconsultas EXISTS
            System.out.println("\n11. ¿Se soportan las subconsultas EXISTS?");
            System.out.println("   Respuesta: " + (dbmd.supportsSubqueriesInExists() ? "Sí" : "No"));

            // Verificar si se soportan las subconsultas en expresiones de comparación IN
            System.out.println("\n12. ¿Se soportan las subconsultas en expresiones de comparación IN?");
            System.out.println("   Respuesta: " + (dbmd.supportsSubqueriesInIns() ? "Sí" : "No"));

            // Verificar si se soportan las subconsultas en expresiones cuantificadas
            System.out.println("\n13. ¿Se soportan las subconsultas en expresiones cuantificadas?");
            System.out.println("   Respuesta: " + (dbmd.supportsSubqueriesInQuantifieds() ? "Sí" : "No"));

            System.out.println("=============================================");
        } catch (SQLException e) {
            System.out.println("Error al mostrar información sobre soporte de características.");
            e.printStackTrace();
        }
    }





    //3.7
    public static void showQueryColumnsDetails(String query) {
        try (Connection connection = connectionDDBB.createConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            // Obtener los metadatos del ResultSet
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount(); // Número de columnas

            System.out.println("Número de columnas recuperadas: " + columnCount);

            // Iterar sobre cada columna y obtener su nombre, tipo, tamaño y si permite nulos
            for (int i = 1; i <= columnCount; i++) {
                String columnName = metaData.getColumnName(i);
                String columnType = metaData.getColumnTypeName(i);
                int columnSize = metaData.getColumnDisplaySize(i);
                boolean isNullable = metaData.isNullable(i) == ResultSetMetaData.columnNullable;

                // Imprimir la información de cada columna
                System.out.println("Columna " + i + ":");
                System.out.println("  Nombre: " + columnName);
                System.out.println("  Tipo: " + columnType);
                System.out.println("  Tamaño: " + columnSize);
                System.out.println("  ¿Permite nulos? " + (isNullable ? "Sí" : "No"));
                System.out.println("------------------------------------------------");
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener los metadatos de la consulta: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Aquí iría el código para establecer la conexión con la base de datos
    private static class connectionDDBB {
        public static Connection createConnection() throws SQLException {
            // Código para crear la conexión a la base de datos
            // Por ejemplo, utilizando DriverManager:
            // return DriverManager.getConnection("jdbc:mysql://localhost:3306/mi_base_de_datos", "usuario", "contraseña");
            return null; // Placeholder para la conexión
        }
    }







}
