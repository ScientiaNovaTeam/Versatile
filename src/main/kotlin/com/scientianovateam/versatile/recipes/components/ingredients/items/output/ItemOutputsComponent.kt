package com.scientianovateam.versatile.recipes.components.ingredients.items.output

import com.google.gson.JsonObject
import com.scientianovateam.versatile.common.extensions.getPrimitiveOrNull
import com.scientianovateam.versatile.common.extensions.json
import com.scientianovateam.versatile.common.extensions.toResLocV
import com.scientianovateam.versatile.common.serialization.IRegisterableJSONSerializer
import com.scientianovateam.versatile.machines.BaseTileEntity
import com.scientianovateam.versatile.machines.gui.layout.IGUIComponent
import com.scientianovateam.versatile.machines.gui.layout.components.slots.ItemSlotComponent
import com.scientianovateam.versatile.machines.gui.layout.components.stacksuppliers.RecipeOutputItemStackSupplierSlot
import com.scientianovateam.versatile.machines.properties.ITEBoundProperty
import com.scientianovateam.versatile.machines.properties.implementations.items.TEItemInventoryProperty
import com.scientianovateam.versatile.machines.properties.implementations.items.TEItemOutputProperty
import com.scientianovateam.versatile.machines.properties.implementations.processing.handlers.ItemOutputsProcessingHandler
import com.scientianovateam.versatile.recipes.Recipe
import com.scientianovateam.versatile.recipes.components.IRecipeComponent
import com.scientianovateam.versatile.recipes.components.grouping.RecipeComponentFamilies
import com.scientianovateam.versatile.recipes.components.ingredients.recipestacks.ChancedRecipeStack
import net.minecraft.item.ItemStack
import kotlin.math.min

data class ItemOutputsComponent(val min: Int, val max: Int) : IRecipeComponent<List<ChancedRecipeStack<ItemStack>>> {
    override val name = "itemOutputs"
    override val family = RecipeComponentFamilies.OUTPUT_SLOTS
    override val serializer = Serializer

    override fun isRecipeValid(recipe: Recipe): Boolean {
        val handler = recipe[this] ?: return min <= 0
        return handler.value.size in min..max
    }

    override fun addDefaultProperty(te: BaseTileEntity, properties: MutableList<ITEBoundProperty>) {
        properties += TEItemOutputProperty(max, "itemOutputs", te)
    }

    override fun addGUIComponents(machine: BaseTileEntity?): List<IGUIComponent> =
            machine?.let {
                val property = it.teProperties["itemOutputs"] as? TEItemInventoryProperty ?: return emptyList()
                (0 until property.value.slots).map { index -> ItemSlotComponent(property, index) }
            } ?: (0 until max).map(::RecipeOutputItemStackSupplierSlot)

    override fun addRecipeGUIComponents(machine: BaseTileEntity?, recipe: Recipe): List<IGUIComponent> {
        val handler = recipe[this]?.value ?: return emptyList()
        return machine?.let {
            val property = it.teProperties["itemOutputs"] as? TEItemInventoryProperty ?: return emptyList()
            (0 until min(property.value.slots, handler.size)).map { index -> ItemSlotComponent(property, index) }
        } ?: (handler.indices).map(::RecipeOutputItemStackSupplierSlot)
    }

    override fun getProcessingHandler(machine: BaseTileEntity) = (machine.teProperties["itemOutputs"] as? TEItemOutputProperty)?.let {
        ItemOutputsProcessingHandler(it)
    }

    object Serializer : IRegisterableJSONSerializer<ItemOutputsComponent, JsonObject> {
        override val registryName = "item_outputs".toResLocV()

        override fun read(json: JsonObject) = ItemOutputsComponent(
                json.getPrimitiveOrNull("min")?.asInt ?: 0,
                json.getPrimitiveOrNull("max")?.asInt ?: error("Missing maximum amount of item outputs")
        )

        override fun write(obj: ItemOutputsComponent) = json {
            "min" to obj.min
            "max" to obj.max
        }
    }
}