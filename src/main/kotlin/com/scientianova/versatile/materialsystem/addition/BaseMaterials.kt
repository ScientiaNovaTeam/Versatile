@file:JvmName("BaseMaterials")

package com.scientianova.versatile.materialsystem.addition

import com.scientianova.versatile.materialsystem.builders.dustMaterial
import com.scientianova.versatile.materialsystem.builders.gemMaterial
import com.scientianova.versatile.materialsystem.builders.ingotMaterial
import com.scientianova.versatile.materialsystem.builders.liquidMaterial
import com.scientianova.versatile.materialsystem.properties.BlockCompaction
import com.scientianova.versatile.materialsystem.properties.CompoundType
import net.minecraft.block.Blocks
import net.minecraft.fluid.Fluids
import net.minecraft.item.ArmorMaterial
import net.minecraft.item.ItemTier
import net.minecraft.item.Items

val BRICK = ingotMaterial("brick") {
    tier = 1
    color = 0xb55c42
    blockCompaction = BlockCompaction.FROM_2X2
    compoundType = CompoundType.CHEMICAL
    malleable = false
}
val NETHER_BRICK = ingotMaterial("nether_brick") {
    tier = 1
    color = 0x472a30
    blockCompaction = BlockCompaction.FROM_2X2
    compoundType = CompoundType.CHEMICAL
    malleable = false
}
val IRON = ingotMaterial("iron") {
    tier = 1
    textureSet = ROUGH
    element = BaseElements.IRON
    unrefinedColor = 0x947664
    itemTier = ItemTier.IRON
    armorMaterial = ArmorMaterial.IRON
    liquidTemperature = 1538
    hasOre = true
}
val GOLD = ingotMaterial("gold") {
    tier = 2
    color = 0xfad64a
    textureSet = SHINY
    element = BaseElements.GOLD
    itemTier = ItemTier.GOLD
    armorMaterial = ArmorMaterial.GOLD
    liquidTemperature = 1064
    hasOre = true
}
val COAL = gemMaterial("coal") {
    color = 0x1a1a1a
    textureSet = FUEL
    standardBurnTime = 1600
    element = BaseElements.CARBON
    hasOre = true
}
val CHARCOAL = gemMaterial("charcoal") {
    color = 0x443e33
    textureSet = FUEL
    standardBurnTime = 1600
    element = BaseElements.CARBON
}
val FLINT = gemMaterial("flint") {
    color = 0x222020
    textureSet = SHARP
    blockCompaction = BlockCompaction.NONE
}
val LAPIS = gemMaterial("lapis") {
    color = 0x2351be
    hasOre = true
}
val QUARTZ = gemMaterial("quartz") {
    color = 0xe8dfd0
    textureSet = CRYSTAL
    blockCompaction = BlockCompaction.FROM_2X2
    hasOre = true
}
val DIAMOND = gemMaterial("diamond") {
    tier = 2
    color = 0x34ebe3
    textureSet = PENTAGONAL
    element = BaseElements.CARBON
    itemTier = ItemTier.DIAMOND
    armorMaterial = ArmorMaterial.DIAMOND
    hasOre = true
}
val EMERALD = gemMaterial("emerald") {
    tier = 2
    color = 0x08ad2c
    textureSet = OCTAGONAL
    hasOre = true
}
val WOODEN = dustMaterial("wooden") {
    tier = -1
    color = 0x87672c
    textureSet = FINE
    standardBurnTime = 200
    itemTier = ItemTier.WOOD
}
val STONE = dustMaterial("stone") {
    color = 0xb1b0ae
    textureSet = FINE
    itemTier = ItemTier.STONE
}
val BONE = dustMaterial("bone") {
    color = 0xfcfaed
    rodOutputCount = 3
}
val BLAZE = dustMaterial("blaze") {
    color = 0xffc20c
    rodOutputCount = 3
}
val REDSTONE = dustMaterial("redstone") {
    tier = 1
    color = 0xfc1a19
    hasOre = true
}
val GLOWSTONE = dustMaterial("glowstone") {
    tier = 1
    color = 0xfcbe60
    blockCompaction = BlockCompaction.FROM_2X2
}
val OBSIDIAN = dustMaterial("obsidian") {
    tier = 1
    color = 0x3c2a53
    textureSet = FINE
}
val WATER = liquidMaterial("water") {
    color = 0x3e4ac6
    textureSet = FLUID
    liquidTemperature = 300
    gasNames = listOf("steam")
    gasTemperature = 373
    gasColor = -1
}
val LAVA = liquidMaterial("lava") {
    color = 0xc54c13
    textureSet = FLUID
}

