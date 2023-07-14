package ru.netology

import fee
import isInLimit
import org.junit.Test

import org.junit.Assert.*

class MainKtTest {

    @Test
    fun feeVKUser() {
        val cartType: String = "VKPay"
        val amountCurMonth: Int = 100
        val amountThis: Int = 10

        val result = fee(cartType, amountCurMonth, amountThis)

        assertEquals(0, result)
    }

    @Test
    fun feeInFreeLimitUser() {
        val cartType: String = "Maestro"
        val amountCurMonth: Int = 10_000
        val amountThis: Int = 10_000

        val result = fee(cartType, amountCurMonth, amountThis)

        assertEquals(0, result)
    }

    @Test
    fun feeTinyVisaMirUser() {
        val cartType: String = "Visa"
        val amountCurMonth: Int = 1_000
        val amountThis: Int = 1_000

        val result = fee(cartType, amountCurMonth, amountThis)

        assertEquals(35, result)
    }

    @Test
    fun feeWhaleVisaMirUser() {
        val cartType: String = "Mir"
        val amountCurMonth: Int = 1_000
        val amountThis: Int = 10_000

        val result = fee(cartType, amountCurMonth, amountThis)

        assertEquals(75, result)
    }

    @Test
    fun feeLowMCMUser() { //MCM = mastercard & maestro
        val cartType: String = "Maestro"
        val amountCurMonth: Int = 0
        val amountThis: Int = 100

        val result = fee(cartType, amountCurMonth, amountThis)

        assertEquals(20, result)
    }

    @Test
    fun feeWhaleMCMUser() {
        val cartType: String = "MasterCard"
        val amountCurMonth: Int = 100_000
        val amountThis: Int = 10_000

        val result = fee(cartType, amountCurMonth, amountThis)

        assertEquals(80, result)
    }

    @Test
    fun feeErrorUser() {
        val cartType: String = "error"
        val amountCurMonth: Int = 100_000
        val amountThis: Int = 10_000

        val result = fee(cartType, amountCurMonth, amountThis)

        assertEquals(-1, result)
    }

    @Test
    fun isInLimitOverCaseInThis() {
        val cartType: String = "VKPay"
        val amountCurMonth: Int = 0
        val amountThis: Int = 20_000

        val result = isInLimit(cartType, amountCurMonth, amountThis)

        assertEquals(false, result)
    }

    @Test
    fun isInLimitOverCaseInMonth() {
        val cartType: String = "noVKPay"
        val amountCurMonth: Int = 600_000
        val amountThis: Int = 1_000

        val result = isInLimit(cartType, amountCurMonth, amountThis)

        assertEquals(false, result)
    }

    @Test
    fun isInLimitTrueVK() {
        val cartType: String = "noVKPay"
        val amountCurMonth: Int = 10_000
        val amountThis: Int = 1_000

        val result = isInLimit(cartType, amountCurMonth, amountThis)

        assertEquals(true, result)
    }

    @Test
    fun isInLimitTrueNoVK() {
        val cartType: String = "noVKPay"
        val amountCurMonth: Int = 100_000
        val amountThis: Int = 1_000

        val result = isInLimit(cartType, amountCurMonth, amountThis)

        assertEquals(true, result)
    }
}