package com.scientianovateam.versatile.items.serializable

import com.google.gson.JsonObject
import com.scientianovateam.versatile.common.extensions.json
import com.scientianovateam.versatile.common.extensions.toResLocV
import com.scientianovateam.versatile.common.extensions.toStack
import com.scientianovateam.versatile.common.serialization.IRegisterableJSONSerializer
import com.scientianovateam.versatile.items.properties.ExtendedItemProperties
import net.minecraft.block.BlockState
import net.minecraft.client.util.ITooltipFlag
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.text.ITextComponent
import net.minecraft.util.text.TranslationTextComponent
import net.minecraft.world.World

open class RegularItem(val extendedProperties: ExtendedItemProperties) : Item(extendedProperties), ISerializableItem {
    override fun getDestroySpeed(stack: ItemStack, state: BlockState) = extendedProperties.destroySpeed
    override fun hasContainerItem(stack: ItemStack?) = extendedProperties.containerItem != null
    override fun getContainerItem(itemStack: ItemStack?): ItemStack = extendedProperties.containerItem?.toStack()
            ?: ItemStack.EMPTY

    override fun addInformation(stack: ItemStack, world: World?, tooltips: MutableList<ITextComponent>, flat: ITooltipFlag) {
        tooltips.addAll(extendedProperties.tooltips)
    }

    override fun getTranslationKey(): String = extendedProperties.translationKey ?: super.getTranslationKey()
    override fun hasEffect(stack: ItemStack) = extendedProperties.glows || super.hasEffect(stack)
    override fun isEnchantable(p_77616_1_: ItemStack) = extendedProperties.isEnchantable
    override fun getItemEnchantability(stack: ItemStack?) = extendedProperties.enchantability
    override fun getEntityLifespan(itemStack: ItemStack?, world: World?) = extendedProperties.entityLifespan
    override fun isBookEnchantable(stack: ItemStack?, book: ItemStack?) = extendedProperties.isBookEnchantable
    override fun getBurnTime(itemStack: ItemStack?) = extendedProperties.burnTime
    private var localizationFunction: () -> ITextComponent = { TranslationTextComponent(translationKey) }
    override fun getDisplayName(stack: ItemStack) = localizationFunction()
    override fun setLocalization(function: () -> ITextComponent) {
        localizationFunction = function
    }

    override val serializer = Serializer

    object Serializer : IRegisterableJSONSerializer<RegularItem, JsonObject> {
        override val registryName = "regular".toResLocV()
        override fun read(json: JsonObject) = RegularItem(ExtendedItemProperties.Serializer.read(json))
        override fun write(obj: RegularItem) = json {
            "type" to "regular"
            ExtendedItemProperties.Serializer.write(obj.extendedProperties).extract()
        }
    }
}