package com.atakan.randomduck

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.DrawableRes
import com.atakan.randomduck.databinding.ActivityMainBinding
import com.squareup.picasso.Picasso
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import retrofit2.Response


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
                    Picasso.get().load(imageDuck).into(binding.duckImg)
                }
            }
        }
        binding.newBtn.setOnClickListener(){
            lifecycleScope.launch{
                var result = randDuck.getRandomDuck()
                if(result != null){
                    imageDuck = result.body()?.url
                    withContext(Dispatchers.Main){
                        Picasso.get().load(imageDuck).into(binding.duckImg)
                    }
                }
            }
        }
        setContentView(binding.root)
    }
}