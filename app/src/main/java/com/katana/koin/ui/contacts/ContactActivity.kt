package com.katana.koin.ui.contacts

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.base.BaseActivity
import com.google.android.material.navigation.NavigationView
import com.katana.koin.BR
import com.katana.koin.R
import com.katana.koin.data.local.db.entities.Member
import com.katana.koin.databinding.ActivityContactBinding
import com.katana.koin.ui.history.HistoryActivity
import com.katana.koin.ui.history.HistoryAdapter
import com.katana.koin.ui.history.HistoryData
import com.katana.koin.ui.login.LoginActivity
import com.katana.koin.ui.send_mail.MailActivity
import kotlinx.android.synthetic.main.input_dialog.view.*
import org.koin.android.viewmodel.ext.android.viewModel
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class ContactActivity : BaseActivity<ActivityContactBinding, ContactViewModel>(), ContactNavigator, NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private val contactViewModel: ContactViewModel by viewModel()

    private var drawer: DrawerLayout? = null

    private lateinit var adapter: ContactsAdapter

    private val arrayList = ArrayList<ContactData>()

    private lateinit var listView : ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        listView = findViewById(R.id.history_list_view);

        getMembers();

        listView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->

            val builder = AlertDialog.Builder(this@ContactActivity)
            builder.setTitle("Confirmation")
            builder.setMessage("Are you sure you want to delete this member?")
            builder.setPositiveButton("YES") { dialog, which ->
                val item = parent.getItemAtPosition(position) as ContactData
                contactViewModel.deleteMember(item.email)
                dialog.dismiss()
            }

            builder.setNegativeButton("No") { dialog, which ->
                dialog.dismiss()
            }

            val dialog: AlertDialog = builder.create()
            dialog.show()

        }

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        drawer = findViewById(R.id.drawer_layout)
        val navigationView: NavigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        toggle.drawerArrowDrawable.color = resources.getColor(R.color.white_color)
        drawer?.addDrawerListener(toggle)
        toggle.syncState()
        navigationView.setNavigationItemSelectedListener(this)

        val headerView: View = navigationView.getHeaderView(0)
        val cancelIcon = headerView.findViewById<ImageView>(R.id.cancelIcon)
        cancelIcon.setOnClickListener(this)
    }

    override fun getLayoutId(): Int = R.layout.activity_contact

    override fun getBindingVariable(): Int = BR.contactModel

    override fun updateUI(savedInstanceState: Bundle?) {
        contactViewModel.setNavigator(this)
    }

    override fun getViewModel(): ContactViewModel = contactViewModel

    override fun onNavigationItemSelected(item: MenuItem): Boolean { // Handle navigation view item clicks here.
        val id = item.itemId
        var myIntent: Intent? = null
        when (id) {
            R.id.nav_history -> {
                myIntent = Intent(applicationContext, HistoryActivity::class.java)
                startActivity(myIntent)
            }
            R.id.nav_mail -> {
                myIntent = Intent(applicationContext, MailActivity::class.java)
                startActivity(myIntent)
            }
            R.id.nav_logout -> {
                val builder = AlertDialog.Builder(this@ContactActivity)
                builder.setTitle("Confirmation")
                builder.setMessage("Are you sure you want to logout?")
                builder.setPositiveButton("YES") { dialog, which ->
                    contactViewModel.saveLoginMode(false)
                    myIntent = Intent(applicationContext, LoginActivity::class.java)
                    startActivity(myIntent)
                    dialog.dismiss()
                }

                builder.setNegativeButton("NO") { dialog, which ->
                    dialog.dismiss()
                }

                val dialog: AlertDialog = builder.create()
                dialog.show()

            }
        }
        val drawer: DrawerLayout = findViewById(R.id.drawer_layout)
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    private fun closeDrawer() {
        if (drawer?.isDrawerOpen(GravityCompat.START)!!) {
            drawer!!.closeDrawer(GravityCompat.START)
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.cancelIcon -> closeDrawer()
        }
    }

    fun newContact(view: View) {
        val mDialogView = LayoutInflater.from(this).inflate(R.layout.input_dialog, null)
        val mBuilder = AlertDialog.Builder(this).setView(mDialogView);
        val mAlertDialog = mBuilder.show()
        mDialogView.addToListBtn.setOnClickListener {

            val name = mDialogView.nameEditText.text.toString()
            val email = mDialogView.emailEditText.text.toString()
            val phone = mDialogView.phoneEditText.text.toString()

            if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(phone)) {
                val toast = Toast.makeText(applicationContext, "Please fill all fields", Toast.LENGTH_LONG)
                toast.show()
                return@setOnClickListener
            }

            val member = Member(name = name, email = email, phone = phone)
            contactViewModel.saveMember(member)
            mAlertDialog.dismiss()
        }
    }

    override fun getResponse(id: Long) {
        if (id > 0) {
            showToast("New Member Added Successfully");
            getMembers()
            adapter.notifyDataSetChanged()
        } else {
            showToast("Error Adding New Member")
        }
    }

    override fun deleteMemberResponse(deleted: Int) {
        println("deleted $deleted")
        if (deleted > 0) {
            showToast("Member Deleted Successfully")
            getMembers()
            adapter.notifyDataSetChanged()
        } else {
            showToast("Could not delete member, Please try again later.")
        }
    }

    override fun handleError(error: Throwable) {
        showToast("Error Adding New Member")
        showToast(error.message.toString())
        println(error.message)
    }

    private fun showToast(message: String) {
        val toast = Toast.makeText(applicationContext, message, Toast.LENGTH_LONG)
        toast.show()
    }

    private fun getMembers(){
        arrayList.clear();
        for (item in contactViewModel.getMembers()) {
            arrayList.add(ContactData(item.name!!.first().toString().toUpperCase(Locale.US), item.name.toString(), item.email.toString(), item.phone.toString()))
            adapter = ContactsAdapter(this, arrayList)
            listView.adapter = adapter
        }
    }

    fun onBack(view: View) {
        onBackPressed()
    }

}
