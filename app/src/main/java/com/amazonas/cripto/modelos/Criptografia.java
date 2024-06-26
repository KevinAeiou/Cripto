package com.amazonas.cripto.modelos;

import android.annotation.SuppressLint;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Locale;
import java.util.Objects;
import java.util.regex.Pattern;

public class Criptografia extends Mensagem{
    private static final String TAG = "classeCriptografia";
    private String textoCriptografado;
    private int CHAVE_CODIGO_DEZ_LETRAS = 10;
    public Criptografia(String texto) {
        super(texto);
        this.textoCriptografado = textoCriptografado;
    }

    public String getTextoCriptografado() {
        return textoCriptografado;
    }

    public boolean textoEstaCriptografado(String texto) {
        texto = texto.replaceAll(" ", "");
        if (texto.length() >= CHAVE_CODIGO_DEZ_LETRAS){
            String ultimasLetras = texto.substring(texto.length() - CHAVE_CODIGO_DEZ_LETRAS);
            return ehDataValida(ultimasLetras);
        }
        return false;
    }

    private static boolean ehDataValida(String dataString) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        try {
            java.sql.Date data = new java.sql.Date(Objects.requireNonNull(formato.parse(dataString)).getTime());
            return true;
        } catch (ParseException exception) {
            return false;
        }
    }

    public String criptografaTexto(String stringTexto) {
        String resultado = "";
        String alfabetoCriptografado = criptografaAlfabeto();
        return resultado;
    }

    private String criptografaAlfabeto() {
        int dia;
        String alfabeto = "";
        Locale local = new Locale("pt", "BR");
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd'de'MMMM", local);
        LocalDate dataAtual = LocalDate.now();
        String dataFormatada = formato.format(dataAtual);
        dia = Integer.parseInt(dataFormatada.substring(0, 2));
        try {
            String diaPorExtenso = getDiaPorExtenso(dia);
            String dataPorExtenso = diaPorExtenso+dataFormatada.substring(2,dataFormatada.length());
            String dataPorExtensoSemRepeticao = removeRepeticoes(dataPorExtenso);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return alfabeto;
    }

    private String removeRepeticoes(String dataPorExtenso) {
        char[] arrayDataExtenso = dataPorExtenso.toCharArray();
        String[] resultado = new String[arrayDataExtenso.length];
        for (int i = 0; i < arrayDataExtenso.length; i ++) {
            String letraAtual = String.valueOf(arrayDataExtenso[i]);
            if (resultado[0] == null) {
                resultado[0] = letraAtual;
            } else {
                boolean existe = false;
                for (int x = 0; x < resultado.length; x ++) {
                    if (resultado[x] != null &&  resultado[x].equals(letraAtual)) {
                        existe = true;
                        break;
                    }
                }
                if (!existe) {
                    for (int x = 0; x < resultado.length; x ++) {
                        if (resultado[x] == null) {
                            resultado[x] = letraAtual;
                            break;
                        }
                    }
                }
            }
        }
        return Arrays.toString((resultado));
    }

    private static String getDiaPorExtenso(int dia) throws Exception {
        String dias[] = {"zero","um","dois","tres","quatro","cinco","seis","sete","oito","nove"};
        String retorno = "";
        if (dia < 1 || dia > 31) {
            throw new Exception("Não existe esse dia");
        } else if (dia < 10) {
            retorno = dias[dia];
        } else if (dia < 20) {
            retorno = new String[] {
                    "dez","onze","doze","treze","quatorze","quinze","dezesseis","dezessete","dezoito","dezenove"
            }[dia - 10];
        } else if (dia < 30) {
            if (dia == 20) {
                retorno = "vinte";
            } else {
                retorno = "vintee"+ dias[dia - 20];
            }
        } else {
            if (dia == 30) {
                retorno = "trinta";
            } else {
                retorno = "trintae"+ dias[dia - 30];
            }
        }
        return retorno;
    }

    public String descriptografaTexto(String stringTexto) {
        return "";
    }
}
