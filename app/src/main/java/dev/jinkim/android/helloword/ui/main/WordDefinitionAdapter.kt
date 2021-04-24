package dev.jinkim.android.helloword.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import dev.jinkim.android.helloword.R
import dev.jinkim.android.helloword.databinding.LayoutWordDefinitionBinding
import dev.jinkim.android.helloword.model.network.WordOfTheDay

/**
 * Manage a list of definitions of a word to render in a recycler view.
 */
class WordDefinitionAdapter : RecyclerView.Adapter<WordDefinitionVH>() {

    private val definitionList = mutableListOf<WordOfTheDay.WordDefinition>()

    fun setItems(items: List<WordOfTheDay.WordDefinition>) {
        definitionList.clear()
        definitionList.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordDefinitionVH {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<LayoutWordDefinitionBinding>(
            layoutInflater,
            R.layout.layout_word_definition,
            parent,
            false
        )
        return WordDefinitionVH(binding)
    }

    override fun onBindViewHolder(holder: WordDefinitionVH, position: Int) {
        holder.bind(definitionList[position], position)
    }

    override fun getItemCount(): Int {
        return definitionList.size
    }
}