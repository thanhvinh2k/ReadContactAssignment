package com.thanhvinh.readcontactassignment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.thanhvinh.readcontactassignment.adapter.DiaryAdapter
import kotlinx.android.synthetic.main.fragment_diary.*

class DiaryFragment : Fragment() {

    private val adapter by lazy { DiaryAdapter() }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_diary, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter.updateData(MainActivity.getDiary(context))
        recycleViewDiary.setHasFixedSize(true)
        recycleViewDiary.adapter = adapter
    }
}
