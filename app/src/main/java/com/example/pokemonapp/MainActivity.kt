package com.example.pokemonapp

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    companion object{
        lateinit var maincontext: Context
    }

    private lateinit var pok: Pokemon
    private lateinit var firePok: Pokemon.FirePokemon
    private lateinit var waterpok: Pokemon.WaterPokemon

    private fun loadDataPokemon(tv: TextView, p: Pokemon){
        var description: String = ""

        description += p.getName() + " ("
        description += "AP: " + (p.getAttackPower()?.toInt() ?: 0)
        description += " - L: " + (p.getLife()?.toInt() ?: 0) + ")"

        if (p is Pokemon.FirePokemon)
        description += "\nHR: " + p.getHeatResistance()

        if (p is Pokemon.WaterPokemon)
            description += "\nSR: " + p.getWaterResistance()

        tv.text = description
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        maincontext = this

        //region Pokemon playing code
//        var monster = Pokemon()
//        monster.pokemon("Pikachu", 45f)
//        monster.pokemon(("Charmander"), 50f)
//        monster.pokemon("Bulbasaur", 40f)
//        monster.pokemon("Squirtle", 55f)
//
//        var monster2: Pokemon = Pokemon()
//        monster2.pokemonEvolution("Raichu", 65f)
//        monster2.pokemonEvolution("Charmeleon", 70f)
//        monster2.pokemonEvolution("Ivysaur", 60f)
//        monster2.pokemonEvolution("Wartortle", 75f)
//
//        var monster3: Pokemon = Pokemon()
//        println("Base Pokemon Stats: \nBase name: ${monster3.getName()}" +
//                " \nBase Attack Power: ${monster3.getAttackPower()}" +
//                " \nBase Life: ${monster3.getLife()}")
        // endregion


        var btFight = findViewById<Button>(R.id.btFight)
        btFight.setOnClickListener{
            fight(firePok, waterpok)
        }

    }

    fun createNewPokemon(v: View){
        var etName = findViewById<EditText>(R.id.etName)
        var etAttackPower = findViewById<EditText>(R.id.etAttackPower)

        pok = Pokemon()

        if (!etName.text.isNullOrEmpty() && !etAttackPower.text.isNullOrEmpty())
            pok.pokemon(etName.text.toString(), etAttackPower.text.toString().toFloat())

        var ivPokemon = findViewById<ImageView>(R.id.ivPokemon)
        ivPokemon.setImageResource(R.mipmap.nopokemon)

        var tvPokemon = findViewById<TextView>(R.id.tvPokemon)
        loadDataPokemon(tvPokemon, pok)
    }

    fun createFirePokemon(v: View){
        var etFirePokemon = findViewById<EditText>(R.id.etFirePokemon)
        var etFireAttack = findViewById<EditText>(R.id.etFireAttack)
        var etFireHeatResistance = findViewById<EditText>(R.id.etFireHeatResistance)

        firePok = Pokemon.FirePokemon()

        if (!etFirePokemon.text.isNullOrEmpty() && !etFireAttack.text.isNullOrEmpty() && !etFireHeatResistance.text.isNullOrEmpty())
            firePok.firePokemon(etFirePokemon.text.toString(),
                etFireAttack.text.toString().toFloat(),
                etFireHeatResistance.text.toString().toInt())

        var ivFirePokemon = findViewById<ImageView>(R.id.ivFirePokemon)
        ivFirePokemon.setImageResource(R.mipmap.fire)

        var tvFirePokemon = findViewById<TextView>(R.id.tvFirePokemon)
        loadDataPokemon(tvFirePokemon, firePok)

    }

    fun createWaterPokemon(v: View){
        var etWaterPokemon = findViewById<EditText>(R.id.etWaterPokemon)
        var etWaterAttack = findViewById<EditText>(R.id.etWaterAttack)
        var etWaterSplashResistance = findViewById<EditText>(R.id.etWaterSplashResistance)

        waterpok = Pokemon.WaterPokemon()

        if (!etWaterPokemon.text.isNullOrEmpty() && !etWaterAttack.text.isNullOrEmpty() && !etWaterSplashResistance.text.isNullOrEmpty())
            waterpok.waterPokemon(etWaterPokemon.text.toString(),
                etWaterAttack.text.toString().toFloat(),
                etWaterSplashResistance.text.toString().toInt())

        var ivWaterPokemon = findViewById<ImageView>(R.id.ivWaterPokemon)
        ivWaterPokemon.setImageResource(R.mipmap.water)

        var tvWaterPokemon = findViewById<TextView>(R.id.tvWaterPokemon)
        loadDataPokemon(tvWaterPokemon, waterpok)

    }

    fun evolveFirePokemon(v: View){
        var etEvolveFirePokemon = findViewById<EditText>(R.id.etEvolveFirePokemon)
            firePok.evolve(etEvolveFirePokemon.text.toString())

            var ivPokemon = findViewById<ImageView>(R.id.ivFirePokemon)
            ivPokemon.setImageResource(R.mipmap.fire_evolved)

            var tvPokemon = findViewById<TextView>(R.id.tvFirePokemon)
            loadDataPokemon(tvPokemon, firePok)
        }

    fun cureFirePokemon(v:View){
        waterpok.cure()
        var tvFirePokemon = findViewById<TextView>(R.id.tvFirePokemon)
        loadDataPokemon(tvFirePokemon, firePok)

    } fun evolveWaterPokemon(v: View){
        var etEvolveWaterPokemon = findViewById<EditText>(R.id.etEvolveWaterPokemon)
            waterpok.evolve(etEvolveWaterPokemon.text.toString())

            var ivPokemon = findViewById<ImageView>(R.id.ivWaterPokemon)
            ivPokemon.setImageResource(R.mipmap.water_evolved)

            var tvPokemon = findViewById<TextView>(R.id.tvWaterPokemon)
            loadDataPokemon(tvPokemon, waterpok)
        }

    fun cureWaterPokemon(v:View){
        firePok.cure()
        var tvFirePokemon = findViewById<TextView>(R.id.tvWaterPokemon)
        loadDataPokemon(tvFirePokemon, waterpok)

    }

    private fun fight(p1: Pokemon, p2: Pokemon) {

        var battleLogs = findViewById<EditText>(R.id.battleLogs)
        battleLogs.setText("")
        var text = ""

        text += "\n${p1.getName()} (${p1.getLife()?.toInt()}) Vs ${p2.getName()} (${p2.getLife()
            ?.toInt()})"

        while (p1.getLife()!! > 0 && p2.getLife()!! > 0){
            text += "\n${p1.getName()} attack!"
            p1.attack();
            p2.setLife(p2.getLife()!! - p1.getAttackPower()!!)
            text += "\n${p1.getName()} (${p1.getLife()!!.toInt()}) Vs ${p2.getName()} (${p2.getLife()
                ?.toInt()})"
            if (p2.getLife()!! > 0){
                text += "\n${p2.getName()} attack!"
                p2.attack()
                p1.setLife(p1.getLife()?.minus(p2.getAttackPower()!!) ?: 0f)
                text += "\n${p1.getName()} (${p1.getLife()?.toInt()}) Vs ${p2.getName()} (${p2.getLife()
                    ?.toInt()})"
            }
        }

        if (p1.getLife()!! > 0) text += "\n\nCHAMPION IS ${p1.getName()}"
        else text += "\n\nCHAMPION IS ${p2.getName()}"

        battleLogs.setText(text)


        var tvFirePokemon = findViewById<TextView>(R.id.tvFirePokemon)
        loadDataPokemon(tvFirePokemon, firePok)

        var tvWaterPokemon = findViewById<TextView>(R.id.tvWaterPokemon)
        loadDataPokemon(tvWaterPokemon, waterpok)
    }
}

