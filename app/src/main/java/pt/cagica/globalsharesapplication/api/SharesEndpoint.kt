package pt.cagica.globalsharesapplication.api

import io.reactivex.Completable
import io.reactivex.Single
import pt.cagica.globalsharesapplication.api.data.MyCertificate
import pt.cagica.globalsharesapplication.api.data.Share
import pt.cagica.globalsharesapplication.utils.Constants
import retrofit2.http.*


interface SharesEndpoint {

    @GET(Constants.ENDPOINT_SHARES)
    fun getCertificatesList(): Single<List<MyCertificate>>

    @GET(Constants.ENDPOINT_SHARE_PRICE)
    fun getShare(): Single<Share>

    @FormUrlEncoded
    @POST
    fun sellShare(@Url url: String,
                  @Field("certificateId") certificateId : Int,
                  @Field("numberOfShares") numberOfShares : Int)
            : Completable
}
