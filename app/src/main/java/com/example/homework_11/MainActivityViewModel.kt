package com.example.homework_11

import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel: ViewModel() {
    private val repository: MainActivityModel = MainActivityModel()
    private val dataObservable: MutableLiveData<List<String>> = MutableLiveData(repository.getListData())
    var searchValue = ObservableField("")

    /**
     * Получить Observer на обновление данных
     */
    fun getDataObservable(): MutableLiveData<List<String>> {
       return dataObservable
    }

    /**
     * Получить новые данные
     */
    fun getData(searchValue: String?) {
        dataObservable.value = repository.getListData(searchValue)
    }

    /**
     * Добавить данные
     */
    fun addData(view: View) {
        repository.addListData(searchValue.get() as String)
        getData(searchValue.get())
    }

    /**
     * Подписка на нажатие кнопки Очистить
     */
    fun clearSearch(view: View) {
        searchValue.set("")
    }
}