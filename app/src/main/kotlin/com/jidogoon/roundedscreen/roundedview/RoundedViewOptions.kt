package com.jidogoon.roundedscreen.roundedview

import android.os.Parcel
import android.os.Parcelable

class RoundedViewOptions : Parcelable {
    var topLeft: Boolean = true
    var topRight: Boolean = true
    var bottomLeft: Boolean = true
    var bottomRight: Boolean = true
    var size: Int = 30

    companion object {
        @JvmField val CREATOR: Parcelable.Creator<RoundedViewOptions> = object : Parcelable.Creator<RoundedViewOptions> {
            override fun createFromParcel(source: Parcel): RoundedViewOptions = RoundedViewOptions(source)
            override fun newArray(size: Int): Array<RoundedViewOptions?> = arrayOfNulls(size)
        }
    }
    constructor()

    constructor(source: Parcel) : this() {
        topLeft = source.readInt() != 0
        topRight = source.readInt() != 0
        bottomLeft = source.readInt() != 0
        bottomRight = source.readInt() != 0
    }

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(if (topLeft) 1 else 0)
        dest.writeInt(if (topRight) 1 else 0)
        dest.writeInt(if (bottomLeft) 1 else 0)
        dest.writeInt(if (bottomRight) 1 else 0)
    }
}