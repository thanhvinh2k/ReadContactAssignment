package com.thanhvinh.readcontactassignment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.thanhvinh.readcontactassignment.adapter.ContactAdapter
import kotlinx.android.synthetic.main.fragment_contact.*

class ContactFragment : Fragment() {

    private val adapter by lazy { ContactAdapter() }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter.updateData(MainActivity.getContact(context))
        recycleViewContact.setHasFixedSize(true)
        recycleViewContact.adapter = this.adapter
    }
}
