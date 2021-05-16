package com.thanhvinh.readcontactassignment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thanhvinh.readcontactassignment.R
import com.thanhvinh.readcontactassignment.model.Contact
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_layout_contact.view.*

class ContactAdapter : RecyclerView.Adapter<ContactAdapter.ViewHolder?>(){
    private val contactList = mutableListOf<Contact>()

    fun updateData(contactList: MutableList<Contact>?){
        contactList?.let {
            this.contactList.clear()
            this.contactList.addAll(it)
            notifyDataSetChanged()
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout_contact, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return contactList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindViewData(contactList[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindViewData(contact: Contact) {
            itemView.apply {
                textViewUserName.text = contact.userName
                textViewUserPhoneNumber.text = contact.userPhoneNumber
                getImage(contact)
            }
        }

        fun getImage(contact: Contact) {
            Glide.with(itemView.context)
                .load(contact.userImage)
                .into(itemView.imageViewUserContact)
        }
    }
}
