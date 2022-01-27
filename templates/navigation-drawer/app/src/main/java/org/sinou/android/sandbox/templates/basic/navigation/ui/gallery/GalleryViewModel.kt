package org.sinou.android.sandbox.templates.basic.navigation.ui.gallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GalleryViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "1. This is gallery Fragment\n" +
                "2. This is gallery Fragment\n" +
                "3. This is gallery Fragment\n" +
                "4. This is gallery Fragment\n" +
                "5. This is gallery Fragment\n" +
                "6. This is gallery Fragment\n" +
                "7. This is gallery Fragment\n" +
                "8. This is gallery Fragment\n" +
                "1. This is gallery Fragment\n" +
                "2. This is gallery Fragment\n" +
                "3. This is gallery Fragment\n" +
                "4. This is gallery Fragment\n" +
                "5. This is gallery Fragment\n" +
                "6. This is gallery Fragment\n" +
                "7. This is gallery Fragment\n" +
                "8. This is gallery Fragment\n" +
                "1. This is gallery Fragment\n" +
                "2. This is gallery Fragment\n" +
                "3. This is gallery Fragment\n" +
                "4. This is gallery Fragment\n" +
                "5. This is gallery Fragment\n" +
                "6. This is gallery Fragment\n" +
                "7. This is gallery Fragment\n" +
                "8. This is gallery Fragment\n" +
                "1. This is gallery Fragment\n" +
                "2. This is gallery Fragment\n" +
                "3. This is gallery Fragment\n"
    }
    val text: LiveData<String> = _text
}