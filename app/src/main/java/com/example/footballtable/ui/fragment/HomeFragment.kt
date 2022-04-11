package com.example.footballtable.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.footballtable.R
import com.example.footballtable.adapters.StandingsRvAdapter
import com.example.footballtable.databinding.FragmentHomeBinding
import com.example.footballtable.models.club.FootballClub
import com.example.footballtable.network.ApiClient
import com.example.footballtable.repositories.FootballRepository
import com.example.footballtable.utils.ItemFootballClubResource
import com.example.footballtable.utils.NetworkHelper
import com.example.footballtable.viewmodel.FootballViewModel
import com.example.footballtable.viewmodel.ViewModelFactory
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class HomeFragment : Fragment(), CoroutineScope {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var networkHelper: NetworkHelper
    private lateinit var footballViewModel: FootballViewModel
    private lateinit var footballRepository: FootballRepository
    private lateinit var standingsRvAdapter: StandingsRvAdapter
    private lateinit var footballClubList: ArrayList<FootballClub>
    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        networkHelper = NetworkHelper(requireContext())
        footballRepository = FootballRepository(ApiClient.apiService)
        footballClubList = ArrayList()
        setupViewModel()
        setupAds()
        getFootballData()
        return binding.root
    }

    private fun getFootballData() {
        launch {
            footballViewModel.fetchFootball()
                .collect {
                    when (it) {
                        is ItemFootballClubResource.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                            binding.rv.visibility = View.GONE
                        }
                        is ItemFootballClubResource.Error -> {
                            binding.progressBar.visibility = View.GONE
                            binding.rv.visibility = View.VISIBLE
                        }
                        is ItemFootballClubResource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            binding.rv.visibility = View.VISIBLE
                            footballClubList = ArrayList(it.list)
                            standingsRvAdapter = StandingsRvAdapter(footballClubList,
                                object : StandingsRvAdapter.OnItemClickListener {
                                    override fun onItemClick(footballClub: FootballClub) {
                                        val bundle = Bundle()
                                        bundle.putSerializable("football", footballClub)
                                        findNavController().navigate(R.id.leagueFragment, bundle)
                                    }

                                })
                        }
                    }
                }
        }
    }
//
//                        is ItemFootballClubResource.Success -> {
//                            binding.progressBar.visibility = View.GONE
//                            binding.rv.visibility = View.VISIBLE
//                            footballClubList = ArrayList(it.list)
//                            standingsRvAdapter = StandingsRvAdapter(footballClubList,
//                                object : StandingsRvAdapter.OnItemClickListener {
//                                    override fun onItemClick(footballClub: FootballClub) {
//                                        val bundle = Bundle()
//                                        bundle.putSerializable("football", footballClub)
//                                        findNavController().navigate(R.id.leagueFragment, bundle)
//                                    }
//                                })
//                            binding.rv.adapter = standingsRvAdapter
//                        }
//                    }
//                }
//        }
//    }

    private fun setupAds() {
        MobileAds.initialize(binding.root.context) {}
        val adRequest = AdRequest.Builder().build()
        binding.adView.loadAd(adRequest)
    }

    private fun setupViewModel() {
        footballViewModel = ViewModelProvider(
            this,
            ViewModelFactory(footballRepository, networkHelper),
        )[FootballViewModel::class.java]
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
}