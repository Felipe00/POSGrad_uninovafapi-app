package io.graf.posgraduninovafapi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView


/**
 * Created by javab0y on 17/03/18.
 */
class AdapterDisciplinas(list: List<String>) : BaseAdapter() {

    private var listDisciplinas: List<String> = list

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val nomeDisciplina: String = listDisciplinas.get(position)
        val view: View = LayoutInflater.from(parent?.context).inflate(R.layout.disciplina_item_layout, parent, false)
        val holder = ViewHolder(view)
        holder.nome.setText(nomeDisciplina)
        return view
    }

    override fun getItem(position: Int): Any {
        return listDisciplinas.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return listDisciplinas.size
    }

    inner class ViewHolder(view: View) {

        internal val nome: TextView

        // Iniciando o construtor
        init {
            nome = view.findViewById(R.id.disciplina_nome) as TextView
        }
    }

}