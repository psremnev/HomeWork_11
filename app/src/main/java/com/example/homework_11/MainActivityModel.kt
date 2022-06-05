package com.example.homework_11

/**
 * Мок репозиторий получения данных
 */
class MainActivityModel {
    // мок массив
    private val listData: ArrayList<String> = arrayListOf("Игрушка", "Велосипед")

    /**
     * Вернет все данные для списка по фильтру, тут мог бы быть объект фильтра с параметрами но так как значение одно то сделано так
     */
    fun getListData(searchValue: String? = ""): List<String> {
        return listData.filter { it.lowercase().contains(searchValue!!.lowercase()) }
    }

    /**
     * Добавить данные
     */
    fun addListData(item: String) {
        if (!listData.contains(item)) {
            listData.add(item)
        }
    }

    /**
     * Удалить данные, реализовал хоть он и не нужен в рамках задачи
     */
    fun deleteListData(item: String) {
        listData.remove(item)
    }

    /**
     * Обновить данные, реализовал хоть он и не нужен в рамках задачи
     */
    fun updateListData(index: Int, item: String) {
        listData[index] = item
    }
}