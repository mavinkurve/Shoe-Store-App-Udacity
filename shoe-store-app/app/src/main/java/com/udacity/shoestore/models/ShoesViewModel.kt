package com.udacity.shoestore.models

import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.R
import com.udacity.shoestore.models.Shoe
import timber.log.Timber

class ShoesViewModel : ViewModel() {

    private val _shoeList = MutableLiveData<MutableList<Shoe>>()

    // internal storage for shoes list
    val shoeList: LiveData<MutableList<Shoe>>
            get() = _shoeList

    // shoe model properties
    val name = MutableLiveData("")
    val company = MutableLiveData("")
    val size = MutableLiveData("")
    val description = MutableLiveData("")

    // button handlers
    private val _save: MutableLiveData<Boolean> = MutableLiveData(false)
    val save: LiveData<Boolean>
        get() = _save

    private val _canceled: MutableLiveData<Boolean> = MutableLiveData(false)
    val cancel: LiveData<Boolean>
        get() = _canceled

    private val _invalidFormData: MutableLiveData<Boolean> = MutableLiveData(false)
    val invalidFormData: LiveData<Boolean>
        get() = _invalidFormData

    init {
        Timber.i("Shoes View Model created!")
        //initialize shoe list with some dummy data
        createDummyShoes()
    }

    private fun addShoe(shoe: Shoe) {
        Timber.d("Adding ${shoe.name} to shoe list")
        _shoeList.value?.add(shoe)
    }

    fun onClickSave() {
        if (validateFormFields()) {
            Timber.d("Saving shoe")
            addShoe(Shoe(name.value.orEmpty(),size.value.orEmpty(),company.value.orEmpty(),
                description.value.orEmpty()))
            _save.value = true
        }
        else {
            Timber.d("Setting invalid form data to be true")
            _invalidFormData.value = true
        }
    }

    private fun validateFormFields():Boolean {
       if (name?.value.isNullOrBlank() ||
           company?.value.isNullOrBlank() ||
           description?.value.isNullOrBlank() ||
           size?.value.isNullOrBlank()) {
               Timber.d("Name: ${name.value.orEmpty()} ")
           Timber.d("Company: ${company.value.orEmpty()} ")
           Timber.d("Size: ${size.value.orEmpty()} ")
           Timber.d("Description: ${description.value.orEmpty()} ")
               Timber.d("Form data invalid")
           return false
       }
        Timber.d("Form data is valid")
        return true
    }

    fun onSaveComplete() {
        _save.value = false
        _invalidFormData.value = false
    }

    fun onClickCancel() {
        _canceled.value = true
    }

    fun onCancelComplete() {
        _canceled.value = false
        _invalidFormData.value = false
    }

    private fun createDummyShoes() {
        Timber.d("Adding 3 fake shoes to the list")
        _shoeList.value = arrayListOf()
        (_shoeList.value as ArrayList<Shoe>).add(
            Shoe(
                "Jordan Delta 2",
                "8.5",
                "Nike",
                "The Jordan " +
                        "Delta 2 offers a fresh, fearless take on the features you want: durability, comfort " +
                        "and an attitude that's Jordan to the core. We updated design lines and swapped out " +
                        "some components, but the idea is the same as the first Delta.",
                listOf("Image1")
            )
        )

        (_shoeList.value as ArrayList<Shoe>).add(
            Shoe(
                "Bardo Slipper",
                "7.0",
                "Jimmy Choo",
                "Dress in comfort with the BARDO Slipper. Inspired by classic ballet pointe " +
                        "slippers, our pair is crafted in Italy from gathered pink violet satin and is " +
                        "completed with a coordinating silk eye mask. The perfect gift or go-to slipper " +
                        "for at-home glamour.",
                listOf("Image1")
            )
        )
        (_shoeList.value as ArrayList<Shoe>).add(
            Shoe(
                "Susa Strappy Leather Slide Sandals",
                "6.5",
                "Manolo Blahnik",
                "Thong sandal in leather.\n" +
                        "2\" covered kitten heel.\n" +
                        "Crisscross toe strap.\n" +
                        "Braided band upper.\n" +
                        "Leather lining and sole.\n" +
                        "Slide style.\n" +
                        "\"Susa\" is made in Italy.",
                listOf("Image1")
            )
        )
    }
}