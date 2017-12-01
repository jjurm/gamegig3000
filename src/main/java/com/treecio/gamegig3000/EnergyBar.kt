package com.treecio.gamegig3000

import java.awt.Color
import java.awt.Graphics2D

class EnergyBar : Renderable, Updatable {

    private var value: Double = 0.0

    companion object {
        val HEIGHT = 8
    }

    fun reset() {
            value = 30.0
    }

    fun canConsume(amount: Double) = value > amount

    fun consume(amount: Double) {
        value -= amount
    }

    fun add(amount: Double) {
        value += amount
    }

    override fun update(input: Input) {
        // this method is called only after all other updates are done
        var addition = Constants.REGENERATION / App.FPS
        if (input.down || input.left || input.up || input.right) {
            addition /= 2
        }
        value += addition
        value = Utils.toRange(value, 0.0, 100.0)
    }

    fun isPositive() = value > 0

    override fun render(g: Graphics2D) {
        val alpha = 100
        val vx = (App.WIDTH * value / 100).toInt()

        g.color = Color(255, 255, 0, alpha)
        g.fillRect(0, App.HEIGHT - HEIGHT, vx, HEIGHT)
        g.color = Color(128, 128, 128, alpha)
        g.fillRect(vx, App.HEIGHT - HEIGHT, App.WIDTH - vx, HEIGHT)
    }

}
