package com.katana.koin.ui.contacts

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.katana.koin.R
import com.katana.koin.data.local.db.entities.Member
import com.katana.koin.ui.history.HistoryNavigator
import com.katana.koin.ui.history.OnDeleteClickListener

class ContactsAdapter(context: Context, private var dataSource: ArrayList<ContactData>) : BaseAdapter() {

    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    //private lateinit var listener: OnDeleteClickListener

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view: View
        val holder: ViewHolder

        if (convertView == null) {
            view = inflater.inflate(R.layout.contact_layout, parent, false)

            holder = ViewHolder()

            holder.initialsTextView = view.findViewById(R.id.initials) as TextView
            holder.nameTextView = view.findViewById(R.id.name) as TextView
            holder.emailTextView = view.findViewById(R.id.email) as TextView
            holder.phoneTextView = view.findViewById(R.id.phone) as TextView
            holder.deleteIcon = view.findViewById(R.id.deleteIcon) as ImageView

            view.tag = holder
        } else {
            view = convertView
            holder = convertView.tag as ViewHolder
        }

        val contact = getItem(position) as ContactData

        holder.initialsTextView.text = contact.initials
        holder.nameTextView.text = contact.name
        holder.emailTextView.text = contact.email
        holder.phoneTextView.text = contact.phone

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
        lateinit var initialsTextView: TextView
        lateinit var nameTextView: TextView
        lateinit var emailTextView: TextView
        lateinit var phoneTextView: TextView
        lateinit var deleteIcon: ImageView
    }
}