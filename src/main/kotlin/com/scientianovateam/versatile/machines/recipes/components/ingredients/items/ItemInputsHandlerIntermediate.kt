package com.scientianovateam.versatile.machines.recipes.components.ingredients.items

import com.scientianovateam.versatile.common.serialization.IJSONIntermediate
import com.scientianovateam.versatile.machines.recipes.components.ingredients.recipestacks.ChancedRecipeStack
import com.scientianovateam.versatile.velisp.evaluated.IEvaluated
import net.minecraft.item.ItemStack

data class ItemInputsHandlerIntermediate(val stackIntermediates: List<IJSONIntermediate<ChancedRecipeStack<ItemStack>>>) : IJSONIntermediate<ItemInputsHandler> {
    override fun resolve(map: Map<String, IEvaluated>) = ItemInputsHandler(stackIntermediates.map { it.resolve(map) })
}