package com.muiezarif.inspirationalquotes.adapters

import android.content.Context
import android.content.res.AssetManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.muiezarif.inspirationalquotes.R
import com.muiezarif.inspirationalquotes.adapters.viewholders.MyAuthorViewHolder
import com.muiezarif.inspirationalquotes.databinding.CustomAuthorItemBinding
import com.muiezarif.inspirationalquotes.db.models.AuthorModel
import com.muiezarif.inspirationalquotes.interfaces.AuthorClickListener


import java.io.IOException
import java.io.InputStream
import java.util.*
import kotlin.collections.ArrayList


class AuthorRecyclerAdapter(var context: Context, var authorList:ArrayList<AuthorModel>, var authorClickListener: AuthorClickListener): RecyclerView.Adapter<MyAuthorViewHolder>() {
    private var colors: Array<Int> = arrayOf(
        R.color.colorCombo1,
        R.color.colorCombo2,
        R.color.colorCombo3,
        R.color.colorCombo4,
        R.color.colorCombo5,
        R.color.colorCombo6,
        R.color.colorCombo7,
        R.color.colorCombo8,
        R.color.colorCombo9,
        R.color.colorCombo10
    )
    val min = 0
    val max = 9

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAuthorViewHolder {
        val binding: CustomAuthorItemBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.custom_author_item,parent,false)
        return MyAuthorViewHolder(binding,authorClickListener)
    }

    override fun getItemCount(): Int {
        return authorList.size
    }

    override fun onBindViewHolder(holder: MyAuthorViewHolder, position: Int) {
        val model=authorList[position]
        holder.binding.tvAuthorName.text=model.name
        val random = Random().nextInt(max - min + 1) + min
        Log.i("COLOR", colors[random].toString())
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP){
            holder.binding.cvAuthorItem.setCardBackgroundColor(context.resources.getColor(colors[random]))
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            holder.binding.cvAuthorItem.setCardBackgroundColor(context.getColor(colors[random]))
        }
    }

    fun filterList(filteredList:ArrayList<AuthorModel>){
        authorList=filteredList
        notifyDataSetChanged()
    }
}