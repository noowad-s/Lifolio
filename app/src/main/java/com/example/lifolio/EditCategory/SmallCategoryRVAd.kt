package com.example.lifolio.EditCategory

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.lifolio.Category.EditSmallCategoryActivity
import com.example.lifolio.EditCategory.models.SmallCategory
import com.example.lifolio.R
import com.example.lifolio.databinding.ItemSmallCategoryBinding

class SmallCategoryRVAd (context : Context, val smallCategoryList : MutableList<SmallCategory>) : RecyclerView.Adapter<SmallCategoryRVAd.DataViewHolder>(),ItemTouchHelperListener {
    inner class DataViewHolder(var binding : ItemSmallCategoryBinding) :
        RecyclerView.ViewHolder(binding.root){

        fun bind(smallCategory: SmallCategory){
            binding.itemSmallcategoryTitle.text = smallCategory.smallCategory

            binding.smallcategoryEditBtn.setOnClickListener {
                val popupMenu = PopupMenu(binding.root.context, it)
                popupMenu.menuInflater.inflate(R.menu.edit_category_context_menu, popupMenu.menu)
                popupMenu.setOnMenuItemClickListener {
                    when(it.itemId){
                        R.id.editcategory_delete_context_menu -> {
                            smallCategoryList.removeAt(adapterPosition)
                            notifyDataSetChanged()
                        }
                        else -> {
                            binding.root.context.startActivity(Intent(binding.root.context,EditSmallCategoryActivity::class.java))
                        }
                    }
                    true
                }
                popupMenu.show()
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val binding = ItemSmallCategoryBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return DataViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(smallCategoryList[position])
    }

    override fun getItemCount(): Int = smallCategoryList.size

    // ???????????? ??????????????? ???????????? ?????????
    override fun onItemMove(from_position: Int, to_position: Int): Boolean {
        val name = smallCategoryList[from_position]
        // ????????? ??????
        smallCategoryList.removeAt(from_position)
        smallCategoryList.add(to_position, name)

        // fromPosition?????? toPosition?????? ????????? ?????? ??????
        notifyItemMoved(from_position, to_position)
        return true
    }

    override fun onItemSwipe(position: Int) {
        smallCategoryList.removeAt(position)
        notifyItemRemoved(position)
    }

}