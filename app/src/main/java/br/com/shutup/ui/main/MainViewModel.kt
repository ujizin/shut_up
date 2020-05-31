package br.com.shutup.ui.main

import android.view.View
import android.widget.SeekBar
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import br.com.shutup.R
import br.com.shutup.base.BaseViewModel
import br.com.shutup.data.repositories.sound.SoundRepositoryContract
import kotlinx.coroutines.*

class MainViewModel(private val soundRepository: SoundRepositoryContract) : BaseViewModel(), View.OnClickListener {

    val volume = ObservableField(0)
    val isLoading = MutableLiveData<Boolean>()
    val ipConfig = ObservableField<String>()
    val errorMessage = MutableLiveData<String>()
    val isLockDevice = MutableLiveData(false)
    val obsIsLockDevice = ObservableField(isLockDevice.value == true)

    private var oldIpConfig: String? = ipConfig.get()

    private val volumeThrowable = getExceptionHandler {
        errorMessage.value = it.message
        isLoading.value = false
    }

    fun fetchVolume() {
        ipConfig.set(soundRepository.getIpConfig())
        viewModelScope.launch(volumeThrowable) {
            isLoading.postValue(true)
            val sound = soundRepository.getVolume()
            withContext(Dispatchers.Main) {
                volume.run { set(sound.volume) }
                isLoading.value = false
            }
        }
    }

    private fun setVolume(volume: Int) {
        this.volume.set(volume)
        viewModelScope.launch(volumeThrowable) {
            isLoading.postValue(true)
            saveIpConfig()
            soundRepository.setVolume(volume)
            withContext(Dispatchers.Main) {
                isLoading.value = false
            }
        }
    }

    private fun saveIpConfig() {
        val ipConfig = ipConfig.get() ?: ""
        if(oldIpConfig != ipConfig) {
            oldIpConfig = ipConfig
            soundRepository.setIpConfig(ipConfig)
        }
    }


    val seekBarListener = object : SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) =
            setVolume(progress)

        override fun onStartTrackingTouch(seekBar: SeekBar?) {}

        override fun onStopTrackingTouch(seekBar: SeekBar?) = fetchVolume()
    }

    override fun onClick(view: View?) {
        when(view?.id) {
            R.id.btDisabled -> unlockDevice()
            R.id.btEnabled -> lockDevice()
        }
    }

    private fun unlockDevice() {
        viewModelScope.launch(volumeThrowable) {
            soundRepository.unlockDevice()
            withContext(Dispatchers.Main) {
                isLockDevice.value = false
            }
        }
    }

    private fun lockDevice() {
        viewModelScope.launch(volumeThrowable) {
            soundRepository.lockDevice()
            withContext(Dispatchers.Main) {
                isLockDevice.value = true
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        saveIpConfig()
    }
}
