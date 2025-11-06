package org.example.json_case

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue

data class DataPoint(val x: Double, val y: Double)

fun main() {
    // Lector, lee el archivo JSON
    val mapper = jacksonObjectMapper()

    // Cargar archivo JSON desde resources/data/data.json
    val inputStream = object {}.javaClass.getResourceAsStream("/data/data.json")
        ?: return println("No se encontró el archivo JSON")

    // Leemos cada objeto JSON del file y lo convertimos en una lista (dataPoints)
    val dataPoints: List<DataPoint> = mapper.readValue(inputStream)

    // Listas X e Y (Recorre la lista y saca solo la coordenada X o Y de cada punto)
    val x = dataPoints.map { it.x }
    val y = dataPoints.map { it.y }

    // Calcular regresión lineal
    val n = x.size.toDouble()
    val sumX = x.sum()
    val sumY = y.sum()
    val sumXY = x.indices.sumOf { i -> x[i] * y[i] }
    val sumX2 = x.sumOf { it * it }

    val m = (n * sumXY - sumX * sumY) / (n * sumX2 - sumX * sumX)
    val b = (sumY - m * sumX) / n

    // Mostrar resultados
    println("=====================================")
    println("Puntos cargados: ${dataPoints.size}")
    println("Pendiente (m): %.4f".format(m))
    println("Intersección (b): %.4f".format(b))
    println("Fórmula: Y = %.4f + %.4f * X".format(b, m))

    // Ejemplo de predicción
    val xPred = 28.0
    val yPred = m * xPred + b
    println("Predicción para X = $xPred: Y = %.4f".format(yPred))
}
