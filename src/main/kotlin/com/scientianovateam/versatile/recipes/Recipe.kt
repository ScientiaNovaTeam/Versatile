package com.scientianovateam.versatile.recipes

import com.scientianovateam.versatile.recipes.components.IRecipeComponent
import com.scientianovateam.versatile.recipes.components.IRecipeComponentHandler
import com.scientianovateam.versatile.recipes.lists.IRecipeLIst
import net.minecraft.util.ResourceLocation

class Recipe(val recipeList: IRecipeLIst, val name: ResourceLocation, componentHandlers: List<IRecipeComponentHandler<*>>) {
    val componentMap = componentHandlers.map { it.pairedComponentType to it }.toMap()

    val page by lazy { recipeList.createRecipeBasedComponentGroup(null, this) }

    @Suppress("UNCHECKED_CAST")
    operator fun <T> get(component: IRecipeComponent<T>) = componentMap[component.javaClass] as? IRecipeComponentHandler<T>

    @Suppress("UNCHECKED_CAST")
    operator fun <T> get(clazz: Class<out IRecipeComponent<T>>) = componentMap[clazz] as? IRecipeComponentHandler<T>
}