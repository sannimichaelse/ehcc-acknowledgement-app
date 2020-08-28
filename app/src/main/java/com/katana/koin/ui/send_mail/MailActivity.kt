package com.katana.koin.ui.send_mail

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.base.BaseActivity
import com.katana.koin.BR
import com.katana.koin.R
import com.katana.koin.data.remote.model.MailResponse
import com.katana.koin.databinding.ActivityMainBinding
import com.katana.koin.ui.contacts.ContactActivity
import com.katana.koin.ui.history.HistoryActivity
import kotlinx.android.synthetic.main.activity_mail.*
import kotlinx.android.synthetic.main.tithe_category_layout.view.*
import org.koin.android.viewmodel.ext.android.viewModel
import java.text.NumberFormat
import java.util.*
import kotlin.collections.HashMap


class MailActivity : BaseActivity<ActivityMainBinding, MailViewModel>(), MailNavigator, AdapterView.OnItemSelectedListener {

    private val mailViewModel: MailViewModel by viewModel()

    private val mailMap = HashMap<String, Int>()

    private val arrayList = ArrayList<String>()

    private lateinit var spinner: Spinner

    private lateinit var to : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        spinner = findViewById(R.id.spinner)
        val adapter= ArrayAdapter(this@MailActivity, android.R.layout.simple_spinner_dropdown_item, getMembers())
        spinner.adapter = adapter
        spinner.onItemSelectedListener = this
    }

    override fun getLayoutId(): Int = R.layout.activity_mail

    override fun getBindingVariable(): Int = BR.mailModel

    override fun updateUI(savedInstanceState: Bundle?) {
        mailViewModel.setNavigator(this)
    }

    override fun getViewModel(): MailViewModel = mailViewModel

    fun onCheckboxClicked(view: View) {
        if (view is CheckBox) {
            val checked: Boolean = view.isChecked
            when (view.id) {
                R.id.checkbox_tithe -> {
                    if (checked) {
                        val mDialogView = LayoutInflater.from(this).inflate(R.layout.tithe_category_layout, null)
                        val mBuilder = AlertDialog.Builder(this).setView(mDialogView);
                        val mAlertDialog = mBuilder.show()
                        mDialogView.category_title.text = "Tithe";
                        mDialogView.saveToListBtn.setOnClickListener {
                            val amount = mDialogView.categoryAmountEditText.text.toString()
                            if (TextUtils.isEmpty(amount)) {
                                val toast = Toast.makeText(applicationContext, "Please Enter Tithe Amount", Toast.LENGTH_LONG)
                                toast.show()
                                return@setOnClickListener
                            }

                            mailMap["Tithe"] = amount.toInt();
                            mAlertDialog.dismiss()

                            var sum = 0.0f
                            for (f in mailMap.values) {
                                sum += f
                            }

                            totalEditText.visibility = View.VISIBLE
                            totalEditText.text = "Total : $sum"

                        }
                        mAlertDialog.setCanceledOnTouchOutside(false)
                    } else {
                        val others = mailMap["Tithe"];
                        var sum = 0.0f
                        for (f in mailMap.values) {
                            sum += f
                        }

                        val sub = sum - others?.toFloat()!!;

                        totalEditText.text = "Total : $sub"
                        mailMap.remove("Tithe");

                    }
                }
                R.id.checkbox_offering -> {
                    if (checked) {
                        val mDialogView = LayoutInflater.from(this).inflate(R.layout.tithe_category_layout, null)
                        val mBuilder = AlertDialog.Builder(this).setView(mDialogView);
                        val mAlertDialog = mBuilder.show()
                        mDialogView.category_title.text = "Offering";
                        mDialogView.saveToListBtn.setOnClickListener {
                            val amount = mDialogView.categoryAmountEditText.text.toString()
                            if (TextUtils.isEmpty(amount)) {
                                val toast = Toast.makeText(applicationContext, "Please Enter Offering Amount", Toast.LENGTH_LONG)
                                toast.show()
                                return@setOnClickListener
                            }

                            mailMap["Offering"] = amount.toInt();
                            mAlertDialog.dismiss()

                            var sum = 0.0f
                            for (f in mailMap.values) {
                                sum += f
                            }

                            totalEditText.visibility = View.VISIBLE
                            totalEditText.text = "Total : $sum"

                        }
                        mAlertDialog.setCanceledOnTouchOutside(false)
                    } else {
                        val others = mailMap["Offering"];
                        var sum = 0.0f
                        for (f in mailMap.values) {
                            sum += f
                        }

                        val sub = sum - others?.toFloat()!!;

                        totalEditText.text = "Total : $sub"
                        mailMap.remove("Offering");

                    }
                }
                R.id.checkbox_seed -> {
                    if (checked) {
                        val mDialogView = LayoutInflater.from(this).inflate(R.layout.tithe_category_layout, null)
                        val mBuilder = AlertDialog.Builder(this).setView(mDialogView);
                        val mAlertDialog = mBuilder.show()
                        mDialogView.category_title.text = "Seed";
                        mDialogView.saveToListBtn.setOnClickListener {
                            val amount = mDialogView.categoryAmountEditText.text.toString()
                            if (TextUtils.isEmpty(amount)) {
                                val toast = Toast.makeText(applicationContext, "Please Enter Seed Amount", Toast.LENGTH_LONG)
                                toast.show()
                                return@setOnClickListener
                            }

                            mailMap["Seed"] = amount.toInt();
                            mAlertDialog.dismiss()

                            var sum = 0.0f
                            for (f in mailMap.values) {
                                sum += f
                            }

                            totalEditText.visibility = View.VISIBLE
                            totalEditText.text = "Total : $sum"

                        }
                        mAlertDialog.setCanceledOnTouchOutside(false)
                    } else {
                        val others = mailMap["Seed"];
                        var sum = 0.0f
                        for (f in mailMap.values) {
                            sum += f
                        }

                        val sub = sum - others?.toFloat()!!;

                        totalEditText.text = "Total : $sub"
                        mailMap.remove("Seed");

                    }
                }
                R.id.checkbox_project -> {
                    if (checked) {
                        val mDialogView = LayoutInflater.from(this).inflate(R.layout.tithe_category_layout, null)
                        val mBuilder = AlertDialog.Builder(this).setView(mDialogView);
                        val mAlertDialog = mBuilder.show()
                        mDialogView.category_title.text = "Others";
                        mDialogView.saveToListBtn.setOnClickListener {
                            val amount = mDialogView.categoryAmountEditText.text.toString()
                            if (TextUtils.isEmpty(amount)) {
                                val toast = Toast.makeText(applicationContext, "Please Enter Others Amount", Toast.LENGTH_LONG)
                                toast.show()
                                return@setOnClickListener
                            }

                            mailMap["Others"] = amount.toInt();
                            mAlertDialog.dismiss()


                            var sum = 0.0f
                            for (f in mailMap.values) {
                                sum += f
                            }

                            totalEditText.visibility = View.VISIBLE
                            totalEditText.text = "Total : $sum"

                        }
                        mAlertDialog.setCanceledOnTouchOutside(false)
                    } else {

                        val others = mailMap["Others"];
                        var sum = 0.0f
                        for (f in mailMap.values) {
                            sum += f
                        }

                        val sub = sum - others?.toFloat()!!;

                        totalEditText.text = "Total : $sub"
                        mailMap.remove("Others")

                    }
                }
            }
        }
    }

    fun onBackClicked(view: View) {
       onBackPressed()
    }

    fun sendMail(view: View) {
       
        val name = nameEditText.text.toString()
        val message = messageEditText.text.toString();

        if(spinner.count == 0 || spinner.selectedItem == "Select Email" || TextUtils.isEmpty(name) || TextUtils.isEmpty(message)){
            showToast("Please fill all fields");
            return
        }

        if(mailMap.isEmpty()){
            showToast("You must select at least one category");
            return
        }

        var sum = 0.0f
        for (f in mailMap.values) {
            sum += f
        }

        val params : MutableMap<String, Any> = mutableMapOf()

        params["email"] = to;
        params["name"] = name;
        params["message"] = message;
        params["total"] = NumberFormat.getNumberInstance(Locale.US).format(sum);

        if(mailMap.containsKey("Tithe")){
            params["tithe"] = NumberFormat.getNumberInstance(Locale.US).format(mailMap["Tithe"])
        }else{
            params["tithe"] = "0.00"
        }
        if(mailMap.containsKey("Offering")){
            params["offering"] =  NumberFormat.getNumberInstance(Locale.US).format(mailMap["Offering"])
        }else{
            params["offering"] = "0.00"
        }
        if(mailMap.containsKey("Seed")){
            params["seed"] =  NumberFormat.getNumberInstance(Locale.US).format(mailMap["Seed"])
        }else{
            params["seed"] = "0.00"
        }
        if(mailMap.containsKey("Others")){
            params["others"] =  NumberFormat.getNumberInstance(Locale.US).format(mailMap["Others"])
        }else{
            params["others"] = "0.00"
        }

       mailViewModel.sendMail(params)

    }

    private fun showToast(message: String) {
        val toast = Toast.makeText(applicationContext, message, Toast.LENGTH_LONG)
        toast.show()
    }

    override fun handleMailResponse(result: MailResponse) {
        showToast(result.message)
    }

    override fun handleSaveMailResponse(result: Long) {
        if(result > 0){
            showToast("Data Saved Successfully")
            val intent = Intent(this, HistoryActivity::class.java)
            startActivity(intent)
        }else{
            showToast("Data could not be saved at this time")
        }
    }

    override fun handleMailError(error: Throwable) {
        showToast("Mail Could not be Sent at this Time. Please try later")
    }

    private fun getMembers() : List<String>{
        arrayList.clear();
        arrayList.add("Select Email")
        for (item in mailViewModel.getMembers()) {
            arrayList.add(item.email.toString())
        }

        return arrayList;
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
        to = parent.getItemAtPosition(pos) as String;
    }

    override fun onNothingSelected(parent: AdapterView<*>) {
        // Another interface callback
    }
}
