package com.mobile.fair.view.candidatesdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.mobile.fair.R
import com.mobile.fair.data.entities.Candidate
import com.mobile.fair.utils.Resource.Status.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.candidate_detail_fragment.*

@AndroidEntryPoint
class CandidateDetailsFragment : Fragment() {

    private val viewModel: CandidateDetailViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.candidate_detail_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getString("cand_id")?.let { viewModel.start(it) }
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.candidateDetails.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                SUCCESS -> {
                    bindCandidate(it.data!!)
                    progress_bar.visibility = View.GONE
                    candidate_cl.visibility = View.VISIBLE
                }

                ERROR ->
                    Toast.makeText(activity, it.message, Toast.LENGTH_SHORT).show()

                LOADING -> {
                    progress_bar.visibility = View.VISIBLE
                    candidate_cl.visibility = View.GONE
                }
            }
        })
    }

    private fun bindCandidate(candidate: Candidate) {
        name_text.text = candidate.title + "." + candidate.firstName + " " + candidate.lastName
        gender_text.text = candidate.gender
        dob_text.text = candidate.dateOfBirth
        email_text.text = candidate.email

        street_text.text = candidate.location?.street
        city_text.text = candidate.location?.city
        state_text.text = candidate.location?.state
        country_text.text = candidate.location?.country
        time_zone_text.text = candidate.location?.timezone
        Glide.with(this)
            .load(candidate.picture)
            .transform(CircleCrop())
            .into(image)
    }
}
