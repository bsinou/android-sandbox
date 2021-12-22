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

package org.sinou.android.sandbox.course.devbyteviewer.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.sinou.android.sandbox.course.devbyteviewer.database.VideoDatabase
import org.sinou.android.sandbox.course.devbyteviewer.database.asDomainModel
import org.sinou.android.sandbox.course.devbyteviewer.domain.Video
import org.sinou.android.sandbox.course.devbyteviewer.network.Network
import org.sinou.android.sandbox.course.devbyteviewer.network.asDatabaseModel


class VideosRepository(private val database: VideoDatabase){

    val videos: LiveData<List<Video>> = Transformations.map(database.videoDao.getVideos()){
        it.asDomainModel()
    }

    suspend fun refreshVideos(){
        withContext(Dispatchers.IO){
            val playlist = Network.devbytes.getPlaylist().await()
            database.videoDao.insertAll(*playlist.asDatabaseModel())
        }
    }
}