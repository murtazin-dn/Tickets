package com.example.common.mapper

interface Mapper<Entity, Model> {
    fun transform(entity: Entity): Model
}