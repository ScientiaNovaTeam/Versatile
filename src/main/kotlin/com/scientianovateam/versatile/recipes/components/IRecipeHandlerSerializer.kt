package com.scientianovateam.versatile.recipes.components

import com.google.gson.JsonElement
import com.scientianovateam.versatile.common.serialization.IRegisterableSerializer
import com.scientianovateam.versatile.recipes.RECIPE_COMPONENT_HANDLER_SERIALIZERS
import net.minecraft.util.ResourceLocation

abstract class IRecipeHandlerSerializer<T : IRecipeComponentHandler<*>>(final override val registryName: ResourceLocation) : IRegisterableSerializer<T, JsonElement> {
    init {
        RECIPE_COMPONENT_HANDLER_SERIALIZERS[registryName] = this
    }
}