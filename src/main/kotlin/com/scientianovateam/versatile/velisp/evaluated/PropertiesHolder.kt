package com.scientianovateam.versatile.velisp.evaluated

import com.scientianovateam.versatile.materialsystem.properties.IPropertyContainer
import com.scientianovateam.versatile.velisp.types.NOTHING_TYPE
import com.scientianovateam.versatile.velisp.types.NothingType

data class PropertiesHolder(override val properties: Map<String, IEvaluated>) : IEvaluated, IPropertyContainer {
    override val value = properties
    override val type = NOTHING_TYPE
    override fun toJson() = error("Properties Holders shouldn't be used in expressions")
}