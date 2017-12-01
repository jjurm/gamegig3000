package com.treecio.gamegig3000

import com.treecio.gamegig3000.entity.Particle
import com.treecio.gamegig3000.entity.Projectile
import com.treecio.gamegig3000.entity.entities.*
import com.treecio.gamegig3000.entity.spawn.WaveMobSpawner
import com.treecio.gamegig3000.graphics.Sprite
import com.treecio.gamegig3000.state.GameState
import com.treecio.gamegig3000.state.PlayGameState
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D
import java.awt.Graphics2D
import java.util.*
import kotlin.collections.ArrayList

object Game : Renderable, Updatable {

    var time = 0L

    var player = Player()
    val mobs = ArrayList<AbstractMob>()
    val explosions = ArrayList<Explosion>()
    val bullets = ArrayList<Projectile>();
    val energyBar = EnergyBar()

    val mobSpawner: WaveMobSpawner = WaveMobSpawner(App.WIDTH, App.HEIGHT, 5000)
    val starCount = 50
    val stars = ArrayList<Particle>()

    var state: GameState = PlayGameState

    fun start() {
        initializeBackground()
        state.init()
        /*mobs.add(mobSpawner.spawn())
        mobs.add(mobSpawner.spawn())
        mobs.add(ScaleMob(Vector2D((50.0), 32.0), 4.0, 100.0, ScaleMob.defaultFrequency, ScaleMob.defaultAmplitude))
        mobs.add(TeleMob(Vector2D(100.0, 150.0), 100.0, TeleMob.defaulsRelocateProbability, TeleMob.defaultSpeed));
        */
        /*return new Mob(
                new Vector2D((int)(Math.random()*widthRange), -32),
        Math.random()*Math.PI*2,
        (Math.random()+0.5)*Mob.defaultFrequency,
        (Math.random()+0.5)*Mob.defaultAmplitude,
        4,
        (Math.random()+0.5)*Mob.defaultSpeed,
        (Math.random()+0.5)*Mob.defaultHealth);*/
    }

    override fun update(input: Input) {
        state.update(input)
    }

    override fun render(g: Graphics2D) {
        state.render(g)
    }

    fun manageState(input: Input) {
        val newState = state.getNextState(input)
        if (state != newState) {
            state = newState
            state.init()
        }
    }

    private fun initializeBackground() {
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
        mobs.add(mob)
    }

    fun addTeleMob(mob: TeleMob){
        mobs.add(mob)
    }

    fun addScaleMob(mob: ScaleMob){
        mobs.add(mob)
    }
}
