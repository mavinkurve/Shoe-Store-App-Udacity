package com.udacity.shoestore.screen.shoelist

import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe
import timber.log.Timber

class ShoeListViewModel : ViewModel() {

    // internal storage for shoes list
    var shoeList: MutableCollection<Shoe> = ArrayList()

    init {
        Timber.i("ShoeListViewModel created!")
        //initialize shoe list with some dummy data
        createDummyShoes()
    }

    private fun createDummyShoes() {
        shoeList.add(
            Shoe(
                "Jordan Delta 2", 8.5, "Nike", "The Jordan " +
                        "Delta 2 offers a fresh, fearless take on the features you want: durability, comfort " +
                        "and an attitude that's Jordan to the core. We updated design lines and swapped out " +
                        "some components, but the idea is the same as the first Delta.",
                listOf("Image 1", "Image 2")
            )
        )
        shoeList.add(
            Shoe(
                "Bardo Slipper",
                7.0,
                "Jimmy Choo",
                "Dress in comfort with the BARDO Slipper. Inspired by classic ballet pointe " +
                        "slippers, our pair is crafted in Italy from gathered pink violet satin and is " +
                        "completed with a coordinating silk eye mask. The perfect gift or go-to slipper " +
                        "for at-home glamour.",
                listOf("Image 1", "Image 2")
            )
        )
        shoeList.add(
            Shoe(
                "Susa Strappy Leather Slide Sandals",
                6.5,
                "Manolo Blahnik",
                "Thong sandal in leather.\n" +
                        "2\" covered kitten heel.\n" +
                        "Crisscross toe strap.\n" +
                        "Braided band upper.\n" +
                        "Leather lining and sole.\n" +
                        "Slide style.\n" +
                        "\"Susa\" is made in Italy.",
                listOf("Image 1", "Image 2")
            )
        )
        shoeList.add(
            Shoe(
                "Wayde Wedge Booties\n",
                10.5,
                "Nine West",
                "The classic Chelsea boot gets a trendy utilitarian update with the Wayde " +
                        "slip on bootie. The seamless hidden wedge give this ankle boot some height and " +
                        "the lug sole adds some texture to this classic silhouette.",
                listOf("Image 1", "Image 2")
            )
        )


    }
}