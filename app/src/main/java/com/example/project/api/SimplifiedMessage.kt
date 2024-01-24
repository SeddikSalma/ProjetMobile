package com.example.project.api

import org.json.JSONException
import org.json.JSONObject

object SimplifiedMessage {
    fun get(stringMessage: String): HashMap<String,String>{
        val messages= HashMap<String,String>()
        val jsonObject = JSONObject(stringMessage)

        try{
            messages["message"]=jsonObject.getString("data")
        }
        catch (e:JSONException){
            messages["message"]="Unknown error occurred."
        }

        return messages
    }
}