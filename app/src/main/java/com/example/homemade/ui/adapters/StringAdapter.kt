package com.example.homemade.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import com.example.homemade.R

class StringAdapter(
    context: Context,
    data: ArrayList<String>,
) :
    ArrayAdapter<String>(context, data) {
    private var fullList: ArrayList<String>
    private var mOriginalValues: ArrayList<String>?


    override fun getCount() = fullList.size

    override fun getItem(position: Int): String {
        return fullList[position]
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var currentViewItem = convertView
        if (currentViewItem== null){
            currentViewItem = LayoutInflater.from(parent.context).inflate(R.layout.shopping_list_item, parent, false)
            return currentViewItem
        }
        return View(context)
    }


    private inner class ArrayFilter : Filter() {
        private val lock: Any? = null
        protected override fun performFiltering(prefix: CharSequence?): FilterResults {
            val results = FilterResults()
            if (mOriginalValues == null) {
                synchronized(lock!!) { mOriginalValues = ArrayList(fullList) }
            }
            if (prefix == null || prefix.isEmpty()) {
                synchronized(lock!!) {
                    val list = ArrayList(mOriginalValues)
                    results.values = list
                    results.count = list.size
                }
            } else {
                val prefixString = prefix.toString().toLowerCase()
                val values = mOriginalValues
                val count: Int = values!!.size
                val newValues = ArrayList<String>(count)
                for (i in 0 until count) {
                    val item = values[i]
                    if (item.toLowerCase().contains(prefixString)) {
                        newValues.add(item)
                    }
                }
                results.values = newValues
                results.count = newValues.size
            }
            return results
        }

        protected override fun publishResults(constraint: CharSequence?, results: FilterResults) {
            fullList = if (results.values != null) (
                {
                    results.values
                }
                ) as ArrayList<String> else {
                ArrayList()
            }
            if (results.count > 0) {
                notifyDataSetChanged()
            } else {
                notifyDataSetInvalidated()
            }
        }
    }

    init {
        this.fullList = data
        this.mOriginalValues = ArrayList(fullList)
    }
}
