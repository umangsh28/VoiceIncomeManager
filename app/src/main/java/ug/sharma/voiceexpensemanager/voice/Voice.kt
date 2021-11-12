package ug.sharma.voiceexpensemanager.voice

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import androidx.core.app.ActivityCompat.startActivityForResult
import java.util.*

class Voice {



        private var speechRecognizer: SpeechRecognizer? = null

         val RQ = 102
          var i=Intent()

       fun speak(context: Context){
            i=Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            i.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
            i.putExtra(RecognizerIntent.EXTRA_PROMPT,"say something")

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                speechRecognizer= SpeechRecognizer.createOnDeviceSpeechRecognizer(context)
            }


        }



}