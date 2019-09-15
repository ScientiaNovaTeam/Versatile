package com.emosewapixel.pixellib.recipes

import net.minecraft.util.ResourceLocation

/*
Recipe Lists are objects used for storing recipes with a maximum amount of inputs and outputs.
Recipe Lists are intertwined with Recipe Builders and the Machine Recipes themselves
*/
abstract class AbstractRecipeList<T : SimpleMachineRecipe, B : AbstractRecipeBuilder<T, B>>(val name: ResourceLocation, val maxInputs: Int, val maxFluidInputs: Int, val maxOutputs: Int, val maxFluidOutputs: Int) {
    val recipes = mutableListOf<T>()

    val maxRecipeSlots: Int
        get() = maxInputs + maxOutputs

    val maxRecipeTanks: Int
        get() = maxFluidInputs + maxFluidOutputs

    fun add(recipe: T) = recipes.add(recipe)

    abstract fun recipeBuilder(): B
    abstract fun build(dsl: B.() -> Unit)
}