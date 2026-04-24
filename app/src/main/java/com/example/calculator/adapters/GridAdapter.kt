package com.example.calculator.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.calculator.R
import com.example.calculator.classes.Btn

class GridAdapter (
    private val btnList: List<Btn>,
    private val context: Context,
    private val onBtnClick: (String) -> Unit
)
    : BaseAdapter() {
    private var layoutInflater: LayoutInflater? = null
    private lateinit var name: TextView

    override fun getCount(): Int {
        return btnList.size
    }

    override fun getItem(position: Int): Any {
        return btnList[position]
    }

    override fun getItemId(position: Int): Long {
        return btnList[position].id.toLong()
    }

    // in below function we are getting individual item of grid view.
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val gridView = convertView ?: LayoutInflater.from(parent?.context)
            .inflate(R.layout.gridview_item, parent, false)
        val textView = gridView!!.findViewById<TextView>(R.id.txt)

            textView.text = btnList[position].text

            gridView.setOnClickListener {
                onBtnClick(textView.text.toString())
            }


        return gridView
    }
}