package com.esragungor.kotlinretrofit.service

import com.esragungor.kotlinretrofit.model.CryptoModel
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET

interface CryptoAPI  {
    @GET("prices?key=6f51f3ab29902a5f5a140f55395be944")
    fun getData():Observable<List<CryptoModel>>

}