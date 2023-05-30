package com.example.assignment2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Booking : AppCompatActivity() {

    private lateinit var handler: SQLiteHelper2
    private lateinit var fullNameED: EditText
    private lateinit var departureED: EditText
    private lateinit var destinationED: EditText
    private lateinit var departureDateTimeED: EditText
    private lateinit var flag: String
    private lateinit var flightClassSP: Spinner
    private lateinit var options: Array<String>
    private lateinit var addBTN: Button
    private lateinit var viewBTN: Button
    private lateinit var updateBTN: Button
    private lateinit var recyclerView: RecyclerView
    private var adapter: TicketAdapter? = null
    private var ticket: TicketModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.booking)

        handler = SQLiteHelper2(this)
        initView()
        initRecyclerView()

        flightClassSP.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, options)
        flightClassSP.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                flag = options.get(p2)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        addBTN.setOnClickListener { addTicket() }
        viewBTN.setOnClickListener { getTickets() }
        updateBTN.setOnClickListener { updateTicket() }


        adapter?.setOnClickItem {
            Toast.makeText(this, it.fullName, Toast.LENGTH_SHORT).show()
            fullNameED.setText(it.fullName)
            departureED.setText(it.departure)
            destinationED.setText(it.destination)
            departureDateTimeED.setText(it.departureDateTime)
            val adapter: ArrayAdapter<CharSequence> = ArrayAdapter(this, android.R.layout.simple_spinner_item, options)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            flightClassSP.adapter = adapter
            val selection = it.flightClass
            val spinnerPosition: Int = adapter.getPosition(selection)
            flightClassSP.setSelection(spinnerPosition)
            ticket = it
        }

        adapter?.setOnClickDeleteItem {
            deleteTicket(it.id)
        }
    }

    private fun initView() {
        fullNameED = findViewById(R.id.FullNameED)
        departureED = findViewById(R.id.DepartureED)
        destinationED = findViewById(R.id.DestinationED)
        departureDateTimeED = findViewById(R.id.DepartureDateED)
        flag = ""
        flightClassSP = findViewById(R.id.ClassSP)
        options = arrayOf("", "Economy", "First Class")
        addBTN = findViewById(R.id.AddBTN)
        viewBTN = findViewById(R.id.ViewBTN)
        updateBTN = findViewById(R.id.UpdateBTN)
        recyclerView = findViewById(R.id.RecyclerView)
    }

    private fun initRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = TicketAdapter()
        recyclerView.adapter = adapter
    }

    private fun addTicket() {
        val fullName = fullNameED.text.toString()
        val departure = departureED.text.toString()
        val destination = destinationED.text.toString()
        val departureDateTime = departureDateTimeED.text.toString()
        val flightClass = flag

        if (fullName.isEmpty() || departure.isEmpty() || destination.isEmpty() || departureDateTime.isEmpty() || flightClass == "") {
            Toast.makeText(this, "Please enter required Fields!", Toast.LENGTH_SHORT).show()
        } else {
            val status = handler.insertTicket(fullName = fullName, departure = departure, destination = destination, departureDateTime = departureDateTime, flightClass = flightClass)
            // Check insert successful or not
            if (status > -1) {
                Toast.makeText(this, "Ticket Added!", Toast.LENGTH_SHORT).show()
                clearEditTexts()
                getTickets()
            } else {
                Toast.makeText(this, "Ticket not saved!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateTicket() {
        val fullName = fullNameED.text.toString()
        val departure = departureED.text.toString()
        val destination = destinationED.text.toString()
        val departureDateTime = departureDateTimeED.text.toString()
        val flightClass = flag

        if (fullName == ticket?.fullName && departure == ticket?.departure && destination == ticket?.destination && departureDateTime == ticket?.departureDateTime && flightClass == ticket?.flightClass) {
            Toast.makeText(this, "Ticket not changed!", Toast.LENGTH_SHORT).show()
            return
        }

        if (ticket == null) {
            return
        }

        val ticket = TicketModel(id = ticket!!.id, fullName = fullName, departure = departure, destination = destination, departureDateTime = departureDateTime, flightClass = flightClass)
        val status = handler.updateTicket(ticket)

        if (status > -1) {
            clearEditTexts()
            getTickets()
        } else {
            Toast.makeText(this, "Update failed, try again..", Toast.LENGTH_SHORT).show()
        }
    }

    private fun deleteTicket(id: Int) {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Are you sure you want to delete this ticket?")
        builder.setCancelable(true)
        builder.setPositiveButton("Yes") { dialog, _ ->
            handler.deleteTicketById(id)
            getTickets()
            dialog.dismiss()
        }
        builder.setNegativeButton("No") { dialog, _ -> dialog.dismiss() }

        val alert = builder.create()
        alert.show()
    }

    private fun clearEditTexts() {
        fullNameED.setText("")
        departureED.setText("")
        destinationED.setText("")
        departureDateTimeED.setText("")
        val adapter: ArrayAdapter<CharSequence> = ArrayAdapter(this, android.R.layout.simple_spinner_item, options)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        flightClassSP.adapter = adapter
        val selection = ""
        val spinnerPosition: Int = adapter.getPosition(selection)
        flightClassSP.setSelection(spinnerPosition)
        fullNameED.requestFocus()
    }

    private fun getTickets() {
        val ticketList = handler.getAllTickets()
        Log.e("Size", "${ticketList.size}")

        adapter?.addItems(ticketList)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.flight_menu, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.Rate -> {
                val rate_dialog = RateUsClass()
                rate_dialog.show(supportFragmentManager, "Rate Us Custom Dialog")
            }
            R.id.FirstClassDetails -> {
                val first_class_dialog = FirstClassDetailsClass()
                first_class_dialog.show(
                    supportFragmentManager,
                    "First Class Flight Details Custom Dialog"
                )
            }
            R.id.EconomyClassDetails -> {
                val economy_class_dialog = EconomyClassDetailsClass()
                economy_class_dialog.show(
                    supportFragmentManager,
                    "Economy Class Flight Details Custom Dialog"
                )
            }
            R.id.Location -> {
                val intent = Intent(this, Location::class.java)
                startActivity(intent)
            }
            R.id.ContactUs -> {
                val intent2 = Intent(this, ContactUs::class.java)
                startActivity(intent2)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}