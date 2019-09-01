package com.EmosewaPixel.pixellib.materialsystem.lists

import com.EmosewaPixel.pixellib.materialsystem.materials.IMaterialItem
import com.EmosewaPixel.pixellib.materialsystem.materials.Material
import com.EmosewaPixel.pixellib.materialsystem.types.ObjectType
import com.google.common.collect.HashBasedTable
import com.google.common.collect.Table
import net.minecraft.block.Block

//This class contains functions for interacting with the global list of material blocks
object MaterialBlocks {
    private val materialBlocks = HashBasedTable.create<Material, ObjectType, Block>()

    @JvmStatic
    fun getAll(): MutableCollection<Block> = materialBlocks.values()

    @JvmStatic
    fun getBlock(material: Material, type: ObjectType): Block? = materialBlocks.get(material, type)

    @JvmStatic
    fun contains(material: Material, type: ObjectType) = getBlock(material, type) != null

    @JvmStatic
    fun addBlock(mat: Material, type: ObjectType, item: Block) {
        materialBlocks.put(mat, type, item)
    }

    @JvmStatic
    fun addBlock(item: IMaterialItem) {
        if (item is Block)
            addBlock(item.mat, item.objType, item as Block)
    }

    @JvmStatic
    fun getBlockCell(block: Block): Table.Cell<Material, ObjectType, Block>? = materialBlocks.cellSet().first { it.value === block }

    @JvmStatic
    fun getBlockMaterial(block: Block): Material? = getBlockCell(block)?.rowKey

    @JvmStatic
    fun getBlockObjType(block: Block): ObjectType? = getBlockCell(block)?.columnKey
}