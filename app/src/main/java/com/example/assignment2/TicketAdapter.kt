package com.example.assignment2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TicketAdapter: RecyclerView.Adapter<TicketAdapter.TicketViewHolder>() {

    private var ticketList: ArrayList<TicketModel> = ArrayList()
    private var onClickItem: ((TicketModel) -> Unit)? = null
    private var onClickDeleteItem: ((TicketModel) -> Unit)? = null

    fun addItems(items: ArrayList<TicketModel>) {
        this.ticketList = items
        notifyDataSetChanged()
    }

    fun setOnClickItem(callback: (TicketModel) -> Unit) {
        this.onClickItem = callback
    }

    fun setOnClickDeleteItem(callback: (TicketModel) -> Unit) {
        this.onClickDeleteItem = callback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TicketViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.card_items_ticket, parent, false)
    )

    override fun onBindViewHolder(holder: TicketViewHolder, position: Int) {
        val ticket = ticketList[position]
        holder.bindView(ticket)
        holder.itemView.setOnClickListener { onClickItem?.invoke(ticket) }
        holder.deleteBTN.setOnClickListener { onClickDeleteItem?.invoke(ticket) }
    }

    override fun getItemCount(): Int {
        return ticketList.size
    }

    class TicketViewHolder(var view: View): RecyclerView.ViewHolder(view) {
        private var id = view.findViewById<TextView>(R.id.IDTV)
        private var fullName = view.findViewById<TextView>(R.id.FullNameTV)
        private var departure = view.findViewById<TextView>(R.id.DepartureTV)
        private var destination = view.findViewById<TextView>(R.id.DestinationTV)
        private var departureDateTime = view.findViewById<TextView>(R.id.DepartureDateTimeTV)
        private var flightClass = view.findViewById<TextView>(R.id.FlightClassTV)
        var deleteBTN = view.findViewById<Button>(R.id.DeleteBTN)

        fun bindView(ticket: TicketModel) {
            id.text = ticket.id.toString()
            fullName.text = ticket.fullName
            departure.text = ticket.departure
            destination.text = ticket.destination
            departureDateTime.text = ticket.departureDateTime
            flightClass.text = ticket.flightClass
        }
    }
}