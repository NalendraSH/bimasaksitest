package com.bimasaktitest.main

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel

class ItemMainViewModel(data: MutableMap<String, String>): ViewModel() {
    var label: ObservableField<String?> = ObservableField(data["label"])
    var date: ObservableField<String?> = ObservableField(data["date"])
    var nbVisits: ObservableField<String?> = ObservableField(data["nb_visits"])
    var status: ObservableField<String?> = ObservableField(data["status"])
}