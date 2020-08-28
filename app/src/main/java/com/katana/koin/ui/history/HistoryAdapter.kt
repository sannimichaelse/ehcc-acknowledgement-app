package com.katana.koin.ui.history

import android.content.Context
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders
import com.katana.koin.R
import com.katana.koin.ui.contacts.ContactActivity
import com.katana.koin.ui.contacts.ContactViewModel
import com.katana.koin.ui.history.HistoryData
import org.koin.android.viewmodel.ext.android.viewModel

class HistoryAdapter(context: Context,
                      private val dataSource: ArrayList<HistoryData>) : BaseAdapter() {

    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view: View
        val holder: ViewHolder

        if (convertView == null) {
            view = inflater.inflate(R.layout.history_layout, parent, false)

            holder = ViewHolder()

            holder.imageView = view.findViewById(R.id.history_type) as ImageView
            holder.nameTextView = view.findViewById(R.id.history_name) as TextView
            holder.emailTextView = view.findViewById(R.id.history_email) as TextView
            holder.phoneTextView = view.findViewById(R.id.history_phone) as TextView
            holder.amountTextView = view.findViewById(R.id.history_amount) as TextView

            view.tag = holder
        } else {
            view = convertView
            holder = convertView.tag as ViewHolder
        }

        val history = getItem(position) as HistoryData

        holder.imageView.setImageResource(history.type)
        holder.nameTextView.text = history.name
        holder.emailTextView.text = history.email
        holder.phoneTextView.text = history.phone
        holder.amountTextView.text = history.total

        return view
    }

    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return dataSource.size
    }

    private class ViewHolder {
        lateinit var imageView: ImageView
        lateinit var nameTextView: TextView
        lateinit var emailTextView: TextView
        lateinit var phoneTextView: TextView
        lateinit var amountTextView: TextView
    }
}