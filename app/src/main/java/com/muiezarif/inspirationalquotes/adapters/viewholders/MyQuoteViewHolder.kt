package com.muiezarif.inspirationalquotes.adapters.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.muiezarif.inspirationalquotes.databinding.CustomQuoteItemBinding
import com.muiezarif.inspirationalquotes.interfaces.QuoteClickListener

class MyQuoteViewHolder(var binding: CustomQuoteItemBinding, var quoteClickListener: QuoteClickListener): RecyclerView.ViewHolder(binding.root),
    View.OnClickListener {
    init {
        binding.clQuote.setOnClickListener(this)
    }
    override fun onClick(v: View?) {
        quoteClickListener.onClick(binding,adapterPosition)
    }
}