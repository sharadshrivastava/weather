package com.test.app.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.AdapterView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.test.app.R
import com.test.app.data.network.model.Response
import com.test.app.data.network.wrapper.Resource
import com.test.app.databinding.HomeFragmentBinding
import com.test.app.data.common.Utils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.home_fragment.*

@AndroidEntryPoint
class HomeFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private val vm: HomeViewModel by viewModels()
    private lateinit var binding: HomeFragmentBinding
    private var searchType: SearchType = SearchType.CityName

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.home_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        searchSpinner.onItemSelectedListener = this
        binding.vm = vm
        setupSearch()
        manageRecentCardVisibility()
        recentCard.setOnClickListener {
            findNavController(this).navigate(HomeFragmentDirections.homeFragmentAction())
        }

        if (vm.data != null){
            setValues()
        }else{
            val args by navArgs<HomeFragmentArgs>()
            if (args.id != Utils.INVALID) {
                loadCity(args.id)
            }
        }
    }

    //If there is data in db then only show recent card.
    private fun manageRecentCardVisibility() {
        if (recentCard.visibility != View.VISIBLE) {
            vm.isDBEmpty().observe(viewLifecycleOwner, Observer {
                if (it != 0) {
                    recentCard.visibility = View.VISIBLE
                }
            })
        }
    }

    private fun hideKeyboard() {
        searchEt.onEditorAction(EditorInfo.IME_ACTION_DONE);
    }

    private fun setupSearch() {
        binding.submitBtn.setOnClickListener {
            hideKeyboard()
            when (searchType) {
                SearchType.CityName -> {
                    weatherByCity(searchEt.text.toString())
                }
                SearchType.ZipCode -> {
                    weatherByZipCode(searchEt.text.toString())
                }
            }
        }
    }

    private fun weatherByCity(cityName: String) = handleResponse(vm.weatherByCity(cityName))

    //Expecting, zipCode, country code. like 2000,AU.
    // UI checking is not done as of now.
    private fun weatherByZipCode(zipCode: String) = handleResponse(vm.weatherByZipCode(zipCode))

    private fun handleResponse(liveData: LiveData<Resource<Response>>) {
        liveData.observe(viewLifecycleOwner, Observer {
            when (it?.status) {
                Resource.Status.LOADING -> binding.isLoading = true
                Resource.Status.SUCCESS -> {
                    binding.isLoading = false
                    vm.postResponse(it.data)
                    postResponse()
                }
                else -> {
                    binding.isLoading = false
                    Snackbar.make(
                        binding.root,
                        it?.message ?: getString(R.string.network_error),
                        Snackbar.LENGTH_LONG
                    ).show()
                }
            }
        })
    }

    private fun postResponse() {
        setValues()
        saveCity()
    }

    private fun saveCity() {
        vm.saveCity(vm.data?.name, vm.data?.sys?.country).observe(viewLifecycleOwner, Observer {
            manageRecentCardVisibility()
        })
    }

    private fun setValues() {
        weatherCard.visibility = View.VISIBLE
        temperature.text = getString(R.string.temperature, vm.temperature(vm.data?.main?.temp))
        realFeel.text = getString(R.string.real_feel, vm.temperature(vm.data?.main?.feelsLike))
        tempMin.text = getString(R.string.min_temp, vm.temperature(vm.data?.main?.tempMin))
        tempMax.text = getString(R.string.max_temp, vm.temperature(vm.data?.main?.tempMax))
        humidity.text =
            getString(R.string.humidity, vm.data?.main?.humidity, getString(R.string.percent))
        windSpeed.text = getString(R.string.wind, vm.windSpeed(vm.data?.wind?.speed))
    }

    private fun loadCity(id: Int) {
        weatherCard.visibility = View.VISIBLE
        vm.getCityById(id).observe(viewLifecycleOwner, Observer {
            weatherByCity(it?.cityName + "," + it?.CountryName)
        })
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
        when (pos) {
            SearchType.CityName.pos -> {
                searchEt.text.clear()
                searchEt.hint = context?.getString(R.string.city_hint)
                searchType = SearchType.CityName
            }
            SearchType.ZipCode.pos -> {
                searchEt.text.clear()
                searchEt.hint = context?.getString(R.string.zip_hint)
                searchType = SearchType.ZipCode
            }
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}

    enum class SearchType(val pos: Int) {
        CityName(0),
        ZipCode(1)
    }
}