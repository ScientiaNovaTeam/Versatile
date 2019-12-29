package com.scientianovateam.versatile.machines.recipes.syncing

import com.google.gson.JsonObject
import com.scientianovateam.versatile.common.extensions.getObjectOrNull
import com.scientianovateam.versatile.common.extensions.getStringOrNull
import com.scientianovateam.versatile.common.extensions.toResLocV
import com.scientianovateam.versatile.common.serialization.RECIPE_COMPONENT_HANDLER_SERIALIZERS
import com.scientianovateam.versatile.machines.recipes.RecipeLists
import com.scientianovateam.versatile.machines.recipes.RecipeMaterialTemplate
import com.scientianovateam.versatile.machines.recipes.RecipeSerializer
import com.scientianovateam.versatile.velisp.convertToExpression
import net.minecraft.item.crafting.IRecipeSerializer
import net.minecraft.network.PacketBuffer
import net.minecraft.util.ResourceLocation
import net.minecraftforge.registries.ForgeRegistryEntry

object MaterialBasedRecipeSerializer : ForgeRegistryEntry<IRecipeSerializer<*>>(), IRecipeSerializer<MaterialBasedRecipe> {
    init {
        registryName = "material_based".toResLocV()
    }

    override fun read(recipeId: ResourceLocation, json: JsonObject): MaterialBasedRecipe {
        val list = (json.getStringOrNull("list") ?: error("Didn't specify recipe list for recipe $recipeId")).let {
            RecipeLists[it] ?: error("invalid recipe list $it for recipe $recipeId")
        }
        val template = json.getObjectOrNull("template")?.entrySet()?.mapNotNull { RECIPE_COMPONENT_HANDLER_SERIALIZERS[it.key.toResLocV()]?.read(it.value) }
                ?: error("Missing recipe template for $recipeId")
        val materialPredicate = json.get("predicate")?.let(::convertToExpression)

        return MaterialBasedRecipe(materialPredicate, RecipeMaterialTemplate(list, recipeId.toString(), template), emptyList())
    }

    override fun read(recipeId: ResourceLocation, buffer: PacketBuffer) = MaterialBasedRecipe(null, null, List(buffer.readVarInt()) {
        RecipeSerializer.read(buffer)
    })

    override fun write(buffer: PacketBuffer, recipe: MaterialBasedRecipe) {
        buffer.writeVarInt(recipe.generated.size)
        recipe.generated.forEach { RecipeSerializer.write(buffer, it) }
    }
}