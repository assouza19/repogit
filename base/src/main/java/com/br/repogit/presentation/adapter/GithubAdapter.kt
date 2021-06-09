package com.br.repogit.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.br.repogit.R
import com.br.repogit.databinding.RecyclerRepositoryItemBinding
import com.br.repogit.presentation.VISIBLE_IMAGES_THRESHOLD
import com.br.repogit.presentation.model.RepositoryPresentation
import com.br.repogit.utils.Extensions
import com.squareup.picasso.Picasso

private typealias RepositoryAdapterCallbackAlias = (RepositoryAdapterCallback) -> Unit

internal class GithubAdapter(
    private val callback: RepositoryAdapterCallbackAlias
) : ListAdapter<RepositoryPresentation, RecyclerView.ViewHolder>(RepositoryDiff) {

    private var repositories: List<RepositoryPresentation> = emptyList()
    private lateinit var viewBinding: RecyclerRepositoryItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        viewBinding = RecyclerRepositoryItemBinding.inflate(inflater)

        return ItemsViewHolder(
            RecyclerRepositoryItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            parent.context
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        checkPagination(position)

        when (holder) {
            is ItemsViewHolder -> {
                holder.update(repositories[position])
            }
        }
    }

    fun update(repos: List<RepositoryPresentation>) {
        repositories = emptyList()
        repositories = repos
        submitList(repositories)
    }

    inner class ItemsViewHolder(
        private val binding: RecyclerRepositoryItemBinding,
        private val context: Context
    ) : RecyclerView.ViewHolder(
        binding.root
    ) {
        fun update(repositoryItem: RepositoryPresentation) {
            binding.textViewTitle.text = repositoryItem.owner.name
            binding.textViewDescription.text = repositoryItem.description
            binding.textViewAuthor.text = repositoryItem.fullName

            val iconPrivacy = if (repositoryItem.private) {
                R.drawable.ic_private_icon
            } else {
                R.drawable.ic_public_icon
            }

            binding.positionDescription.text = Extensions.ordinalOf(layoutPosition + 1)

            binding.iconPrivacy.setImageResource(iconPrivacy)

            binding.forksDescription.text =
                context.getString(R.string.description_forks, repositoryItem.forksCount)

            binding.starsDescription.text =
                context.getString(R.string.description_stars, repositoryItem.stargazersCount)

            Picasso.get()
                .load(repositoryItem.owner.avatarURL)
                .placeholder(R.drawable.ic_default_github)
                .error(R.drawable.ic_default_github)
                .into(binding.avatar)
        }
    }

    private fun checkPagination(position: Int) {
        if (repositories.size - position == VISIBLE_IMAGES_THRESHOLD) {
            onNextPage()
        }
    }

    private fun onNextPage() {
        callback(RepositoryAdapterCallback.OnNextPage)
    }

    override fun getItemCount(): Int = repositories.size
}