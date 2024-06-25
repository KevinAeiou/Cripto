package com.amazonas.cripto.ui.notifications;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.amazonas.cripto.databinding.FragmentNotificationsBinding;
import com.amazonas.cripto.modelos.Criptografia;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.edtInputMensagem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String resultado;
                Criptografia criptografia = new Criptografia(charSequence.toString());
                if (criptografia.textoEstaCriptografado(charSequence.toString())){
                    resultado = criptografia.descriptografaTexto(charSequence.toString());
                } else {
                    resultado = criptografia.criptografaTexto(charSequence.toString());
                }
                defineResultado(resultado);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void defineResultado(String resultado) {
        binding.autoCompleteResultado.setText(resultado);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}