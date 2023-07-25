package com.atakan.randomduck

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        val randDuck = RetrofitMain.getInstance().create(DuckApi::class.java)
        lifecycleScope.launch{
            var result = randDuck.getRandomDuck()
            if(result != null){
                imageDuck = result.body()?.url
                withContext(Dispatchers.Main){
                    Glide.with(this@MainActivity).load(imageDuck).into(binding.duckImg)
                }
            }
        }
        binding.newBtn.setOnClickListener(){
            lifecycleScope.launch{
                var result = randDuck.getRandomDuck()
                if(result != null){
                    imageDuck = result.body()?.url
                    withContext(Dispatchers.Main){
                        Glide.with(this@MainActivity).load(imageDuck).into(binding.duckImg)
                    }
                }
            }
        }
        setContentView(binding.root)
    }
}