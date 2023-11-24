package com.example.dessertclicker.ui.theme

import androidx.lifecycle.ViewModel
import com.example.dessertclicker.data.Datasource.dessertList
import com.example.dessertclicker.data.DessertUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DessertViewModel:ViewModel() {
     val _dessertUiState = MutableStateFlow(DessertUiState())
    val dessertUiState: StateFlow<DessertUiState> = _dessertUiState.asStateFlow()


    fun onDessertClicked() {
       _dessertUiState.update {dessertUiState ->
           val dessertsSold = dessertUiState.dessertsSold+1
           val nextDessertIndex = determineDessertIndex(dessertsSold)

           dessertUiState.copy(
               currentDessertIndex = nextDessertIndex,
               revenue = dessertUiState.revenue + dessertUiState.currentDessertPrice,
               dessertsSold = dessertsSold,
               currentDessertImage = dessertList[nextDessertIndex].imageId,
               currentDessertPrice = dessertList[nextDessertIndex].price
           )
       }
    }

     private fun determineDessertIndex(dessertsSold: Int): Int {
        var dessertIndex = 0
         for (index in dessertList.indices){
             if(dessertsSold>= dessertList[index].startProductionAmount){
                 dessertIndex= index
             }else{
                 break
             }
         }
         return dessertIndex
    }
}