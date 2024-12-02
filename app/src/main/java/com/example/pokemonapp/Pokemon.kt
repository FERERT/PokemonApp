package com.example.pokemonapp

import android.widget.Toast
import com.example.pokemonapp.MainActivity.Companion.maincontext

//encapsulation using private keyword and constructors
open class Pokemon(protected var name: String = "Pokemon",
              protected var attackPower: Float? = 35f,
              protected var life: Float? = 100f) {

    fun pokemon(n: String, aP: Float) {
        this.name = n
        this.attackPower = aP
        this.life = 100f
        this.sayHi()
    }

    internal fun getName(): String {
        return this.name
    }

    internal fun getAttackPower(): Float? {
        return this.attackPower
    }

    internal fun getLife(): Float? {
        return this.life
    }

    internal fun setLife(l: Float) {
        this.life = l
    }

    fun sayHi(){Toast.makeText(maincontext, "Hola!!! Soy $name", Toast.LENGTH_LONG).show()}

    fun cure() {
        this.life = 100f
    }

    open fun evolve(n: String) {
        this.name = n
        this.attackPower = this.attackPower?.let { it * 1.5f } ?: 0f
        this.life = 100f
    }

    open fun attack() {
        Toast.makeText(maincontext, "Basic Attack!!", Toast.LENGTH_LONG).show()
    }

    //nested class that inherits from Pokemon class and has its own properties
    class FirePokemon(n: String = "Pokemon", aP: Float? = 40f, l: Float? = 100f) : Pokemon(n, aP, l){

    private var ballTemperature: Int = 90
    lateinit var ballFire : BallFire
    var numBall: Int = 0
    private var heatResistance: Int = 500
    private var fireTime: Int = 0

    fun firePokemon(n: String, Ap: Float, hR: Int) {
        this.name = n
        this.attackPower = Ap
        this.life = 100F
        this.heatResistance = hR
        this.sayHi()
    }

        fun getHeatResistance(): Int {
            this.fireTime = 0; return this.heatResistance
        }

        fun heat() {
            this.fireTime++
        }

        override fun attack(){
            super.attack()
            Toast.makeText(maincontext, "Fireball Attack!!", Toast.LENGTH_LONG).show()

            Toast.makeText(maincontext, "Ball ${++numBall}", Toast.LENGTH_LONG).show()
            ballFire = BallFire(ballTemperature)
            ballFire.throwFireBall()
        }

        override fun evolve(n: String){
            super.evolve(n)
            this.heatResistance = ((this.heatResistance * 1.5f) ?: 0f).toInt()
        }
    }

    class BallFire(var t: Int = 100){
        fun throwFireBall(){
            Toast.makeText(maincontext, "throwing ball at $t temperature", Toast.LENGTH_LONG).show()
        }
    }

    class WaterPokemon(n: String = "Pokemon", aP: Float? = 30f, l: Float? = 100f) : Pokemon(n, aP, l) {

        private var ballTemperature: Int = 90
        lateinit var ballWater : BallWater
        var numBall: Int = 0
        private var waterResistance: Int = 300
        private var waterTime: Int = 0

        fun waterPokemon(n: String, Ap: Float, wR: Int) {
            this.name = n
            this.attackPower = Ap
            this.life = 100F
            this.waterResistance = wR
            this.sayHi()
        }

        fun getWaterResistance(): Int {
            this.waterTime = 0; return this.waterResistance
        }

        fun waterRes() {
            this.waterTime++
        }

        override fun attack(){
            super.attack()
            Toast.makeText(maincontext, "Fireball Attack!!", Toast.LENGTH_LONG).show()

            Toast.makeText(maincontext, "Ball ${++numBall}", Toast.LENGTH_LONG).show()
            ballWater = BallWater(ballTemperature)
            ballWater.throwWaterBall()
        }

        override fun evolve(n: String){
            super.evolve(n)
            this.waterResistance = ((this.waterResistance * 1.5f) ?: 0f).toInt()
        }
    }

    class BallWater(var t: Int = 85){
        fun throwWaterBall(){
            Toast.makeText(maincontext, "throwing ball at $t temperature", Toast.LENGTH_LONG).show()
        }
    }
}
