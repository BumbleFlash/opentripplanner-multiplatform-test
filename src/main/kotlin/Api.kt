import edu.usf.cutr.otp.plan.api.PlanApi
import edu.usf.cutr.otp.plan.model.RequestParameters
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class Api: CoroutineScope {
    private lateinit var job: Job

    override val coroutineContext: CoroutineContext
        get() = job + Main

    fun call() {
        job = Job()
        testApi()
    }

    private fun testApi() {
        val requestParameters = RequestParameters(
            fromPlace = latLong(41.84712, -87.64678),
            toPlace = latLong(41.84584, -87.65214),
            arriveBy = "false")

        val planApi = PlanApi("http://localhost:8080/otp/routers/default/plan",  requestParameters)
        planApi.getPlan(
            success = { launch (Main) { println(it) } },
            failure = ::handleError
        )
    }

    private fun latLong(lat: Double, long: Double): String {
        return "$lat,$long"
    }

    private fun handleError(ex: Throwable?) {
        ex?.printStackTrace()
    }
}
