package com.muiezarif.inspirationalquotes.interfaces

import android.view.View
import com.muiezarif.inspirationalquotes.databinding.CustomQuoteItemBinding


interface QuoteClickListener {
    fun onClick(view: CustomQuoteItemBinding?, position: Int)
}