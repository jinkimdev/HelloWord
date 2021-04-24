package dev.jinkim.android.helloword.ui.main

import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import dev.jinkim.android.helloword.databinding.LayoutWordDefinitionBinding
import dev.jinkim.android.helloword.model.network.WordOfTheDay

/**
 * Recycler view view holder for a row UI of a word definition.
 * Ex) 1. noun
 *        In scholastic theology...
 */
class WordDefinitionVH(private val binding: LayoutWordDefinitionBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(item: WordOfTheDay.WordDefinition, position: Int) {
        binding.setVariable(BR.item, item)
        // Pass in the index of the definition (1-based)
        binding.setVariable(BR.displayIndex, "${position + 1}.")
        binding.executePendingBindings()
    }
}
