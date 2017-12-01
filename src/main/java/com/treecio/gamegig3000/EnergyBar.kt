package com.treecio.gamegig3000

import java.awt.Color
import java.awt.Graphics2D

class EnergyBar : Renderable, Updatable {

    private var value = 30.0

    companion object {
        val HEIGHT = 8
    }

    fun consume(amount: Double) {
        value -= amount
    }

    fun add(amount: Double) {
        value += amount
    }

    override fun update(input: Input) {
        value = Math.min(value + Constants.REGENERATION / App.FPS, 100.0)
    }

    fun isAlive() = value >= 0

    override fun render(g: Graphics2D) {
        val alpha = 100
        val vx = (App.WIDTH * value / 100).toInt()

        g.color = Color(255, 255, 0, alpha)
        g.fillRect(0, App.HEIGHT - HEIGHT, vx, HEIGHT)
        g.color = Color(128, 128, 128, alpha)
        g.fillRect(vx, App.HEIGHT - HEIGHT, App.WIDTH - vx, HEIGHT)
    }

}
