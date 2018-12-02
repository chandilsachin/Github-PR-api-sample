package io.github.chandilsachin.prapisample.modules.pr

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import io.github.chandilsachin.prapisample.databinding.PrListItemBinding

class PRListAdapter(context: Context, var list: List<PullRequest>): RecyclerView.Adapter<PRListAdapter.ViewHolder>() {

    val layoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(PrListItemBinding.inflate(layoutInflater, p0, false))

    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.binding.itemData = list[p1]
    }

    override fun getItemCount() = list.size

    class ViewHolder(var binding: PrListItemBinding): RecyclerView.ViewHolder(binding.root){

    }
}