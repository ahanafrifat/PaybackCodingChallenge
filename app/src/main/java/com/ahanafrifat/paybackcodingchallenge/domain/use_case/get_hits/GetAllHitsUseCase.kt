package com.ahanafrifat.paybackcodingchallenge.domain.use_case.get_hits

import androidx.paging.PagingData
import com.ahanafrifat.paybackcodingchallenge.data.repository.Repository
import com.ahanafrifat.paybackcodingchallenge.domain.model.Hit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllHitsUseCase @Inject constructor(
    private val repository: Repository
) {

//    operator fun invoke(query: String?): Flow<Resource<List<Hit>>> = flow {
//        try {
//            emit(Resource.Loading<List<Hit>>())
//            val hits = repository.getHits(query).hitDtos.map { it.toHit() }
//            emit(Resource.Success<List<Hit>>(hits))
//        } catch (e: HttpException) {
//            emit(Resource.Error<List<Hit>>(e.message ?: "An unexpected error occured"))
//        } catch (e: IOException) {
//            emit(Resource.Error<List<Hit>>(e.message ?:"Couldn't reach server. Check your internet connection."))
//        }
//    }

    operator fun invoke(query: String?): Flow<PagingData<Hit>> {
        return repository.getAllHits(query)
    }

}