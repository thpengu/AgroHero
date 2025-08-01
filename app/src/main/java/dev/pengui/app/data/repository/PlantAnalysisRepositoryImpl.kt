//package dev.pengui.app.data.repository
//
//class PlantAnalysisRepositoryImpl(
//    private val plantIdApi: PlantIdApi,
//    private val context: Context
//) : PlantAnalysisRepository {
//
//    override suspend fun analyzePlant(imageUri: Uri): PlantAnalysis {
//        val file = context.contentResolver.openInputStream(imageUri)?.use { inputStream ->
//            val file = File(context.cacheDir, "temp_plant_image.jpg")
//            file.outputStream().use { outputStream ->
//                inputStream.copyTo(outputStream)
//            }
//            file
//        } ?: throw IOException("Could not create temp file")
//
//        val requestFile = file.asRequestBody("image/jpeg".toMediaType())
//        val imagePart = MultipartBody.Part.createFormData("images", file.name, requestFile)
//
//        val response = plantIdApi.identifyPlant(imagePart)
//        if (!response.isSuccessful) {
//            throw IOException("API request failed: ${response.message()}")
//        }
//
//        return response.body()?.toPlantAnalysis() ?: throw IOException("Empty response")
//    }
//
//    private fun PlantIdResponse.toPlantAnalysis(): PlantAnalysis {
//        return PlantAnalysis(
//            isHealthy = suggestions.first().healthy ?: false,
//            diseases = suggestions.flatMap { it.details?.diseases ?: emptyList() },
//            treatmentSuggestions = suggestions.flatMap {
//                it.details?.treatment?.prevention ?: emptyList()
//            },
//            confidence = suggestions.first().probability ?: 0f
//        )
//    }
//}