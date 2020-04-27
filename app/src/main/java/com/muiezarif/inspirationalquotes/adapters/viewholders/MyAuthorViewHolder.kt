package com.muiezarif.inspirationalquotes.adapters.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.muiezarif.inspirationalquotes.databinding.CustomAuthorItemBinding
import com.muiezarif.inspirationalquotes.interfaces.AuthorClickListener


class MyAuthorViewHolder(var binding: CustomAuthorItemBinding, var authorClickListener: AuthorClickListener): RecyclerView.ViewHolder(binding.root),
    View.OnClickListener {
    init {
        binding.clAuthorItem.setOnClickListener(this)
    }
    override fun onClick(v: View?) {
        authorClickListener.onClick(binding,adapterPosition,binding.tvAuthorName.text.toString())
    }
}