package com.atakan.randomduck

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.DrawableRes
import com.atakan.randomduck.databinding.ActivityMainBinding
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var imageDuck: String? = null
    val randDuck = RetrofitMain.getInstance().create(DuckApi::class.java)
    var isCrash = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        getTheDuck()
        binding.newBtn.setOnClickListener(){
            getTheDuck()
        }

        setContentView(binding.root)
    }

    private fun getTheDuck(){
        val duration = Toast.LENGTH_SHORT
        lifecycleScope.launch{
            try{
                var result = randDuck.getRandomDuck()
                if(result != null){
                    if(isCrash){
                        Toast.makeText(this@MainActivity, "Reconnecting.", duration).show()
                    }
                    imageDuck = result.body()?.url
                    println(imageDuck)
                    withContext(Dispatchers.Main){
                        Glide.with(this@MainActivity).load(imageDuck).into(binding.duckImg)
                        isCrash = false
                    }
                }
                else{
                    isCrash = true
                    Toast.makeText(this@MainActivity, "Failed to fetch the duck. Check your internet connection.", duration).show()
                }
            }catch(e : Exception){
                isCrash = true
                Toast.makeText(this@MainActivity, "Failed to fetch the duck. Check your internet connection.", duration).show()
            }
        }
    }
}