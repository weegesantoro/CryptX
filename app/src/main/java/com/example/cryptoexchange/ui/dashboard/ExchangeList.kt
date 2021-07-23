package com.example.cryptoexchange.ui.dashboard

import android.app.AlertDialog
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cryptoexchange.R
import com.example.cryptoexchange.adapters.CurrencyListAdapter
import com.example.cryptoexchange.adapters.CurrencyListListener
import com.example.cryptoexchange.data.*
import com.example.cryptoexchange.databinding.ExchangeListFragmentBinding

class ExchangeList : Fragment() {

    companion object {
        fun newInstance() = ExchangeList()
    }

    private lateinit var viewModel: ExchangeListViewModel
    private var _binding: ExchangeListFragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // viewModel
        viewModel = ViewModelProvider(this).get(ExchangeListViewModel::class.java)

        // Binding root view
        _binding = ExchangeListFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root


        // Binding adapter to RecyclerView
        val currencyAdapter = CurrencyListAdapter(CurrencyListListener {
            // onClick ...
        })
        binding.currencyRecycler.adapter = currencyAdapter

        // Setting Recycler Properties
        binding.currencyRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.currencyRecycler.setHasFixedSize(true)

        // Set Adapter Data from live data
        viewModel.exchangeData.observe(viewLifecycleOwner, {
            if(it != null && it.isNotEmpty()){
                println("response data = ${it.size}")

                currencyAdapter.data = setImageSrc(it)
            }else{
                Toast.makeText(requireActivity(), "no data to show", Toast.LENGTH_SHORT).show()
            }
        })

        // set change button
        binding.currencyChangeBtn.setOnClickListener {
            showCurrencySelector()
        }

        // make first default call with Bitcoin
        viewModel.getRates("BTC")

        // animate view in
        binding.masterConstraint.apply {
            alpha = 0f
            animate().translationX(1000f).withEndAction {
                animate().apply {
                    duration = 1000
                    alpha(1f)
                    translationX(-1f)
                }
            }

        }


        return root
    }

    private fun setImageSrc(list: List<Data>): List<Data> {

        for(item in list){
            val resID = requireActivity().resources.getIdentifier(
                item.code?.lowercase(), "drawable",
                requireActivity().packageName
            )
            item.iconId = resID
            println("item.iconId = resID ... resID = $resID")
        }
        return list
    }

    private fun showCurrencySelector() {

        binding.apply {

            // animate and show - overlay
            popupOverlay.apply {
                alpha = 0f
                visibility = View.VISIBLE
                animate().apply {
                    duration = 100
                    alpha(1f)
                }
            }
            // animate and show - prompt
            currencySelector.apply {
                alpha = 0f
                visibility = View.VISIBLE
                animate().apply {
                    duration = 100
                    alpha(1f)
                }
            }


            // set button listeners
            btcButton.setOnClickListener {
                onClick(BtcTheme, btcButton, currencyChangeBtn)
            }
            bchButton.setOnClickListener {
                onClick(BchTheme, bchButton, currencyChangeBtn)
            }
            ethButton.setOnClickListener {
                onClick(EthTheme, ethButton, currencyChangeBtn)
            }
            xrpButton.setOnClickListener {
                onClick(XrpTheme, xrpButton, currencyChangeBtn)
            }
            dogeButton.setOnClickListener {
                onClick(DogeTheme, dogeButton, currencyChangeBtn)
            }

        }


    }

    private fun currencySelected(code: String) {

        when (code) {
            "BTC","BCH","ETH","XRP","DOGE" -> viewModel.getRates(code)
            else -> {
                println("nothing selected")
            }
        }

        // close popup & overlay with fade animation
        closeSelectionPopup()
    }

    private fun closeSelectionPopup() {
        // close popup with fade animation
        binding.apply {
            // animate and hide - overlay
            popupOverlay.animate().apply {
                duration = 100
                alpha(0f)
                popupOverlay.visibility = View.GONE
            }
            // animate and hide - prompt
            currencySelector.animate().apply {
                duration = 100
                alpha(0f)
                currencySelector.visibility = View.GONE
            }
        }
    }

    private fun moveHighlighter(btn: Button) {
        // move highlighter to show currently selected item
        val highlightLayout = binding.currencyHighlighter.layoutParams as ConstraintLayout.LayoutParams // btn is a View here
        highlightLayout.topToTop = btn.id // resource ID of new parent field
        highlightLayout.bottomToBottom = btn.id // resource ID of new parent field
        binding.currencyHighlighter.layoutParams = highlightLayout
    }


    private fun onClick(coinTheme: CoinTheme, button: Button, currencyChangeBtn: Button) {
        currencySelected(coinTheme.id)
        moveHighlighter(button)
        currencyChangeBtn.text = coinTheme.displayName
        context?.let {
            currencyChangeBtn.backgroundTintList =
                ColorStateList.valueOf(ContextCompat.getColor(it, coinTheme.color))
        }
    }





}