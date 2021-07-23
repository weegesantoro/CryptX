package com.example.cryptoexchange.ui.onboarding

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.cryptoexchange.R
import com.example.cryptoexchange.databinding.LaunchScreenFragmentBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LaunchScreen : Fragment() {

    companion object {
        fun newInstance() = LaunchScreen()
    }

    private lateinit var viewModel: LaunchScreenViewModel

    private var _binding: LaunchScreenFragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // viewModel
        viewModel = ViewModelProvider(this).get(LaunchScreenViewModel::class.java)

        // Binding root view
        _binding = LaunchScreenFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.apply {

            // pre-set alphas and visibility
            cryptxLogo.apply {
                alpha = 0f
                visibility = View.VISIBLE
                animate().translationX(1000f)
            }
            cryptyxMark.apply {
                alpha = 0f
                visibility = View.VISIBLE
            }

            // animate
            cryptyxMark.animate().apply {
                duration = 2000
                alpha(1f)
            }.withEndAction {
                cryptyxMark.animate().apply {
                    duration = 1000
                    alpha(0f)
                    translationX(-1500f)
                }.withEndAction {
                    cryptxLogo.animate().apply {
                        duration = 1000
                        translationX(-1f)
                        alpha(1f)
                    }.withEndAction {

                        CoroutineScope(Dispatchers.Main).launch {
                            delay(2000)
                            cryptxLogo.animate().apply {
                                duration = 1000
                                alpha(0f)
                                translationX(-1500f)
                            }.withEndAction {
                                findNavController().navigate(R.id.action_launchScreen_to_exchangeList)
                            }
                        }

                    }
                }

            }

        }




        return root
    }


}