package com.example.property.main.property

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.property.R
import com.example.property.databinding.AreaItemBinding
import com.example.property.databinding.HighlightPropertyItemBinding
import com.example.property.databinding.PropertyItemBinding
import com.example.property.main.property.viewentity.PropertyItemEntity

class PropertyItemAdapter :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var context: Context

    private var itemInteractionListener: ItemInteractionListener? = null

    fun setOnItemClickListener(listener: ItemInteractionListener) {
        itemInteractionListener = listener
    }

    private var itemList: MutableList<PropertyItemEntity> = mutableListOf()

    fun updatePropertyItems(list: List<PropertyItemEntity>) {
        itemList.clear()
        itemList.addAll(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        context = parent.context
        when (viewType) {
            TYPE_PROPERTY -> {
                val itemBinding = PropertyItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

                itemBinding.root.layoutParams = RecyclerView.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                return PropertyViewHolder(itemBinding)
            }
            TYPE_AREA -> {
                val itemBinding = AreaItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

                itemBinding.root.layoutParams = RecyclerView.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                return AreaViewHolder(itemBinding)
            }
            TYPE_HIGHLIGHT_PROPERTY -> {
                val itemBinding = HighlightPropertyItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

                itemBinding.root.layoutParams = RecyclerView.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                return HighlightedPropertyViewHolder(itemBinding)
            }
            else -> throw IllegalArgumentException("Unspecified view type.")
        }
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            TYPE_PROPERTY -> {
                (holder as PropertyViewHolder).bind(
                    itemList[position] as PropertyItemEntity.PropertyEntity
                )
            }
            TYPE_AREA -> {
                (holder as AreaViewHolder).bind(
                    itemList[position] as PropertyItemEntity.AreaEntity
                )
            }
            TYPE_HIGHLIGHT_PROPERTY -> {
                (holder as HighlightedPropertyViewHolder).bind(
                    itemList[position] as PropertyItemEntity.HighlightedPropertyEntity
                )
            }
        }
        holder.itemView.setOnClickListener {
            itemInteractionListener?.onItemClick(itemList[position])
        }
    }

    override fun getItemViewType(position: Int): Int =
        when (itemList[position]) {
            is PropertyItemEntity.PropertyEntity -> TYPE_PROPERTY
            is PropertyItemEntity.AreaEntity -> TYPE_AREA
            is PropertyItemEntity.HighlightedPropertyEntity -> TYPE_HIGHLIGHT_PROPERTY
        }

    inner class PropertyViewHolder(private val itemBinding: PropertyItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(viewEntity: PropertyItemEntity.PropertyEntity) {
            Glide.with(context)
                .load(viewEntity.imageUrl)
                .into(itemBinding.imageView)
            itemBinding.titleTextView.text = viewEntity.streetAddress
            itemBinding.areaTextView.text = viewEntity.area + ", " + viewEntity.municipality
            itemBinding.monthlyFeeTextView.text = viewEntity.monthlyFee
            itemBinding.averagePriceTextView.text = viewEntity.askingPrice
        }
    }

    inner class AreaViewHolder(private val itemBinding: AreaItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(viewEntity: PropertyItemEntity.AreaEntity) {
            Glide.with(context)
                .load(viewEntity.imageUrl)
                .centerCrop()
                .into(itemBinding.imageView)
            itemBinding.titleTextView.text = viewEntity.area
            itemBinding.priceTextView.text = context.getString(R.string.price) + viewEntity.averagePrice
            itemBinding.ratingTextView.text = context.getString(R.string.rating) + viewEntity.rating
        }
    }

    inner class HighlightedPropertyViewHolder(private val itemBinding: HighlightPropertyItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(viewEntity: PropertyItemEntity.HighlightedPropertyEntity) {
            Glide.with(context)
                .load(viewEntity.imageUrl)
                .into(itemBinding.imageView)
            itemBinding.titleTextView.text = viewEntity.streetAddress
            itemBinding.areaTextView.text = viewEntity.area + ", " + viewEntity.municipality
            itemBinding.monthlyFeeTextView.text = viewEntity.monthlyFee
            itemBinding.averagePriceTextView.text = viewEntity.askingPrice
        }
    }

    interface ItemInteractionListener {
        fun onItemClick(propertyItemEntity: PropertyItemEntity)
    }

    companion object {
        const val TYPE_HIGHLIGHT_PROPERTY = 1
        const val TYPE_PROPERTY = 2
        const val TYPE_AREA = 3
    }
}