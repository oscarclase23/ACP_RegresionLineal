import java.sql.DriverManager
import kotlin.math.pow

// --- CONFIGURACIÓN DE LA BASE DE DATOS ---
const val DB_URL = "jdbc:mariadb://localhost:3306/abalone_db"
const val DB_USER = "root"
const val DB_PASSWORD = "mi_contraseña_root" // !!! CAMBIA ESTO !!!
const val TABLE_NAME = "abalones"

fun main() {
    println("--- Análisis de Regresión Lineal (Length vs Rings) ---")

    try {
        // 1. Conexión y Carga de Datos
        val connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)
        println("Conexión a la base de datos establecida con éxito.")

        val statement = connection.createStatement()
        val resultSet = statement.executeQuery("SELECT largo, anillos FROM $TABLE_NAME")

        // Inicialización de variables de sumatoria
        var sumX = 0.0
        var sumY = 0.0
        var sumX2 = 0.0 // Suma de X al cuadrado
        var sumXY = 0.0 // Suma de productos (X * Y)
        var n = 0.0     // Número de muestras

        // 2. Cálculo de Sumatorios a partir de la Base de Datos
        while (resultSet.next()) {
            val x = resultSet.getDouble("largo") // X (largo)
            val y = resultSet.getInt("anillos").toDouble() // Y (anillos)

            sumX += x
            sumY += y
            sumX2 += x.pow(2)
            sumXY += x * y
            n++
        }

        if (n == 0.0) {
            println("ERROR: No se encontraron datos para el análisis.")
            connection.close()
            return
        }

        // 3. Cálculo de Parámetros de Regresión

        // Pendiente (m)
        val numeratorM = n * sumXY - sumX * sumY
        val denominatorM = n * sumX2 - sumX.pow(2)
        val m = if (denominatorM != 0.0) numeratorM / denominatorM else 0.0

        // Intersección (b)
        val b = (sumY - m * sumX) / n

        // 4. Mostrar Sumatorios (como en tu código original)
        println("\n--- SUMATORIOS DE LA BASE DE DATOS ---")
        println("Número de muestras (n): $n")
        println("Suma de valores de X: ${String.format("%.4f", sumX)}")
        println("Suma de valores de Y: ${String.format("%.4f", sumY)}")
        println("Suma de valores de X cuadrado: ${String.format("%.4f", sumX2)}")
        println("Suma de productos (X*Y): ${String.format("%.4f", sumXY)}")

        println("-------------------------------------")

        // 5. Mostrar Ecuación de la Recta
        println("Pendiente (m): ${String.format("%.4f", m)}")
        println("Intersección (b): ${String.format("%.4f", b)}")
        println("-------------------------------------")
        println("Ecuación de la Recta (Y = b + mX):")
        println("Y = ${String.format("%.4f", b)} + ${String.format("%.4f", m)} X")
        println("-------------------------------------")

        // 6. Ejemplos de Predicción
        val xPred = 0.600 // Usamos un valor plausible para la longitud (x)
        val yPred = m * xPred + b
        println("Conociendo el valor de X (Length) = ${xPred} mm")
        println("Resultado (Rings) y = ${String.format("%.2f", yPred)}")

        val yPred2 = 12.0 // Usamos un valor plausible para los anillos (y)
        val xPred2 = (yPred2 - b) / m
        println("\nConociendo el valor de Y (Rings) = ${yPred2} anillos")
        println("Resultado (Length) x = ${String.format("%.3f", xPred2)} mm")

        connection.close()

    } catch (e: Exception) {
        println("\nERROR al ejecutar el análisis. Detalles: ${e.message}")
    } finally {
        println("\nConexión cerrada.")
    }
}
