package com.example.uno.presentation.scoreTableActivity

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.widget.Button
import com.example.uno.databinding.ModalDeleteColumnBinding

class ModalDeleteColumn() {

     val setOnDeleteClickListener: ((Unit) -> Unit)? = null

    companion object{

        private lateinit var modalDeleteColumn: Dialog
        private lateinit var binding: ModalDeleteColumnBinding
        lateinit var btnCancel: Button
        lateinit var btnDelete: Button

        fun createLayout(context: Context, layoutInflater: LayoutInflater): Dialog {
            modalDeleteColumn = Dialog(context);
            binding = ModalDeleteColumnBinding.inflate(layoutInflater)
            modalDeleteColumn.setContentView(binding.root);
            btnDelete = binding.btnDelete
            btnCancel = binding.btnCancel
            return modalDeleteColumn
        }
    }

}