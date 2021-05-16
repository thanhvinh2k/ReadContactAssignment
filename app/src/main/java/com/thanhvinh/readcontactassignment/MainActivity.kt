package com.thanhvinh.readcontactassignment

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.CallLog
import android.provider.ContactsContract.CommonDataKinds.Phone
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.material.tabs.TabLayout
import com.thanhvinh.readcontactassignment.adapter.PagerAdapter
import com.thanhvinh.readcontactassignment.model.Contact
import com.thanhvinh.readcontactassignment.model.Diary
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private val requestCode = 2
    private val formatter = "HH:mm:ss dd-MM-yyyy"
    val simpleDateFormat = SimpleDateFormat(formatter)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_CONTACTS
            ) == PackageManager.PERMISSION_DENIED
            || ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_CALL_LOG
            ) == PackageManager.PERMISSION_DENIED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_CONTACTS, Manifest.permission.READ_CALL_LOG),
                requestCode
            )
        } else {
            initView()
            getDiary(this)
        }
    }

    private fun initView() {
        val contactTab = tabLayoutContact.newTab()
        contactTab.text = "DANH BA"
        tabLayoutContact.addTab(contactTab)

        val diaryTab = tabLayoutContact.newTab()
        diaryTab.text = "NHAT KY"
        tabLayoutContact.addTab(diaryTab)

        val adapter = PagerAdapter(
            supportFragmentManager,
            tabLayoutContact.tabCount
        )
        viewpagerContact.adapter = adapter
        viewpagerContact.addOnPageChangeListener(
            TabLayout.TabLayoutOnPageChangeListener(
                tabLayoutContact
            )
        )
        tabLayoutContact.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewpagerContact.currentItem = tab!!.position
            }
        })
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        initView()
        getDiary(this)
    }

    companion object {

        fun getDiary(context: Context?): MutableList<Diary> {
            val callDiary = mutableListOf<Diary>()
            val contentUri = CallLog.Calls.CONTENT_URI

            try {
                val cursor = context!!.contentResolver.query(contentUri, null, null, null, null)

                if (cursor!!.moveToNext()) {
                    do {
                        val phoneNumber =
                            cursor.getString(cursor.getColumnIndex(CallLog.Calls.NUMBER))
                        val timeTotal =
                            cursor.getString(cursor.getColumnIndex(CallLog.Calls.DURATION))
                        val timeStart = Date(
                            cursor.getString(cursor.getColumnIndex(CallLog.Calls.DATE)).toLong()
                        )
                        val timeFormatted = MainActivity().simpleDateFormat.format(timeStart)
                        callDiary.add(Diary(phoneNumber, timeTotal, timeFormatted))
                    } while (cursor.moveToNext())
                    cursor.close()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return callDiary
        }

        fun getContact(context: Context?): MutableList<Contact> {
            val contactList = mutableListOf<Contact>()
            val contactUri = Phone.CONTENT_URI
            val cursor = context!!.contentResolver.query(contactUri, null, null, null, null)

            try {
                if (cursor!!.moveToFirst()) {
                    do {
                        val nameContact = cursor.getString(cursor.getColumnIndex(Phone.DISPLAY_NAME))
                        val userPhoneNumber = cursor.getString(cursor.getColumnIndex(Phone.NUMBER))
                        val userImage = cursor.getString(cursor.getColumnIndex(Phone.PHOTO_URI))
                        contactList.add(Contact(userImage, nameContact, userPhoneNumber))
                    } while (cursor.moveToNext())
                    cursor.close()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return contactList
        }
    }
}
