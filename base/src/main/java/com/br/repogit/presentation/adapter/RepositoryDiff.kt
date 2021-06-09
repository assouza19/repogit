package com.br.repogit.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.br.repogit.presentation.model.RepositoryPresentation

internal object RepositoryDiff : DiffUtil.ItemCallback<RepositoryPresentation>() {
    override fun areItemsTheSame(
        oldItem: RepositoryPresentation,
        newItem: RepositoryPresentation
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: RepositoryPresentation,
        newItem: RepositoryPresentation
    ): Boolean {
        return oldItem == newItem
    }
}