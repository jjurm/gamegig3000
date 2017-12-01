package com.treecio.gamegig3000.state

import com.treecio.gamegig3000.App
import com.treecio.gamegig3000.Game
import com.treecio.gamegig3000.Input
import com.treecio.gamegig3000.deepCopy
import com.treecio.gamegig3000.entity.entities.Explosion
import com.treecio.gamegig3000.entity.entities.Player
import java.awt.Color
import java.awt.Graphics2D
import java.awt.image.BufferedImage

object PlayGameState : GameState {

    lateinit var lastFrame: BufferedImage

    override fun init() = Game.run {
        time = 0L
        player = Player()
        mobs.clear()
        bullets.clear()
        energyBar.reset()

        mobs.add(mobSpawner.spawn())
        //mobs.add(ScaleMob(Vector2D((50.0), 32.0), 4.0, 100.0, ScaleMob.defaultFrequency, ScaleMob.defaultAmplitude))
        //mobs.add(TeleMob(Vector2D(100.0, 150.0), 100.0, TeleMob.defaulsRelocateProbability, TeleMob.defaultSpeed));

        Unit
    }

    override fun update(input: Input) = Game.run {
        // update each entity
        explosions.forEach { it.update(input) }
        stars.forEach { it.update(input) }
        player.update(input)
        bullets.forEach { it.update(input) }
        mobs.forEach { it.update(input) }

        // detect collisions
        var playerCollides = false
        for (mob in mobs) {
            if (player.collidesWith(mob)) {
                mob.remove()
                energyBar.consume(mob.damage)
                playerCollides = true
            }
            if (mob.isAlive) {
                for (bullet in bullets) {
                    if (mob.collidesWith(bullet)) {
                        mob.remove()
                        bullet.remove()
                        energyBar.add(4.0)
                        explosions.add(Explosion(mob.pos, 0.0, Explosion.sprites, 4.0))
                        break
                    }
                }
            }
        }
        if (playerCollides) {
            explosions.add(Explosion(player.pos, 0.0, Explosion.sprites_short, 4.0))
        }

        // remove dead entities
        mobs.removeIf({ !it.isAlive })
        bullets.removeIf({ !it.isAlive })
        explosions.removeIf({ !it.isAlive })

        mobSpawner.update();

        energyBar.update(input)

        time += 1
    }

    override fun getNextState(input: Input): GameState = Game.run {
        if (energyBar.isPositive()) {
            return PlayGameState
        } else {
            lastFrame = App.app.closedBuffer.deepCopy()
            return GameOverState
        }
    }

    override fun render(g: Graphics2D) = Game.run {
        g.background = Color.black;
        g.clearRect(0, 0, App.WIDTH, App.HEIGHT)

        stars.forEach { it.render(g) }
        player.render(g)
        bullets.forEach { it.render(g) }
        mobs.forEach {
            it.render(g)
        }
        energyBar.render(g)

        explosions.forEach { it.render(g) }

        g.color = Color.white
        g.drawString(time.toString(), 15, 15)
    }
}
