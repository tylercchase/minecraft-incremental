package com.example.rPGPlugin.data


class PlayerData {
    private lateinit var id: String
    private var joinCount: Int = 0

    constructor()
    constructor(id: String) {
        this.id = id
    }

    fun setId(id: String) {
        this.id = id
    }

    fun getId(): String {
        return id
    }
    fun setJoinCount(joinCount: Int) {
        this.joinCount = joinCount
    }
    fun getJoinCount(): Int {
        return joinCount
    }
}