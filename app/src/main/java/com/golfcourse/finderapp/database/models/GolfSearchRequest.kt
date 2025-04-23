package com.golfcourse.finderapp.database.models

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity(tableName = "golfSearch")

data class GolfSearchRequest(
    @PrimaryKey @ColumnInfo(name = "id") @SerializedName("id") var id: Long = Date().time,
    @ColumnInfo(name = "name") @SerializedName("name") var name: String? = null,

    @ColumnInfo(name = "response") @SerializedName("response") var response: String? = null,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString(),
        parcel.readString(),
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(name)
        parcel.writeString(response)

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<GolfSearchRequest> {
        override fun createFromParcel(parcel: Parcel): GolfSearchRequest {
            return GolfSearchRequest(parcel)
        }

        override fun newArray(size: Int): Array<GolfSearchRequest?> {
            return arrayOfNulls(size)
        }
    }
}
