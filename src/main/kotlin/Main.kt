package org.example

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    //Datos de X
    val x = listOf(5.0, 10.0, 15.0, 20.0, 25.0,5.0, 10.0, 15.0, 20.0, 25.0,5.0, 10.0, 15.0, 20.0, 25.0)
    val y = listOf(9.6, 20.1, 29.9, 39.1, 50.0,9.6, 19.4, 29.7, 40.3, 49.9,10.7, 21.3, 30.7, 41.8, 51.2)
    val n = x.size.toDouble()

    // Calculo de sumatorios
    val sumX = x.sum()
    val sumY = y.sum()
    val sumX2 = x.sumOf {it * it}
    var sumXY = 0.0

    //Suma de productos (x * y)
    for (i in x.indices){
        sumXY = sumXY + x[i] * y[i]
    }

    //Pendiente (m)
    val m = (n*sumXY - sumX * sumY) / (n * sumX2 - sumX * sumX)

    // Intersección (b)
    val b = (sumY - m *sumX) / n

    println("Suma de productos: $sumXY")
    println("Suma de valores de Y: $sumY")
    println("Suma de valores de X: $sumX")
    println("Suma de valores de X cuadrado: $sumX2")
    println("-------------------------------------")
    println("Pendiente (m): $m")
    println("Intersección (b): $b")

    println("-------------------------------------")

    // Formula
    println("Conociendo el valor de X = 28")
    val xPred = 28.0
    val yPred = m * xPred + b
    println("Resultado: y = $yPred")

    println("-------------------------------------")

    println("Conociendo el valor de Y = 56.47")
    val yPred2 = 56.47
    val xPred2 = (yPred2 - b) / m
    println("Resultado: x = $xPred2")

}
