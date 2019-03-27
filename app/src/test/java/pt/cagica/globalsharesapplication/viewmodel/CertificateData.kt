package pt.cagica.globalsharesapplication.viewmodel

import pt.cagica.globalsharesapplication.api.data.MyCertificate
import pt.cagica.globalsharesapplication.api.data.Share
import java.util.*

internal object CertificateData {

    val certificateList: List<MyCertificate>
        get() {
            return Arrays.asList(
                MyCertificate(0, 100, Date(Date().time)),
                MyCertificate(1, 1000, Date(Date().time)),
                MyCertificate(2, 100, Date(Date().time)
            ))
        }

    val share: Share
        get() {
            return Share("ABC", "ABC", 2.50)
        }

    val sellShare: Boolean
        get() {
            return true
        }
}
