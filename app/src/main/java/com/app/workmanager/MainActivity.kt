package com.app.workmanager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.work.PeriodicWorkRequest
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private val workManager by lazy {
        WorkManager.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val data = workDataOf(
            "title" to "عنوان نوتیفیکیشن",
            "description" to "توضیحات"
        )
        val timeReminderRequest = PeriodicWorkRequestBuilder<ShowNotificationWorker>(
            PeriodicWorkRequest.MIN_PERIODIC_INTERVAL_MILLIS, TimeUnit.MILLISECONDS
        )
            .setInputData(data)
            .build()

        workManager.enqueue(timeReminderRequest)
    }
}