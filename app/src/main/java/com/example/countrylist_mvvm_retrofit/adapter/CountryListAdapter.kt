package com.example.countrylist_mvvm_retrofit.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.countrylist_mvvm_retrofit.R
import com.example.countrylist_mvvm_retrofit.databinding.ItemCountryListBinding
import com.example.countrylist_mvvm_retrofit.model.CountryModel


class CountryListAdapter(private var countryList: ArrayList<CountryModel>) :
    RecyclerView.Adapter<CountryListAdapter.MyViewHolder>() {
    private lateinit var bindingCountryList: ItemCountryListBinding


    fun setCountryList(countryList: ArrayList<CountryModel>) {
        this.countryList = countryList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CountryListAdapter.MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_country_list, parent, false)
        bindingCountryList = ItemCountryListBinding.bind(itemView)
        return MyViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CountryListAdapter.MyViewHolder, position: Int) {
        val infoToList = countryList[position]
        holder.apply {
            tvName.text = infoToList.name //+ "(" + infoToList.alpha2Code + ")"
            tvCapital.text = "Capital: " + infoToList.capital
            tvRegion.text = "Region: " + infoToList.region
            Glide.with(itemView).load(infoToList.flags).into(flagImage)
        }
//        holder.bind(countryList?.get(position)!!, activity)
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val flagImage: AppCompatImageView
        val tvName: AppCompatTextView
        val tvCapital: AppCompatTextView
        val tvRegion: AppCompatTextView

        init {
            flagImage = itemView.findViewById(R.id.flagImage)
            tvName = itemView.findViewById(R.id.tvName)
            tvCapital = itemView.findViewById(R.id.tvCapital)
            tvRegion = itemView.findViewById(R.id.tvRegion)
        }
    }
}
