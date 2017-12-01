package com.treecio.gamegig3000.state

import com.treecio.gamegig3000.Input
import java.awt.Graphics2D

object GameOverState : ScreenOverlayState() {

    override fun init() {}

    override fun getNextState(input: Input) = if (input.r) PlayGameState else GameOverState

    override fun update(input: Input) {
        // do nothing
    }

    override fun render(g: Graphics2D) {
        super.render(g)

        // show "Game over"
    }
}
