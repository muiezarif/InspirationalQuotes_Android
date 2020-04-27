package com.muiezarif.inspirationalquotes.interfaces

import android.view.View
import com.muiezarif.inspirationalquotes.databinding.CustomAuthorItemBinding

interface AuthorClickListener {
    fun onClick(view: CustomAuthorItemBinding?, position: Int, authorName:String)
}