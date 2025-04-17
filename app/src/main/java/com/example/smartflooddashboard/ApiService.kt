import retrofit2.Call
import retrofit2.http.GET

data class SensorData(
    val timestamp: String,
    val water_level: Float,
    val temperature: Float,
    val humidity: Float
)

interface ApiService {
    @GET("api/data")
    fun getData(): Call<Map<String, List<SensorData>>>
}