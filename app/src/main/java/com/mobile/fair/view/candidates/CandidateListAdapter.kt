package com.mobile.fair.view.candidates

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mobile.fair.R
import com.mobile.fair.data.entities.Candidate
import kotlinx.android.synthetic.main.item_candidate.view.*

class CandidateAdapter(private val listener: candidateItemListener) :
    RecyclerView.Adapter<CandidateViewHolder>() {

    interface candidateItemListener {
        fun onClickCandidate(candidateId: String)
    }

    private val items = ArrayList<Candidate>()

    fun setItems(items: ArrayList<Candidate>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CandidateViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_candidate, parent, false)

        return CandidateViewHolder(view, listener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: CandidateViewHolder, position: Int) =
        holder.bind(items[position])
}

class CandidateViewHolder(
    private val rowItemView: View,
    private val listener: CandidateAdapter.candidateItemListener
) : RecyclerView.ViewHolder(rowItemView),
    View.OnClickListener {

    private lateinit var candidate: Candidate

    init {
        rowItemView.setOnClickListener(this)
    }

    fun bind(item: Candidate) {
        this.candidate = item
        rowItemView.name.text = "${
            item.title + "." + item.firstName + " " + item.lastName
        }"
        rowItemView.candidate_id.text = "Id: " + item.id
        Glide.with(rowItemView)
            .load(item.picture)
            .into(rowItemView.image)
    }

    override fun onClick(v: View?) {
        listener.onClickCandidate(this.candidate.id)
    }
}

