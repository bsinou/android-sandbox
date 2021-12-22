/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.sinou.android.sandbox.course.devbyteviewer.work

import android.content.Context
import android.content.ContextParams
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import org.sinou.android.sandbox.course.devbyteviewer.database.getDatabase
import org.sinou.android.sandbox.course.devbyteviewer.repository.VideosRepository
import retrofit2.HttpException


class RefreshDataWorker(appContext: Context, params: WorkerParameters): CoroutineWorker(appContext, params){

    companion object {
        const val WORKER_NAME = "RefreshDataWorker"
    }

    override suspend fun doWork(): Result {

        val database = getDatabase(applicationContext)
        val repository = VideosRepository(database)

        return try {
            repository.refreshVideos()
            return Result.success()
        } catch (exception: HttpException){
            return Result.retry()
        }
    }
}