fun addVanilla() {
    GEM_FORM[COAL]?.item = Items.COAL
    BLOCK_FORM[COAL]?.block = Blocks.COAL_BLOCK
    ORE_FORM[COAL]?.block = Blocks.COAL_ORE

    GEM_FORM[CHARCOAL]?.item = Items.CHARCOAL

    INGOT_FORM[IRON]?.item = Items.IRON_INGOT
    NUGGET_FORM[IRON]?.item = Items.IRON_NUGGET
    BLOCK_FORM[IRON]?.block = Blocks.IRON_BLOCK
    ORE_FORM[IRON]?.block = Blocks.COAL_ORE

    INGOT_FORM[GOLD]?.item = Items.GOLD_INGOT
    NUGGET_FORM[GOLD]?.item = Items.GOLD_NUGGET
    BLOCK_FORM[GOLD]?.block = Blocks.GOLD_BLOCK
    ORE_FORM[GOLD]?.block = Blocks.GOLD_ORE

    GEM_FORM[LAPIS]?.item = Items.LAPIS_LAZULI
    BLOCK_FORM[LAPIS]?.block = Blocks.LAPIS_BLOCK
    ORE_FORM[LAPIS]?.block = Blocks.LAPIS_ORE

    GEM_FORM[DIAMOND]?.item = Items.DIAMOND
    BLOCK_FORM[DIAMOND]?.block = Blocks.DIAMOND_BLOCK
    ORE_FORM[DIAMOND]?.block = Blocks.DIAMOND_ORE

    GEM_FORM[EMERALD]?.item = Items.EMERALD
    BLOCK_FORM[EMERALD]?.block = Blocks.EMERALD_BLOCK
    ORE_FORM[EMERALD]?.block = Blocks.EMERALD_ORE

    GEM_FORM[QUARTZ]?.item = Items.QUARTZ
    BLOCK_FORM[QUARTZ]?.block = Blocks.QUARTZ_BLOCK
    ORE_FORM[QUARTZ]?.block = Blocks.NETHER_QUARTZ_ORE

    DUST_FORM[REDSTONE]?.item = Items.REDSTONE
    BLOCK_FORM[REDSTONE]?.block = Blocks.REDSTONE_BLOCK
    ORE_FORM[REDSTONE]?.block = Blocks.REDSTONE_ORE

    DUST_FORM[GLOWSTONE]?.item = Items.GLOWSTONE_DUST
    BLOCK_FORM[GLOWSTONE]?.block = Blocks.GLOWSTONE

    INGOT_FORM[BRICK]?.item = Items.BRICK
    BLOCK_FORM[BRICK]?.block = Blocks.BRICKS

    INGOT_FORM[NETHER_BRICK]?.item = Items.NETHER_BRICK
    BLOCK_FORM[NETHER_BRICK]?.block = Blocks.NETHER_BRICKS

    GEM_FORM[FLINT]?.item = Items.FLINT

    DUST_FORM[BONE]?.item = Items.BONE_MEAL
    BLOCK_FORM[BONE]?.block = Blocks.BONE_BLOCK

    DUST_FORM[BLAZE]?.item = Items.BLAZE_POWDER

    LIQUID_FORM[WATER]?.item = Items.WATER_BUCKET
    LIQUID_FORM[WATER]?.block = Blocks.WATER
    LIQUID_FORM[WATER]?.stillFluid = Fluids.WATER
    LIQUID_FORM[WATER]?.flowingFluid = Fluids.FLOWING_WATER

    LIQUID_FORM[LAVA]?.item = Items.LAVA_BUCKET
    LIQUID_FORM[LAVA]?.block = Blocks.LAVA
    LIQUID_FORM[LAVA]?.stillFluid =  Fluids.LAVA
    LIQUID_FORM[LAVA]?.flowingFluid = Fluids.FLOWING_LAVA
}