package com.example.ecommmerceapp.presentation.Compra.ViewModel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommmerceapp.data.Entities.Producto
import com.example.ecommmerceapp.data.Entities.Usuario
import com.example.ecommmerceapp.data.Repository.ProductoRepository
import com.example.ecommmerceapp.data.Repository.UsuarioRepository
import com.example.ecommmerceapp.utils.MemoryConsumption
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.Duration
import java.time.LocalTime
import javax.inject.Inject

@HiltViewModel
class CompraViewModel @Inject constructor(
    private val usuarioRepository: UsuarioRepository,
    private val productoRepository: ProductoRepository
): ViewModel(){

    val vendedor = mutableStateOf(Usuario())
    fun getVendedor(id:String){
        viewModelScope.launch {
            vendedor.value = usuarioRepository.getVendedor(id)!!
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun comprar(producto: Producto){
        viewModelScope.launch{
            val startTime = LocalTime.now()
            productoRepository.comprarProducto(producto)
            Log.i("comp-ExecutionTime", Duration.between(startTime,LocalTime.now()).toMillis().toString())
            Log.i("comp-MemoryConsump", MemoryConsumption().getUsedMemorySize().toString())

        }
    }
}