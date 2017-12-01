package com.treecio.gamegig3000.state

import com.treecio.gamegig3000.Input
import com.treecio.gamegig3000.Renderable
import com.treecio.gamegig3000.Updatable

interface GameState : Renderable, Updatable {

    fun init()

    fun getNextState(input: Input): GameState

}
