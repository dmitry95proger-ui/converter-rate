package com.example.currencyconverter.domain.useCase

import com.example.converterrate.domain.entity.RateDomain

/**
 * @author d.borodin
 */

class MoveSelectedRateToUpUseCase {
    fun invoke(selectedRate: RateDomain, list: List<RateDomain>): List<RateDomain>{
//        if (!list.contains(selectedRate)) {
//            throw IllegalArgumentException("Selected rate is not in the list")
//        }
        //val updatedList = list.filterNot { it.currency == selectedRate.currency }.toMutableList().sortBy { it.currency }

        val filteredList = list.filterNot { it.currency == selectedRate.currency }

// Создаем новый отсортированный список
        val sortedList = filteredList.toMutableList().apply { sortBy { it.currency } }
        sortedList.add(0, selectedRate)
        return sortedList
    }
}