package ckh.study

import java.text.NumberFormat
import java.util.Locale

fun main() {
    println(
        statement(
            Invoice(
                "BigCo",
                listOf(
                    Performance("hamlet", 55),
                    Performance("as-like", 35),
                    Performance("othello", 40)
                )
            ),
            mapOf(
                "hamlet" to Play("tragedy"),
                "as-like" to Play("comedy"),
                "othello" to Play("tragedy")
            )
        )
    )
}

fun statement(invoice: Invoice, plays: Map<String, Play>): String {
    var totalAmount = 0
    var volumeCredits = 0.0
    var result = "청구 내역 (고객명: ${invoice.customer})"

    val format: NumberFormat = NumberFormat.getCurrencyInstance(Locale.US).apply {
        minimumFractionDigits = 2
    }

    for (perf in invoice.performances) {
        val play = plays[perf.playID] ?: error("알 수 없는 장르: ${perf.playID}")
        var thisAmount = 0

        when (play.type) {
            "tragedy" -> {
                thisAmount = 40000
                if (perf.audience > 30) {
                    thisAmount += 1000 * (perf.audience - 30)
                }
            }
            "comedy" -> {
                thisAmount = 30000
                if (perf.audience > 20) {
                    thisAmount += 10000 + 500 * (perf.audience - 20)
                }
                thisAmount += 300 * perf.audience
            }
            else -> {
                error("알 수 없는 장르: ${play.type}")
            }
        }

        volumeCredits += (perf.audience - 30).coerceAtLeast(0)
        if ("comedy" == play.type) volumeCredits += perf.audience / 5

        result += "${play.type}: ${format.format(thisAmount / 100.0)} (${perf.audience}석)\n"
        totalAmount += thisAmount
    }
    result += "총액: ${format.format(totalAmount / 100.0)}\n"
    result += "적립 포인트: $volumeCredits 점\n"
    return result
}

data class Invoice (
    val customer: String,
    val performances: List<Performance>
)

data class Performance (
    val playID: String,
    val audience: Int
)

data class Play (
    val type: String
)