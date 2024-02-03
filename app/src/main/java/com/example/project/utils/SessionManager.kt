import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.project.MyApp
import com.example.project.R
import com.example.project.dataclasses.login.LoginResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object SessionManager {
    private const val ACCESS_TOKEN = "access_token"
    private const val REFRESH_TOKEN = "refresh_token"

    private val _isAuthenticated: MutableLiveData<Boolean> = MutableLiveData<Boolean>().apply {
        value = false
    }
    val isAuthenticated: LiveData<Boolean> = _isAuthenticated

    /**
     * Function to save session tokens
     */
    fun saveAuthSession(sessionInfo: LoginResponse) {
        saveString(ACCESS_TOKEN, sessionInfo.accessToken)
        saveString(REFRESH_TOKEN, sessionInfo.refreshToken)
        _isAuthenticated.value = true
    }

    /**
     * Function to fetch access token
     */
    fun getAccessToken(): String? {
        return getString(ACCESS_TOKEN)
    }

    /**
     * Function to fetch refresh token
     */
    fun getRefreshToken(): String? {
        return getString(REFRESH_TOKEN)
    }

    /**
     * Function to check if there's a user currently logged in
     */
    fun isLoggedIn(): Boolean {
        val token = getAccessToken()
        GlobalScope.launch {
            withContext(Dispatchers.Main){
                _isAuthenticated.value = token != null
            }
        }
        return token != null
    }

    /**
     * Function to disconnect user & remove session tokens
     */
    fun disconnectUser(){
        val context = MyApp.getContext()
        val editor = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE).edit()
        editor.remove(ACCESS_TOKEN)
        editor.remove(REFRESH_TOKEN)
        editor.apply()

        GlobalScope.launch {
            withContext(Dispatchers.Main){
                _isAuthenticated.value = false
            }
        }
    }

    private fun saveString(key: String, value: String) {
        val context = MyApp.getContext()
        val prefs: SharedPreferences =
            context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putString(key, value)
        editor.apply()

    }

    private fun getString(key: String): String? {
        val context = MyApp.getContext()

        val prefs: SharedPreferences =
            context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)
        return prefs.getString(key, null)
    }
}