package com.mobile.fair.view.candidates

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobile.fair.R
import com.mobile.fair.utils.Resource
import com.mobile.fair.view.candidatesdetails.CandidateDetailsFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.candidates_list_fragment.*

@AndroidEntryPoint
class CandidateListFragment : Fragment(), CandidateAdapter.candidateItemListener {

    private val viewModel: CandidateListViewModel by viewModels()
    private lateinit var candidateAdapter: CandidateAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return inflater.inflate(R.layout.candidates_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObservers()
    }

    private fun setupRecyclerView() {
        candidateAdapter = CandidateAdapter(this)
        val layoutManager = LinearLayoutManager(requireContext())
        candidate_rv.layoutManager = layoutManager
        candidate_rv.adapter = candidateAdapter
    }

    private fun setupObservers() {
        viewModel.candidateList.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    progress_bar.visibility = View.GONE
                    if (!it.data.isNullOrEmpty()) candidateAdapter.setItems(ArrayList(it.data))
                }
                Resource.Status.ERROR ->
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()

                Resource.Status.LOADING ->
                    progress_bar.visibility = View.VISIBLE
            }
        })
    }

    override fun onClickCandidate(candidateId: String) {
        val bundle = bundleOf("cand_id" to candidateId)
        activity?.supportFragmentManager?.commit {
             add<CandidateDetailsFragment>(R.id.fragment_container_view, args = bundle)
            addToBackStack("")
        }
    }
}
