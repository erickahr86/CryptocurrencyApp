package com.plcoding.cryptocurrencyappyt.domain.use_case.get_coin


import com.plcoding.cryptocurrencyappyt.common.Resource
import com.plcoding.cryptocurrencyappyt.data.remote.dto.toCoin
import com.plcoding.cryptocurrencyappyt.data.remote.dto.toCoinDetail
import com.plcoding.cryptocurrencyappyt.domain.model.Coin
import com.plcoding.cryptocurrencyappyt.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(
    private val repository: CoinRepository

    ) {

    operator fun invoke( coinId: String ): Flow<Resource> = flow {
        try {
            emit(Resource.Loading())

            val coin = repository.getCoinById( coinId ).toCoinDetail()
            emit( Resource.Success( coin ))

        }catch ( e: HttpException){

            emit(Resource.Error( e.localizedMessage ?: "An unexpected error occured"))


        }catch ( e: IOException ){

            emit( Resource.Error( "Couldn't reach server"))

        }
    }
}