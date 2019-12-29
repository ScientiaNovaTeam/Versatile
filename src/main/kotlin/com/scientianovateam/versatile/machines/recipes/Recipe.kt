package com.scientianovateam.versatile.machines.recipes

import com.scientianovateam.versatile.machines.recipes.components.IRecipeComponent
import com.scientianovateam.versatile.machines.recipes.components.IRecipeComponentHandler

class Recipe(val recipeList: RecipeList, val name: String, componentHandlers: List<IRecipeComponentHandler<*>>) {
    val componentMap = componentHandlers.map { it.pairedComponentType to it }.toMap()

    init {
        recipeList.addRecipe(this)
    }

    val page by lazy { recipeList.createRecipeBasedComponentGroup(null, this) }

    @Suppress("UNCHECKED_CAST")
    operator fun <T> get(component: IRecipeComponent<T>) = componentMap[component.javaClass] as? IRecipeComponentHandler<T>

    @Suppress("UNCHECKED_CAST")
    operator fun <T> get(clazz: Class<out IRecipeComponent<T>>) = componentMap[clazz] as? IRecipeComponentHandler<T>
}