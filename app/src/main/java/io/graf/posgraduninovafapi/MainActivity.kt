package io.graf.posgraduninovafapi

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.transition.TransitionManager
import android.view.View
import android.widget.Toast
import com.google.firebase.database.*
import io.graf.posgraduninovafapi.utils.DbKeys
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.carga_horaria_include.*
import kotlinx.android.synthetic.main.grade_curricular_include.*


class MainActivity : AppCompatActivity() {

    private lateinit var database: FirebaseDatabase

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            TransitionManager.beginDelayedTransition(container)
        }
        when (item.itemId) {
            R.id.navigation_home -> {
                view_flipper.displayedChild = 0
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                view_flipper.displayedChild = 1
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                view_flipper.displayedChild = 2
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        carregando_lista_label.visibility = View.VISIBLE
        disciplinas_listview.visibility = View.GONE

        // Iniciando o Firebase
        database = FirebaseDatabase.getInstance()
        // Pega a referência do firebase (disciplinas)
        val myRef = database.getReference(DbKeys.DISCIPLINAS.toString())
        // Listando os dados do banco de dados
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Tipo da resposta (Mapeamento para o firebase saber que ele tem que transformar a resposta numa lista de String)
                val typeOfResponse = object : GenericTypeIndicator<ArrayList<String>>() {}
                // Se dataSnapshot igual a nulo, seta lista vazia em value. Se estiver povoado, seta a lista de disciplinas
                val value = dataSnapshot.getValue(typeOfResponse) ?: ArrayList<String>()
                // Seta "value" no AdapterDisciplinas e inicializa AdapterDisciplinas na listview
                disciplinas_listview.adapter = AdapterDisciplinas(value)
                // Transição pra deixar mais legal ;)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    TransitionManager.beginDelayedTransition(container)
                }
                carregando_lista_label.visibility = View.GONE
                disciplinas_listview.visibility = View.VISIBLE
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Toast.makeText(this@MainActivity, "Erro ao baixar informações: " + error.message, Toast.LENGTH_LONG).show()
                carregando_lista_label.visibility = View.VISIBLE
                disciplinas_listview.visibility = View.GONE
                carregando_lista_label.setText("Não foi possível carregar a lista")
            }
        })

        btn_inscricao.setOnClickListener({
            val URL = "https://uninova.geradordematriculas.com.br/signup"
            val i = Intent()
            i.action = Intent.ACTION_VIEW
            i.addCategory(Intent.CATEGORY_BROWSABLE)
            i.data = Uri.parse(URL)
            startActivity(i)
        })

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

}
