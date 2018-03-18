package io.graf.posgraduninovafapi

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase
import io.graf.posgraduninovafapi.utils.DbKeys
import java.util.*


class SplashActivity : AppCompatActivity() {
//    private lateinit var database: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed(Runnable {
//            database = FirebaseDatabase.getInstance()
//            // Pegar referência do firebase (disciplinas)
//            val myRef = database.getReference(DbKeys.DISCIPLINAS.toString())
//            // Converte a lista do string.xml e transforma em lista
//            val disciplinas = Arrays.asList(*resources.getStringArray(R.array.disciplinas))
//            // Manda pro Firebase
//            myRef.setValue(disciplinas)
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }, 3000L)
    }
}
