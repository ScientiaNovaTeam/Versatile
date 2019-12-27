package com.scientianovateam.versatile.velisp.evaluated

import com.scientianovateam.versatile.common.extensions.toResLoc
import com.scientianovateam.versatile.velisp.registry.VELISPRegistries
import com.scientianovateam.versatile.velisp.unevaluated.IUnevaluated
import com.scientianovateam.versatile.velisp.unresolved.IUnresolved

interface IEvaluated : IUnevaluated {
    override fun evaluate() = this
    val value: Any
}

val IEvaluated.function
    get() = when(this) {
        is FunctionValue -> this
        is StringValue -> VELISPRegistries.FUNCTION_REGISTRY[this.value.toResLoc("versatile", '/')]?: error("No such function with name $value")
        else -> error("Invalid function")
    }

fun IEvaluated.callFunction(vararg inputs: IUnresolved) = function.evaluate(inputs.toList())