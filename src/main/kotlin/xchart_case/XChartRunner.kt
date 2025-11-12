package org.example.xchart_case

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.knowm.xchart.SwingWrapper
import org.knowm.xchart.XYChartBuilder
import org.knowm.xchart.style.markers.SeriesMarkers

fun main() {
    runXChart()
}

// Clase para representar los puntos
data class DataPoint(val x: Double, val y: Double)

fun runXChart() {
    // Lector, lee el archivo JSON
    val mapper = jacksonObjectMapper()

    // Cargar el archivo JSON desde resources/data/data.json
    val inputStream = object {}.javaClass.getResourceAsStream("/data/data.json")
        ?: return println("No se encontró el archivo JSON")

    // Leer el JSON como lista de DataPoint
    val dataPoints: List<DataPoint> = mapper.readValue(inputStream)

    // Extraer listas X e Y
    val x = dataPoints.map { it.x }
    val y = dataPoints.map { it.y }

    // Crear el gráfico
    val chart = XYChartBuilder()
        .width(800)
        .height(600)
        .title("Regresión Lineal - Datos del JSON")
        .xAxisTitle("Eje X")
        .yAxisTitle("Eje Y")
        .build()

    // Añadir los puntos
    val series = chart.addSeries("Datos", x, y)
    series.marker = SeriesMarkers.CIRCLE
    // Quitar lineas: 
    series.lineStyle = org.knowm.xchart.style.lines.SeriesLines.NONE

    // Mostrar el gráfico en pantalla
    SwingWrapper(chart).displayChart()

}