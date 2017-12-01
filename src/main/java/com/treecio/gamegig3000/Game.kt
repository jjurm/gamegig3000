package com.treecio.gamegig3000

import com.treecio.gamegig3000.entity.Entity
import com.treecio.gamegig3000.entity.Particle
import com.treecio.gamegig3000.entity.Projectile
import com.treecio.gamegig3000.entity.entities.*
import com.treecio.gamegig3000.entity.spawn.MobSpawner
import com.treecio.gamegig3000.entity.spawn.Spawner
import com.treecio.gamegig3000.entity.spawn.WaveMobSpawner
import com.treecio.gamegig3000.graphics.Sprite
import javafx.scene.transform.Scale
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D
import java.awt.Color
import java.awt.Graphics2D
import java.util.*
import kotlin.collections.ArrayList

object Game {

    private var time = 0L
    private val starCount = 50;

    val player = Player()
    val mobs = ArrayList<AbstractMob>()
    val bullets = ArrayList<Projectile>();
    val energyBar = EnergyBar()

    private val mobSpawner: WaveMobSpawner = WaveMobSpawner(App.WIDTH, App.HEIGHT, 5000)

    val stars = ArrayList<Particle>()

    fun start() {
        mobs.add(mobSpawner.spawn())
        mobs.add(ScaleMob(Vector2D((50.0), 32.0), 4.0, 100.0, ScaleMob.defaultFrequency, ScaleMob.defaultAmplitude))
        mobs.add(TeleMob(Vector2D(100.0, 150.0), 100.0, TeleMob.defaulsRelocateProbability, TeleMob.defaultSpeed));

        /*return new Mob(
                new Vector2D((int)(Math.random()*widthRange), -32),
        Math.random()*Math.PI*2,
        (Math.random()+0.5)*Mob.defaultFrequency,
        (Math.random()+0.5)*Mob.defaultAmplitude,
        4,
        (Math.random()+0.5)*Mob.defaultSpeed,
        (Math.random()+0.5)*Mob.defaultHealth);*/
    }

    fun update(input: Input) {
        // update each entity
        stars.forEach { it.update(input) }
        player.update(input)
        bullets.forEach { it.update(input) }
        mobs.forEach { it.update(input) }

        // detect collisions
        for (mob in mobs) {
            if (player.collidesWith(mob)) {
                mob.kill()
                energyBar.consume(mob.damage)
            }
            if (mob.isAlive) {
                for (bullet in bullets) {
                    if (mob.collidesWith(bullet)) {
                        mob.kill()
                        bullet.kill()
                        energyBar.add(Constants.BONUS_KILL)
                        break
                    }
                }
            }
        }

        // remove dead entities
        mobs.removeIf({ !it.isAlive })
        bullets.removeIf({ !it.isAlive})

        // check if not game over

        val iterate = mobs.listIterator()
        while (iterate.hasNext()) {
            val m = iterate.next()
            if (m.isRemoved) iterate.remove()
            else m.update(input)
        }

        mobSpawner.update();

        energyBar.update(input)

        time++
    }


    fun render(g: Graphics2D) {
        g.background = Color.black;
        g.clearRect(0, 0, App.WIDTH, App.HEIGHT)

        g.color = Color.white
        g.drawString(time.toString(), 20, 20)

        stars.forEach { it.render(g) }
        player.render(g)
        bullets.forEach { it.render(g) }
        mobs.forEach {
            it.render(g)
        }
        energyBar.render(g)
    }

    fun initializeBackground() {
        val r = Random()
        val starSpeed = 6;

        for (i in 0..starCount) {
            val speed = r.nextDouble()
            stars.add(Particle(
                    Vector2D(r.nextDouble() * App.WIDTH, r.nextDouble() * App.HEIGHT),
                    Vector2D(0.0, starSpeed + starSpeed * speed)
                    , 1 / (4 - 2 * speed)))
        }
    }

    fun addBullet(pos: Vector2D, angle: Double, sprites: List<Sprite>, scale: Double, speed: Double) {
        bullets.add(Projectile(pos, angle, sprites, scale, speed));
    }

    fun addMob(mob: Mob){
        mobs.add(mob);
    }

}
