package com.katana.koin.ui.history

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.base.BaseActivity
import com.katana.koin.BR
import com.katana.koin.R
import com.katana.koin.data.local.db.entities.Member
import com.katana.koin.databinding.ActivityHistoryBinding
import com.katana.koin.ui.contacts.ContactActivity
import com.katana.koin.ui.send_mail.MailActivity
import kotlinx.android.synthetic.main.input_dialog.view.*
import org.koin.android.viewmodel.ext.android.viewModel
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class HistoryActivity : BaseActivity<ActivityHistoryBinding, HistoryViewModel>(), HistoryNavigator {

    private val historyViewModel: HistoryViewModel by viewModel()

    private lateinit var adapter: HistoryAdapter

    private val arrayList = ArrayList<HistoryData>()

    private lateinit var listView : ListView

    override fun getLayoutId(): Int = R.layout.activity_history

    override fun getBindingVariable(): Int = BR.historyModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun updateUI(savedInstanceState: Bundle?) {
        historyViewModel.setNavigator(this)

        listView = findViewById(R.id.list_view)

        listView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val item = parent.getItemAtPosition(position) as HistoryData
            val builder = AlertDialog.Builder(this@HistoryActivity)
            builder.setTitle("Details")
            builder.setMessage("Name : "+item.name + "\n\nEmail : "+item.email + "\n\nTotal : "+item.total + "\n\nDate : "+item.date);
            builder.setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }

            val dialog: AlertDialog = builder.create()
            dialog.show()

        }

        getMails()


    }

    override fun getViewModel(): HistoryViewModel = historyViewModel

    private fun showToast(message: String) {
        val toast = Toast.makeText(applicationContext, message, Toast.LENGTH_LONG)
        toast.show()
    }

    fun onBack(view: View) {
        val intent = Intent(this, ContactActivity::class.java)
        startActivity(intent)
    }

    fun newMail(view: View) {
        val intent = Intent(this, MailActivity::class.java)
        startActivity(intent)
    }

    private fun getMails(){
        arrayList.clear()
        for (item in historyViewModel.getMails()) {
            val pattern = "EEE, MMM d, HH:mm:ss"
            val simpleDateFormat = SimpleDateFormat(pattern, Locale.US)
            val date: String = simpleDateFormat.format(item.date)
            val total = NumberFormat.getNumberInstance(Locale.US).format(item.total);
            arrayList.add(HistoryData(R.drawable.ic_person, item.name.toString(), item.to.toString(), date, total, item.date.toString()))
            adapter = HistoryAdapter(this, arrayList)
            listView.adapter = adapter
        }
    }
}