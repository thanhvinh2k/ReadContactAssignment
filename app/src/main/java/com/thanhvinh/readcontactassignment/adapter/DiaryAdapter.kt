package com.thanhvinh.readcontactassignment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thanhvinh.readcontactassignment.R
import com.thanhvinh.readcontactassignment.model.Diary
import kotlinx.android.synthetic.main.item_layout_diary.view.*

class DiaryAdapter : RecyclerView.Adapter<DiaryAdapter.ViewHolder?>(){

    private val diaryList = mutableListOf<Diary>()

    fun updateData(diaryList: MutableList<Diary>?){
        diaryList?.let {
            this.diaryList.clear()
            this.diaryList.addAll(it)
            notifyDataSetChanged()
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout_diary, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return diaryList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindViewData(diaryList[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindViewData(diary: Diary) {
            itemView.apply {
                textViewPhoneNumber.text = diary.phoneNumber
                textViewContentDiary.text = "[${diary.callTimeTotal}] : ${diary.callTimeStart}"
            }
        }
    }
}
