package com.example.homework_11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import com.example.homework_11.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding;
    private lateinit var model: MainActivityViewModel;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        model = ViewModelProvider(this)[MainActivityViewModel::class.java]
        binding.model = model

        subscribeOnListDataChanged()
        initListView()
        subscribeOnTextChanged()
        setAddBtnEnabled(binding.searchField.text.isNotEmpty())
    }

    /**
     * Подписка на изменение данных
     */
    private fun subscribeOnListDataChanged() {
        model.getDataObservable().observe(this) {
            updateList(it)
        }
    }

    /**
     * Инициализируем список
     */
    private fun initListView() {
        binding.list.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, ArrayList<String>())
    }

    /**
     * Обновляем список
     */
    private fun updateList(newItems: List<String>) {
        setEmptyVisibility(newItems)
        // так как адаптер простой то обновляю весь список, но понятно что лучше обновлять только то что изменилось через методы адаптера, либо DiffUtil
        (binding.list.adapter as ArrayAdapter<String>).apply {
            clear()
            addAll(newItems)
            notifyDataSetChanged()
        }
    }

    /**
     * Устанваливаем видимость пустой заглушки
     */
    private fun setEmptyVisibility(items: List<String>) {
        if (items.isEmpty()) {
            binding.empty.visibility = View.VISIBLE
        } else {
            binding.empty.visibility = View.GONE
        }
    }

    /**
     * Устанваливаем активность кнопки добавить
     */
    private fun setAddBtnEnabled(enabled: Boolean) {
        binding.addBtn.isEnabled = enabled
    }

    /**
     * Подписка на изменение строки поиска
     */
    private fun subscribeOnTextChanged() {
        binding.searchField.addTextChangedListener(
            afterTextChanged = { p0: CharSequence? ->
                model.getData(p0.toString())
                setAddBtnEnabled(binding.searchField.text.isNotEmpty())
            }
        )
    }
}