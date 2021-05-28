package com.br.repogit.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.br.repogit.R
import com.br.repogit.databinding.RecyclerRepositoryItemBinding
import com.br.repogit.presentation.model.RepositoryPresentation
import com.squareup.picasso.Picasso

class GithubAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var repositories: List<RepositoryPresentation> = emptyList()
    private lateinit var viewBinding: RecyclerRepositoryItemBinding

    private val firstItem = 1
    private val secondItem = 2
    private val thirdItem = 3
    private var counter = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        viewBinding = RecyclerRepositoryItemBinding.inflate(inflater)

        return when (viewType) {
            firstItem -> {
                ItemsViewHolder(
                    RecyclerRepositoryItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    ),
                    parent.context
                )
            }
            secondItem -> {
                ItemsViewHolder(
                    RecyclerRepositoryItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    ),
                    parent.context
                )
            }
            else -> {
                ItemsViewHolder(
                    RecyclerRepositoryItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    ),
                    parent.context
                )
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ItemsViewHolder -> {
                holder.update(repositories[position])
            }
        }
    }

    fun update(repos: List<RepositoryPresentation>) {
        this.repositories = repos
        notifyDataSetChanged()
    }

    inner class ItemsViewHolder(
        private val binding: RecyclerRepositoryItemBinding,
        private val context: Context
    ) : RecyclerView.ViewHolder(
        binding.root
    ) {
        fun update(repositoryItem: RepositoryPresentation) {
            // Fazer o bind
            binding.textViewTitle.text = repositoryItem.owner.name
            binding.textViewDescription.text = repositoryItem.description
            binding.textViewAuthor.text = repositoryItem.fullName

            val iconPrivacy = if (repositoryItem.private) {
                R.drawable.ic_private_icon
            } else {
                R.drawable.ic_public_icon
            }

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

    override fun getItemViewType(position: Int): Int {
        var viewType = 0
        if (counter < 3) {
            when (counter) {
                1 -> viewType = firstItem
                2 -> viewType = secondItem
            }
            counter++
        } else {
            viewType = thirdItem
            counter = 1
        }

        return viewType
    }

    override fun getItemCount(): Int = repositories.size

}