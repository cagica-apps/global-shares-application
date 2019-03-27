package pt.cagica.globalsharesapplication.api.data

import com.google.gson.annotations.SerializedName
import java.sql.Timestamp
import java.text.SimpleDateFormat

import java.util.*

class MyCertificate(certificateId: Int, numberOfShares: Int, issuedDate: Date?) {
    var position: Int = 0

    @SerializedName("certificateId")
    private var certificateId: Int = 0

    @SerializedName("numberOfShares")
    private var numberOfShares: Int = 0

    @SerializedName("issuedDate")
    private var issuedDate: Date? = null

    init {
        this.certificateId = certificateId
        this.numberOfShares = numberOfShares
        this.issuedDate = issuedDate
    }

    fun getCertificateIdString(): String {
        return certificateId.toString()
    }

    fun getCertificateId(): Int {
        return certificateId
    }

    fun getNumberOfShares(): String {
        return numberOfShares.toString()
    }

    fun getIssuedDate(): String {
        return issuedDate.toString()
    }

    fun getLocaleIssuedDate(): String? {
        val sdf = SimpleDateFormat("dd/MM/yy HH:mm:ss", Locale.getDefault())
        return sdf.format(issuedDate)
    }
}
