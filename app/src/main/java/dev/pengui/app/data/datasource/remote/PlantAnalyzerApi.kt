import android.net.Uri
import retrofit2.Retrofit

interface PlantAnalyzerApi {
    suspend fun analyze(imageUri: Uri): PlantAnalyzerApi
}

class PlantIdApi(
    private val retrofit: Retrofit
)
//    : PlantAnalyzerApi {
//    override suspend fun analyze(imageUri: Uri): PlantAnalyzerApi {
//        return
////        val response = retrofit.create(PlantIdService::class.java)
////            .analyzePlant(File(imageUri.path)) // Convert URI to file
////
////        return response.toDomainModel()
//    }
//}