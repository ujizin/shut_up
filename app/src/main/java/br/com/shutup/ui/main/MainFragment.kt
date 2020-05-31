package br.com.shutup.ui.main

import android.widget.Toast
import androidx.lifecycle.Observer
import br.com.shutup.R
import br.com.shutup.base.BaseFragment
import br.com.shutup.databinding.MainFragmentBinding
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MainFragment : BaseFragment<MainFragmentBinding, MainViewModel>() {

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun viewModelInstance() = getViewModel<MainViewModel>()

    override fun layoutRes() = R.layout.main_fragment

    override fun init() {
        viewModel.fetchVolume()
        binding.viewModel = viewModel
        binding.sbVolume.setOnSeekBarChangeListener(viewModel.seekBarListener)
        viewModel.errorMessage.observe(this, Observer {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        })

        binding.run {
            listOf(btEnabled, btDisabled).forEach {
                it.setOnClickListener(viewModel)
            }
        }
        binding.btEnabled.setOnClickListener(viewModel)
        binding.btDisabled.setOnClickListener(viewModel)

        viewModel.isLockDevice.observe(this, Observer { enabled ->
            viewModel.obsIsLockDevice.set(enabled)
            binding.sbVolume.isEnabled = !enabled
        })
    }

}
