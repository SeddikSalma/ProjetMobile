import android.content.Context
import android.content.SharedPreferences
import com.example.project.MyApp
import com.example.project.R
import com.example.project.dataclasses.login.LoginResponse

object SessionManager {
    private const val ACCESS_TOKEN = "access_token"
    private const val REFRESH_TOKEN = "refresh_token"

    /**
     * Function to save session tokens
     */
    fun saveAuthSession(sessionInfo: LoginResponse) {
        saveString(ACCESS_TOKEN, sessionInfo.accessToken)
        saveString(REFRESH_TOKEN, sessionInfo.refreshToken)
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