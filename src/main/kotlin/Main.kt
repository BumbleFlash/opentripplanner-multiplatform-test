import kotlinx.coroutines.delay

suspend fun main() {
    Api().call()
    delay(10000)
}