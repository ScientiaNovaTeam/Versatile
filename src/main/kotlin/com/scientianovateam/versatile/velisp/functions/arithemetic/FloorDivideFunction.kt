package com.scientianovateam.versatile.velisp.functions.arithemetic

import com.scientianovateam.versatile.velisp.evaluated.NumberValue
import com.scientianovateam.versatile.velisp.functions.IFunction
import com.scientianovateam.versatile.velisp.unresolved.IUnresolved
import com.scientianovateam.versatile.velisp.unresolved.evaluate
import kotlin.math.floor

object FloorDivideFunction : IFunction {
    override val name = "versatile/floor_divide"
    override val inputCount = 2..2
    override fun evaluate(inputs: List<IUnresolved>) = NumberValue(floor(inputs.first().evaluate().value as Double / inputs.last().evaluate().value as Double))
}