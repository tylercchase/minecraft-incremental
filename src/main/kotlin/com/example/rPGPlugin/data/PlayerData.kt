package com.example.rPGPlugin.data

import org.bson.types.ObjectId

class PlayerData {
    private lateinit var id: ObjectId
    private lateinit var name: String

    constructor() {
    }
    constructor(id: ObjectId, name: String) {
        this.id = id
        this.name = name
    }

    public fun setId(id: ObjectId) {
        this.id = id
    }
    public fun setName(name: String) {
        this.name = name
    }
    public fun getId(): ObjectId {
        return id
    }
    public fun getName(): String {
        return name
    }
}